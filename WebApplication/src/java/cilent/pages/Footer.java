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
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Footer extends AbstractPage {

    public Footer(HttpServletRequest request) {
        super(request);
    }

    public ArrList get_footer() throws FileNotFoundException {
        if (main.Datas.settings.getValue("footer") != null) {
            return this.get_old_footer((Map) main.Datas.settings.getValue("footer"));
        }

        return this.get_new_footer();
    }

    private ArrList get_old_footer(Map map) throws FileNotFoundException {

        ArrList<String> result = new ArrList<String>();
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

    private ArrList get_new_footer() throws FileNotFoundException {
        String x = System.getProperty("user.dir") + "/data/footer.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map) map.get("footer");
        return get_old_footer(map);
    }

    public String get_wiget_3(XStack data) {
        String result = "";
        while (!data.isEmpty()) {
            result += build_html((Map) data.pop());
        }
        return result;
    }

    public String get_wiget_6(XStack data) {
        String result = "";
        while (!data.isEmpty()) {
            result += build_right_html((Map) data.pop());
        }
        return result;
    }

    public String get_anou(XStack data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"row\">");
        sb.append("<div class=\"col-sm-12\">");
        Map x;
        int show;
        while (!data.isEmpty()) {
            x = (Map) data.pop();
            show = Integer.parseInt(x.get("show").toString());
            if (show == 1) {
                sb.append(String.format("<a href=\"%s\" class=\"fa %s pull-right fa-master\"></a>", x.get("l"), x.get("c")));
            }
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }

    private String build_html(Map data) {
        return String.format("<%s><a class=\"f_a_link\" href=\"%s\">%s</a></%s>", data.get("s"), data.get("l"), data.get("t"), data.get("s"));
    }

    private String build_right_html(Map data) {
        return String.format("<div class=\"row\"> <div class=\"col-sm-12\"> <%s class=\"pull-right\"><a class=\"f_a_link\" href=\"%s\">%s</a></%s> </div> </div>",
                data.get("s"), WebConfig.WEB_URL + data.get("l"), data.get("t"), data.get("s"));
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrList<String> getHtmls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
