            <%-- 
    Document   : UploadServlet
    Created on : Dec 4, 2020, 8:26:26 AM
    Author     : ITSUKA KOTORI
--%>

<%@ page import="main.*"%>
<%@ page import="cilent.*"%>
<%@ page import="cilent.filter.*"%>
<%@ page import="cilent.pages.*"%>
<%@ page import="cilent.servlet.*"%>
<%@ page import="entity.*"%>
<%@ page import="adt.node.*"%>
<%@ page import="adt.interfaces.*"%>
<%@ page import="csv.converter.*"%>
<%@ page import="xenum.*"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    File file;
    int maxFileSize = 5000 * 1024;
    int maxMemSize = 5000 * 1024;
    User user = main.Functions.getUserSession(request);
    ServletContext context = pageContext.getServletContext();
    String path_store = WebConfig.PROJECT_URL + "web/img/profile/";

    // Verify the content type
    String contentType = request.getContentType();

    if ((contentType.indexOf("multipart/form-data") >= 0) && user != null) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>JSP File upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = user.getId();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    file = new File(path_store + fileName+".png");
                    fi.write(file);
                    out.println("Uploaded Filename: " + path_store
                            + fileName + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Error</p>");
            ex.printStackTrace(response.getWriter());
            out.println("</body>");
            out.println("</html>");
            System.out.println(ex);
        }
    } else {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet upload</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>No file uploaded</p>");
        out.println("</body>");
        out.println("</html>");
    }
%>