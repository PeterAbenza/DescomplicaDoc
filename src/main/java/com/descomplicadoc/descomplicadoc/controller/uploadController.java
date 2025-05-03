package com.descomplicadoc.descomplicadoc.controller;

import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.descomplicadoc.descomplicadoc.configs.PDFExtractor;
import com.descomplicadoc.descomplicadoc.model.Documento;
import com.descomplicadoc.descomplicadoc.model.Documento.StatusDocumento;
import com.descomplicadoc.descomplicadoc.model.Usuario;
import com.descomplicadoc.descomplicadoc.repository.DocumentoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class uploadController {

    @Autowired
    private DocumentoRepository documentoRepository;

    @GetMapping("/uploadPDF")
    public ModelAndView showHome(HttpSession session) {
        ModelAndView mv = new ModelAndView("upload/index");

        // Pega a lista de resumos da sessão (se existir)
        List<String> resumos = (List<String>) session.getAttribute("resumos");
        if (resumos == null) {
            resumos = new ArrayList<>();
        }

        mv.addObject("resumos", resumos);
        return mv;
    }

    @PostMapping("/upload-pdf")
    public String handleFileUpload(MultipartFile file, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            if (usuario == null) {
                redirectAttributes.addFlashAttribute("erro", "Você precisa estar logado para enviar documentos.");
                return "redirect:/login";
            }

            if (file == null || !file.getOriginalFilename().endsWith(".pdf")) {
                redirectAttributes.addFlashAttribute("erro", "Somente arquivos PDF são permitidos.");
                return "redirect:/uploadPDF";
            }

            String uploadDir = System.getProperty("user.home") + File.separator + "uploads";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String filePath = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            Documento doc = new Documento();
            doc.setUsuario(usuario);
            doc.setNomeArquivo(file.getOriginalFilename());
            doc.setCaminhoArquivo(filePath);
            doc.setStatus(StatusDocumento.PENDENTE);
            doc.setDataEnvio(Timestamp.from(Instant.now()));
            documentoRepository.save(doc);

            // Processar o arquivo e adicionar resumo na sessão
            processarArquivo(doc, filePath, session);

            redirectAttributes.addFlashAttribute("sucesso", "Documento enviado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("erro", "Erro ao enviar o arquivo: " + e.getMessage());
        }

        return "redirect:/uploadPDF";
    }

    public String resumirTexto(String texto) {
        String[] palavras = texto.split("\\s+");
        int numeroDePalavras = Math.min(palavras.length, 200);
        StringBuilder resumo = new StringBuilder();
        for (int i = 0; i < numeroDePalavras; i++) {
            resumo.append(palavras[i]).append(" ");
        }
        return resumo.toString() + "...";
    }

    @Async
    public void processarArquivo(Documento documento, String filePath, HttpSession session) {
        try {
            PDFExtractor extractor = new PDFExtractor();
            String extractedText = extractor.extractTextFromPDF(filePath);
            String resumo = resumirTexto(extractedText);

            // Adiciona o resumo à sessão
            synchronized (session) {
                List<String> resumos = (List<String>) session.getAttribute("resumos");
                if (resumos == null) {
                    resumos = new ArrayList<>();
                }
                resumos.add("Resumo do documento \"" + documento.getNomeArquivo() + "\": " + resumo);
                session.setAttribute("resumos", resumos);
            }

            documento.setStatus(StatusDocumento.CONCLUIDO);
            documentoRepository.save(documento);
        } catch (Exception e) {
            documento.setStatus(StatusDocumento.FALHA);
            documentoRepository.save(documento);
        }
    }
}
