<%-- 
    Document   : payment
    Created on : Dec 9, 2020, 7:58:15 AM
    Author     : ITSUKA KOTORI
--%>

<%@page import="entity.help.Range"%>
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
<%
    User user = main.Functions.getUserSession(request);
    if (!response.isCommitted()) {
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
    }
    String id = request.getParameter("id");
    XArrayList<PaymentMethodType> v = new XArrayList(PaymentMethodType.values());
    XArrayList list = AbstractEntity.readDataFormCsv(new Booking());
    Booking b = (Booking) list.binarySearchAndSort("booking_id", id, Booking.class).get(0);
    if (b == null) {
        return;
    }
    XArrayList list2 = AbstractEntity.readDataFormCsv(new Mapping());
    Mapping m = (Mapping) list.binarySearchAndSort("map_id", b.getMapping_id(), Mapping.class).get(0);
    if (m == null) {
        return;
    }
    XArrayList list3 = AbstractEntity.readDataFormCsv(new Payment());
    list.binarySearchAndSort("payment_id", b.getPaymentNumber(), Payment.class);

    Range r = m.getPriceRange(b.getCar_type());
%>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="payment"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="payment"/>
        </jsp:include>

        <div class="container">
            <h1 class="text-center">Confirm Payment</h1>
            <br>
            <div class="register"> 
                <form id="mainForm" method="post" action="<%= WebConfig.WEB_URL%>RegisterAction">
                    <img src="<%= main.Functions.getProfileUrl(request)%>" alt="Users" 
                         class="img-circle img-login center-block" width="80" height="80"> 
                    <sup>You can use letter, number and underscore</sup>

                    <div class="form-group row">
                        <div class="col-sm-6">
                            Payment Method Types : 
                        </div>
                        <div class="col-sm-6">
                            <select  class="form-control" id="pm" name="pm">
                                <%for (PaymentMethodType pmt : v) {%>
                                <option value="<%= pmt.getCode()%>"><%= pmt.getName()%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input input type="number" min="<%=r.getLower()%>" step="0.01" class="form-control" id="amount" name="amount" placeholder="Payment amount">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group row">
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-6">
                            <input type="submit" class="btn btn-lg btn-success pull-right" value="Pay Now">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>