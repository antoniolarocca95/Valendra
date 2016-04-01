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

public class resultsTest extends Mockito{
    
    @Test
    public void initTest() throws Exception{
    	when(getServletContext().getInitParameter.thenReturn(1));
    	assertEquals(getServletContext(), 1);
    }

    @Test
    public void doGet() throws Exception{
    	HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HTTPServletResponse.class);

        when(response.setContentType("text/html").thenReturns(1));
        when(response.getWriter().thenReturns(2));
        when(request.getParameter("document").thenReturns(3));

        assertEquals(response.setContentType("text/html"), 1);
        assertEquals(response.getWriter(), 2);
        assertEquals(request.getParameter("document"), 3);
    }
}