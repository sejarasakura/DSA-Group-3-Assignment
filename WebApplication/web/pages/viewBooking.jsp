<%-- 
    Document   : viewBooking
    Created on : Dec 2, 2020, 12:46:18 AM
    Author     : ITSUKA KOTORI
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
    response.encodeURL("/store/catalog");
    User user = main.Functions.getUserSession(request);
    main.Functions.checkLogin(response, user);

    if (user == null) {
        return;
    }

    String id_match;
    String current_match;
    String user_match;
    String wating_syntax;
    
    Booking booking;
    User opposite_user;
    boolean found = false;

    current_match = user.isCustomer() ? "Booking" : "Fetch";
    user_match = user.isCustomer() ? "Customer" : "Driver";
    wating_syntax = user.isCustomer() ? "Pendding driver to accept your booking" : "Fetching the customer of";
    opposite_user = user.isCustomer() ? new Driver() : new Customer();
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
    
    if(!found){
        if (user.isCustomer()) {
            response.sendRedirect(WebConfig.WEB_URL + "pages/customerBooking.jsp");
        } else {
            response.sendRedirect(WebConfig.WEB_URL + "pages/driverFetch.jsp");
        }
        return;
    }

    XArrayList ar = AbstractEntity.readDataFormCsv(opposite_user);

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
            <div class="panel panel-success">
                <div class="panel-heading">Current <%=current_match%></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-4 col-md-3">
                            <img src="http://placehold.it/380x500" alt="" class="img-rounded" height="170" width="170"/>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <h4>
                                Customer name</h4>
                            <small><i class="glyphicon glyphicon-map-marker map-marker-form"></i><cite title="San Francisco, USA">Form address</cite></small>
                            <small><i class="glyphicon glyphicon-map-marker map-marker-to"></i><cite title="San Francisco, USA">To address</cite></small>
                            <p>
                                <i class="glyphicon glyphicon glyphicon-earphone"></i>phone number<br />
                                <i class="glyphicon glyphicon-time"></i>June 02, 1988<br />
                                <i class="glyphicon glyphicon-yen"></i><span class="text-success"><b>RM 100.00 - 200.00</b></span></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>