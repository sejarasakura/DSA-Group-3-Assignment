/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

/**
 * Local project import
 */
import adt.*;
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import entity.help.Range;
import javax.servlet.http.HttpServletRequest;
import xenum.*;

/**
 *
 * @author Lim Sai Keat
 */
public class DriverFetch extends AbstractPage {

    XQueue<Booking> booking;

    XArrayList<MapAddress> map_add;
    XArrayList<User> users;
    XArrayList<Mapping> mapping;

    User temp, login_user;
    MapAddress _form, _to;
    Range _range;
    Mapping _mapping;
    Customer _customer;

    private boolean is_fetching;

    public DriverFetch(HttpServletRequest request, User user) {
        super(request);
        // read data
        map_add = (XArrayList<MapAddress>) AbstractEntity.readDataFormCsv(new MapAddress());
        map_add.sort("address_id", MapAddress.class);
        // read data
        users = (XArrayList<User>) AbstractEntity.readDataFormCsv(new Customer());
        users.sort("user_id", Customer.class);
        // read data
        mapping = (XArrayList<Mapping>) AbstractEntity.readDataFormCsv(new Mapping());
        mapping.sort("map_id", Mapping.class);

        login_user = user;
        booking = main.Datas.currentBooking.clone();

    }

    private void displayFetchList() {
        int count = 0;
        boolean display_row;
        while (!booking.isEmpty()) {
            // prevent 0 % 2
            display_row = (count + 1) % 2 == 0;
            if (display_row) {
                stringBuilder.append("<div class=\"row\">");
            }
            displayWidget(booking.dequeue());
            if (display_row) {
                stringBuilder.append("</div>");
            }
            count++;
        }
    }

    private void displayWidget(Booking b) {

        _mapping = mapping.binarySearchOnce("map_id", b.getMapping_id(), Mapping.class);
        _customer = (Customer) users.binarySearchOnce("user_id", b.getCustomer_id(), Customer.class);
        _form = map_add.binarySearchOnce("address_id", _mapping.getSource_id(), MapAddress.class);
        _to = map_add.binarySearchOnce("address_id", _mapping.getDestination_id(), MapAddress.class);

        //<div class="col-xs-12 col-sm-6 col-md-6">
        stringBuilder.append("<div class=\"col-xs-12 col-sm-6 col-md-6\">");

        //    <a href="#" class="deco-none"> <!--URL to fetch-->
        stringBuilder.append("<a href=\"");
        stringBuilder.append(WebConfig.WEB_URL);
        stringBuilder.append("start-fetch-now?uid=");
        stringBuilder.append(login_user.getId());
        stringBuilder.append("&id=");
        stringBuilder.append(b.getBooking_id());
        stringBuilder.append("\" class=\"deco-none\">");

        //        <div class="well well-sm">
        stringBuilder.append("<div class=\"well well-sm\">");

        //            <div class="row">
        stringBuilder.append("<div class=\"row\">");

        //                <div class="col-sm-6 col-md-4">
        stringBuilder.append("<div class=\"col-sm-6 col-md-4\">");

        //                    <img src="http://placehold.it/380x500" alt="" class="img-rounded" height="170" width="170"/>
        stringBuilder.append("<img src=\"");
        stringBuilder.append(Functions.getProfilePic_byid(_customer.getId()));
        stringBuilder.append("\" alt=\"\" class=\"img-rounded\" height=\"170\" width=\"170\"/>");

        //                </div>
        stringBuilder.append("</div>");

        //                <div class="col-sm-6 col-md-8">
        stringBuilder.append("<div class=\"col-sm-6 col-md-8\">");

        //                    <h4>Customer name</h4>
        stringBuilder.append("<h4>");
        stringBuilder.append(_customer.getName());
        stringBuilder.append("</h4>");

        //                    <small><i class="glyphicon glyphicon-map-marker map-marker-form"></i><cite title="San Francisco, USA">Form address</cite></small>
        stringBuilder.append("<small><i class=\"glyphicon glyphicon-map-marker map-marker-form\"></i><cite title=\"");
        stringBuilder.append(_form.get_display_address());
        stringBuilder.append("\">");
        stringBuilder.append(_form.getFull_address_hide_extra());
        stringBuilder.append("</cite></small>");

        //                    <small><i class="glyphicon glyphicon-map-marker map-marker-to"></i><cite title="San Francisco, USA">To address</cite></small>
        stringBuilder.append("<small><i class=\"glyphicon glyphicon-map-marker map-marker-to\"></i><cite title=\"");
        stringBuilder.append(_to.get_display_address());
        stringBuilder.append("\">");
        stringBuilder.append(_to.getFull_address_hide_extra());
        stringBuilder.append("</cite></small>");

        //                    <p>
        stringBuilder.append("<p>");

        //                        <i class="glyphicon glyphicon glyphicon-earphone"></i>phone number<br />
        stringBuilder.append("<i class=\"glyphicon glyphicon glyphicon-earphone\"></i>");
        stringBuilder.append(_customer.getPhoneNumber());
        stringBuilder.append("<br />");

        //                        <i class="glyphicon glyphicon-time"></i>June 02, 1988<br />
        stringBuilder.append("<i class=\"glyphicon glyphicon-time\"></i>");
        stringBuilder.append(main.WebConfig.CHAT_FORMAT_LONG.format(b.getBooking_date()));
        stringBuilder.append("<br />");

        //                        <i class="glyphicon glyphicon-yen"></i><span class="text-success"><b>RM 100.00 - 200.00</b></span>
        //                    </p>
        _range = _mapping.getPriceRange(b.getCar_type());
        stringBuilder.append("<i class=\"glyphicon glyphicon-yen\"></i><span class=\"text-success\"><b>RM ");
        stringBuilder.append(_range.isSame()
                ? String.format("%.2f", _range.getLower())
                : String.format("%.2f - %.2f", _range.getLower(), _range.getHigher()));
        stringBuilder.append("</b></span></p>");

        //                </div>
        //            </div>
        //        </div>
        //    </a>
        //</div>
        stringBuilder.append("</div></div></div></a></div>");
    }

    @Override
    public XArrayList<String> getHtmls() {
        XArrayList<String> x = new XArrayList<String>();
        x.add(getHtml());
        return x;
    }

    @Override
    public String getHtml() {
        displayFetchList();
        return stringBuilder.toString();
    }
}
