<%-- 
    Document   : chat
    Created on : Dec 6, 2020, 4:17:42 PM
    Author     : Lim Sai Keat
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
    // Check user login or not
    User user = main.Functions.getUserSession(request);
    if (response.isCommitted()) {
        return;
    }
    if (!main.Functions.checkLogin(response, user)) {
        return;
    }

    MessagePages mp = null;
    String target = request.getParameter("target");
    if (mp == null) {
        mp = new MessagePages(request, response, target);
    }
    if (target == null) {
        target = mp.getTarget();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="chat"/>
        </jsp:include>

        <link rel="stylesheet" href="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/chat.css">
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="chat"/>
        </jsp:include>

        <h3 class="text-center">Messaging</h3>
        <div class="messaging" style="padding-bottom: 1px">
            <div class="inbox_msg">
                <div class="inbox_people">
                    <div class="headind_srch">
                        <div class="recent_heading">
                            <h4>Recent</h4>
                        </div>
                    </div>
                    <%= mp.getChatList()%>
                </div>
                <div class="mesgs">
                    <div class="msg_history" id="msg_history">

                    </div>
                    <form id="send_message">
                        <div class="type_msg">
                            <div class="input_msg_write">
                                <input type="text" class="write_msg" placeholder="Type a message"  id="message" name="message"/>
                                <input type="hidden" id="target" name="target" value="<%=target%>"/>
                                <button class="msg_send_btn" type="submit"> <span class="glyphicon glyphicon-send"></span></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
        $(document).ready(function () {
            $('#msg_history').scrollTop($('#msg_history')[0].scrollHeight);
            $('#msg_history').click(function () {
                myFunction();
            });
            function myFunction() {
                $.post("/WebApplication/reload_chat_api",
                        {"target": "<%=target%>"}, function (result) {
                    $('#msg_history').html(result);
                });
            }


            $('#send_message').on('submit', function (e) {
                e.preventDefault();
                $.ajax({
                    type: 'post',
                    url: '/WebApplication/send_chat_api',
                    data: $('#send_message').serialize(),
                    success: function (data) {
                        $('#msg_history').append(data);
                        $('#msg_history').scrollTop($('#msg_history')[0].scrollHeight);
                        $('#message').val("");
                    }
                });
            });

            myFunction();
        });
    </script>
</html>