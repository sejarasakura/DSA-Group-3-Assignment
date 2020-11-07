<%-- 
    Document   : meta
    Created on : Nov 7, 2020, 3:47:26 PM
    Author     : ITSUKA KOTORI
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= "Rent Car" + (request.getParameter("title") == null?"":request.getParameter("title"))%></title>