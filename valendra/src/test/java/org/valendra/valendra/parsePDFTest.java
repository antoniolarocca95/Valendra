package org.valendra.searchengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ParserPDFTest extends Mockito{
PDFParser parser = mock(PDFParser.class);
PDDocument pdDoc = mock(PDDocument.class);
COSDocument cosDoc = mock(COSDocument.class);
when(parser.getDocument().thenReturn(2));
}