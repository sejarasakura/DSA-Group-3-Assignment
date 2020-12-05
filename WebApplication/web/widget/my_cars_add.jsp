<%-- 
    Document   : my_cars
    Created on : Dec 4, 2020, 5:29:33 AM
    Author     : ITSUKA KOTORI
--%>

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
<%
    // get user login session 
    User user = main.Functions.getUserSession(request);
    main.Functions.checkLogin(response, user);

    // List of cars, and plate
    StringBuilder sb_cartype = new StringBuilder();

    // get edit
    String id = (String) IDManager.generateId(new Car());

    // Driver special
    if (!user.isDriver()) {
        return;
    }

    String bool_data = request.getParameter("type");
    String callback = request.getParameter("callback");
    boolean is_taxi = bool_data == null ? false : bool_data.equals("taxi");
    callback = callback == null ? "widget/my_cars_add.jsp" : callback;
%>

<div class="row">
    <div class="col-sm-6">
        <h4><b>My Cars</b></h4>
    </div>
    <div class="col-sm-6">
    </div>
</div>
<ul class="list-group">
    <li class="list-group-item" id="<%= id%>">
        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-heading">

                </div>
            </div>
            <form method="post" action="/WebApplication/updateCar?id=<%= id%>&action=update" >
                <div class="panel-body">
                    <blockquote style="border-color: #00000000">
                        <%if (is_taxi) {%>
                        <div class="row">
                            <div class="col-sm-6">Registered taxi identity</div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="taxi_id" name="taxi_id" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">Official taxi license</div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="taxi_license" name="taxi_license"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">Industry and company</div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="taxi_company" name="taxi_company"  >
                            </div>
                        </div>
                        <%}%>      
                        <div class="row">
                            <div class="col-sm-6">License of plates</div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="plate_lic" name="plate_lic"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">Transport registered date</div>
                            <div class="col-sm-6">
                                <input type="datetime-local" class="form-control" id="reg_date" name="reg_date" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">Service type provide</div>
                            <div class="col-sm-6">
                                <select class="form-control" id="cartype" name="cartype"  >
                                    <%= sb_cartype.toString()%>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="plate_alpha" name="plate_alpha" placeholder="Plate alpha: eg. WMB">
                            </div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="plate_num" name="plate_num" placeholder="Plate mumber: eg. 2232">
                            </div>
                        </div>
                    </blockquote>
                </div>
                <div class="panel-footer">
                    <input type="submit" class="btn btn-warning" value="Update" />
                    <%if (is_taxi) {%>
                    <a class="btn btn-defualt">Register as taxi</a>
                    <%} else {%>
                    <%}%>
                </div>
                <%}%>
            </form>
        </div>
    </li>
    <li class="list-group-item">
        <div class="row">
            <div class="col-sm-6">
                Add a new Taxi or Car to your current account.
            </div>
            <div class="col-sm-6">
                <a href="" class="btn btn-success pull-right">Add now</a>
            </div>
        </div>
    </li>
</ul>
