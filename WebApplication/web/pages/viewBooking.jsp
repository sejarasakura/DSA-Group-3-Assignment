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
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="View Booking"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="View Booking"/>
        </jsp:include>
        <br>
        <br>
        <div class="container">
            
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>