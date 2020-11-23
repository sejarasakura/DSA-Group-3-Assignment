/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.ArrList;
import adt.XStack;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AdminHeader {

    private int i = 0;
    private HttpServletRequest request;
    private ArrList<Boolean> arrlist = null;
    private StringBuilder sb = new StringBuilder();
    private StringBuilder jquery = new StringBuilder();

    public AdminHeader(HttpServletRequest request) {
        arrlist = main.Functions.getAdminBarStatus(request);
        this.request = request;
    }

    public ArrList<String> get_admin_nav() throws FileNotFoundException {
        ArrList<String> result = new ArrList<String>();
        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map) map.get("admin-nav");
        XStack data = new XStack(map.keySet().iterator());
        String key;
        if (arrlist == null) {
            arrlist = main.Functions.createAdminBarStatus(request, data.size());
        }
        while (!data.isEmpty()) {
            key = (String) data.pop();
            result.add(get_html((Map) map.get(key), key));
        }
        result.add(getJQuery());
        return result;
    }

    private String getJQuery() {
        sb.setLength(0);
        sb.append("<script>$(document).ready(function(){");
        sb.append("     $(\".admin-menu\").click(function(){");
        sb.append("     var x = \"/WebApplication/AdminBarUpdate?c=\" + $(this).attr('datatype');");
        sb.append("     $.get(x, function(data, status){");
        sb.append("         console.log('The status' + status+', and data: ' + data );");
        sb.append("     });");
        sb.append(" });");
        sb.append("});</script>");
        return sb.toString();
    }

    private String get_html(Map map, String key) {
        sb.setLength(0);
        sb.append("<div class=\"panel panel-default\" style=\"margin: 0px\">");
        sb.append("<div class=\"panel-heading\">");
        sb.append("<h4 class=\"panel-title\">");
        sb.append("<a data-toggle=\"collapse\" class=\" admin-menu \" href=\"#").append(key)
                .append("\" ").append(" datatype='").append(i).append('\'')
                .append("><b>").append(map.get("t")).append("</b></a>");
        sb.append("</h4></div>");
        if (map.get("child") == null) {
            sb.append("</div>");
            return sb.toString();
        }
        XStack childQueue = new XStack((Iterable) map.get("child"));
        sb.append("<div id=\"").append(key).append("\" class=\"panel-collapse collapse ")
                .append((!arrlist.get(i) ? "" : "in")).append("\" style=\"padding-left: 10px\"")
                .append(">");
        sb.append("<ul class=\"list-group\">");
        Map ref;
        while (!childQueue.isEmpty()) {
            ref = (Map) childQueue.pop();
            sb.append(String.format("<li class=\"list-group-item\"><a href=\"%s\">%s</a></li>",
                    ref.get("l"), ref.get("t")));
        }
        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</div>");
        i++;
        return sb.toString();
    }

}
