<%-- 
    Document   : footer
    Created on : Nov 7, 2020, 3:47:43 PM
    Author     : ITSUKA KOTORI
--%>
<%@page import="pages.Footer"%>
<%@page import="adt.ArrList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

    <footer style="padding-top: 40px; padding-bottom: 40px; margin-top: 50px; background-color: #555">

        <div class="container" style="color: #fff">
            <div class="row">
            <%ArrList<String> lists = new Footer(request).get_footer();%>
            <% for(String list: lists){
                out.write(list);
            }%>
            </div>
        </div>

    </footer>