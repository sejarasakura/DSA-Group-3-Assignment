/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

/**
 * Local project import
 */
import adt.XArrayList;
import adt.XArraySortList;
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import javax.servlet.http.HttpServletRequest;
import xenum.*;

/**
 *
 * @author Lim Sai Keat
 */
public class BookingHistory extends AbstractPage {

    XArrayList<MapAddress> map_add;
    XArrayList<User> users;
    XArrayList<Booking> booking;
    XArrayList<Mapping> mapping;
    User temp, login_user;

    public BookingHistory(HttpServletRequest request, User user) {
        super(request);
        // read data
        map_add = (XArrayList<MapAddress>) AbstractEntity.readDataFormCsv(new MapAddress());
        map_add.sort("address_id", MapAddress.class);
        // read data
        users = (XArrayList<User>) AbstractEntity.readDataFormCsv(new Driver());
        users.sort("user_id", Driver.class);
        // read data
        booking = (XArrayList<Booking>) AbstractEntity.readDataFormCsv(new Booking());
        booking.sort("customer_id", Booking.class);
        // read data
        mapping = (XArrayList<Mapping>) AbstractEntity.readDataFormCsv(new Mapping());
        mapping.sort("map_id", Mapping.class);

        login_user = user;
    }

    private void displayBookingHistory() {

        booking = booking.binarySearch("customer_id", login_user.getUser_id(), Booking.class);
        XArraySortList x = new XArraySortList(booking);
        x.sortDesc("booking_date", Booking.class);
        for (int i = 0; i < x.size(); i++) {
            Booking b = (Booking) x.get(i);
            displayWidget(b, mapping.binarySearchOnce("map_id", b.getMapping_id(), Mapping.class));
        }
        System.out.println(
                xenum.OutputColor.TEXT_PURPLE
                + "Found History of user "
                + login_user.getId()
                + " : in total "
                + x
                + " record found"
                + xenum.OutputColor.TEXT_RESET);

    }

    private void displayWidget(Booking b, Mapping m) {

        //<li>
        stringBuilder.append("<li>");

        //    <a href="#" class="pull-right">Booking Date</a>
        stringBuilder.append("<a href=\"#\" class=\"pull-right\">");
        stringBuilder.append(WebConfig.CHAT_FORMAT_LONG.format(b.getBooking_date()));
        stringBuilder.append("</a>");

        //    <a href="#">From Location</a>: Jalan 28, Taman Putra, Ampang<br>
        stringBuilder.append("From Location: <a target=\"_blank\" href=\"");
        stringBuilder.append("https://maps.google.com/?q=");
        stringBuilder.append(m.getDestination_id().replace('_', ','));
        stringBuilder.append("\">");
        stringBuilder.append(map_add.binarySearchOnce("address_id", m.getDestination_id(), MapAddress.class).getFull_address_hide_extra());
        stringBuilder.append("</a>");
        stringBuilder.append("<br>");

        //    <a href="#">To Location</a>: 39, Jalan Temenggung 9/9,
        stringBuilder.append("To Location: <a target=\"_blank\" href=\"");
        stringBuilder.append("https://maps.google.com/?q=");
        stringBuilder.append(m.getSource_id().replace('_', ','));
        stringBuilder.append("\">");
        stringBuilder.append(map_add.binarySearchOnce("address_id", m.getSource_id(), MapAddress.class).getFull_address_hide_extra());
        stringBuilder.append("</a>: ");
        stringBuilder.append("<br>");

        //    <p>
        stringBuilder.append("<p>");

        //        Booking id: 11-11-2020<br>
        stringBuilder.append("Booking id: ");
        stringBuilder.append("<a href=\"#\"> #");
        stringBuilder.append(b.getBooking_id());
        stringBuilder.append("</a>");
        stringBuilder.append("<br>");

        temp = users.binarySearchOnce("user_id", b.getDriver_id(), Driver.class);
        //        Driver: [driver-name][driver-id][rent-type]<br>
        stringBuilder.append("Driver: ");
        if (temp != null) {
            stringBuilder.append(temp.getName());
            stringBuilder.append(" [");
            stringBuilder.append(temp.getUsername());
            stringBuilder.append("] ");
        }
        stringBuilder.append("<br>");

        //        Status: [booking-status]<br>
        stringBuilder.append("Status: ");
        stringBuilder.append(b.getBookingStatus().getName());
        stringBuilder.append(",");
        stringBuilder.append(b.getBookingStatus().getDecription());
        stringBuilder.append("<br>");

        //        View Chat: <a>[to-chat]</a><br>
        stringBuilder.append("<a target=\"_blank\" href='");
        stringBuilder.append("http://localhost:8080/WebApplication/pages/chat.jsp?target=");
        stringBuilder.append(b.getChats_id());
        stringBuilder.append("'>");
        stringBuilder.append("Chat with ");
        if (temp != null) {
            stringBuilder.append(temp.getName());
        }
        stringBuilder.append("</a>");
        stringBuilder.append("<br>");

        //        Note to driver: [description]
        stringBuilder.append("Note to driver: ").append(b.getBooking_description());

        //    </p>
        stringBuilder.append("</p>");

        //</li>
        stringBuilder.append("</li>");
    }

    @Override
    public String getHtml() {
        displayBookingHistory();
        return this.stringBuilder.toString();
    }

    @Override
    public XArrayList<String> getHtmls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
