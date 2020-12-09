<%-- 
    Document   : login.jsp
    Created on : Nov 7, 2020, 3:54:02 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="entity.User"%>
<%@page import="cilent.Login"%>
<%@page import="main.Datas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<%
    Login login = new Login(request);
    if (login.getUser() != null) {
        response.sendRedirect((String) main.Datas.settings.getValue("pages/account"));
    } else {
        User u = main.Functions.getUserSession(request);
        if (u != null) {
            response.sendRedirect((String) main.Datas.settings.getValue("pages/account"));
        }
    }
%>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Home"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>

        <div class="container">
            <h1 class="text-center">Login to rent car</h1>
            <br>
            <div class="login"> 
                <form submit="login.jsp" id="mainForm" method="post">
                    <img src="<%= main.Functions.getProfileUrl(request)%>" alt="Users" class="img-circle img-login center-block" width="200" height="200"> 
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <label for="usr">Username / Email :</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input placeholder="Email/Username" value="${param.username}" type="text" class="form-control" id="username" name="username" autofocus>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <label for="usr">Password :</label>                        
                            <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password pull-right" style="margin-top:10px;"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input placeholder="Password" type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <br><br>
                    <div class="form-group row">
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-6">
                            <input type="submit" class="btn-lg btn btn-info pull-right" id="usr" value="Login">
                        </div>
                    </div>
                </form>
                <p>Don't not have an account ? <a href="<%= main.Datas.settings.getValue("pages/register")%>">Register Now</a>.</p>
                <style>
                    .fa-eye::after {
                        content: 'Show password';
                    }
                    .fa-eye-slash::after {
                        content: 'Hide password';
                    }
                </style>
                <script>
                    $(".toggle-password").click(function () {
                        $(this).toggleClass("fa-eye fa-eye-slash");
                        var input = $($(this).attr("toggle"));
                        if (input.attr("type") == "password") {
                            input.attr("type", "text");
                        } else {
                            input.attr("type", "password");
                        }
                    });
                    $(document).ready(function () {
                        $("#mainForm").validate({
                            rules: {
                                username: {
                                    required: true,
                                    minlength: 2
                                },
                                password: {
                                    required: true,
                                    minlength: 8,
                                    pwcheck: true
                                }
                            },
                            messages: {
                                username: {
                                    required: "Please enter a username",
                                    minlength: "Your username must consist of at least 2 characters"
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
            </div>
        </div>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
