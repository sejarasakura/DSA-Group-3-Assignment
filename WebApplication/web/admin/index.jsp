<%-- 
    Document   : index
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="java.util.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="adtClass.ArrList"%>
<%@page import="org.apache.commons.text.StringEscapeUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    Gson gsonObj = new Gson();
    Map<Object, Object> map = null;
    List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

    int count = 1000;
    int yVal = 100;
    Random rand = new Random();

    for (int i = 0; i < count; i++) {
        yVal += rand.nextInt(11) - 5;
        map = new HashMap<Object, Object>();
        map.put("x", i + 1);
        map.put("y", yVal);
        list.add(map);
    }

    String dataPoints = gsonObj.toJson(list);
    
Gson gsonObj2 = new Gson();
Map<Object,Object> map2 = null;
List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();
 
map2 = new HashMap<Object,Object>(); map2.put("x", 10); map2.put("y", 31); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 20); map2.put("y", 65); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 30); map2.put("y", 40); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 40); map2.put("y", 84); map2.put("indexLabel", "Highest"); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 50); map2.put("y", 68); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 60); map2.put("y", 64); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 70); map2.put("y", 38); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 80); map2.put("y", 71); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 90); map2.put("y", 54); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 100); map2.put("y", 60); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 110); map2.put("y", 21); map2.put("indexLabel", "Lowest"); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 120); map2.put("y", 49); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 130); map2.put("y", 41); list2.add(map2);
String dataPoints2 = gsonObj2.toJson(list2);
 System.out.println(dataPoints2);
 System.out.println(dataPoints);
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
                <jsp:param name="title" value="Admin" />
            </jsp:include>    
        </head>
        <body>
            <jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>" >
                <jsp:param name="menu_bar" value="Overview" />
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

            <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>" />
        </body>
    </html>
