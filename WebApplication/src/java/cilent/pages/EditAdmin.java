/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.XArrayList;
import adt.XTreeDictionary;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditAdmin extends AbstractPage {

    public static String ERROR = "<h1>Error found</h1>";
    private String json_data = "";
    private String e_title;
    private String base;
    private final String edit;
    private final String t;
    private boolean status = false;
    private XTreeDictionary map;

    public EditAdmin(HttpServletRequest request) {
        super(request);
        edit = request.getParameter("edit");
        t = request.getParameter("t");
        json_data = System.getProperty("user.dir") + "/data/"
                + edit + ".json";
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(json_data));
            Gson gson = new Gson();
            map = new XTreeDictionary(
                    gson.fromJson(reader, WebConfig.WRITING_CLASS)
            );
            e_title = (String) map.getValue("e-title");
            base = (String) map.getValue("base");
            map = new XTreeDictionary(map.getValue(base));
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EditAdmin() {
        super(null);
        edit = "adminNav";
        t = "";
        json_data = System.getProperty("user.dir") + "/data/"
                + edit + ".json";
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(json_data));
            Gson gson = new Gson();
            map = new XTreeDictionary(
                    gson.fromJson(reader, WebConfig.WRITING_CLASS)
            );
            e_title = (String) map.getValue("e-title");
            base = (String) map.getValue("base");
            map = new XTreeDictionary(map.getValue(base));
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public XArrayList<String> getHtmls() {
        XArrayList<String> result = new XArrayList<String>();
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

    private XArrayList<String> generateAdminNav() {
        XArrayList<String> result = new XArrayList<String>();
        map.getKeyList().forEach((m) -> {
            if (m.equals(t)) {
                result.add(editProperties(new XTreeDictionary(map.getValue(m)), (String) m));
            } else {
                result.add(displayProperties(new XTreeDictionary(map.getValue(m)), (String) m));
            }
        });
        result.add("<div class=\"form-group row\"> <div class=\"col-sm-12\"> <button type=\"button\" class=\"btn btn-success form-control\" data-toggle=\"modal\" data-target=\"#myModal\"> <i class='fas fa-plus'></i> Add new element </button> </div> </div>");
        result.add("<div class=\"modal fade\" id=\"myModal\" role=\"dialog\"> <div class=\"modal-dialog\"> <!-- Modal content--> <div class=\"modal-content\"> <form action=\"/WebApplication/admin/add-new-ele?add=" + edit + "\" method=\"post\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">Add new element</h4> </div> <div class=\"modal-body\"> <div class=\"form-group row\"> <div class=\"col-sm-6\"> Items name / json key: </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"add-new\" name=\"add-new\" placeholder=\"Name\"> </div> </div> <div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_title\" name=\"_title\" placeholder=\"Title\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" id=\"_url\" name=\"_url\" placeholder=\"URL\"> </div> </div> </div> <div class=\"modal-footer\"> <button type=\"submit\" class=\"btn btn-success\">Add</button> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button> </div> </form> </div> </div> </div>");
        result.add("<a href=\"" + main.WebConfig.WEB_URL + "ClearSetting?key=" + base + "\" class=\"btn btn-success pull-right\">Reload</a>");
        return result;
    }

    private XArrayList<String> generateAdminNor() {
        XArrayList<String> result = new XArrayList<String>();
        map.getKeyList().forEach((m) -> {
            if (m.equals(t)) {
                result.add(editProperties((Iterable) map.getValue(m), (String) m));
            } else {
                result.add(displayProperties((Iterable) map.getValue(m), (String) m));
            }
        });
        result.add("<script>$(document).ready(function(){");
        map.getKeyList().forEach((m) -> {
            result.add(jquery_generate((String) m));
        });
        result.add("});</script>");
        result.add("<a href=\"" + main.WebConfig.WEB_URL + "ClearSetting?key=" + base + "\" class=\"btn btn-success pull-right\">Reload</a>");
        return result;
    }

    private String jquery_generate(String key) {
        return "$(\"#p-h-" + key + "\").click(function(){ $(\"#p-b-" + key + "\").toggle(); $(\"#p-f-" + key + "\").toggle(); if($(\"#p-b-" + key + "\").is(\":hidden\")){ $(this).html(\"<span class=\\\"glyphicon glyphicon-plus\\\"></span> show\"); }else{ $(this).html(\"<span class=\\\"glyphicon glyphicon-minus\\\"></span> hide\"); } });";
    }

    private String displayProperties(Iterable prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\" id=\"").append(key.replace("-", "_")).append("\">")
                .append("<div class=\"panel-heading\">").append("<div class=\"row\"> <div class=\"col-sm-6\"> ")
                .append(title).append(" </div> <div class=\"col-sm-6\"> <button type=\"button\" class=\"btn btn-default pull-right\" id=\"p-h-")
                .append(key).append("\"> <span class=\"glyphicon glyphicon-plus\"></span> show </button> </div> </div>")
                .append("</div>").append("<div class=\"panel-body\" id=\"p-b-")
                .append(key).append("\">");
        int count = 0;
        boolean header = false;
        XTreeDictionary x;
        for (Iterator it = prop.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            if (x.getValue("child") == null) {
                if (x.getValue("show") != null) {
                    sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l"), (String) x.getValue("show")));
                } else if (x.getValue("s") != null) {
                    sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l"), (String) x.getValue("s")));
                } else {
                    sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l")));
                }
            } else {
                XArrayList ar = new XArrayList((Iterable) x.getValue("child"));
                if (ar.isEmpty()) {
                    sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l")));
                }
                sb.append(displayChildProp(x));
            }
            count++;
        }
        sb.append("</div><div class=\"panel-footer\" id=\"p-f-").append(key).append("\">")
                .append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit)
                .append("&t=").append(key).append("#").append(key.replace("-", "_")).append("\" class=\"btn btn-default pull-right\">Edit</a>")
                .append("</div></div></div></div>");
        return sb.toString();
    }

    private String editProperties(Iterable prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        StringBuilder sb = new StringBuilder();
        sb.append("<form action=\"/WebApplication/admin/EditElement\" method=\"post\" id=\"").append(key.replace("-", "_")).append("\">").append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">").append("<div class=\"row\"> <div class=\"col-sm-6\"> ")
                .append(title).append(" </div> <div class=\"col-sm-6\"> <button type=\"button\" class=\"btn btn-default pull-right\" id=\"p-h-")
                .append(key).append("\"> <span id='add-btn' class=\"glyphicon glyphicon-plus\"></span> show </button> </div> </div>")
                .append("</div>").append("<div class=\"panel-body\" id=\"p-b-")
                .append(key).append("\">");
        int count = 0;
        String header = "";
        XTreeDictionary x;
        for (Iterator it = prop.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            if (x.getValue("child") == null) {
                if (x.getValue("show") != null) {
                    header = "show";
                    sb.append(form_group_S(base + "." + key + ".child[" + count + "]", (String) x.getValue("t"), (String) x.getValue("l"), (String) x.getValue("c"), (String) x.getValue("show")));
                } else if (x.getValue("s") != null) {
                    header = "s";
                    sb.append(form_group(base + "." + key + ".child[" + count + "]", (String) x.getValue("t"), (String) x.getValue("l"), (String) x.getValue("s")));
                } else {
                    sb.append(form_group(base + "." + key + ".child[" + count + "]", (String) x.getValue("t"), (String) x.getValue("l")));
                }
            } else {
                XArrayList ar = new XArrayList((Iterable) x.getValue("child"));
                sb.append(editChildProp(x, base + "." + key + ".child[" + count + "]"));
            }
            count++;
        }
        switch (header) {
            case "show":
                sb.append(form_group_S(base + "." + key + ".child[" + count + "]", "", "", "", ""));
                break;
            case "s":
                sb.append(form_group(base + "." + key + ".child[" + count + "]", "", "", ""));
                break;
            default:
                sb.append(form_group(base + "." + key + ".child[" + count + "]", "", ""));
                break;
        }
        sb.append("</div>")
                .append("<div class=\"panel-footer\">").append("<div class=\"form-group row\">")
                .append(" <input type=\"hidden\" id=\"count\" name=\"count\" value=\"").append(count).append("\">")
                .append(" <input type=\"hidden\" id=\"edit\" name=\"edit\" value=\"").append(edit).append("\">")
                .append(" <input type=\"hidden\" id=\"t\" name=\"t\" value=\"").append(t).append("\">")
                .append(" <input type=\"hidden\" id=\"header\" name=\"header\" value=\"").append(header).append("\">")
                .append("<div class=\"col-sm-6\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit).append("\" class=\"btn btn-danger form-control\">cancel</a>")
                .append("</div>")
                .append("<div class=\"col-sm-6\">")
                .append("<button type=\"submit\" class=\"btn btn-warning form-control\">update</button>")
                .append("</div>")
                .append("</div>").append("</div>")
                .append("</div>")
                .append("</form>");
        return sb.toString();
    }

    private String displayChildProp(XTreeDictionary prop) {
        XArrayList datas = new XArrayList((Iterable) prop.getValue("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"form-group row\" style=\"margin: 10px 0px\">").
                append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">")
                .append("<div class=\"row\">")
                .append("<div class=\"col-sm-6\"> ").append(prop.getValue("t")).append("</div>")
                .append("<div class=\"col-sm-6\"> ").append(prop.getValue("l")).append("</div>")
                .append("</div></div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        XTreeDictionary x;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l")));
            count++;
        }
        sb.append("</div></div></div>");
        return sb.toString();
    }

    private String editChildProp(XTreeDictionary prop, String id) {
        XArrayList datas = new XArrayList((Iterable) prop.getValue("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"form-group row\" style=\"margin: 10px 0px\">").
                append("<div class=\"panel panel-default\">")
                .append("<div class=\"panel-heading\">")
                .append(form_group(id, (String) prop.getValue("t"), (String) prop.getValue("l")))
                .append("</div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        XTreeDictionary x;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            sb.append(form_group(id + "." + count, (String) x.getValue("t"), (String) x.getValue("l")));
            count++;
        }
        sb.append(form_group(id + "." + count, "", ""));
        sb.append("</div></div></div>")
                .append(" <input type=\"hidden\" name=\"").append(id).append(".count\" value=\"").append(count).append("\">");
        return sb.toString();
    }

    private String displayProperties(XTreeDictionary prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        XArrayList datas = prop.getValue("child") == null ? new XArrayList() : new XArrayList((Iterable) prop.getValue("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\" id=\"").append(key.replace("-", "_")).append("\">")
                .append("<div class=\"panel-heading\">")
                .append("<h2>").append(title).append("</h2><br>")
                .append(display_group((String) prop.getValue("t"), (String) prop.getValue("l")))
                .append("</div>")
                .append("<div class=\"panel-body\">");
        int count = 0;

        XTreeDictionary x;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            sb.append(display_group((String) x.getValue("t"), (String) x.getValue("l")));
            count++;
        }
        sb.append("<div class=\"form-group row\">")
                .append("<div class=\"col-sm-12\">")
                .append("<a href=\"edit_app.jsp?edit=").append(edit)
                .append("&t=").append(key).append("#").append(key.replace("-", "_")).append("\" class=\"btn btn-default pull-right\">Edit</a>")
                .append("</div>")
                .append("</div>");
        sb.append("</div>")
                .append("</div>");
        return sb.toString();
    }

    private String editProperties(XTreeDictionary prop, String key) {
        String title = main.Functions.friendlyJsonTitle(key);
        XArrayList datas = prop.getValue("child") == null ? new XArrayList() : new XArrayList((Iterable) prop.getValue("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\" id=\"").append(key.replace("-", "_")).append("\">")
                .append("<form action=\"/WebApplication/admin/update-ele\" method=\"post\">")
                .append("<div class=\"panel-heading\">")
                .append("<h2>").append(title).append("</h2><br>")
                .append(form_group(base + "." + key, (String) prop.getValue("t"), (String) prop.getValue("l")))
                .append("</div>")
                .append("<div class=\"panel-body\">");
        int count = 0;
        XTreeDictionary x;
        for (Iterator it = datas.iterator(); it.hasNext();) {
            x = new XTreeDictionary(it.next());
            sb.append(form_group(base + "." + key + ".child[" + count + "]",
                    (String) x.getValue("t"), (String) x.getValue("l")));
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
    private String form_group(String id, String title, String url) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" name=\"%s.t\" placeholder=\"Shown Title ( Empty to remove )\" value=\"%s\"> </div> <div class=\"col-sm-6\"> <input type=\"text\" class=\"form-control\" name=\"%s.l\" placeholder=\"To URL\" value=\"%s\"> </div> </div>",
                id, title, id, url);
    }

    private String form_group_S(String id, String title, String url, String class_s, String show) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.t\" placeholder=\"Shown Title ( Empty to remove )\" value=\"%s\"> </div> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.l\" placeholder=\"To URL\" value=\"%s\"> </div> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.show\" placeholder=\"Show = 1 / Not show = 0\" value=\"%s\"> <input type=\"hidden\" class=\"form-control\" name=\"%s.c\" value=\"%s\"></div> </div>",
                id, title, id, url, id, show, id, class_s);
    }

    private String form_group(String id, String title, String url, String html) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.t\" placeholder=\"Shown Title ( Empty to remove )\" value=\"%s\"> </div> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.l\" placeholder=\"To URL\" value=\"%s\"> </div> <div class=\"col-sm-4\"> <input type=\"text\" class=\"form-control\" name=\"%s.s\" placeholder=\"Html tag\" value=\"%s\"> </div> </div>",
                id, title, id, url, id, html);
    }

    private String display_group(String title, String url) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-6\"> %s </div> <div class=\"col-sm-6\"> %s </div> </div>", title, url);
    }

    private String display_group(String title, String url, String html) {
        return String.format("<div class=\"form-group row\"> <div class=\"col-sm-4\"> %s </div> <div class=\"col-sm-4\"> %s </div> <div class=\"col-sm-4\"> %s </div> </div>", title, url, html);
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
