<%-- 
    Document   : newjsp
    Created on : Nov 19, 2020, 4:18:10 PM
    Author     : Admin-NBB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
        <style>
            #map {
                height: 400px;
                /* The height is 400 pixels */
                width: 100%;
                /* The width is the width of the web page */
            }

        </style>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>
        <!--The div element for the map -->
        <div id="map"></div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
        <script
            src="https://maps.googleapis.com/maps/api/js?key=<%= main.WebConfig.api_key%>&callback=initMap&libraries=&v=weekly"
            defer
        ></script>
        <script>
            function initMap() {
                const myLatLng = {lat: -25.363, lng: 131.044};
                const map = new google.maps.Map(document.getElementById("map"), {
                    zoom: 4,
                    center: myLatLng,
                });
                new google.maps.Marker({
                    position: myLatLng,
                    map,
                    title: "Select!",
                });
            }
        </script>
    </body>
</html>
