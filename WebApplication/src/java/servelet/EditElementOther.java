/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import adt.ArrList;
import adt.XHashedDictionary;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "EditElementOther", urlPatterns = {"/admin/EditElement"})
public class EditElementOther extends HttpServlet {

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

        int count = Integer.parseInt(request.getParameter("count")) + 1, x_count;
        String edit = request.getParameter("edit");
        String t = request.getParameter("t");
        String header = request.getParameter("header");
        String dir = System.getProperty("user.dir") + "/data/" + edit + ".json";
        JsonReader reader = new JsonReader(new FileReader(dir));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        String base = (String) map.get("base");
        String front_id = new StringBuilder().append(base).append(".").append(t).append(".").toString();
        Map base_map = (Map) map.get(base);
        ArrList new_detias_map = new ArrList();
        ArrList child;
        File jsonFile = new File(dir);
        StringBuilder str = new StringBuilder();
        String id, x_title, x_url, x_s, x_c, x_counts;

        //update syntax
        switch (header) {
            case "show":
                for (int i = 0; i < count; i++) {
                    id = front_id + "child[" + i + "].";
                    x_title = request.getParameter(id + "t");
                    x_url = request.getParameter(id + "l");
                    x_s = request.getParameter(id + "show");
                    x_c = request.getParameter(id + "c");
                    if (!x_title.isEmpty()) {
                        new_detias_map.add(getDataShow(x_title, x_url, x_s, x_c));
                    }
                }
                break;
            case "s":
                for (int i = 0; i < count; i++) {
                    id = front_id + "child[" + i + "].";
                    x_title = request.getParameter(id + "t");
                    x_url = request.getParameter(id + "l");
                    x_s = request.getParameter(id + "s");
                    if (!x_title.isEmpty()) {
                        new_detias_map.add(getDataS(x_title, x_url, x_s));
                    }
                }
                break;
            default:
                for (int i = 0; i < count; i++) {
                    id = front_id + "child[" + i + "].";
                    x_counts = request.getParameter(id + "count");
                    if (x_counts == null) {
                        x_title = request.getParameter(id + "t");
                        x_url = request.getParameter(id + "l");
                        if (!x_title.isEmpty()) {
                            new_detias_map.add(getData(x_title, x_url));
                        }
                    } else {
                        x_count = Integer.parseInt(x_counts) + 1;
                        child = new ArrList();
                        for (int j = 0; j < x_count; j++) {
                            x_title = request.getParameter(id + j + ".t");
                            x_url = request.getParameter(id + j + ".l");
                            if (!x_title.isEmpty()) {
                                child.add(getData(x_title, x_url));
                            }
                        }
                        x_title = request.getParameter(id + "t");
                        x_url = request.getParameter(id + "l");
                        if (!x_title.isEmpty()) {
                            new_detias_map.add(getDataChild(x_title, x_url, child));
                        }
                    }
                }
                break;
        }
        base_map.put(t, new_detias_map.toArray());
        map.put(base, base_map);

        // write new json string into jsonfile1.json file
        try (OutputStream outputStream = new FileOutputStream(jsonFile)) {
            outputStream.write(gson.toJson(map).getBytes());
            outputStream.flush();
        }
        str.append("http://localhost:8080/WebApplication/admin/edit_app.jsp?edit=").append(edit);
        response.sendRedirect(str.toString());
    }

    private Map getDataS(String title, String url, String s) {
        XHashedDictionary map = new XHashedDictionary();
        map.add("l", url);
        map.add("t", title);
        map.add("s", s);
        return map.getMap();
    }

    private Map getDataShow(String title, String url, String show, String c) {
        XHashedDictionary map = new XHashedDictionary();
        map.add("l", url);
        map.add("t", title);
        map.add("show", show);
        map.add("c", c);
        return map.getMap();
    }

    private Map getData(String title, String url) {
        XHashedDictionary map = new XHashedDictionary();
        if (title.contains(".child")) {
            ArrList al = new ArrList();
            al.add(getData("new", "#"));
            map.add("child", al.toArray());
            title = title.split(".child")[0];
        }
        map.add("l", url);
        map.add("t", title);
        return map.getMap();
    }

    private Map getDataChild(String title, String url, ArrList x) {
        XHashedDictionary map = new XHashedDictionary();
        map.add("child", x.toArray());
        map.add("l", url);
        map.add("t", title);
        return map.getMap();
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
