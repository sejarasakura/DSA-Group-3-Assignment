/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.ArrList;
import adt.MapConverter;
import adt.XTreeDictionary;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AdminUpdateElement extends HttpServlet {

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

        int count = Integer.parseInt(request.getParameter("count"));
        String edit = request.getParameter("edit");
        String t = request.getParameter("t");
        String dir = System.getProperty("user.dir") + "/data/" + edit + ".json";
        JsonReader reader = new JsonReader(new FileReader(dir));
        Gson gson = new Gson();
        // <editor-fold defaultstate="collapsed" desc="Read json data">
        MapConverter map = new MapConverter(new XTreeDictionary(
                gson.fromJson(reader, WebConfig.WRITING_CLASS)
        ));
        String base = (String) map.get("base");
        String front_id = new StringBuilder()
                .append(base).append(".").append(t).append(".").toString();
        MapConverter base_map = new MapConverter(new XTreeDictionary(map.get(base)));
        MapConverter details_map = new MapConverter(new XTreeDictionary(base_map.get(t)));
        // </editor-fold>
        ArrList child = new ArrList();
        File jsonFile = new File(dir);
        StringBuilder str = new StringBuilder();
        String d_title, d_url;

        // update syntax
        d_title = request.getParameter(front_id + "t");
        if (d_title.isEmpty()) {
            base_map.remove(t);
        } else {
            details_map.put("t", d_title);
            details_map.put("l", request.getParameter(front_id + "l"));
            for (int i = 0; i < count; i++) {
                d_title = request.getParameter(front_id + "child[" + i + "].t");
                d_url = request.getParameter(front_id + "child[" + i + "].l");
                if (d_title.isEmpty()) {
                } else {
                    if (d_url.isEmpty()) {
                        d_url = "#";
                    }
                    child.add(getData(d_title, d_url));
                }
            }
            if (count > 0) {
                details_map.put("child", child.toArray());
            }
            base_map.put(t, details_map);
        }
        map.put(base, base_map);

        // write new json string into jsonfile1.json file
        try (OutputStream outputStream = new FileOutputStream(jsonFile)) {
            outputStream.write(gson.toJson(map).getBytes());
            outputStream.flush();
        }
        str.append(WebConfig.WEB_URL).append("admin/edit_app.jsp?edit=").append(edit);
        response.sendRedirect(str.toString());
    }

    private MapConverter getData(String title, String url) {
        XTreeDictionary map = new XTreeDictionary();
        map.add("t", title);
        map.add("l", url);
        return new MapConverter(map);
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
