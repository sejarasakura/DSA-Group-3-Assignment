<%-- 
    Document   : change_password
    Created on : Dec 6, 2020, 1:23:46 AM
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
<!DOCTYPE html>
<% User u = Functions.getUserSession(request);%>

<form action="/WebApplication/update_password" id="update_password_form" method="post">
    <div class="row" id="change_password">
        <div class="col-sm-6">
            <h4><b>Change my password</b></h4>
        </div>
        <div class="col-sm-6">
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item">
            <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password pull-right" style="margin-bottom:10px;"></span>
                    
            <div class="form-group row">
                <div class="col-sm-12">
                    <input placeholder="Old Password" type="password" class="form-control" id="opassword" name="opassword">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div>
                        <sup>Use 8 or more characters with a 
                            mix of letters, numbers &amp; symbols</sup>
                    </div>                       
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-6">
                    <input placeholder="New Password" type="password" class="form-control" id="password" name="password">
                </div>
                <div class="col-sm-6">
                    <input placeholder="Confirm new password" type="password" class="form-control" id="cpassword" name="cpassword">
                </div>
            </div>
        </li>
        <div class="row">
            <div class="col-sm-12">
                <input type="submit" class="btn btn-info pull-right" style="margin-top:10px;" id="usr" value="Change password">
                <input type="hidden" name="id" value="<%= u.getUser_id()%>">
            </div>
        </div>
    </ul>



</form>
<script>
    $(".toggle-password").click(function () {
        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $($(this).attr("toggle"));
        var input2 = $("#cpassword");
        var input3 = $("#opassword");
        if (input.attr("type") == "password") {
            input.attr("type", "text");
            input2.attr("type", "text");
            input3.attr("type", "text");
        } else {
            input.attr("type", "password");
            input2.attr("type", "password");
            input3.attr("type", "password");
        }
    });
    $(document).ready(function () {
        $("#update_password_form").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 8,
                    pwcheck: true
                },
                cpassword: {
                    equalTo: "#password"
                }
            },
            messages: {
                cpassword: {
                    equalTo: "The password must be same"
                },
                password: {
                    required: "Please provide a password",
                    pwcheck: "The password need at least 1 Uppercase, 1 Lowercase and 1 digit",
                    minlength: "Your password must be at least 8 characters long"
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
            }
        });
    });
</script>