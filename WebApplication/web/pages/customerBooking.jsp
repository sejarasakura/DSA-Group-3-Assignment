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
                width: 80%;
                margin-top: 10px;
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
            src="https://maps.googleapis.com/maps/api/js?key=<%= main.WebConfig.api_key%>&callback=initMap&libraries=places&v=weekly"
            defer
        ></script>
        <script>
            function initMap() {
                const myLatLng = {lat: 4.2105, lng: 101.9758};
                const map = new google.maps.Map(document.getElementById("map"), {
                    zoom: 8,
                    center: myLatLng,
                    mapTypeId: "roadmap",
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
                // Create the search box and link it to the UI element.
                const input = document.getElementById("pac-input");
                const searchBox = new google.maps.places.SearchBox(input);
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
                // Bias the SearchBox results towards current map's viewport.
                map.addListener("bounds_changed", () => {
                    searchBox.setBounds(map.getBounds());
                });
                // Listen for the event fired when the user selects a prediction and retrieve
                // more details for that place.
                searchBox.addListener("places_changed", () => {
                    const places = searchBox.getPlaces();

                    if (places.length == 0) {
                        return;
                    }
                    // For each place, get the icon, name and location.
                    const bounds = new google.maps.LatLngBounds();
                    places.forEach((place) => {
                        if (!place.geometry) {
                        console.log("Returned place contains no geometry");
                                return;
                        }
                        marker.setPosition(place.geometry.location);

                        if (place.geometry.viewport) {
                            // Only geocodes have viewport.
                            bounds.union(place.geometry.viewport);
                        } else {
                            bounds.extend(place.geometry.location);
                        }
                    });
                    map.fitBounds(bounds);
                });
            }
        </script>
    </body>
</html>
