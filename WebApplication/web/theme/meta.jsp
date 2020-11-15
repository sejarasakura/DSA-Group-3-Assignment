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
<title><%= main.Functions.getWebpageTitle(request, "title")%></title>

<%-- import css lib--%>
<link href="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/datatable/datatables.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<%-- CSS --%>
<link href="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/Master.css" rel="stylesheet" />

<%-- import javascript lib--%>
<script src="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/jquery/jquery-3.5.1.min.js"></script>
<script src="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/datatable/datatables.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

