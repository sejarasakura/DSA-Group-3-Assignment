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
import javax.servlet.http.HttpServletRequest;
import main.Datas;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Footer extends AbstractPage {

    public Footer(HttpServletRequest request) {
        super(request);
    }

    public XArrayList get_footer() throws FileNotFoundException {
        if (main.Datas.settings.getValue("footer") != null) {
            return this.get_old_footer((XTreeDictionary) main.Datas.settings.getValue("footer"));
        }

        return this.get_new_footer();
    }

    private XArrayList get_old_footer(XTreeDictionary map) throws FileNotFoundException {

        XArrayList<String> result = new XArrayList<String>();
        //Load data of widget 1
        XStack widget = new XStack((Iterable) map.getValue("widget-1"));
        result.add("<div class=\"col-sm-3\">");
        result.add(get_wiget_3(widget));
        result.add("</div>");

        //Load data of widget 2
        widget = new XStack((Iterable) map.getValue("widget-2"));
        result.add("<div class=\"col-sm-3\">");
        result.add(get_wiget_3(widget));
        result.add("</div>");
        result.add("<div class=\"col-sm-6\">");

        //Load data of widget 3
        widget = new XStack((Iterable) map.getValue("widget-3"));
        result.add(get_wiget_6(widget));

        //Load data of widget 4
        widget = new XStack((Iterable) map.getValue("widget-4"));
        result.add(get_anou(widget));
        result.add("</div>");
        return result;
    }

    private XArrayList get_new_footer() throws FileNotFoundException {
        String x = System.getProperty("user.dir") + "/data/footer.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        XTreeDictionary map = new XTreeDictionary(gson.fromJson(reader, WebConfig.WRITING_CLASS));
        map = new XTreeDictionary(map.getValue("footer"));
        Datas.settings.add("footer", map);
        return get_old_footer(map);
    }

    public String get_wiget_3(XStack data) {
        String result = "";
        while (!data.isEmpty()) {
            result += build_html(new XTreeDictionary(data.pop()));
        }
        return result;
    }

    public String get_wiget_6(XStack data) {
        String result = "";
        while (!data.isEmpty()) {
            result += build_right_html(new XTreeDictionary(data.pop()));
        }
        return result;
    }

    public String get_anou(XStack data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"row\">");
        sb.append("<div class=\"col-sm-12\">");
        XTreeDictionary x;
        int show;
        while (!data.isEmpty()) {
            x = new XTreeDictionary(data.pop());
            show = Integer.parseInt(x.getValue("show").toString());
            if (show == 1) {
                sb.append(String.format("<a href=\"%s\" class=\"fa %s f_a_link pull-right fa-master\"></a>", x.getValue("l"), x.getValue("c")));
            }
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }

    private String build_html(XTreeDictionary data) {
        return String.format("<%s><a class=\"f_a_link\" href=\"%s\">%s</a></%s>", data.getValue("s"), data.getValue("l"), data.getValue("t"), data.getValue("s"));
    }

    private String build_right_html(XTreeDictionary data) {
        return String.format("<div class=\"row\"> <div class=\"col-sm-12\"> <%s class=\"pull-right\"><a class=\"f_a_link\" href=\"%s\">%s</a></%s> </div> </div>",
                data.getValue("s"), WebConfig.WEB_URL + data.getValue("l"), data.getValue("t"), data.getValue("s"));
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public XArrayList<String> getHtmls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
