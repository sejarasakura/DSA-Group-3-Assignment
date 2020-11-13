<%-- 
    Document   : edit_app
    Created on : Nov 12, 2020, 4:33:48 PM
    Author     : ITSUKA KOTORI
--%>

<%@page import="adtClass.ArrList"%>
<%@page import="pages_.EditAdmin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EditAdmin editadmin = new EditAdmin(request);
    ArrList<String> view = editadmin.generateHtml();
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
        <%
            for (String v : view) {
                out.write(v);
            }
        %>
        <div class="form-group row">
            <div class="col-sm-12">
                <button type="button" class="btn btn-success form-control" data-toggle="modal" data-target="#myModal">
                    <i class='fas fa-plus'></i> Add new element
                </button>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <form action="/WebApplication/admin/add-new-ele?add=${param.edit}" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add new element</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group row">
                                <div class="col-sm-6">
                                    Items name / json key:
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="add-new" name="add-new" placeholder="Name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="_title" name="_title" placeholder="Title">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="_url" name="_url" placeholder="URL">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Add</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <jsp:include page="<%= main.WebConfig.ADMIN_FOOTER_URL%>" />
    </body>
</html>
