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
                height: 600px;
                /* The height is 600 pixels */
                width: 100%;
                /* The width is the width of the web page */
            }

            #pac-input {
                text-overflow: ellipsis;
                position:relative;
            }
        </style>
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>
        <!--The div element for the map -->
        <input
            id="pac-input"
            class="form-control"
            type="text"
            placeholder="Search Box"
            />
        <div id="map"></div>

        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
        <script
            src="https://maps.googleapis.com/maps/api/js?key=<%= main.WebConfig.api_key%>&callback=initMap&libraries=&v=weekly"
            defer
        ></script>
        <script>
            function initMap() {
                const myLatLng = {lat: 4.2105, lng: 101.9758};
                const map = new google.maps.Map(document.getElementById("map"), {
                    zoom: 8,
                    center: myLatLng,
                });
                const marker = new google.maps.Marker({
                    position: myLatLng,
                    map,
                    title: "Click to zoom",
                });
                map.addListener("click", (mapsMouseEvent) => {
                    // 3 seconds after the center of the map has changed, pan back to the
                    // marker.
                    marker.setPosition(mapsMouseEvent.latLng);
                });
                marker.addListener("click", () => {
                    map.setZoom(8);
                    map.setCenter(marker.getPosition());
                });
            }
        </script>
    </body>
</html>
