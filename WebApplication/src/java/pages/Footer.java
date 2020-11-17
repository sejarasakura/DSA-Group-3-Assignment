/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import adt.ArrList;
import adt.XStack;
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
public class Footer {
    
    public Footer(HttpServletRequest request){
        
    }
    
    public ArrList get_footer() throws FileNotFoundException {
        ArrList<String> result = new ArrList<String>();
        String x = System.getProperty("user.dir") + "/data/footer.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map)map.get("footer");
        
        //Load data of widget 1
        XStack widget = new XStack((Iterable) map.get("widget-1"));
        result.add("<div class=\"col-sm-3\">");
        result.add(get_wiget_3(widget));
        result.add("</div>");
        
        //Load data of widget 2
        widget = new XStack((Iterable) map.get("widget-2"));
        result.add("<div class=\"col-sm-3\">");
        result.add(get_wiget_3(widget));
        result.add("</div>");
        result.add("<div class=\"col-sm-6\">");
        
        //Load data of widget 3
        widget = new XStack((Iterable) map.get("widget-3"));
        result.add(get_wiget_6(widget));
        
        //Load data of widget 4
        widget = new XStack((Iterable) map.get("widget-4"));
        result.add(get_anou(widget));
        result.add("</div>");
        return result;
    }
    
    public String get_wiget_3(XStack data){
        String result = "";
        while (!data.isEmpty()) {
            result += build_html((Map) data.pop());
        }
        return result;
    }
    
    public String get_wiget_6(XStack data){
        String result = "";
        while (!data.isEmpty()) {
            result += build_right_html((Map) data.pop());
        }
        return result;
    }
    
    public String get_anou(XStack data){
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"row\">");
        sb.append("<div class=\"col-sm-12\">");
        Map x;
        int show;
        while(!data.isEmpty()){
            x = (Map) data.pop();
            show = Integer.parseInt(x.get("show").toString());
            if(show == 1)
                sb.append(String.format("<a href=\"%s\" class=\"fa %s pull-right fa-master\"></a>",x.get("l"), x.get("c")));
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String build_html(Map data){
        return String.format("<%s><a class=\"f_a_link\" href=\"%s\">%s</a></%s>", data.get("s"), data.get("l"), data.get("t"), data.get("s"));
    } 
    private String build_right_html(Map data){
        return String.format("<div class=\"row\"> <div class=\"col-sm-12\"> <%s class=\"pull-right\"><a class=\"f_a_link\" href=\"%s\">%s</a></%s> </div> </div>", 
                data.get("s"), WebConfig.WEB_URL + data.get("l"), data.get("t"), data.get("s"));
    }
    
}
