<%-- 
    Document   : search_function
    Created on : Dec 2, 2020, 10:10:18 PM
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
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="search_function"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="search_function"/>
        </jsp:include>

        <%
            ArrList list = new ArrList(AbstractEntity.readDataFormCsv(new Customer()));
            InterList list2 = list.searchByField("memberType", MemberShip.NORMAL, Customer.class);
            XArraySortList slist = new XArraySortList(list);
            slist.sort("phoneNumber", Customer.class);
        %>
        <%= list2.toHtml()%>
        <hr>
        <%= slist.toHtml()%>
        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>