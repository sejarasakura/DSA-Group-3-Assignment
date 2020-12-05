<%-- 
    Document   : dev-ope-gen-id
    Created on : Nov 23, 2020, 2:07:30 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="adt.interfaces.InterList"%>
<%@page import="entity.*"%>
<%@page import="adt.*" %>
<%@page import="cilent.pages.EditAdmin" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    StringBuilder sb = new StringBuilder();
    String x_field = request.getParameter("x_field");
    String x_f = request.getParameter("x_f");
    String x_e = request.getParameter("x_e");
    String x_sep = request.getParameter("x_sep");
    String x_class = request.getParameter("x_class");
    AbstractEntity data;
    XArrayList raw_data;
    InterList<String> col_data = null;
    if (x_class != null && x_field != null) {
        if (!x_class.isEmpty() && !x_field.isEmpty()) {
            Class<?> _class = Class.forName(x_class);
            data = (AbstractEntity)_class.getConstructor(null).newInstance();
            raw_data = AbstractEntity.readDataFormCsv(data);
            if(raw_data.size() > 0){
                col_data = raw_data.getField(x_field, x_class);
            }
            if(col_data != null){
                sb.append(x_f);
                for(String s: col_data){
                    sb.append(s).append(x_sep);
                }
                sb.deleteCharAt(sb.length() -x_sep.length());
                sb.append(x_e);
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="<%= main.WebConfig.META_URL%>">
            <jsp:param name="title" value="Development Operation"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="<%= main.WebConfig.ADMIN_HEADER_URL%>">
            <jsp:param name="menu_bar" value="Development Operation"/>
        </jsp:include>
        <h2>Generate ID</h2>
        <form action="dev-ope-gen-id.jsp" method="post">
            <div class="form-group">
                <label>Class :</label>
                <input type="text" class="form-control" id="pwd" placeholder="Enter class" name="x_class" value="${param.x_class}">
            </div>
            <div class="form-group">
                <label>Field :</label>
                <input type="text" class="form-control" id="email" placeholder="Enter field" name="x_field" value="${param.x_field}">
            </div>
            <div class="row">
                <div class="form-group col-sm-4">
                    <label>Front :</label>
                    <input type="text" class="form-control" id="pwd" placeholder="Enter start" name="x_f" value='${param.x_f == null?"(":param.x_f}'>
                </div>
                <div class="form-group col-sm-4">
                    <label>Seperator :</label>
                    <input type="text" class="form-control" id="pwd" placeholder="Enter seperator" name="x_sep" value="${param.x_sep == null?"|":param.x_sep}">
                </div>
                <div class="form-group col-sm-4">
                    <label>End :</label>
                    <input type="text" class="form-control" id="pwd" placeholder="Enter end char" name="x_e" value="${param.x_e == null?")":param.x_e}">
                </div>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <p style="word-wrap: break-word"><%= sb.toString()%></p>
        <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>"/>
    </body>
</html>
