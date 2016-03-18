package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ParserPDF extends Parser {
	public static String parse(String path) {
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(path);

		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			String parsedText = pdfStripper.getText(pdDoc);
			return parsedText;
		} catch (IOException e) {
			return "";
		}
	}
}
