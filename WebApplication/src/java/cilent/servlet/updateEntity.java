/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import cilent.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lim Sai Keat
 */
@WebServlet(name = "updateEntity", urlPatterns = {"/admin/updateEntity"})
public class updateEntity extends HttpServlet {

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
        String jsp_action = request.getParameter("jsp-action");
        String edit = request.getParameter("edit");
        edit = edit.substring(0, 1).toUpperCase() + edit.substring(1);

        switch (jsp_action.toLowerCase()) {
            case "delete":
                DeleteEntityListCilent z = new DeleteEntityListCilent(edit, request, response);
                if (z.status) {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0010");
                } else {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0009");
                }
                return;
            case "add":
                AddEntityListCilent y = new AddEntityListCilent(edit, request, response);
                if (y.status) {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0005");
                } else {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0006");
                }
                return;
            case "update":
                UpdateEntityListCilent x = new UpdateEntityListCilent(edit, request, response);
                if (x.status) {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0008");
                } else {
                    response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit + "?I=I-0007");
                }
                return;
            default:
                System.out.println("Error not doing something");
        }
        response.sendRedirect(main.WebConfig.ADMIN_URL + "edit/" + edit);
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
