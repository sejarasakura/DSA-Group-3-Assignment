<%-- 
    Document   : edit_entity
    Created on : Nov 16, 2020, 7:56:26 AM
    Author     : ITSUKA KOTORI
--%>

<%@page import="pages.EditEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EditEntity editEntity = EditEntity.getNewEditEntity(request.getParameter("edit"));
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
            <jsp:param name="menu_bar" value="Edit ${param.edit}" />
        </jsp:include>    
        <%= editEntity.getHtml()%>
        <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>" />
    </body>
</html>
