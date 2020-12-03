/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.XArrayList;
import adt.XTreeDictionary;
import adt.XStack;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import main.Datas;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AdminHeader extends AbstractPage {

    private int i = 0;
    private XArrayList<Boolean> arrlist = null;

    public AdminHeader(HttpServletRequest request) {
        super(request);
        arrlist = main.Functions.getAdminBarStatus(request);
    }

    @Override
    public XArrayList<String> getHtmls() {
        XArrayList<String> result = new XArrayList<String>();
        try {
            String x = System.getProperty("user.dir") + "/data/adminNav.json";
            JsonReader reader = new JsonReader(new FileReader(x));
            Gson gson = new Gson();
            XTreeDictionary map = null;
            if (Datas.settings.getValue("admin-nav") == null) {
                map = new XTreeDictionary(gson.fromJson(reader, WebConfig.WRITING_CLASS));
                Datas.settings.add("admin-nav", map);
            } else {
                map = (XTreeDictionary) Datas.settings.getValue("admin-nav");
            }
            map = new XTreeDictionary(map.getValue("admin-nav"));
            XStack data = new XStack(map.newKeyIterator());
            String key;
            if (arrlist == null) {
                arrlist = main.Functions.createAdminBarStatus(request, data.size());
            }
            while (!data.isEmpty()) {
                key = (String) data.pop();
                result.add(get_html(new XTreeDictionary(map.getValue(key)), key));
            }
            result.add(getJQuery());
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminHeader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private String getJQuery() {
        stringBuilder.setLength(0);
        stringBuilder.append("<script>$(document).ready(function(){");
        stringBuilder.append("     $(\".admin-menu\").click(function(){");
        stringBuilder.append("     var x = \"/WebApplication/AdminBarUpdate?c=\" + $(this).attr('datatype');");
        stringBuilder.append("     $.get(x, function(data, status){");
        stringBuilder.append("         console.log('The status' + status+', and data: ' + data );");
        stringBuilder.append("     });");
        stringBuilder.append(" });");
        stringBuilder.append("});</script>");
        return stringBuilder.toString();
    }

    private String get_html(XTreeDictionary map, String key) {
        stringBuilder.setLength(0);
        stringBuilder.append("<div class=\"panel panel-default\" style=\"margin: 0px\">");
        stringBuilder.append("<div class=\"panel-heading\">");
        stringBuilder.append("<h4 class=\"panel-title\">");
        stringBuilder.append("<a data-toggle=\"collapse\" class=\" admin-menu \" href=\"#").append(key);
        stringBuilder.append("\" ").append(" datatype='").append(i).append('\'');
        stringBuilder.append("><b>").append(map.getValue("t")).append("</b></a>");
        stringBuilder.append("</h4></div>");
        if (map.getValue("child") == null) {
            stringBuilder.append("</div>");
            return stringBuilder.toString();
        }
        XStack childQueue = new XStack((Iterable) map.getValue("child"));
        stringBuilder.append("<div id=\"").append(key).append("\" class=\"panel-collapse collapse ");
        stringBuilder.append((!arrlist.get(i) ? "" : "in")).append("\" style=\"padding-left: 10px\"");
        stringBuilder.append(">");
        stringBuilder.append("<ul class=\"list-group\">");
        XTreeDictionary ref;
        while (!childQueue.isEmpty()) {
            ref = new XTreeDictionary(childQueue.pop());
            stringBuilder.append("<li class=\"list-group-item\"><a href=\"");
            stringBuilder.append(ref.getValue("l"));
            stringBuilder.append("\">");
            stringBuilder.append(ref.getValue("t"));
            stringBuilder.append("</a></li>");
        }
        stringBuilder.append("</ul>");
        stringBuilder.append("</div>");
        stringBuilder.append("</div>");
        i++;
        return stringBuilder.toString();
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
