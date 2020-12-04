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
    StringBuilder sb_
    InterList<Car> cars = null;
    InterList<Plate> tempPlate = null;
    XTreeDictionary<String, Plate> plates = null;
    XHashedDictionary<String, Boolean> edit_plates = new XHashedDictionary<>();

    // get edit
    String edit = request.getParameter("edit");
    String id = request.getParameter("id");
    boolean got_edit = edit == null ? false : edit.equals("car"), local_edit;

    // Driver special
    if (!user.isDriver()) {
        return;
    }

    XArrayList<Car> all_car = new XArrayList(AbstractEntity.readDataFormCsv(new Car()));
    XArrayList all_taxi = new XArrayList(AbstractEntity.readDataFormCsv(new Taxi()));
    all_car.addAll(all_taxi);
    cars = all_car.searchByField("driver_id", user.getUser_id(), Car.class);
    XArrayList<Plate> all_plate = new XArrayList(AbstractEntity.readDataFormCsv(new Plate()));
    plates = new XTreeDictionary();
    for (Car car : cars) {
        tempPlate = null;
        tempPlate = all_plate.searchByField("plate_id", car.getPlate_id(), Plate.class);
        if (tempPlate == null ? false : !tempPlate.isEmpty()) {
            plates.add(car.getPlate_id(), tempPlate.get(0));
            if (got_edit && car.getPlate_id().equals(id)) {
                edit_plates.add(car.getPlate_id(), true);
            }
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
    <%for (int i = 0; i < cars.size(); i++) {%>
    <%local_edit = got_edit && (edit_plates.getValue(cars.get(i).getPlate_id()) != null);%>
    <li class="list-group-item" id="<%= cars.get(i).getPlate_id()%>">
        <div class="panel panel-default">
            <%if (!local_edit) {%>
            <div class="panel-heading">
                <div class="panel-heading">
                    <span class="pull-right"><%= cars.get(i).isTaxi() ? ((Taxi) cars.get(i)).getTaxiCompany() : ""%></span>
                    <i><%= plates.getValue(cars.get(i).getPlate_id()).getPlateAlpha()%></i> 
                    <%= plates.getValue(cars.get(i).getPlate_id()).getPlateNumber()%>
                </div>
            </div>
            <div class="panel-body">
                <blockquote style="border-color: #00000000">
                    <%if (cars.get(i).isTaxi()) {%>
                    <div class="row">
                        <div class="col-sm-6">Registered taxi identity</div>
                        <div class="col-sm-6"><%= ((Taxi) cars.get(i)).getTaxiId()%></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Official taxi license</div>
                        <div class="col-sm-6"><%= ((Taxi) cars.get(i)).getTaxiLicense()%></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Industry and company</div>
                        <div class="col-sm-6"><%= ((Taxi) cars.get(i)).getTaxiCompany()%></div>
                    </div>
                    <%}%>      
                    <div class="row">
                        <div class="col-sm-6">License of plates</div>
                        <div class="col-sm-6"><%= cars.get(i).getLicense()%></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Transport registered date</div>
                        <div class="col-sm-6"><%= WebConfig.LOCAL_DATETIME_FORMAT.format(cars.get(i).getRegDate()).replace("T", " ")%></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Service type provide</div>
                        <div class="col-sm-6"><%= cars.get(i).getCarType().getName()%></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Car plate</div>
                        <div class="col-sm-6">
                            <i><%= plates.getValue(cars.get(i).getPlate_id()).getPlateAlpha()%></i> 
                            <%= plates.getValue(cars.get(i).getPlate_id()).getPlateNumber()%>
                        </div>
                    </div>
                </blockquote>
            </div>
            <div class="panel-footer">
                <a class="btn btn-warning" href="account.jsp?edit=car&id=<%= cars.get(i).getPlate_id()%>">Edit</a>
            </div>
            <%} else {%>
            <div class="panel-heading">
                <div class="panel-heading">
                    <span class="pull-right"><%= cars.get(i).isTaxi() ? ((Taxi) cars.get(i)).getTaxiCompany() : ""%></span>
                    <i><%= plates.getValue(cars.get(i).getPlate_id()).getPlateAlpha()%></i> 
                    <%= plates.getValue(cars.get(i).getPlate_id()).getPlateNumber()%>
                </div>
            </div>
            <div class="panel-body">
                <blockquote style="border-color: #00000000">
                    <%if (cars.get(i).isTaxi()) {%>
                    <div class="row">
                        <div class="col-sm-6">Registered taxi identity</div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="taxi_id" name="taxi_id" 
                                   value="<%= ((Taxi) cars.get(i)).getTaxiId()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Official taxi license</div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="taxi_license" name="taxi_license" 
                                   value="<%= ((Taxi) cars.get(i)).getTaxiLicense()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Industry and company</div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="taxi_company" name="taxi_company" 
                                   value="<%= ((Taxi) cars.get(i)).getTaxiCompany()%>">
                        </div>
                    </div>
                    <%}%>      
                    <div class="row">
                        <div class="col-sm-6">License of plates</div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="plate_lic" name="plate_lic" 
                                   value="<%= cars.get(i).getLicense()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Transport registered date</div>
                        <div class="col-sm-6">
                            <input type="datetime-local" class="form-control" id="reg_date" name="reg_date"
                                   value="<%= WebConfig.LOCAL_DATETIME_FORMAT.format(cars.get(i).getRegDate())%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Service type provide</div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="plate_lic" name="plate_lic" 
                                   value="<%= cars.get(i).getCarType().getName()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">Car plate</div>
                        <div class="col-sm-6">
                            <i><%= plates.getValue(cars.get(i).getPlate_id()).getPlateAlpha()%></i> 
                            <%= plates.getValue(cars.get(i).getPlate_id()).getPlateNumber()%>
                        </div>
                    </div>
                </blockquote>
            </div>
            <div class="panel-footer">
                <a class="btn btn-warning" href="account.jsp?edit=car&id=<%= cars.get(i).getPlate_id()%>#<%= cars.get(i).getPlate_id()%>">Edit</a>
            </div>
            <%}%>
        </div>
    </li>
    <%}%>
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
