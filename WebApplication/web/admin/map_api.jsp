<%-- 
    Document   : map_api
    Created on : Nov 14, 2020, 4:50:04 PM
    Author     : ITSUKA KOTORI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="<%= main.WebConfig.META_URL%>">
        <jsp:param name="title" value="Apprearance"/>
    </jsp:include>
</head>
<body>
<jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>">
    <jsp:param name="menu_bar" value="Overview"/>
</jsp:include>
<div class="panel panel-default">
    <div class="panel-heading">
        <h1>
            Google Map API Key
        </h1>
    </div>
    <div class="panel-body">
        <form type="post" action="/WebApplication/admin/UpdateApiKey">
            <div class="form-group row">
                <div class="col-sm-6">
                    Your map api key
                </div>
                <div class="col-sm-6">
                    <input type="password" class="form-control" name="apiKey" placeholder="YOUR API KEY"
                           value="${param.apiKey}">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-6">
                </div>
                <div class="col-sm-6">
                    <input type="submit" class="btn btn-success pull-right" placeholder="" value="Update API Key">
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>"/>
</body>
</html>
