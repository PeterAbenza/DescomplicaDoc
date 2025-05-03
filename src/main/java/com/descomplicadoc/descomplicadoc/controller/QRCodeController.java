package com.descomplicadoc.descomplicadoc.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class QRCodeController {

    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public void gerarQRCode(@RequestParam String chave, @RequestParam double valor, HttpServletResponse response) throws IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;

        // Gerar o payload no formato EMV
        String payload = gerarEMVPayload(chave, valor);

        try (OutputStream os = response.getOutputStream()) {
            var bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", os);
        } catch (WriterException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar QRCode");
        }
    }

    private String gerarEMVPayload(String chave, double valor) {
        // Gerando o Payload EMV com dados padrão de PIX
        StringBuilder payload = new StringBuilder();
        payload.append("000201"); // Início do padrão
        payload.append("26"); // Tamanho do payload
        payload.append("5802BR"); // Código do país
        payload.append("01"); // Tipo de transação (PIX)
        payload.append("36"); // Chave PIX (tipo)
        payload.append(chave); // Chave PIX
        payload.append("52"); // Tipo de valor
        payload.append("40"); // Tipo de valor (real)
        payload.append("00"); // Tipo de moeda
        payload.append(String.format("%010.2f", valor).replace(",", "")); // Valor com duas casas decimais
        payload.append("58"); // Nome do recebedor
        payload.append("13"); // Tamanho
        payload.append("DescomplicaDoc"); // Nome do recebedor
        payload.append("60"); // Cidade
        payload.append("09"); // Tamanho
        payload.append("SaoPaulo"); // Cidade
        payload.append("62"); // Informações extras
        payload.append("05");
        payload.append("6304"); // Checksum

        return payload.toString();
    }
}
