/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.*;
import entity.*;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xenum.MemberShip;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "RegisterAction", urlPatterns = {"/RegisterAction"})
public class RegisterAction extends HttpServlet {

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
        /* Decalation */
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String registerAsDriver = request.getParameter("registerAsDriver");
        XArrayList users;
        XArrayList reader;
        User current_user;
        boolean usernameUsed, driver = false;

        /* Read data to array list */
        if (registerAsDriver != null) {
            reader = AbstractEntity.readDataFormCsv(new Driver());
            driver = true;
            username = "d-" + username;
        } else {
            reader = AbstractEntity.readDataFormCsv(new Customer());
        }

        /* Check have data or not */
        if (reader == null) {
            users = new XArrayList();
        } else {
            users = reader;
        }

        /* Find username is it excite or not */
        usernameUsed = User.searchUsername(users, username);
        if (usernameUsed) {
            response.sendRedirect(request.getHeader("referer") + "?E=18");
            return;
        }

        /* Find email is it excite or not */
        usernameUsed = User.searchEmail(users, email);
        if (usernameUsed) {
            response.sendRedirect(request.getHeader("referer") + "?E=20");
            return;
        }

        /* Double check password is it same or not */
        if (password == null ? password2 != null : !password.equals(password2)) {
            response.sendRedirect(request.getHeader("referer") + "?E=19");
            return;
        }

        if (driver) {
            current_user = new Driver("", "", "", fname + ' ' + lname, email,
                    "", username, password);
        } else {
            current_user = new Customer("", MemberShip.NORMAL.getDatabaseCode(),
                    "", "", fname + ' ' + lname, email, "", username, password);
        }

        current_user.setUser_id((String) cilent.IDManager.generateId(current_user, true));

        users.add(current_user);

        AbstractEntity.reWriteAllDataToCsv(users);

        main.Functions.setUserSession(request, current_user);

        response.sendRedirect((String) main.Datas.settings.getValue("pages/account"));
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
