/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.ArrList;
import cilent.IDManager;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Functions;
import main.WebConfig;
import xenum.BookingStatus;
import xenum.CarType;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "StartBookingNow", urlPatterns = {"/start/booking/now"})
public class StartBookingNow extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /*Get login user*/
            User user = Functions.getUserSession(request);

            /*Create a;; object need to add*/
            Booking booking = new Booking();
            Chats chats = new Chats();
            Mapping mapping = new Mapping();
            Payment payment = new Payment();

            /*Get all hte input*/
            String form = request.getParameter("form-latlng");
            String to = request.getParameter("to-latlng");
            String booking_type = request.getParameter("ride-type");
            String ride_note = request.getParameter("ride-note");

            out.print(form + "<br>");
            out.print(to + "<br>");
            out.print(booking_type + "<br>");
            out.print(ride_note + "<br>");

            /*Validation*/
            if (user == null ? true : !user.isNotNull()) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/login.jsp?E=21");
                return;
            }
            if (!user.isCustomer()) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/index.jsp?I=I-0011");
                return;
            }
            if (form == null || to == null ? false : form.isEmpty() || to.isEmpty()) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/login.jsp");
                return;
            }

            /*generate ID*/
            String booking_id = (String) IDManager.generateId(booking);
            String chats_id = (String) IDManager.generateId(chats);
            String mapping_id = (String) IDManager.generateId(mapping);
            String payment_id = (String) IDManager.generateId(payment);

            booking = new Booking(booking_id, ride_note, CarType.getValue(booking_type), new Date(), "",
                    user.getId(), chats_id, mapping_id, payment_id, BookingStatus.WATING_ACCEPTED);

            chats = new Chats(chats_id, null, null, new ArrList());

            mapping = new Mapping();

            chats.addThisToCsv();
            booking.addThisToCsv();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
