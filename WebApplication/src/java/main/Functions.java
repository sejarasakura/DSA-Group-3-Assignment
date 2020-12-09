/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adt.XArrayList;
import adt.XHashedDictionary;
import adt.XQueue;
import com.univocity.parsers.csv.CsvWriterSettings;
import entity.AbstractEntity;
import entity.Booking;
import entity.InfoMessage;
import entity.User;
import entity.json.ClassSaving;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.*;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static main.Datas.TYPE_SWITCH;
import xenum.BookingStatus;
import xenum.ErrorDetails;

/**
 *
 * @author Lim Sai Keat
 */
public class Functions {

    public static String getWebpageTitle(HttpServletRequest request, String prag) {
        return "Rent Car" + (request.getParameter(prag) == null ? "" : (" - " + request.getParameter(prag)));
    }

    public static int startUpInitialData() {
        Datas.settings = new XHashedDictionary<String, Object>();
        Datas.settings.add("image/logo", WebConfig.IMG_URL + "logo.png");
        Datas.settings.add("image/user", WebConfig.WEB_URL + "imageRetrive?type=profile&id=user");
        Datas.settings.add("image/upload", WebConfig.WEB_URL + "imageRetrive?type=&id=upload");
        Datas.settings.add("text/title", "Rentcars.com");
        Datas.settings.add("pages/login", WebConfig.WEB_URL + "pages/login.jsp");
        Datas.settings.add("pages/register", WebConfig.WEB_URL + "pages/register.jsp");
        Datas.settings.add("pages/account", WebConfig.WEB_URL + "pages/account.jsp");
        Datas.settings.add("widget/cartype-select", "../widget/carousel_feeinfo.jsp");
        Datas.allMessage = (XArrayList<InfoMessage>) AbstractEntity.readDataFormCsv(new InfoMessage());
        XArrayList booking_list = AbstractEntity.readDataFormCsv(new Booking()), temp;
        booking_list.sort("bookingStatus", Booking.class);
        temp = booking_list.binarySearch("bookingStatus", BookingStatus.WATING_ACCEPTED, Booking.class);
        temp.sortDesc("booking_date", Booking.class);
        Datas.currentBooking = new XQueue(temp);
        temp = booking_list.binarySearch("bookingStatus", BookingStatus.PENDING_RENTING, Booking.class);
        temp.sortDesc("booking_date", Booking.class);
        Datas.waitingDriver = new XQueue(temp);

        System.out.println();
        System.out.println("Currrent Request Booking Loaded to queue");
        System.out.println("===========================================================");
        System.out.println(Datas.currentBooking);
        System.out.println("===========================================================");
        System.out.println();
        System.out.println("Currrent Waiting Driver Loaded to queue");
        System.out.println("===========================================================");
        System.out.println(Datas.waitingDriver);
        System.out.println("===========================================================");
        return 1;
    }

    public static void add_update_para() {
        Datas.updateparam.add("car", new XArrayList());
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

        XArrayList<String> data = null;
        if (res.contains("-")) {
            data = new XArrayList<String>(res.split("-"));
        }

        if (res.contains("_")) {
            data = new XArrayList<String>(res.split("_"));
        }

        if (data == null) {
            data = new XArrayList<String>();
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
            if (u.getId() != null) {
                return (String) WebConfig.WEB_URL + "/imageRetrive?type=profile&id=" + u.getId() + "&default=upload";
            } else {
                return (String) WebConfig.WEB_URL + "/imageRetrive?type=profile&id=" + u.getId();
            }
        }
        return (String) Datas.settings.getValue("image/user");
    }

    public static String getProfilePic_byid(String id) {
        if (id != null) {
            return (String) WebConfig.WEB_URL + "/imageRetrive?type=profile&id=" + id + "&default=upload";
        } else {
            return (String) Datas.settings.getValue("image/user");
        }
    }

