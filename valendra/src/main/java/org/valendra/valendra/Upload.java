package org.valendra.valendra;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.valendra.accounts.AccountsLogin;

public class Upload extends HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private boolean isMultipart;
  private String filePath;
  private int maxFileSize = 500 * 1024;
  private int maxMemSize = 40 * 1024;
  private File file;

  public void init() {
    // Get the file location where it would be stored.
    filePath = getServletContext().getInitParameter("file-upload");
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    Header.drawHeader(out);
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
    } else {
      out.println("<head>");
      out.println("<title>Valendra</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"upload.css\">");
      out.println("</head>");
      out.println("<h1>Upload</h1>");
      out.println(
          "<div style=\"width:400px; position:relative; top:200px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
      out.println("<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">");
      out.println("<input type=\"file\" name=\"file\" size=\"50\" />");
      out.println("<br />");
      out.println("<input type=\"submit\" value=\"Upload File\" />");
      out.println("</form>");
      out.println("</div>");
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    // Check that we have a file upload request
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
    } else {
      isMultipart = ServletFileUpload.isMultipartContent(request);
      if (!isMultipart) {
        out.println("<script>alert(\"File not uploaded!\");</script>");
        out.println(
            "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/upload\" />");
        return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("/tmp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax(maxFileSize);

      try {
        // Parse the request to get file items.
        List<FileItem> fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator<FileItem> i = fileItems.iterator();

        while (i.hasNext()) {
          FileItem fi = (FileItem) i.next();
          if (!fi.isFormField()) {
            // Get the uploaded file parameters
            String fileName = fi.getName();
            // Write the file
            if (fileName.lastIndexOf("\\") >= 0) {
              file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
            } else {
              file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
            }
            fi.write(file);
            out.println("<script>alert(\"File uploaded!\");</script>");
            out.println(
                "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra\" />");
          }
        }

      } catch (Exception ex) {
        out.println("Invalid file");
      }
    }
  }
}
