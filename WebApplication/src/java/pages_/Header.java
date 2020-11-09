/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages_;

import adtClass.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import entityClass.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Header {

    private User user = null;
    private HttpServletRequest request;

    public Header(HttpServletRequest request) {
        Cookie[] cs = request.getCookies();
        for (Cookie c : cs) {
            if (c.getName().equals("user")) {
                get_user_data(c.getValue());
            }
        }
        this.request = request;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void get_user_data(String uid) {

    }

    public ArrList get_menu() throws FileNotFoundException {

        ArrList<String> result = new ArrList<String>();
        String x = System.getProperty("user.dir") + "/data/navigationBar.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new Gson();
        Map map = gson.fromJson(reader, Map.class);
        map = (Map) map.get("nav");
        boolean author_user = true;
        // all user
        Stack all_user = new Stack((Iterable) map.get("all_user"));
        while (!all_user.isEmpty()) {
            result.add(build_html((Map) all_user.pop()));
        }

        // all user end
        // all authorise
        if (user != null) {

            String role = user.getRole();

            switch (role) {
                case "A":
                    all_user = new Stack((Iterable) map.get("admin"));
                    while (!all_user.isEmpty()) {
                        result.add(build_html((Map) all_user.pop()));
                    }
                    break;
                // all authorise end
                case "C":
                    all_user = new Stack((Iterable) map.get("customer"));
                    while (!all_user.isEmpty()) {
                        result.add(build_html((Map) all_user.pop()));
                    }
                    break;
                case "D":
                    all_user = new Stack((Iterable) map.get("driver"));
                    while (!all_user.isEmpty()) {
                        result.add(build_html((Map) all_user.pop()));
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
            all_user = new Stack((Iterable) map.get("author_user"));
            while (!all_user.isEmpty()) {
                result.add(build_html((Map) all_user.pop()));
            }
        } else {
            all_user = new Stack((Iterable) map.get("unauthor_user"));
            while (!all_user.isEmpty()) {
                result.add(build_html((Map) all_user.pop()));
            }
        }
        return result;
    }

    private String build_html(Map data) {
        if (data.get("child") == null) {
            return getlist(data);
        }

        Stack childQueue = new Stack((Iterable) data.get("child"));
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown\">");
        sb.append("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"")
                .append(main.WebConfig.WEB_URL)
                .append(data.get("url"))
                .append("\">")
                .append(data.get("text"))
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
                + data.get("url")
                + "\">" + data.get("text") + "</a></li>";
    }

}
