<%-- 
    Document   : customer-review
    Created on : Dec 5, 2020, 11:52:09 PM
    Author     : Lim Sai Keat
--%>

<%@page import="main.*"%>
<%@page import="cilent.*"%>
<%@page import="cilent.filter.*"%>
<%@page import="cilent.pages.*"%>
<%@page import="cilent.servlet.*"%>
<%@page import="entity.*"%>
<%@page import="adt.*"%>
<%@page import="adt.node.*"%>
<%@page import="adt.interfaces.*"%>
<%@page import="csv.converter.*"%>
<%@page import="xenum.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
    User user = main.Functions.getUserSession(request);
    if (!response.isCommitted()) {
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
    }
    if (!user.isCustomer()) {
        response.sendRedirect(WebConfig.WEB_URL + "pages/index.jsp?I=I-0011");
        return;
    }
    XArraySortList<Booking> bookings;
    XArrayList<User> users;
    XArrayList<Review> reviews;
    bookings = new XArraySortList(AbstractEntity.readDataFormCsv(new Booking()));
    bookings.sortDesc("booking_date", Booking.class);
    // read data
    users = (XArrayList<User>) AbstractEntity.readDataFormCsv(new Driver());
    users.sort("user_id", Driver.class);
    // read data
    reviews = (XArrayList<Review>) AbstractEntity.readDataFormCsv(new Review());
    reviews.sort("paymentNumber", Review.class);
    User u;
    Booking b;
    Review r;
    final String con_review_que = "<b>Review Now !!</b>";
    String review_que = "<b>Review Now !!</b>";
%>
<html>
    <head>
        <jsp:include page="<%= WebConfig.META_URL%>">
            <jsp:param name="title" value="customer-review"/>
        </jsp:include>
        <style>
        </style>
    </head>
    <body>
        <jsp:include page="<%= WebConfig.HEADER_URL%>">
            <jsp:param name="menu_bar" value="customer-review"/>
        </jsp:include>

        <div class="container">
            <h1 class="text-center">Review Sorted List [Sort by Booking Date]</h1><br>
            <%
                for (int i = 0; i < bookings.size(); i++) {
                    review_que = con_review_que;
                    b = bookings.get(i);
                    if (b.getCustomer_id().equals(user.getUser_id())) {
                        u = users.binarySearchOnce("user_id", b.getDriver_id(), Driver.class);
                        r = reviews.binarySearchOnce("paymentNumber", b.getPaymentNumber(), Review.class);
            %>Booking ID:<a href="viewBooking.jsp?id=<%
                out.print(b.getBooking_id());
                          %>"><%
                    out.print(b.getBooking_id());
                %></a><%
                                      out.print("|Driver:");
                                      out.print(u == null ? "No driver" : u.getName());
                                      out.print("[ id = ");
                                      out.print(u == null ? "null" : u.getId());
                                      out.print(" ]");
                                      out.print("<br/>");
                                      out.print("Rating : ");
                                      out.print(r == null ? "No review yet. <br>" : r.getDriverRating() + " Star</br>");
                                      out.print("Booking Date : ");
                                      out.print(b.getBooking_date());
                                      out.print(" </br>");
                                      if (r != null) {
                                          out.print("Review Date : ");
                                          out.print(r.getReviewDate());
                                          out.print(" </br>");
                                          out.print("Review Comments : <br />");
                                          out.print("<blockquote>");
                                          out.print(Functions.unhash_csv_record(r.getComments().toHtml()));
                                          out.print("</blockquote>");
                                          review_que = "<i>edit!!<i>";
                                      }
                                      if (b.getDriver_id() != null ? !b.getDriver_id().isEmpty() : false) {
                                          out.print("<a href=\"");
                                          out.print("review.jsp?id=");
                                          out.print(b.getPaymentNumber());
                                          out.print("&oid=");
                                          out.print(b.getDriver_id());
                                          out.print("\">");
                                          out.print(review_que);
                                          out.print("</a>");
                                      } else {
                                          out.print("<p class=\"text-success\"><b>You booking had not yet accept by any driver !!</b></p>");
                                      }
                                      out.print("<hr><br>");
                                  }
                              }
                %>
        </div>
        <jsp:include page="<%= WebConfig.FOOTER_URL%>"/>
    </body>
    <script>
    </script>
</html>