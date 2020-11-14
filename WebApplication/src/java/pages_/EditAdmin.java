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
                return generateAdminNor();
            case "admin-nav":
                return generateAdminNav();
            case "footer":
                return generateAdminNor();
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
        result.add("<div class=\"form-group row\"> <div class=\"col-sm-12\"> <button type=\"button\" class=\"btn btn-success form-control\" data-toggle=\"modal\" data-target=\"#myModal\"> <i class='fas fa-plus'></i> Add new element </button> </div> </div>");
        result.add("<div class=\"modal fade\" id=\"myModal\" role=\"dialog\"> <div class=\"modal-dialog\"> <!-- Modal content--> <div class=\"modal-content\"> <form action=\"/WebApplication/admin/add-new-ele?add=" + edit + "\" method=\"post\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">Add new element</h4> </div> <div class=\"modal-body\"> <div class=\"form-group row\"> <div class=\"col-sm-6\"> Items name / json key: </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"add-new\" name=\"add-new\" placeholder=\"Name\"> </div> </div> <div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_title\" name=\"_title\" placeholder=\"Title\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_url\" name=\"_url\" placeholder=\"URL\"> </div> </div> </div> <div class=\"modal-footer\"> <button type=\"submit\" class=\"btn btn-success\">Add</button> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button> </div> </form> </div> </div> </div>");
        return result;
    }

    public ArrList<String> generateAdminNor() {
        ArrList<String> result = new ArrList<String>();
        map.keySet().forEach((m) -> {
            if (((String) m).equals(t)) {
                result.add(editProperties((Iterable) map.get(m), (String) m));
            } else {
                result.add(displayProperties((Iterable) map.get(m), (String) m));
            }
        });
        return result;
    }

    public String displayProperties(Iterable prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">").append("<div class=\"row\"> <div class=\"col-sm-6\"> ")
                .append(title).append(" </div> <div class=\"col-sm-6\"> <button type=\"button\" class=\"btn btn-default pull-right\" id=\"p-h-")
                .append(key).append("\"> <span class=\"glyphicon glyphicon-plus\"></span> show </button> </div> </div>")
                .append("</div>").append("<div class=\"panel-body\" id=\"p-b-")
                .append(key).append("\">");
        int count = 0;
        for (Iterator it = prop.iterator(); it.hasNext();) {
            Map x = (Map) it.next();
            if (x.get("child") == null) {
                sb.append(display_group((String) x.get("t"), (String) x.get("l")));
            } else {
                ArrList ar = new ArrList((Iterable) x.get("child"));
                if (ar.isEmpty()) {
                    sb.append(display_group((String) x.get("t"), (String) x.get("l")));
                }
                sb.append(displayChildProp(x));
            }
            count++;
        }
        sb.append("</div><div class=\"panel-footer\" id=\"p-f-all_user\">")
                .append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit)
                .append("&t=").append(key).append("\" class=\"btn btn-default pull-right\">Edit</a>")
                .append("</div></div></div></div>");
        return sb.toString();
    }

    public String editProperties(Iterable prop, String key) {
        ArrList<String> prag = new ArrList<String>(prop);
        return "";
    }

    private String displayChildProp(Map prop) {
        ArrList datas = new ArrList((Iterable) prop.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"form-group row\" style=\"margin: 10px 0px\">").
                append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">")
                .append("<div class=\"row\">")
                .append("<div class=\"col-sm-6\"> ").append(prop.get("t")).append("</div>")
                .append("<div class=\"col-sm-6\"> ").append(prop.get("l")).append("</div>")
                .append("</div></div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            Map x = (Map) it.next();
            sb.append(display_group((String) x.get("t"), (String) x.get("l")));
            count++;
        }
        sb.append("</div></div></div>");
        return sb.toString();
    }

    public String displayProperties(Map prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        ArrList datas = prop.get("child") == null ? new ArrList() : new ArrList((Iterable) prop.get("child"));
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
                .append("</div>");
        sb.append("</div>")
                .append("</div>");
        return sb.toString();
    }

    public String editProperties(Map prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        ArrList datas = prop.get("child") == null ? new ArrList() : new ArrList((Iterable) prop.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\">")
                .append("<form action=\"/WebApplication/admin/update-ele\" method=\"post\">")
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
                .append(" <input type=\"hidden\" id=\"count\" name=\"count\" value=\"").append(count).append("\">")
                .append(" <input type=\"hidden\" id=\"edit\" name=\"edit\" value=\"").append(edit).append("\">")
                .append(" <input type=\"hidden\" id=\"t\" name=\"t\" value=\"").append(t).append("\">")
                .append("<div class=\"col-sm-6\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit).append("\" class=\"btn btn-danger form-control\">cancel</a>")
                .append("</div>")
                .append("<div class=\"col-sm-6\">")
                .append("<button type=\"submit\" class=\"btn btn-warning form-control\">update</button>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("</form>")
                .append("<div class=\"panel-footer\">")
                .append("<form action=\"/WebApplication/admin/add-new-ele\" method=\"post\">")
                .append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append(" <input type=\"hidden\" id=\"count\" name=\"count\" value=\"").append(count).append("\">")
                .append(" <input type=\"hidden\" id=\"add\" name=\"add\" value=\"").append(edit).append("\">")
                .append(" <input type=\"hidden\" id=\"t\" name=\"t\" value=\"").append(t).append("\">")
                .append(" <input type=\"hidden\" id=\"add-new\" name=\"add-new\" value=\"").append(t).append("\">")
                .append("<button type=\"button\" data-toggle=\"modal\" data-target=\"#modal2\" class=\"btn btn-default form-control\"><i class='fas fa-plus'></i> Add new child</button>")
                .append("</div>")
                .append("</div>")
                .append("<div class=\"modal fade\" id=\"modal2\" role=\"dialog\"> <div class=\"modal-dialog\"> <!-- Modal content--> <div class=\"modal-content\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">Add new child</h4> </div> <div class=\"modal-body\"> <div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_title\" name=\"_title\" placeholder=\"Title\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_url\" name=\"_url\" placeholder=\"URL\"> </div> </div> </div> <div class=\"modal-footer\"> <button type=\"submit\" class=\"btn btn-success\">Add</button> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button> </div> </div> </div> </div>")
                .append("</form>")
                .append("</div>")
                .append("</div>");
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
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" name=\"%s.t\" placeholder=\"Shown Title ( Empty to remove )\" value=\"%s\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" name=\"%s.l\" placeholder=\"To URL\" value=\"%s\"> </div> </div>",
                id, title, id, url);
    }

    public String display_group(String title, String url) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> %s </div> <div class=\"col-sm-6\"> %s </div> </div>", title, url);
    }

}
