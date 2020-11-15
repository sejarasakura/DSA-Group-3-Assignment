<%-- 
    Document   : map_api
    Created on : Nov 14, 2020, 4:50:04 PM
    Author     : ITSUKA KOTORI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>
            Google Map API Key
        </h1>
        
        <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>" />
    </body>
</html>
