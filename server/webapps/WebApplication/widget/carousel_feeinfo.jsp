<%-- 
    Document   : carousel_feeinfo
    Created on : Nov 20, 2020, 9:36:10 AM
    Author     : ITSUKA KOTORI
--%>

<%@page import="xenum.CarType"%>
<%@page import="adt.ArrList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrList<CarType> types = new ArrList(xenum.CarType.values()); %>


<div class="form-group">
    <select class="form-control" id="ride-type" name="ride-type">
        <%for (int i = 0; i < types.size(); i++) {%>
        <option data-valuea="<%= types.get(i).getName()%>" data-valueb="<%= i%>" value="<%= types.get(i).getCode()%>"><b><%= types.get(i).getName()%></b></option>
            <%}%>
    </select>
</div>
<div id = "map-note">

</div>
<input type="hidden" name="map-distance" id="map-distance" value="null">
<input type="hidden" name="map-time" id="map-time" value="null">
<div id="myCarousel" class="carousel slide" data-ride="">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarouse1" data-slide-to="0" class="active"></li>
            <%for (int i = 1; i < types.size(); i++) {%>
        <li data-target="#myCarouse1" data-slide-to="<%= i%>"></li>
            <%}%>
    </ol>
    <style>
        .well{margin:0}
        .carousel-control.left {background-image:none;}
        .carousel-control.right {background-image:none;}
        .carousel-indicators li{border-color:#333;}
        .carousel-indicators li.active{background-color:#333;}
        .carousel-control{color:#333}
        .carousel-control:hover{color:#333}
    </style>
    <!-- Wrapper for slides -->
    <div class="carousel-inner">

        <%for (int i = 0; i < types.size(); i++) {%>
        <div class="well item <%= i == 0 ? "active" : ""%>">

            <h3><%= types.get(i).getName()%></h3>
            <p><%= types.get(i).getDecription()%></p>
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <td class="text-center" colspan="2" style="width: 50%"><p>Toll charge is not include in the fare estimate</p></td>
                    </tr>
                    <tr>
                        <td style="width: 50%"><b>Base fare</b></td>
                        <td style="width: 50%">RM <%= String.format("%.2f", types.get(i).getBase_fair_price())%></td>
                    </tr>
                    <tr>
                        <td style="width: 50%"><b>Minimum Fare</b></td>
                        <td style="width: 50%">RM <%= String.format("%.2f", types.get(i).getMinimum_price())%></td>
                    </tr>
                    <tr>
                        <td style="width: 50%"><b>Fees each KM</b></td>
                        <td style="width: 50%">RM <%= String.format("%.2f", types.get(i).getPrice_per_km())%></td>
                    </tr>
                    <tr>
                        <td style="width: 50%"><b>Fees each Min</b></td>
                        <td style="width: 50%">RM <%= String.format("%.2f", types.get(i).getPrice_per_min())%></td>
                    </tr>
                    <tr>
                        <td class="text-success" style="width: 50%"><b>Price estimation</b></td>
                        <td style="width: 50%" id="price_<%= i%>"></td>
                    </tr>
                </tbody>
            </table>
            <br>
        </div>
        <%}%>
    </div>

</div>



<script>
    $(document).ready(function () {
        $('#ride-type').on('change', function () {
            $('.carousel').carousel(parseInt($(this).children("option:selected").data('valueb')));
            $('.carousel').carousel('pause');
            $('#booking-btn').val("Book " + $(this).children("option:selected").data('valuea'));
        });
    });
</script>