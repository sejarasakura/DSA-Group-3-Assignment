/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adt.ArrList;
import adt.XHashedDictionary;
import com.univocity.parsers.csv.CsvWriterSettings;
import entity.AbstractEntity;
import entity.InfoMessage;
import entity.User;
import entity.json.ClassSaving;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static main.Datas.TYPE_SWITCH;
import xenum.ErrorDetails;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Functions {

    public static String getWebpageTitle(HttpServletRequest request, String prag) {
        return "Rent Car" + (request.getParameter(prag) == null ? "" : (" - " + request.getParameter(prag)));
    }

    public static int startUpInitialData() {
        Datas.settings = new XHashedDictionary<String, Object>();
        Datas.settings.add("image/logo", WebConfig.IMG_URL + "logo.png");
        Datas.settings.add("image/user", WebConfig.IMG_URL + "user.png");
        Datas.settings.add("text/title", "Rentcars.com");
        Datas.settings.add("pages/login", WebConfig.WEB_URL + "pages/login.jsp");
        Datas.settings.add("pages/register", WebConfig.WEB_URL + "pages/register.jsp");
        Datas.settings.add("pages/account", WebConfig.WEB_URL + "pages/account.jsp");
        Datas.settings.add("widget/cartype-select", "../widget/carousel_feeinfo.jsp");
        Datas.allMessage = new ArrList(AbstractEntity.readDataFormCsv(new InfoMessage()));
        return 1;
    }

    public static CsvWriterSettings basic_setting() {
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setNullValue("?");
        settings.getFormat().setComment('-');
        settings.setEmptyValue("!");
        settings.setSkipEmptyLines(false);
        return settings;
    }

    public static String getApiKey() {
        String api_key = null;
        if (WebConfig.API_KEY_URL != null) {
            try {
                FileInputStream fileIn = new FileInputStream(WebConfig.API_KEY_URL);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                api_key = (String) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
        return api_key;
    }

    public static void setApiKey(String api_key) {
        main.WebConfig.api_key = api_key;
        try {
            FileOutputStream fileOut = new FileOutputStream(WebConfig.API_KEY_URL);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(api_key);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String friendlyJsonTitle(String res) {

        ArrList<String> data = null;
        if (res.contains("-")) {
            data = new ArrList<String>(res.split("-"));
        }

        if (res.contains("_")) {
            data = new ArrList<String>(res.split("_"));
        }

        if (data == null) {
            data = new ArrList<String>();
            data.add(res);
        }
        StringBuilder sb = new StringBuilder();
        char[] chars;
        for (String x : data) {
            chars = x.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            sb.append(chars).append(" ");
        }
        return sb.toString();
    }

    public static ClassSaving getSavingClass(Class cls) {
        for (int i = 0; i < WebConfig.CLASS_SAVING.size(); i++) {
            if (cls.getName().equals(WebConfig.CLASS_SAVING.get(i).getClassname())) {
                return WebConfig.CLASS_SAVING.get(i);
            }
        }
        return null;
    }

    public static void setUserSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(main.WebConfig.LOGIN_SEESION_KEY, user);
    }

    public static User getUserSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(main.WebConfig.LOGIN_SEESION_KEY);
    }

    public static String getProfileUrl(HttpServletRequest request) {
        User u = getUserSession(request);
        if (u != null) {
            boolean _exists;
            String url = WebConfig.PROFILE_IMG_URL + u.getUsername();
            File temp;
            try {
                temp = File.createTempFile(url, ".png");
                _exists = temp.canRead();
                if (_exists) {
                    return url + ".png";
                }
                temp = File.createTempFile(url, ".jpeg");
                _exists = temp.canRead();
                if (_exists) {
                    return url + ".jpeg";
                }
                temp = File.createTempFile(url, ".jpg");
                _exists = temp.canRead();
                if (_exists) {
                    return url + ".jpg";
                }
            } catch (IOException e) {
            }
        }
        return (String) Datas.settings.getValue("image/user");
    }

    private static String displayErrorMessage(String e, String c) {
        ErrorDetails er = ErrorDetails.getValue(e);
        return new StringBuilder()
                .append("<div class=\"container\">")
                .append("<div class=\"alert ")
                .append(c)
                .append(" alert-dismissible fade in\">")
                .append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>")
                .append("<strong> ERROR ")
                .append(er.getCode())
                .append(" ")
                .append(er.getName())
                .append("!!</strong> ")
                .append(er.getDecription())
                .append(". </div> </div>").toString();
    }

    private static String displayErrorMessage(String e) {
        if (main.WebConfig.DEBUG_MODE) {
            Datas.allMessage = new ArrList<InfoMessage>((Iterator<InfoMessage>) AbstractEntity.readDataFormCsv(new InfoMessage()));
        }
        InfoMessage result = Datas.allMessage.searchByField("code", e, InfoMessage.class).get(0);

        return new StringBuilder()
                .append("<div class=\"container\">")
                .append("<div class=\"alert ")
                .append(result.getCssClass())
                .append(" alert-dismissible fade in\">")
                .append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>")
                .append("<strong> ")
                .append(result.getCode())
                .append(" ")
                .append(result.getName())
                .append("!!</strong> ")
                .append(result.getDecription())
                .append(". </div> </div>").toString();
    }

    public static String displayErrorMessage(HttpServletRequest request) {
        String e = request.getParameter("E");
        if (e != null) {
            return displayErrorMessage(e, "alert-danger");
        }
        e = request.getParameter("W");
        if (e != null) {
            return displayErrorMessage(e, "alert-warning");
        }
        e = request.getParameter("I");
        if (e != null) {
            return displayErrorMessage(e);
        }
        return "";
    }

    public static String repeat(String str, int count) {
        if (count <= 0) {
            return "";
        }
        return new String(new char[count]).replace("\0", str);
    }

    public static boolean checkLogin(HttpServletResponse response, User user) throws IOException {
        ArrList u = null;
        if (user == null) {
            response.sendRedirect((String) Datas.settings.getValue("pages/login"));
            return false;
        }
        if (!user.isNotNull()) {
            response.sendRedirect((String) Datas.settings.getValue("pages/login"));
            return false;
        }

        if (user.getRole().isEmpty()) {
            response.sendRedirect((String) Datas.settings.getValue("pages/login"));
            return false;
        }
        return true;
    }

    public static String getProjectDir() {
        File currDir = new File(".");
        String path = ((String) currDir.getAbsolutePath());
        path = path.substring(0, path.length() - 1).split("server")[0];
        path = path.replace('\\', '/') + "WebApplication/";
        System.out.println(xenum.OutputColor.TEXT_BLUE + "Loaded path: " + path + xenum.OutputColor.TEXT_RESET);
        return path;
    }

    public static String fieldToGetter(String name) {
        return new StringBuilder().append("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
    }

    public static String fieldToSetter(String name) {
        return new StringBuilder().append("set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
    }

    public static boolean isGeneralType(String type) {
        return TYPE_SWITCH.contains(type);
    }

    public static ArrList<Boolean> getAdminBarStatus(HttpServletRequest request) {
        return main.Datas.admin_bar_status;
    }

    public static ArrList<Boolean> createAdminBarStatus(HttpServletRequest request, int x) {
        ArrList<Boolean> status = new ArrList<Boolean>();
        for (int i = 0; i < x; i++) {
            status.add(Boolean.FALSE);
        }
        setAdminBarStatus(request, status);
        return status;
    }

    public static void setAdminBarStatus(HttpServletRequest request, ArrList<Boolean> v) {
        main.Datas.admin_bar_status = v;
    }

}
