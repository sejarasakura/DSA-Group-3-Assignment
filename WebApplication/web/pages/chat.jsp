<%-- 
    Document   : chat
    Created on : Dec 6, 2020, 4:17:42 PM
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
    // Check user login or not
    response.encodeURL("/store/catalog");
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
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="chat"/>
        </jsp:include>

        <link rel="stylesheet" href="<%= getServletContext().getInitParameter("DomainName")%>theme/lib/chat.css">
        <link href="../theme/lib/full-page.css" rel="stylesheet" type="text/css"/>
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
                    <div class="msg_history">
                        <div class="outgoing_msg">
                            <div class="sent_msg">
                                <p>Apollo University, Delhi, India Test</p>
                                <span class="time_date"> 11:01 AM | Today</span>
                            </div>
                        </div>
                        <div class="incoming_msg">
                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="received_msg">
                                <div class="received_withd_msg">
                                    <p>We work directly with our designers and suppliers,
                                        and sell direct to you, which means quality, exclusive
                                        products, at a price anyone can afford.</p>
                                    <span class="time_date"> 11:01 AM | Today</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write">
                            <input type="text" class="write_msg" placeholder="Type a message" />
                            <button class="msg_send_btn" type="button"> <span class="glyphicon glyphicon-send"></span></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>