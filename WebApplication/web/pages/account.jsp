<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="xenum.*"%>
<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<%
    // get user login session 
    response.encodeURL("/store/catalog");
    User user = main.Functions.getUserSession(request);
    main.Functions.checkLogin(response, user);

    // get the select option list
    StringBuilder sb_role;
    StringBuilder car_tpye;
    for (MemberShip mb : MemberShip.values()) {
        
    }
    
    for (CarType ct : CarType.values()) {
        
    }
%>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>

        <style>
            .img-control{height:200px;width:200px;margin-top:-60px; margin-bottom:20px;}
            .border-dark{border:5px solid #333;}
        </style>
        <div class="jumbotron text-center">
            <h1>Lim sai keat</h1>
        </div>

        <div class="container">
            <center>
                <div class="img-control">
                    <img src="<%= main.Functions.getProfileUrl(request)%>" class="img-circle border-dark" alt="Cinque Terre" height="100%" width="100%" >
                </div>
            </center>
            <div class="well well-sm">
                <h5><b>Personal Information</b></h5>
                <form>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Username
                        </div>
                        <div class="col-sm-6">
                            <input id="username" type="text" class="form-control" name="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Display name
                        </div>
                        <div class="col-sm-6">
                            <input id="name" type="text" class="form-control" name="name" placeholder="Display Name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Identity Card Number
                        </div>
                        <div class="col-sm-6">
                            <input id="ic" type="text" class="form-control" name="ic" placeholder="IC Number">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Email
                        </div>
                        <div class="col-sm-6">
                            <input id="email" type="email" class="form-control" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Phone Number
                        </div>
                        <div class="col-sm-6">
                            <input id="phonenumber" type="text" class="form-control" name="phonenumber" placeholder="Phone number">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Member type/role
                        </div>
                        <div class="col-sm-6">
                            <Select class="form-control" name="role">
                                <option></option>
                            </Select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Car type
                        </div>
                        <div class="col-sm-6">
                            <Select class="form-control" name="car_type">
                                <option></option>
                            </Select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Driver License
                        </div>
                        <div class="col-sm-6">
                            <input id="license" type="text" class="form-control" name="license" placeholder="License">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
