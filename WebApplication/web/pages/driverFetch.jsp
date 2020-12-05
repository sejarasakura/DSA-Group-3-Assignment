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
    XArrayList arr_list = AbstractEntity.readDataFormCsv(new Booking());
    response.encodeURL("/store/catalog");
    User user = main.Functions.getUserSession(request);
    if (!response.isCommitted()) {
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="driverFetch"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="driverFetch"/>
        </jsp:include>

        <div class="container">
            <%for(int i = 0; i < 1;i++){%>
            <%%><div class="row"><%%>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <img src="http://placehold.it/380x500" alt="" class="img-rounded" height="170" width="170"/>
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <h4>
                                    Customer name</h4>
                                <small><i class="glyphicon glyphicon-map-marker map-marker-form"></i><cite title="San Francisco, USA">Form address</cite></small>
                                <small><i class="glyphicon glyphicon-map-marker map-marker-to"></i><cite title="San Francisco, USA">To address</cite></small>
                                <p>
                                    <i class="glyphicon glyphicon glyphicon-earphone"></i>phone number
                                    <br />
                                    <i class="glyphicon glyphicon-time"></i>June 02, 1988</p>
                                <button type="button" class="btn btn-success pull-right">Fetch Now</button>
                            </div>
                        </div>
                    </div>
                </div>
            <%%></div><%%>
            <%}%>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>