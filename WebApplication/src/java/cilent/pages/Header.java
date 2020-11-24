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
import entity.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Header extends AbstractPage {

    private User user = null;

    public Header(HttpServletRequest request) {
        super(request);
        this.user = main.Functions.getUserSession(request);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public ArrList<String> getHtmls() {
        if (main.Datas.settings.getValue("nav") == null) {
            try {
                return get_new_menu();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return get_pre_menu((Map) main.Datas.settings.getValue("nav"));
        }
        return (new ArrList<String>());

    }

    private ArrList get_new_menu() throws FileNotFoundException {

        String x = System.getProperty("user.dir") + "/data/navigationBar.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map) map.get("nav");
        main.Datas.settings.add("nav", map);
        return get_pre_menu(map);

    }

    private String build_html(Map data) {
        if (data.get("child") == null) {
            return getlist(data);
        }

        XStack childQueue = new XStack((Iterable) data.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown\">");
        sb.append("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"")
                .append(main.WebConfig.WEB_URL)
                .append(data.get("l"))
                .append("\">")
                .append(data.get("t"))
                .append(" <span class=\"caret\"></span>")
                .append("</a>");
        sb.append("<ul class=\"dropdown-menu\">");
        while (!childQueue.isEmpty()) {
            sb.append(getlist((Map) childQueue.pop()));
        }
        sb.append("</ul>");
        sb.append("</li>");
        return sb.toString();
    }

    private String getlist(Map data) {
        return "<li class=\"art-list\"><a href=\""
                + main.WebConfig.WEB_URL
                + data.get("l")
                + "\">" + data.get("t") + "</a></li>";
    }

    private ArrList get_pre_menu(Map map) {

        ArrList<String> result = new ArrList<String>();
        boolean author_user = true;
        // all user
        XStack all_user = new XStack((Iterable) map.get("all_user"));
        while (!all_user.isEmpty()) {
            result.add(build_html((Map) all_user.pop()));
        }

        // all user end
        // all authorise
        if (user != null) {

            String role = user.getRole().toUpperCase();

            switch (role) {
                case "A":
                case "C":
                case "D":
                    if (role.equals("A") || role.equals("C")) {
                        all_user = new XStack((Iterable) map.get("customer"));
                        while (!all_user.isEmpty()) {
                            result.add(build_html((Map) all_user.pop()));
                        }
                    }
                    if (role.equals("A") || role.equals("D")) {
                        all_user = new XStack((Iterable) map.get("driver"));
                        while (!all_user.isEmpty()) {
                            result.add(build_html((Map) all_user.pop()));
                        }
                    }
                    break;
                default:
                    author_user = false;
                    break;
            }
        } else {
            author_user = false;
        }
        if (author_user) {
            all_user = new XStack((Iterable) map.get("author_user"));
            while (!all_user.isEmpty()) {
                result.add(build_html((Map) all_user.pop()));
            }
        } else {
            all_user = new XStack((Iterable) map.get("unauthor_user"));
            while (!all_user.isEmpty()) {
                result.add(build_html((Map) all_user.pop()));
            }
        }
        return result;
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
