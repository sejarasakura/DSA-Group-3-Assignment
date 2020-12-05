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
    // List of cars, and plate
    StringBuilder sb_cartype = new StringBuilder();

    // get edit
    String id = (String) IDManager.generateId(new Car());

    String bool_data = request.getParameter("type");
    String _callback = request.getParameter("callback");
    boolean is_taxi = bool_data == null ? false : bool_data.equals("taxi");
    _callback = _callback == null
            ? WebConfig.WEB_URL + "widget/my_cars_add.jsp"
            : WebConfig.WEB_URL + _callback;

    for (CarType mb : CarType.values()) {
        if (is_taxi ? mb.isTaxi() : !mb.isTaxi()) {
            sb_cartype.append("<option value='").append(mb.getCode()).append("' >").append(mb.getName()).append("</option>");
        }
    }
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
                <h2><%= is_taxi ? "Add your taxi now" : "Add your car now"%></h2>
            </div>
            <form method="post" action="/WebApplication/updateCar?callback=<%=_callback%>&action=add" >
                <div class="panel-body">
                    <p>
                    <%if (is_taxi) {%>
                    You are not a taxi driver ? <a href="<%= _callback%>?type=car" class="">Register as car</a>
                    <%} else {%>
                    Do you is a taxi driver ? <a href="<%= _callback%>?type=taxi" class="">Register as taxi</a>
                    <%}%>
                    </p>
                    <ul class="list-group">
                        <%if (is_taxi) {%>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">Registered taxi identity</div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="taxi_id" name="taxi_id" >
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">Official taxi license</div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="taxi_license" name="taxi_license"  >
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">Industry and company</div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="taxi_company" name="taxi_company"  >
                                </div>
                            </div>
                        </li>
                        <%}%>      
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">License of plates</div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="plate_lic" name="plate_lic"  >
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">Service type provide</div>
                                <div class="col-sm-6">
                                    <select class="form-control" id="cartype" name="cartype"  >
                                        <%= sb_cartype.toString()%>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="plate_alpha" name="plate_alpha" placeholder="Plate alpha: eg. WMB">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="plate_num" name="plate_num" placeholder="Plate mumber: eg. 2232">
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="panel-footer">
                    <input type="submit" class="btn btn-success" value="Add ${param.type}" />
                </div>
            </form>
        </div>
    </li>
</ul>
