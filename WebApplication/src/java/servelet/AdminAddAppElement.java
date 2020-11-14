/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import adt.ArrList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "AdminAddAppElement", urlPatterns = {"/admin/add-new-ele"})
public class AdminAddAppElement extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String base;
        String edit = request.getParameter("add");
        String new_ = request.getParameter("add-new");
        String title_ = request.getParameter("_title");
        String url_ = request.getParameter("_url");
        String t = request.getParameter("t");

        if (new_ == null || edit == null || title_ == null || url_ == null) {
            return;
        }

        String dir = System.getProperty("user.dir") + "/data/" + edit + ".json";
        JsonReader reader = new JsonReader(new FileReader(dir));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        base = (String) map.get("base");
        Map base_map = (Map) map.get(base);
        if (t == null) {
            base_map.put(new_, getData(title_, url_));
        } else {
            Map details_mp = (Map) base_map.get(t);
            ArrList arr = details_mp.get("child") == null ? new ArrList()
                    : new ArrList((Iterable) details_mp.get("child"));
            arr.add(getData(title_, url_));
            details_mp.put("child", arr.toArray());
            base_map.put(t, details_mp);
        }
        map.put(base, base_map);

        // write new json string into jsonfile1.json file
        File jsonFile = new File(dir);
        try (OutputStream outputStream = new FileOutputStream(jsonFile)) {
            outputStream.write(gson.toJson(map).getBytes());
            outputStream.flush();
        }
        
        StringBuilder str = new StringBuilder();
        str.append("http://localhost:8080/WebApplication/admin/edit_app.jsp?edit=")
                .append(edit);
        if(t != null)
            str.append("&t=").append(t);
        response.sendRedirect(str.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Writing file function">
    private Map getData(String title, String url) {
        HashMap map = new HashMap();
        map.put("t", title);
        map.put("l", url);
        return map;
    }
    // </editor-fold>

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
