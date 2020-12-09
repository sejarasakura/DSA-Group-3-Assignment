<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="main.WebConfig"%>
<%@page import="adt.*"%>
<%@page import="adt.interfaces.*"%>
<%@page import="entity.*"%>
<%@page import="xenum.*"%>
<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<%
    // get user login session 
    response.encodeURL("/store/catalog");
    User user = main.Functions.getUserSession(request);
    if (!response.isCommitted()) {
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
    }

    // get the select option list
    StringBuilder sb_role = new StringBuilder();
    String edit = request.getParameter("edit");

    for (MemberShip mb : MemberShip.values()) {
        sb_role.append("<option value='").
                append(mb.getDatabaseCode()).append("' >").
                append(mb.getName()).append(" - ").append(mb.getDiscount()).append("</option>");
    }
%>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>
        <style>
            .img-control{height:200px;width:200px;margin-top:-60px; margin-bottom:20px;}
            .border-dark{border:5px solid #333;}
            .change-img{opacity: 0.84;position: absolute; color: #666; margin: 8px;border-radius: 50%;height: 50px; width: 50px;outline: none!important;}
            .change-img:hover{color: #666;opacity: 0.99;}
            .fa-camera{opacity: 1.0!important;}
            .change-img:focus, .change-img:focused{outline: none;}
        </style>
        <div class="jumbotron text-center">
            <h1><%= user.getName()%></h1>
        </div>

        <div class="container">
            <center>
                <div class="img-control">
                    <button type="button" class="btn btn-default change-img" data-toggle="modal" data-target="#pic_upload">
                        <span class="glyphicon glyphicon-camera" style="color:#222;font-size:24px;margin-top:5px;line-height: 24px;"></span>
                    </button>
                    <jsp:include page='../widget/pic_upload_modal.jsp'/>
                    <img src="<%= main.Functions.getProfileUrl(request)%>" class="img-circle border-dark" alt="Cinque Terre" height="100%" width="100%" >
                </div>
            </center>
            <div class="well well-lg">
                <br>
                <form action="<%= WebConfig.WEB_URL%>updateUser" method="post">
                    <div class="row">
                        <div class="col-sm-6">
                            <h4><b>Personal Information</b></h4>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Registration name
                                </div>
                                <div class="col-sm-6">
                                    <input id="username" type="text" class="form-control" name="username" 
                                           placeholder="Username" value="<%= user.getUsername()%>" disabled>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Public display name
                                </div>
                                <div class="col-sm-6">
                                    <input id="name" type="text" class="form-control" 
                                           value="<%= user.getName()%>" name="name" placeholder="Display Name">
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Identity card number
                                </div>
                                <div class="col-sm-6">
                                    <input id="ic" type="text" class="form-control" name="ic" placeholder="IC Number format eg. xxxxxx-xx-xxxx" 
                                           <%= user.getIc().length() > 12 ? "disabled value=\"" + user.getIc() + "\"" : ""%>>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Current email Address
                                </div>
                                <div class="col-sm-6">
                                    <input id="email" type="email" value="<%= user.getEmail()%>" class="form-control" name="email" placeholder="Email">
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Mobile phone-number
                                </div>
                                <div class="col-sm-6">
                                    <input id="phonenumber" type="text" value="<%= user.getPhoneNumber()%>" class="form-control" name="phonenumber" 
                                           placeholder="Phone number eg +60 18 392 7135">
                                </div>
                            </div>
                        </li>
                        <%if (user.isCustomer()) {%>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Selected membership plan
                                </div>
                                <div class="col-sm-6">
                                    <Select class="form-control" name="role" value="<%= ((Customer) user).getMemberType().getDatabaseCode()%>" >
                                        <%=sb_role.toString()%>
                                    </Select>
                                </div>
                            </div>
                        </li>
                        <%}%>
                        <%if (user.isDriver()) {%>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    Driving license
                                </div>
                                <div class="col-sm-6">
                                    <input id="license" type="text" class="form-control" name="license" placeholder="License eg XXXXXXXXXX"
                                           <%= ((Driver) user).getDriver_license().length() > 9 ? "disabled value=\"" + ((Driver) user).getDriver_license() + "\"" : ""%>>
                                </div>
                            </div>
                        </li>
                        <%}%>
                        <div class="row">
                        <div class="col-sm-12">
                            <input type="submit" class="btn btn-success pull-right" style="margin-top: 10px;margin-bottom: 10px" value="Update" />
                        </div>
                        </div>
                    </ul>
                </form>
                <br>
                <%if (edit == null ? false : edit.equals("car") && user.isDriver()) {%>
                <jsp:include page='../widget/my_cars.jsp'>
                    <jsp:param name='id' value="${param.id}"/>
                    <jsp:param name='edit' value="${param.edit}"/>
                </jsp:include>
                <%} else {%>
                <jsp:include page='../widget/my_cars.jsp'/>
                <%}%>
                <jsp:include page='../widget/change_password.jsp'/>

            </div>
        </div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
