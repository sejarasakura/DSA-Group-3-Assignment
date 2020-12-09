/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.WebConfig;

/**
 *
 * @author Lim Sai Keat
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            // get user login session
            response.encodeURL("/store/catalog");
            User user = main.Functions.getUserSession(request);
            if (!response.isCommitted()) {
                if (!main.Functions.checkLogin(response, user)) {
                    return;
                }
            }

            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            String role = request.getParameter("role");
            String license = request.getParameter("license");

            if (email != null ? email.length() > 0 : false) {
                user.setEmail(email);
            }
            user.setEmail(email);
            if (name != null ? name.length() > 0 : false) {
                user.setName(name);
            }
            if (ic != null ? ic.length() == 14 : false) {
                user.setIc(ic);
            }
            if (phonenumber != null ? !phonenumber.isEmpty() : false) {
                user.setPhoneNumber(phonenumber);
            }

            if (user.isDriver()) {
                if (license != null ? !license.isEmpty() : false) {
                    ((Driver) user).setDriver_license(license);
                }
            }

            if (user.isCustomer()) {
                if (role != null ? !role.isEmpty() : false) {
                    ((Customer) user).setMemberType(xenum.MemberShip.getValue(role));
                }
            }

            user.updateThisToCsv();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUser at " + request.getContextPath() + "</h1>");
            out.println("<h1>");
            out.println(user);
            out.println("</h1>");
            out.println("<a href=\"" + WebConfig.WEB_URL + "pages/account.jsp\">Return Back To Account Page</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
