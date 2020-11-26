<%-- 
    Document   : edit_app
    Created on : Nov 12, 2020, 4:33:48 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="adt.ArrList" %>
<%@page import="cilent.pages.EditAdmin" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    EditAdmin editadmin = new EditAdmin(request);
    ArrList<String> view = editadmin.getHtmls();
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="<%= main.WebConfig.META_URL%>">
        <jsp:param name="title" value="Edit ${param.edit}"/>
    </jsp:include>
</head>
<body>
<jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>">
    <jsp:param name="menu_bar" value="Edit ${param.edit}"/>
</jsp:include>
<%
    for (String v : view) {
        out.write(v);
    }
%>
<jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>"/>
</body>
</html>
