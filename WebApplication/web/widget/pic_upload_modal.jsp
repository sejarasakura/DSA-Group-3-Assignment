<%-- 
    Document   : pic_upload_modal
    Created on : Dec 4, 2020, 7:28:25 AM
    Author     : ITSUKA KOTORI
--%>

<%@page import="main.*"%>
<%@page import="cilent.*"%>
<%@page import="cilent.filter.*"%>
<%@page import="cilent.pages.*"%>
<%@page import="cilent.servlet.*"%>
<%@page import="entity.*"%>
<%@page import="adt.node.*"%>
<%@page import="adt.interfaces.*"%>
<%@page import="csv.converter.*"%>
<%@page import="xenum.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%

%>

<!-- 
<style>
    .change-img{position: absolute; color: #666; margin: 8px;border-radius: 50%;height: 50px; width: 50px;box-shadow: none;outline: none!important;}
    .change-img:hover{color: #666;}
</style>
<button type="button" class="btn btn-default change-img" data-toggle="modal" data-target="#pic_upload">
    <i class="fa fa-camera" style="font-size:24px"></i>
</button>
-->

<div class="modal fade" id="pic_upload" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change profile picture</h4>
            </div>
            <div class="modal-body">
                <div class="text-danger">Note * kindly upload a square picture</div>
                <form action="/WebApplication/pages/UploadServlet.jsp" method="post"
                      enctype="multipart/form-data">
                    <img id="blah" src="<%= main.Functions.getProfileUrl(request)%>" alt="profile picture" style="margin: 20px;"height="300px" width="300px"/>
                    <div class="form-group">
                        <input type="file" class="form-control" id="profile" name="profile"
                               aria-describedby="inputGroupFileAddon01">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="submit" value="Upload File" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        }
    }

    $("#profile").change(function () {
        readURL(this);
    });
</script>