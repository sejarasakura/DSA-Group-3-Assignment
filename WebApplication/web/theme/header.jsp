<%-- 
    Document   : header
    Created on : Nov 7, 2020, 3:47:36 PM
    Author     : ITSUKA KOTORI
--%>
<%@page import="main.Datas" %>
<%@page import="main.WebConfig" %>
<%@page import="pages.Header" %>
<%@page import="adt.ArrList" %>
<%@page import="entity.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<% User u = main.Functions.getUserSession(request); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<nav class="navbar navbar-default" style="padding-top: 10px; padding-bottom: 10px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="" href="<%= getServletContext().getInitParameter("DomainName")%>">
                <img class="pull-left" src="<%= Datas.settings.getValue("image/logo")%>" alt="Rent Car" width="64"
                     height="50">
            </a>
            <a class="navbar-brand" href="<%= getServletContext().getInitParameter("DomainName")%>"
               style="padding-left: 30px">
                <%= Datas.settings.getValue("text/title")%>
            </a>
        </div>
        <ul class="nav navbar-nav">
            <%ArrList<String> lists = new Header(request).get_menu();%>
            <% for (String list : lists) {
                out.write(list);
            }%>
        </ul>

        <div class="navbar-header pull-right">
            <a class="" href="">
                <img src="<%= main.Functions.getProfileUrl(request)%>" alt="Users"
                     width="50"
                     height="50"
                     class="img-circle">
            </a>
        </div>
    </div>
</nav>
<%= main.Functions.displayErrorMessage(request)%>