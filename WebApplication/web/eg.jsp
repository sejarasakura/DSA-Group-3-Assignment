<%-- 
    Document   : eg
    Created on : Nov 25, 2020, 3:24:56 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="java.util.Iterator"%>
<%@page import="adt.*"%>
<%@page import="entity.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            XArraySortList all_user_1 = new XArraySortList((Iterator) AbstractEntity.readDataFormCsv(new Customer()));
            out.print(all_user_1.toHtml());
            out.print("<br><hr><br>");
            all_user_1.sort("email", Customer.class);
            out.print(all_user_1.toHtml());
            
        %>
    </body>
</html>
