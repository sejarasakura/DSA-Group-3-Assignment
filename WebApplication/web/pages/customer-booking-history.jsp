<%-- 
    Document   : customer-booking-history
    Created on : Dec 5, 2020, 11:31:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="main.*"%>
<%@page import="cilent.*"%>
<%@page import="cilent.filter.*"%>
<%@page import="cilent.pages.*"%>
<%@page import="cilent.servlet.*"%>
<%@page import="entity.*"%>
<%@page import="adt.node.*"%>
<%@page import="adt.interfaces.*"%>
<%@page import="csv.converter.*"%>
<%@page import="xenum.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
    // get user login session 
    User user = main.Functions.getUserSession(request);
    main.Functions.checkLogin(response, user);

    if (user == null) {
        return;
    }
    BookingHistory bh = new BookingHistory(request, user);
%>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="customer-booking-history"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="customer-booking-history"/>
        </jsp:include>


        <div class="container">
            <div class="container mt-5 mb-5">
                <div class="row">
                    <div class="col-md-8 offset-md-3">
                        <h4>Booking History</h4>
                        <ul class="timeline">
                            <%= bh.getHtml()%>
                        </ul>
                    </div>
                </div>
            </div>


        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>