/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Functions;
import main.WebConfig;

/**
 *
 * @author Lim Sai Keat
 */
@WebServlet(name = "imageRetrive", urlPatterns = {"/imageRetrive"})
public class imageRetrive extends HttpServlet {

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
        response.setContentType("image/gif");
        String id = request.getParameter("id");
        String displayType = request.getParameter("type");
        String defaultI = request.getParameter("default");
        String defaultT = request.getParameter("defaulttype");
        id = id == null ? "" : id;
        displayType = displayType == null ? "" : displayType;
        defaultI = defaultI == null ? "" : defaultI;
        defaultT = defaultT == null ? "" : defaultT;
        String path = WebConfig.PROJECT_URL + "/web/img/" + displayType + "/" + id + ".png";
        File file = new File(path);
        if (!file.exists()) {
            path = WebConfig.PROJECT_URL + "/web/img/" + defaultT + "/" + defaultI + ".png";
            file = new File(path);
        }
        if (file.exists()) {
            RandomAccessFile f;
            f = new RandomAccessFile(path, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.read(bytes);
            response.getOutputStream().write(bytes);
        } else {
            if (WebConfig.default_image == null) {
                WebConfig.default_image = Functions.getDefaultImage();
            }
            response.getOutputStream().write(WebConfig.default_image);
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
