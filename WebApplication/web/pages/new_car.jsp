 <%-- 
    Document   : new_car
    Created on : Dec 5, 2020, 6:54:07 PM
    Author     : Lim Sai Keat
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
    String type = request.getParameter("type"), car = "car";
%>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="new_car"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="new_car"/>
        </jsp:include>
        <div class="container">
            <jsp:include page="../widget/my_cars_add.jsp">
                <jsp:param name="callback" value="pages/new_car.jsp"/>
                <jsp:param name="type" value="<%=type==null?car:type%>"/>
            </jsp:include>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>