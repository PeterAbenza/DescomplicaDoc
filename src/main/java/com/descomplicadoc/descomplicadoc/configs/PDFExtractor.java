package com.descomplicadoc.descomplicadoc.configs;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFExtractor {

	// Método para extrair texto de um arquivo PDF
	public String extractTextFromPDF(String filePath) throws IOException {
		// Carregar o documento PDF
		PDDocument document = PDDocument.load(new File(filePath));

		// Criar uma instância do PDFTextStripper para extrair o texto
		PDFTextStripper pdfStripper = new PDFTextStripper();

		// Extrair o texto do documento
		String text = pdfStripper.getText(document);

		// Fechar o documento
		document.close();

		return text;
	}
}
