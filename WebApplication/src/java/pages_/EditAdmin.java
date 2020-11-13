/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages_;

import adtClass.ArrList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditAdmin {

    public static String ERROR = "<h1>Error found</h1>";
    private String json_data = "", e_title, base, edit, t;
    private boolean status = false;
    private Map map;

    public EditAdmin(HttpServletRequest request) {
        edit = request.getParameter("edit");
        t = request.getParameter("t");
        json_data = System.getProperty("user.dir") + "/data/"
                + edit + ".json";
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(json_data));
            Gson gson = new Gson();
            map = gson.fromJson(reader, Map.class);
            e_title = (String) map.get("e-title");
            base = (String) map.get("base");
            map = (Map) map.get(base);
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrList<String> generateHtml() {
        ArrList<String> result = new ArrList<String>();
        result.add(ERROR);
        if (base == null) {
            return result;
        }

        switch (base) {
            case "nav":
                return generateAdminNav();
            case "admin-nav":
                return generateAdminNav();
            case "footer":
                return generateAdminNav();
        }
        return result;
    }

    public ArrList<String> generateAdminNav() {
        ArrList<String> result = new ArrList<String>();
        map.keySet().forEach((m) -> {
            if (((String) m).equals(t)) {
                result.add(editProperties((Map) map.get(m), (String) m));
            } else {
                result.add(displayProperties((Map) map.get(m), (String) m));
            }
        });
        return result;
    }

    public String displayProperties(Map prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        ArrList datas = new ArrList((Iterable) prop.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">")
                .append("<h2>").append(title).append("</h2><br>")
                .append(display_group((String) prop.get("t"), (String) prop.get("l")))
                .append("</div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            Map x = (Map) it.next();
            sb.append(display_group((String) x.get("t"), (String) x.get("l")));
            count++;
        }
        sb.append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit)
                .append("&t=").append(key).append("\" class=\"btn btn-default pull-right\">Edit</a>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("</div>");
        return sb.toString();
    }

    public String editProperties(Map prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        ArrList datas = new ArrList((Iterable) prop.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<form>")
                .append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">")
                .append("<h2>").append(title).append("</h2><br>")
                .append(form_group(base + "." + key, (String) prop.get("t"), (String) prop.get("l")))
                .append("</div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            Map x = (Map) it.next();
            sb.append(form_group(base + "." + key + ".child[" + count + "]",
                    (String) x.get("t"), (String) x.get("l")));
            count++;
        }
        sb.append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append("<button class=\"btn btn-default form-control\"><i class='fas fa-plus'></i> Add new child</button>")
                .append("</div>")
                .append("</div>")
                .append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-6\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit).append("\" class=\"btn btn-danger form-control\">discharge</a>")
                .append("</div>")
                .append("<div class=\"col-sm-6\">")
                .append("<button type=\"button\" class=\"btn btn-warning form-control\">update</button>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("<form>");
        return sb.toString();
    }

    /**
     *
     * id = "admin-nav.nav-1.child[0]" .t and .1 ignore
     *
     * @param id
     * @param title
     * @param url
     * @return
     */
    public String form_group(String id, String title, String url) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"%s.t\" placeholder=\"Shown Title\" value=\"%s\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"%s.l\" placeholder=\"To URL\" value=\"%s\"> </div> </div>",
                id, title, id, url);
    }

    public String display_group(String title, String url) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> %s </div> <div class=\"col-sm-6\"> %s </div> </div>", title, url);
    }

}
