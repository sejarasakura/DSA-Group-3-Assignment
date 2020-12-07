<%-- 
    Document   : driverFetch
    Created on : Dec 5, 2020, 9:20:10 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="adt.XArrayList"%>
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
<%
    User user = main.Functions.getUserSession(request);
    if (!response.isCommitted()) {
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
    }
    if (!user.isDriver()) {
        response.sendRedirect(WebConfig.WEB_URL + "pages/index.jsp?I=I-0021");
        return;
    }
    DriverFetch df = new DriverFetch(request, user);
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="Booking Request"/>
        </jsp:include>
        <style>
        </style>
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="Booking Request"/>
        </jsp:include>

        <div class="jumbotron light-bg-success">
            <h1 class="text-center">Booking Request</h1>
        </div>

        <div class="container">
            <%= df.getHtml()%>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>