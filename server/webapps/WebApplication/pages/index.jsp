<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="main.Datas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>
        
        
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
