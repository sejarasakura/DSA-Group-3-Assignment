<%-- 
    Document   : register.jsp
    Created on : Nov 17, 2020, 12:11:39 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="main.WebConfig"%>
<%@page import="xenum.ErrorDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Register"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.HEADER_URL%>"/>

        <div class="container">
            <h1 class="text-center">Create a new Account</h1>
            <br>
            <div class="register"> 
                <form id="mainForm" method="post" action="<%= WebConfig.WEB_URL%>RegisterAction">
                    <img src="<%= main.Datas.settings.getValue("image/logo")%>" alt="Users" 
                         class="img-circle img-login center-block" width="80" height="80"> 
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="fname" name="fname" placeholder="First name" autofocus>
                        </div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="lname" name="lname" placeholder="Last name">
                        </div>
                    </div>
                    <hr>
                    <sup>You can use letter, number and underscore</sup>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input type="text" class="form-control" id="email" name="email" placeholder="Email Address">
                        </div>
                    </div>
                    <hr>
                    <div>
                        <sup>Use 8 or more characters with a mix of letters, numbers & symbols</sup>
                        <span toggle="#password" toggle2="#password2" class="fa fa-fw fa-eye field-icon toggle-password pull-right"></span>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" name="password"  placeholder="Password">
                        </div>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password2" name="password2"  placeholder="Confrim password">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="agree" name="agree" value="agree" />Please agree to our policy
                                </label>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="custom-control custom-switch pull-right">
                                <label class="toggle-switch">
                                    <input type="checkbox" id="registerAsDriver" name="registerAsDriver" class="toggle-switch-input" value="driver"/>
                                    <label for="registerAsDriver" class="toggle-switch-label"></label>
                                    <div style="color:#888">Register as driver</div>
                                </label>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <div class="form-group row">
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-6">
                            <input type="submit" class="btn btn-lg btn-info pull-right" value="Register">
                        </div>
                    </div>
                </form>
                <p>Already have an account ? <a href="<%= main.Datas.settings.getValue("pages/login")%>">Sign in</a>.</p>
            </div>
        </div>
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
                var input2 = $($(this).attr("toggle2"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                    input2.attr("type", "text");
                } else {
                    input.attr("type", "password");
                    input2.attr("type", "password");
                }
            });

            $(document).ready(function () {
                $("#mainForm").validate({
                    rules: {
                        fname: {
                            required: true,
                            minlength: 2
                        },
                        lname: {
                            required: true,
                            minlength: 3
                        },
                        username: {
                            required: true,
                            minlength: 3
                        },
                        password: {
                            required: true,
                            minlength: 8,
                            pwcheck: true
                        },
                        password2: {
                            required: true,
                            minlength: 8,
                            equalTo: "#password"
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        agree: "required"
                    },
                    messages: {
                        fname: "Please enter your firstname",
                        lname: "Please enter your lastname",
                        username: {
                            required: "Please enter a username",
                            minlength: "Your username must consist of at least 2 characters"
                        },
                        password: {
                            required: "Please provide a password",
                            pwcheck: "The password need at least 1 Uppercase, 1 Lowercase and 1 digit",
                            minlength: "Your password must be at least 8 characters long"
                        },
                        password2: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long",
                            equalTo: "Please enter the same password as above"
                        },
                        email: "Please enter a valid email address",
                        agree: "Please accept our policy"
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
                    }
                    ,
                    highlight: function (element, errorClass, validClass) {
                        $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
                    }
                    ,
                    unhighlight: function (element, errorClass, validClass) {
                        $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
                    }
                }
                );
            });
        </script>
        <jsp:include page="<%= main.WebConfig.FOOTER_URL%>"/>
    </body>
</html>
