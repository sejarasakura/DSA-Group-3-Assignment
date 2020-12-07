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
 * @author ITSUKA KOTORI
 */
public class newClass_temp_3 extends AbstractPage {

    XArrayList<MapAddress> map_add;

    public newClass_temp_3(HttpServletRequest request) {
        super(request);
        map_add = (XArrayList<MapAddress>) AbstractEntity.readDataFormCsv(new MapAddress());
        map_add.sort("address_id", MapAddress.class);
    }

    private void displayWidget(Booking b, Mapping mapping) {

        //<li>
        stringBuilder.append("");
        //    <a href="#" class="pull-right">Booking Date</a>
        stringBuilder.append("");
        //    <a href="#">From Location</a>: Jalan 28, Taman Putra, Ampang<br>
        stringBuilder.append("");
        //    <a href="#">To Location</a>: 39, Jalan Temenggung 9/9,
        stringBuilder.append("");
        //    <p>
        stringBuilder.append("");
        //        Booking date: 11-11-2020<br>
        stringBuilder.append("");
        //        Driver: [driver-name][driver-id][rent-type]<br>
        stringBuilder.append("");
        //        Status: [booking-status]<br>
        stringBuilder.append("");
        //        View Chat: <a>[to-chat]</a><br>
        stringBuilder.append("<a>");
        //        Note to driver: [description]
        stringBuilder.append("Note to driver: ").append(b.getBooking_description());
        //    </p>
        stringBuilder.append("</p>");
        //</li>
        stringBuilder.append("</li>");
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
