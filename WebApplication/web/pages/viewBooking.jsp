<%-- 
    Document   : viewBooking
    Created on : Dec 2, 2020, 12:46:18 AM
    Author     : Lim Sai Keat
--%>

<%@page import="main.*"%>
<%@page import="cilent.*"%>
<%@page import="cilent.filter.*"%>
<%@page import="cilent.pages.*"%>
<%@page import="cilent.servlet.*"%>
<%@page import="entity.*"%>
<%@page import="adt.*"%>
<%@page import="adt.node.*"%>
<%@page import="adt.interfaces.*"%>
<%@page import="csv.converter.*"%>
<%@page import="xenum.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    // get user login session 
    User user = main.Functions.getUserSession(request);
    main.Functions.checkLogin(response, user);
    String id = request.getParameter("id");

    if (user == null) {
        return;
    }

    String id_match;
    String current_match;
    String user_match;
    String wating_syntax;
    String prefix;

    Booking booking = null;
    Mapping m = null;
    MapAddress _to = null, _form = null;
    User opposite_user;
    boolean found = false;

    current_match = user.isCustomer() ? "Booking" : "Fetch";
    user_match = user.isCustomer() ? "Customer" : "Driver";
    prefix = id == null ? "" : "Current ";
    wating_syntax = user.isCustomer() ? "Pendding driver to accept your booking" : "Fetching the customer of";
    opposite_user = user.isCustomer() ? new Driver() : new Customer();

    if (id == null ? true : id.isEmpty()) {
        if (main.Datas.currentBooking != null) {
            for (Booking b : main.Datas.currentBooking) {
                id_match = user.isCustomer() ? b.getCustomer_id() : b.getDriver_id();
                if (user.getUser_id().equals(id_match)) {
                    booking = b;
                    found = true;
                    break;
                }
            }
        }
    } else {
        XArrayList<Booking> ar = (XArrayList<Booking>) AbstractEntity.readDataFormCsv(new Booking());
        ar = ar.binarySearchAndSort("booking_id", id, Booking.class);
        found = !ar.isEmpty();

        id_match = user.isCustomer() ? ar.get(0).getCustomer_id() : ar.get(0).getDriver_id();
        if (user.getUser_id().equals(id_match)) {
            booking = ar.get(0);
            found = true;
        }
    }

    if (!found || booking == null) {
        if (user.isCustomer()) {
            response.sendRedirect(WebConfig.WEB_URL + "pages/customerBooking.jsp");
        } else {
            response.sendRedirect(WebConfig.WEB_URL + "pages/driverFetch.jsp");
        }
        return;
    }

    XArrayList<User> ar_user = (XArrayList<User>) AbstractEntity.readDataFormCsv(opposite_user);
    ar_user = ar_user.binarySearchAndSort("user_id", user.isCustomer() ? booking.getDriver_id() : booking.getCustomer_id(), opposite_user.getClass());
    if (ar_user.isEmpty()) {
    } else {
        opposite_user = ar_user.get(0);
    }

    XArrayList<MapAddress> map_add = (XArrayList<MapAddress>) AbstractEntity.readDataFormCsv(new MapAddress());
    map_add.sort("address_id", MapAddress.class);
    XArrayList<Mapping> mapping = (XArrayList<Mapping>) AbstractEntity.readDataFormCsv(new Mapping());
    mapping.sort("map_id", Mapping.class);

    m = mapping.binarySearchOnce("map_id", booking.getMapping_id(), Mapping.class);
    _form = map_add.binarySearchOnce("address_id", m.getSource_id(), MapAddress.class);
    _to = map_add.binarySearchOnce("address_id", m.getDestination_id(), MapAddress.class);
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="Current <%=current_match%>"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="Current <%=current_match%>"/>
        </jsp:include>
        <br>
        <br>
        <div class="container">
            <h1 class="text-center text-success"><%=current_match%> Details</h1>
            <p class="text-center text-secondary"><%= wating_syntax%></p>
            <br>
            <div id="message">

            </div>
            <div class="panel panel-success">
                <div class="panel-heading"><%=current_match%></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-4 col-md-3">
                            <img id="ouser_profile" src="<%= Functions.getProfilePic_byid(opposite_user.getId())%>" alt="" class="img-rounded" height="170" width="170"/>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <h4 id="0user_name"><%= opposite_user.getName() == null ? "Waiting Accept" : opposite_user.getName()%></h4>
                            <small><i class="glyphicon glyphicon-map-marker map-marker-form"></i>
                                <cite title="<%= _form.get_display_address()%>"><%= _form.getFull_address_hide_extra()%></cite>
                            </small>
                            <small><i class="glyphicon glyphicon-map-marker map-marker-to"></i>
                                <cite title="<%= _to.get_display_address()%>"><%= _to.getFull_address_hide_extra()%></cite>
                            </small>
                            <p>
                                <i id="ouser_phone" class="glyphicon glyphicon glyphicon-earphone"></i><%= opposite_user.getPhoneNumber() == null ? "-" : opposite_user.getPhoneNumber()%><br />
                                <i class="glyphicon glyphicon-time"></i><%= booking.getBooking_date()%><br />
                                <i id="booking_status"class="glyphicon glyphicon-asterisk text-success"></i>
                                <%= booking.getBookingStatus().getName()%>
                                <br />
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- For this part driver view is constant-->
            <%if (user.isCustomer()) {%>
            <input is="reload" type='button' value='Reload' class="btn btn-success"/>
            <%}%>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
        $(document).ready(function () {
            $('#reload').on('click', function () {
                var Status = $(this).val();
                $.ajax({
                    type = 'POST',
                    url: '<%= WebConfig.WEB_URL + "reload_fetch"%>',
                    data: {
                        "bid": "<%= booking.getId()%>",
                        "uid": "<%= opposite_user.getId()%>",
                        "oid": "<%= user.getId()%>"
                    },
                    function(response) {
                        var result = $.parseJSON(response);
                        if (result['status'] == "OK") {
                            $('#ouser_phone').html(result['ouser_phone']);
                            $('#ouser_name').html(result['ouser_name']);
                            $('#booking_status').html(result['booking_status']);
                            $('#ouser_profile').html(result['ouser_profile']);
                        } else {
                            console.log("No update");
                        }
                    }
                });
            });
        });
    </script>
</html>