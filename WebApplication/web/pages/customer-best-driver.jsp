<%-- 
    Document   : customer-best-driver
    Created on : Dec 5, 2020, 11:53:36 PM
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
            <jsp:param name="title" value="customer-best-driver"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="customer-best-driver"/>
        </jsp:include>

        <div id="haha">
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
            ggggg<br>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>

        var position = $(window).scrollTop();

// should start at 0

        $(document).ready(function () {
            $(document).scroll(function () {
                //$('#haha').prepend('<div id="GG">Prepend haha<br></div>');
                $('#haha').append('<div id="GG">Prepend haha<br></div>');
                $('#haha').append('<div id="GG">Prepend haha<br></div>');
                $('#haha').append('<div id="GG">Prepend haha<br></div>');
            });
        });
    </script>
</html>