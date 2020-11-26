<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};
</#if>

/**
 * Local project import
 */ 
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import xenum.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
<#if classAnnotation??>
import javax.servlet.annotation.WebServlet;
</#if>
<#if includeInitParams??>
import javax.servlet.annotation.WebInitParam;
</#if>
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ${user}
 */
<#if classAnnotation??>
${classAnnotation}
</#if>
public class ${name} extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
<#if java17style?? && java17style>
        try (PrintWriter out = response.getWriter()) {
<#else>
        PrintWriter out = response.getWriter();
        try {
</#if>
            /* TODO output your page here. You may use following sample code. */
            out.println("${doctype?j_string}");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ${name}</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ${name} at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
<#if !java17style?? || !java17style>
        } finally {
            out.close();
</#if>
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="${servletEditorFold}">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
<#if java15style??>
    @Override
</#if>
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
<#if java15style??>
    @Override
</#if>
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
<#if java15style??>
    @Override
</#if>
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}