<%-- 
    Document   : edit_app
    Created on : Nov 12, 2020, 4:33:48 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="pages_.EditAdmin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EditAdmin editadmin;
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Apprearance" />
        </jsp:include>    
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>" >
            <jsp:param name="menu_bar" value="Overview" />
        </jsp:include>    
        
        <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>" />
    </body>
</html>
