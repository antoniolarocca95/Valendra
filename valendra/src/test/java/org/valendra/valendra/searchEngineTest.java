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

public class searchEngineTest extends Mockito{
    
    @Test
    public void initTest() throws Exception{
    	when(getServletContext().getInitParameter("file-upload").thenReturn(1));
    	assertEquals(getServletContext().getInitParameter("file-upload"), 1);
    }
    @Test
    public void doGetTest() throws Exception{
    	HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HTTPServletResponse.class);

        when(response.setContentType("text/html").thenReturn(1));
        when(response.getWriter().thenReturn(2));
        when(request.getParameter("search").thenReturn(3));

        assertEquals(response.setContentType("text/html"), 1);
        assertEquals(response.getWriter(), 2);
        assertEquals(request.getParameter("search"), 3);
    }