    private static String displayErrorMessage(String e, String c, String errorQuery) {
        errorQuery = errorQuery == null ? "" : "<br>" + errorQuery;
        ErrorDetails er = ErrorDetails.getValue(e);
        return new StringBuilder()
                .append("<div class=\"container\">")
                .append("<div style=\"margin-top: 10px\" class=\"alert ")
                .append(c)
                .append(" alert-dismissible fade in\">")
                .append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>")
                .append("<strong> ERROR ")
                .append(er.getCode())
                .append(" ")
                .append(er.getName())
                .append("!!</strong> ")
                .append(er.getDecription())
                .append(errorQuery)
                .append(". </div> </div>").toString();
    }

    private static String displayErrorMessage(String e, String errorQuery) {
        errorQuery = errorQuery == null ? "" : "<br>" + errorQuery;
        if (main.WebConfig.DEBUG_MODE) {
            Datas.allMessage = (XArrayList<InfoMessage>) AbstractEntity.readDataFormCsv(new InfoMessage());
        }
        InfoMessage result = Datas.allMessage.searchByField("code", e, InfoMessage.class).get(0);
        if (result == null) {
            return "<div class=\"alert alert-danger alert-dismissible fade in\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a> <strong>Error code not found!</strong> This alert code <a href='#" + e + "'><b>" + e + "</b></a> is dose not excite </div>";
        }
        return new StringBuilder()
                .append("<div class=\"container\" >")
                .append("<div style=\"margin-top: 10px\" class=\"alert ")
                .append(result.getCssClass())
                .append(" alert-dismissible fade in\">")
                .append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>")
                .append("<strong> Alert ")
                .append(result.getCode().replace("-", ""))
                .append(" ")
                .append(result.getName())
                .append("!!</strong> ")
                .append(result.getDecription())
                .append(errorQuery)
                .append(". </div> </div>").toString();
    }

    public static String displayErrorMessage(HttpServletRequest request) {
        String e = request.getParameter("E");
        String eq = request.getParameter("eq");
        if (e != null) {
            return displayErrorMessage(e, "alert-danger", eq);
        }
        e = request.getParameter("W");
        if (e != null) {
            return displayErrorMessage(e, "alert-warning", eq);
        }
        e = request.getParameter("I");
        if (e != null) {
            return displayErrorMessage(e, eq);
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
        XArrayList u = null;
        if (user == null) {
            response.sendRedirect(Datas.settings.getValue("pages/login") + "?I=I-0012");
            return false;
        }
        if (!user.isNotNull()) {
            response.sendRedirect(Datas.settings.getValue("pages/login") + "?I=I-0012");
            return false;
        }

        if (user.getRole().isEmpty()) {
            response.sendRedirect(Datas.settings.getValue("pages/login") + "?I=I-0012");
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

    public static XArrayList<Boolean> getAdminBarStatus(HttpServletRequest request) {
        return main.Datas.admin_bar_status;
    }

    public static XArrayList<Boolean> createAdminBarStatus(HttpServletRequest request, int x) {
        XArrayList<Boolean> status = new XArrayList<Boolean>();
        for (int i = 0; i < x; i++) {
            status.add(Boolean.FALSE);
        }
        setAdminBarStatus(request, status);
        return status;
    }

    public static void setAdminBarStatus(HttpServletRequest request, XArrayList<Boolean> v) {
        main.Datas.admin_bar_status = v;
    }

    public static byte[] getDefaultImage() {
        try {
            RandomAccessFile f;
            String path = WebConfig.PROJECT_URL + "/web/img/error.png";
            f = new RandomAccessFile(path, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.read(bytes);
            return bytes;
        } catch (IOException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean isEmail(String email) {
        final String reg_exp
                = "^([a-z0-9])(([-a-z0-9._])*([a-z0-9]))*@([a-z0-9])"
                + "(([a-z0-9-])*([a-z0-9]))+(.([a-z0-9])([-a-z0-9_-])?"
                + "([a-z0-9])+)+$";
        return Pattern.matches(reg_exp, email);
    }

    public static String hash_csv_record(String str) {
        if (str == null) {
            return null;
        }
        return str.replace(",", "<comal>");
    }

    public static String unhash_csv_record(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("<comal>", ",");
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = currentDate().getTime();
        if (time > now || time <= 0) {
            return "in the future";
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "Moments ago";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "A minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "An hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "Yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }
}
