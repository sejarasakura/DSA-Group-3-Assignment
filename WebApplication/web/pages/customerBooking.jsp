<%-- 
    Document   : newjsp
    Created on : Nov 19, 2020, 4:18:10 PM
    Author     : Admin-NBB
--%>

<%@page import="xenum.CarType"%>
<%@page import="adt.ArrList"%>
<%@page import="main.Datas"%>
<%@page import="main.WebConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String pages = (String) Datas.settings.getValue("widget/cartype-select");%>
<% ArrList<CarType> types = new ArrList(xenum.CarType.values());%>
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

            .pac-input {
                text-overflow: ellipsis;
                position:relative;
                width: 30%;
                margin-top: 12px;
                margin-right: 12px;
            }
            #form-to{
                margin-left: 12px;
            }
        </style>
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>
        <!--The div element for the map -->

        <div id="map"></div>
        <a id="logo-map" class="" href="<%= getServletContext().getInitParameter("DomainName")%>">
            <img style="margin:12px" src="<%= Datas.settings.getValue("image/logo")%>" alt="Rent Car" width="64" height="50">
        </a>
        <input
            id="pac-input-form"
            class="pac-input form-control"
            type="text"
            placeholder="Form Location (Current)"
            />
        <input
            id="pac-input-to"
            class="pac-input form-control"
            type="text"
            placeholder="To "
            />
        <button disabled="true" style="margin-bottom: 30px" id="procide-booking" type="button" id="submit" class="btn btn-lg btn-rent" data-toggle="modal" data-target="#myModal">
            Confirm Location
        </button>

        <form method="post" action="<%= WebConfig.WEB_URL%>start/booking/now">
            <input type="hidden" id="form-latlng" name="form-latlng" value=""/>
            <input type="hidden" id="to-latlng" name="to-latlng" value=""/>

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">
                                Start Booking
                            </h4>
                        </div>
                        <div class="modal-body">
                            <jsp:include page="<%= pages%>"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <input id="booking-btn" type="submit" class="btn btn-rent" value="Book Just Rent">
                        </div>
                    </div>

                </div>
            </div>
        </form>
        <div class="btn-group" id="form-to">
            <button id="form-btn" type="button" class="btn btn-default">
                <img alt="Form" src="<%= WebConfig.IMG_URL + "pin-blue.png"%>"/> Form
            </button>
            <button id="to-btn" type="button" class="btn btn-default">
                <img alt="Form" src="<%= WebConfig.IMG_URL + "pin-red.png"%>"/> To
            </button>
        </div>

        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
        <script
            src="https://maps.googleapis.com/maps/api/js?key=<%= main.WebConfig.api_key%>&callback=initMap&libraries=places&v=weekly&language=en&region=MY_en"
            defer
        ></script>
        <script>

            function initMap() {
                const klLatLng = {lat: 4.2105, lng: 101.9758};
                const map = new google.maps.Map(document.getElementById("map"), {
                    zoom: 12,
                    center: klLatLng,
                    animation: google.maps.Animation.DROP,
                    mapTypeId: "roadmap"
                });
                const marker_form = new google.maps.Marker({
                    position: klLatLng,
                    map,
                    animation: google.maps.Animation.DROP,
                    title: "Form",
                    icon: {
                        url: "<%= WebConfig.IMG_URL + "pin-blue.png"%>"
                    }
                });
                const marker_to = new google.maps.Marker({
                    position: klLatLng,
                    map,
                    title: "To Here",
                    icon: {
                        url: "<%= WebConfig.IMG_URL + "pin-red.png"%>"
                    }
                });
                // Create the search box and link it to the UI element.
                var focus_form = false;
                const geocoder = new google.maps.Geocoder();
                const malaysia = {componentRestrictions: {country: 'my'}};
                const booking = document.getElementById("procide-booking");
                const input_form = document.getElementById("pac-input-form");
                const input_to = document.getElementById("pac-input-to");
                const input_form_lat = document.getElementById("form-latlng");
                const input_to_lat = document.getElementById("to-latlng");
                const btn_ft = document.getElementById("form-to");
                const logo_map = document.getElementById("logo-map");
                const dist_map = document.getElementById("map-distance");
                const time_map = document.getElementById("map-time");
                const searchBox_form = new google.maps.places.SearchBox(input_form);
                const searchBox_to = new google.maps.places.SearchBox(input_to);
                const polylineOptionsActual = new google.maps.Polyline({strokeColor: '#ff1a4b', strokeOpacity: 0.8, strokeWeight: 6});
                const directionsRenderer = new google.maps.DirectionsRenderer
                        ({suppressMarkers: true, polylineOptions: polylineOptionsActual});
                const directionsService = new google.maps.DirectionsService();
                const service = new google.maps.DistanceMatrixService();
                const outputDiv = document.getElementById("map-note");
                map.controls[google.maps.ControlPosition.BOTTOM_CENTER].push(booking);
                map.controls[google.maps.ControlPosition.LEFT_BOTTOM].push(btn_ft);
                map.controls[google.maps.ControlPosition.LEFT_BOTTOM].push(logo_map);
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input_form);
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input_to);
                directionsRenderer.setMap(map);
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(
                            (position) => {
                        const pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };
                        marker_form.setPosition(pos);
                        map.setCenter(pos);
                    },
                            () => {
                        handleLocationError(true, infoWindow, map.getCenter(), map);
                    }
                    );
                } else {
                    // Browser doesn't support Geolocation
                    handleLocationError(false, infoWindow, map.getCenter(), map);
                }
                booking.addEventListener("click", (mapsMouseEvent) => {
                    distanceMatricApix(marker_form, marker_to,
                            service, dist_map, time_map, outputDiv);
                });
                map.addListener("click", (mapsMouseEvent) => {
                    if (focus_form) {
                        marker_form.setPosition(mapsMouseEvent.latLng);
                        reverseLatLng(geocoder, input_form, marker_form, map);
                        fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                        writeToInput(marker_form.getPosition(), input_form_lat);
                        calculateAndDisplayRoute(directionsService, directionsRenderer,
                                marker_form.getPosition(), marker_to.getPosition());
                    } else {
                        booking.disabled = false;
                        marker_to.setPosition(mapsMouseEvent.latLng);
                        reverseLatLng(geocoder, input_to, marker_to, map);
                        fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                        writeToInput(marker_to.getPosition(), input_to_lat);
                        calculateAndDisplayRoute(directionsService, directionsRenderer,
                                marker_form.getPosition(), marker_to.getPosition());
                    }
                });
                // Listen for the event fired when the user selects a prediction and retrieve
                // more details for that place.
                searchBox_form.addListener("places_changed", () => {
                    focus_form = true;
                    search(searchBox_form, marker_form, map);
                    fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                    writeToInput(marker_form.getPosition(), input_form_lat);
                    calculateAndDisplayRoute(directionsService, directionsRenderer,
                            marker_form.getPosition(), marker_to.getPosition());
                });
                searchBox_to.addListener("places_changed", () => {
                    focus_form = false;
                    booking.disabled = false;
                    search(searchBox_to, marker_to, map);
                    fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                    writeToInput(marker_to.getPosition(), input_to_lat);
                    calculateAndDisplayRoute(directionsService, directionsRenderer,
                            marker_form.getPosition(), marker_to.getPosition());
                });
                marker_form.addListener("click", (mapsMouseEvent) => {
                    focus_form = true;
                    setFocusBtn(focus_form);
                    fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                });
                marker_to.addListener("click", (mapsMouseEvent) => {
                    focus_form = false;
                    setFocusBtn(focus_form);
                    fitCurrentBound(marker_form.getPosition(), marker_to.getPosition(), map);
                });
                reverseLatLng(geocoder, input_to, marker_to, map);
                writeToInput(marker_form.getPosition(), input_form_lat);
                setFocusBtn(focus_form);
            }

            function calculateAll() {
                const dist_map = document.getElementById("map-distance");
                const time_map = document.getElementById("map-time");
                <%for (int i = 0; i < types.size(); i++) {%>
                calculatePrice(
                        'price_<%=i%>',
                        <%= types.get(i).getBase_fair_price()%>,
                        <%= types.get(i).getMinimum_price()%>,
                        <%= types.get(i).getPrice_per_km()%>, 
                        <%= types.get(i).getPrice_per_min()%>,
                        dist_map, 
                        time_map) + "\n";
                <%}%>
            }
            function calculatePrice(element_id, base_fare, min_fare, per_km, per_min, dist_map, time_map) {
                const output = document.getElementById(element_id);
                var price_no_base = (dist_map.value * per_km / 1000) + (time_map.value * per_min / 60);
                var price = (dist_map.value * per_km / 1000) + (time_map.value * per_min / 60) + base_fare;
                price = price >= min_fare ? price : min_fare;
                price_no_base = price_no_base >= min_fare ? price_no_base : min_fare;
                if (price_no_base.toFixed() == price.toFixed())
                    document.getElementById(element_id).innerHTML = "<b class='text-success'>RM " +
                            price.toFixed(2) + "</b>";
                else
                    document.getElementById(element_id).innerHTML = "<b class='text-success'>RM " +
                            price_no_base.toFixed(2) + " - " + price.toFixed(2) + "</b>";
            }

            function distanceMatricApix(origin, destination, service, dist_map, time_map, outputDiv) {

                service.getDistanceMatrix({
                    origins: [origin.getPosition()],
                    destinations: [destination.getPosition()],
                    travelMode: google.maps.TravelMode.DRIVING,
                    unitSystem: google.maps.UnitSystem.METRIC,
                    avoidHighways: false,
                    avoidTolls: false
                }, (response, status) => {
                    if (status !== "OK") {
                        alert("Error was: " + status);
                    }
                    dist_map.value = 0;
                    time_map.value = 0;
                    outputDiv.innerHTML = "";
                    const originList = response.originAddresses;
                    const destinationList = response.destinationAddresses;
                    for (let i = 0; i < originList.length; i++) {
                        const results = response.rows[i].elements;
                        for (let j = 0; j < results.length; j++) {
                            if (results[j].status !== "OK") {
                                alert("Error was: " + status);
                            }
                            dist_map.value += results[j].distance.value;
                            time_map.value += results[j].duration.value;
                            outputDiv.innerHTML += "<p>From <span style='color:#1a71ff'>" +
                                    originList[i] +
                                    "</span> to <span style='color:#ff1a4b'>" +
                                    destinationList[j] +
                                    "</span></p>distance: <i>" +
                                    results[j].distance.text +
                                    "</i><span class='pull-right'><b>time estimate: " +
                                    results[j].duration.text +
                                    "</b><br></span>";
                        }
                    }
                    calculateAll();
                });
            }

            function handleLocationError(browserHasGeolocation, infoWindow, pos, map) {
                infoWindow.setPosition(pos);
                infoWindow.setContent(
                        browserHasGeolocation
                        ? "Error: The Geolocation service failed."
                        : "Error: Your browser doesn't support geolocation."
                        );
                infoWindow.open(map);
            }

            function calculateAndDisplayRoute(directionsService, directionsRenderer, origin_p, destination_p) {
                directionsService.route(
                        {
                            origin: origin_p,
                            destination: destination_p,
                            travelMode: google.maps.TravelMode['DRIVING'],
                        },
                        (response, status) => {
                    if (status == "OK") {
                        directionsRenderer.setDirections(response);
                    } else {
                        window.alert("Directions request failed due to " + status);
                    }
                }
                );
            }

            function setFocusBtn(focus_form) {
                const input_form_btn = document.getElementById("form-btn");
                const input_to_btn = document.getElementById("to-btn");
                if (focus_form) {
                    input_form_btn.disabled = false;
                    input_to_btn.disabled = true;
                } else {
                    input_form_btn.disabled = true;
                    input_to_btn.disabled = false;
                }
            }
            function writeToInput(position, input_latlng) {
                input_latlng.value = JSON.stringify(position.toJSON(), null, 2);
            }
            function reverseLatLng(geocoder, input, marker, map) {
                const position = marker.getPosition();
                geocoder.geocode({location: position}, (results, status) => {
                    if (status === "OK") {
                        if (results[0]) {
                            input.value = results[0].formatted_address;
                        } else {
                            window.alert("No results found");
                        }
                    } else {
                        window.alert("Geocoder failed due to: " + status);
                    }
                });
            }
            function fitCurrentBound(form, to, map) {
                var bounds = new google.maps.LatLngBounds();
                bounds.extend(form);
                bounds.extend(to);
                map.fitBounds(bounds);
            }
            function search(searchBox, marker, map) {
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
            }

            $(document).ready(function () {
                $("#pac-input-to").click(function () {
                    setFocusBtn(false);
                });
                $("#pac-input-form").click(function () {
                    setFocusBtn(true);
                });
            });
        </script>
    </body>
</html>
