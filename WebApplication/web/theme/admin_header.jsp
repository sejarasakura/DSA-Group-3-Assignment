<%-- 
    Document   : admin_header
    Created on : Nov 11, 2020, 5:27:01 PM
    Author     : Lim Sai Keat
--%>

<%@page import="cilent.pages.AdminHeader" %>
<%@page import="adt.XArrayList" %>
<%@page import="main.Datas" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:200px">
    <center style="margin: 20px;">
        <a class="" href="<%= getServletContext().getInitParameter("DomainName")%>">
            <img class="" src="<%= Datas.settings.getValue("image/logo")%>" alt="Rent Car" width="64" height="50">
        </a>
    </center>
    <%
        Datas.admin_header = new AdminHeader(request).getHtmls();
        for (String list : Datas.admin_header) {
            out.write(list);
        }
    %>
</div>

<!-- Page Content -->
<div style="margin-left:200px">
    <nav class="navbar navbar-default">
        <div class="w3-container ">
            <h1><%= main.Functions.getWebpageTitle(request, "menu_bar")%>
            </h1>
        </div>
    </nav>


    <div class="w3-container">

        <%= main.Functions.displayErrorMessage(request)%>