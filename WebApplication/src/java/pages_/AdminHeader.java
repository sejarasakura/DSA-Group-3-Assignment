/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages_;

import adtClass.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AdminHeader {

    public AdminHeader(HttpServletRequest request){
        
    }
    
    public ArrList<String> get_admin_nav() throws FileNotFoundException {
        ArrList<String> result = new ArrList<String>();
        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map) map.get("admin-nav");
        Stack data = new Stack(map.keySet().iterator());
        String key;
        while (!data.isEmpty()) {
            key = (String) data.pop();
            result.add(get_html((Map) map.get(key), key));
        }
        return result;
    }

    private String get_html(Map map, String key) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel panel-default\" style=\"margin: 0px\">");
        sb.append("<div class=\"panel-heading\">");
        sb.append("<h4 class=\"panel-title\">");
        sb.append(String.format("<a data-toggle=\"collapse\" href=\"#%s\"><b>%s</b></a>", key, map.get("t")));
        sb.append("</h4></div>");
        if (map.get("child") == null) {
            sb.append("</div>");
            return sb.toString();
        }
        Stack childQueue = new Stack((Iterable) map.get("child"));
        sb.append(String.format("<div id=\"%s\" class=\"panel-collapse collapse\" style=\"padding-left: 10px\">", key));
        sb.append("<ul class=\"list-group\">");
        Map ref;
        while (!childQueue.isEmpty()) {
            ref = (Map)childQueue.pop();
            sb.append(String.format("<li class=\"list-group-item\"><a href=\"%s\">%s</a></li>",
            WebConfig.ADMIN_URL + ref.get("l"), ref.get("t")));
        }
        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</div>");

        return sb.toString();
    }

}
