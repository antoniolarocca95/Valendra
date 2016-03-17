package parser;

public class PDFParser extends Parser{
	public static String parse(String path){
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
			return '';
		}
	}
}
