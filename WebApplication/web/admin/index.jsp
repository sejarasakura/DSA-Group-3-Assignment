<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="java.util.*" %>
<%@page import="adt.ArrList" %>
<%@page import="com.google.gson.Gson" %>
<%@page import="pages.AdminCreateGraph" %>
<%@page import="cilent.Graph_allocation" %>
<%@page import="org.apache.commons.text.StringEscapeUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%
    AdminCreateGraph acg = new AdminCreateGraph();
    String dataPoints = acg.getSampleData();
    String dataPoints2 = acg.getSampleData2();
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        window.onload = function () {

            var chart = new CanvasJS.Chart("chartContainer", {
                theme: "light1", // "light2", "dark1", "dark2"
                animationEnabled: true,
                zoomEnabled: true,
                title: {
                    text: "User trafic"
                },
                data: [{
                    type: "line",
                    dataPoints: <%out.print(dataPoints);%>
                }]
            });
            chart.render();


            var chart2 = new CanvasJS.Chart("chartContainer2", {
                animationEnabled: true,
                exportEnabled: true,
                title: {
                    text: "Sales"
                },
                axisY: {
                    includeZero: true
                },
                data: [{
                    type: "column", //change type to bar, line, area, pie, etc
                    //indexLabel: "{y}", //Shows y value on all Data Points
                    indexLabelFontColor: "#5A5757",
                    indexLabelPlacement: "outside",
                    dataPoints: <%out.print(dataPoints2);%>
                }]
            });
            chart2.render();
        }
    </script>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Admin"/>
        </jsp:include>
    </head>
<body>
<jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>">
    <jsp:param name="menu_bar" value="Overview"/>
</jsp:include>

<div class="jumbotron text-center">
    <h1>Rent car dashboard</h1>
    <p>Welcome back admin pages !! </p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <div id="chartContainer" style="height: 370px; width: 100%;"></div>
        </div>
        <div class="col-sm-6">
            <div id="chartContainer2" style="height: 370px; width: 100%;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        </div>
    </div>
</div>

<jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>"/>
</body>
</html>
