<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

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
    main.Functions.checkLogin(response, user);

    // get the select option list
    StringBuilder sb_role = new StringBuilder();
    StringBuilder car_tpye = new StringBuilder();
    for (MemberShip mb : MemberShip.values()) {
        sb_role.append("<option " + mb.getDatabaseCode() + " >" + mb.getName() + "</option>");
    }

    InterList<Car> cars = null;
    XTreeDictionary<String,Plate> plates = null;
    InterList<Plate> tempPlate = null;
    // Driver special
    if (user.isDriver()) {
        XArrayList<Car> all_car = new XArrayList(AbstractEntity.readDataFormCsv(new Car()));
        cars = all_car.searchByField("driver_id", user.getUser_id(), Car.class);
        XArrayList<Plate> all_plate = new XArrayList(AbstractEntity.readDataFormCsv(new Plate()));
        plates = new XTreeDictionary();
        for (Car car : cars) {
            tempPlate = null;
            tempPlate = all_plate.searchByField("plate_id", car.getPlate_id(), Plate.class);
            if (tempPlate == null ? false : !tempPlate.isEmpty()) {
                plates.add(car.getPlate_id(), tempPlate.get(0));
            }
        }
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
            <h1><%= user.getName()%></h1>
        </div>

        <div class="container">
            <center>
                <div class="img-control">
                    <img src="<%= main.Functions.getProfileUrl(request)%>" class="img-circle border-dark" alt="Cinque Terre" height="100%" width="100%" >
                </div>
            </center>
            <div class="well well-sm">
                <h3><b>Personal Information</b></h3>
                <br>
                <form>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Username
                        </div>
                        <div class="col-sm-6">
                            <input id="username" type="text" class="form-control" name="username" 
                                   placeholder="Username" value="<%= user.getUsername()%>" disabled>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Display name
                        </div>
                        <div class="col-sm-6">
                            <input id="name" type="text" class="form-control" value="<%= user.getName()%>" name="name" placeholder="Display Name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Identity Card Number
                        </div>
                        <div class="col-sm-6">
                            <input id="ic" type="text" class="form-control" name="ic" placeholder="IC Number format eg. xxxxxx-xx-xxxx" 
                                   <%= user.getIc().length() > 12 ? "disabled" : "value=\"" + user.getIc() + "\""%>>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Email
                        </div>
                        <div class="col-sm-6">
                            <input id="email" type="email" value="<%= user.getEmail()%>" class="form-control" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Phone Number
                        </div>
                        <div class="col-sm-6">
                            <input id="phonenumber" type="text" value="<%= user.getPhoneNumber()%>" class="form-control" name="phonenumber" placeholder="Phone number">
                        </div>
                    </div>
                    <%if (user.isCustomer()) {%>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Member type
                        </div>
                        <div class="col-sm-6">
                            <Select class="form-control" name="role" value="<%= ((Customer) user).getRole()%>" >
                                <%=sb_role.toString()%>
                            </Select>
                        </div>
                    </div>
                    <%}%>
                    <%if (user.isDriver()) {%>
                    <!--                    <div class="form-group row">
                                            <div class="col-sm-6">
                                                Car type
                                            </div>
                                            <div class="col-sm-6">
                                                <Select class="form-control" name="car_type" value="" >
                                                </Select>
                                            </div>
                                        </div>-->
                    <div class="form-group row">
                        <div class="col-sm-6">
                            Driver License
                        </div>
                        <div class="col-sm-6">
                            <input id="license" type="text" class="form-control" name="license" placeholder="License"
                                   <%= ((Driver) user).getDriver_license().length() > 12 ? "disabled" : "value=\"" + ((Driver) user).getDriver_license() + "\""%>>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            My Cars
                        </div>
                        <div class="col-sm-6">
                            <input type='button' class="form-control" value="add other car"/>
                        </div>
                    </div>
                    <%}%>
                </form>
            </div>
        </div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
