<%-- 
    Document   : header
    Created on : Nov 7, 2020, 3:47:36 PM
    Author     : ITSUKA KOTORI
--%>
<%@page import="entityClass.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<nav class="navbar navbar-default" style="padding-top: 10px; padding-bottom: 10px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="" href="<%= getServletContext().getInitParameter("DomainName")%>">
                <img class="pull-left" src="../media/logo.png" alt="Rent Car" width="64" height="50">
            </a>
            <a class="navbar-brand" href="<%= getServletContext().getInitParameter("DomainName")%>" style="padding-left: 30px">Rentcars.com
            </a>
        </div>
        <ul class="nav navbar-nav">
            <%
                User user = null;
            %>

            <%--All user--%>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/Home.aspx">Home</a></li>

            <%
                if (user != null) {
                    String role = user.getRole();
                    if (!role.equals("A")) {
            %>

            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/Shop.aspx">Shop</a></li>

            <%
                }
            %>
            <%--autorise user--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">My Account<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class=""><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyAccount.aspx">My Account details</a></li>
                    <li class=""><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/ChangePassword.aspx?post=0">Change Password</a></li>
                    <li class=""><a href="#" runat="server" onserverclick="logout">Logout</a></li>
                </ul>
            </li>
            <%
                if (role.equals("C") || role.equals("B")) {
            %>
            <%-- Customer --%>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyGallery.aspx">My Gallery</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyCart.aspx">My Cart</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyOrder.aspx">My Order</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyWishList.aspx">My Wish List</a></li>
                <%
                } else if (role.equals("A") || role.equals("B")) {
                %>
                <%-- Artiest --%>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/AddArt.aspx">Add New Art</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/ArtWorkSales.aspx">My Artwork</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/MyGallery.aspx">My Gallery</a></li>
                <%
                    }
                } else {
                %>
                <%--unautorise user--%>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/Shop.aspx">Shop</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>%>/pages/Register.aspx">Register</a></li>
            <li class="art-list"><a href="<%= getServletContext().getInitParameter("DomainName")%>/pages/Login.aspx">Login</a></li>
                <%
                    }
                %>
        </ul>

        <div class="navbar-header pull-right">
            <a class="" href="">
                <img src="" alt=""
                     width="50"
                     height="50"
                     class="img-circle">
            </a>
        </div>
    </div>
</nav>