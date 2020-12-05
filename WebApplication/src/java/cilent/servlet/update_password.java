/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.XArrayList;
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
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "update_password", urlPatterns = {"/update_password"})
public class update_password extends HttpServlet {

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
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String opassword = request.getParameter("opassword");
        String id = request.getParameter("id");
        Class<?> _class = null;

        char role = id.charAt(0);
        User user;

        switch (role) {
            case 'A':
            case 'a':
                user = new Admin();
                _class = Admin.class;
                break;
            case 'C':
            case 'c':
                user = new Customer();
                _class = Customer.class;
                break;
            case 'D':
            case 'd':
                user = new Driver();
                _class = Driver.class;
                break;
            default:
                response.sendRedirect(WebConfig.WEB_URL + "pages/login.jsp?I=I-0012");
                return;
        }

        XArrayList users = AbstractEntity.readDataFormCsv(user);
        if (users == null) {
            response.sendRedirect(WebConfig.WEB_URL + "pages/login.jsp?I=I-0016");
            return;
        }
        XArrayList login = (XArrayList) users.searchByField("user_id", id, _class);
        User current = (User) login.get(0);
        if (current.login(user)) {
            response.sendRedirect(WebConfig.WEB_URL + "pages/account.jsp#change_password");
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
