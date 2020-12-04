<%-- 
    Document   : footer
    Created on : Nov 7, 2020, 3:47:43 PM
    Author     : ITSUKA KOTORI
--%>
<%@page import="main.Datas"%>
<%@page import="cilent.pages.Footer" %>
<%@page import="adt.XArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<footer id="customer-footer" style="padding-top: 40px; padding-bottom: 40px; background-color: #555">

    <div class="container" style="color: #fff">
        <div class="row">
            <%
                if (Datas.pages_footer == null) {
                    Datas.pages_footer = new Footer(request).get_footer();
                }
                for (String list : Datas.pages_footer) {
                    out.write(list);
                }
            %>
        </div>
    </div>

</footer>