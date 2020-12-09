/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.XArrayList;
import cilent.IDManager;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Datas;
import main.Functions;
import main.WebConfig;
import xenum.BookingStatus;
import xenum.CarType;
import xenum.OutputColor;
import xenum.PaymentMethodType;
import xenum.PaymentStatus;

/**
 *
 * @author Lim Sai Keat
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
            MapAddress map_address_1 = new MapAddress();
            MapAddress map_address_2 = new MapAddress();

            /*Get all hte input*/
            String form = request.getParameter("form-latlng");
            String to = request.getParameter("to-latlng");
            String form_address = request.getParameter("form-address");
            String to_address = request.getParameter("to-address");
            String dist_value = request.getParameter("dist_value");
            String time_value = request.getParameter("time_value");
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
            if (form == null || to == null ? true : form.isEmpty() || to.isEmpty()) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/login.jsp");
                return;
            }

            /*generate ID*/
            String booking_id = ((int) IDManager.generateId(booking, true)) + "";
            String chats_id = (String) IDManager.generateId(chats, true);
            String mapping_id = (String) IDManager.generateId(mapping, true);
            String payment_id = (String) IDManager.generateId(payment, true);

            booking = new Booking(booking_id, ride_note, CarType.getValue(booking_type), new Date(), "",
                    user.getId(), chats_id, mapping_id, payment_id, BookingStatus.WATING_ACCEPTED);

            chats = new Chats(chats_id, user.getId(), null, new XArrayList());

            mapping = new Mapping();
            mapping.extractDestinationJson(to);
            mapping.extractSourceJson(form);
            mapping.setMap_id(mapping_id);
            mapping.setFetch_date(new Date());
            mapping.setTime_int(Integer.parseInt(time_value));
            mapping.setDest_int(Integer.parseInt(dist_value));

            long date = System.currentTimeMillis() + 14 * 24 * 3600 * 1000;

            payment = new Payment(payment_id, PaymentStatus.Created,
                    PaymentMethodType.NOT_YET_PAID,
                    new Date(), new Date(date), 0.00);

            map_address_1.setAddress_id(mapping.getDestination_id());
            map_address_1.setFull_address(to_address);
            map_address_2.setAddress_id(mapping.getSource_id());
            map_address_2.setFull_address(form_address);

            map_address_1.addThisToCsv();
            map_address_2.addThisToCsv();
            mapping.addThisToCsv();
            chats.addThisToCsv();
            booking.addThisToCsv();
            payment.addThisToCsv();

            out.print(map_address_1);
            out.print("<br>");
            out.print(map_address_2);
            out.print("<br>");
            out.print(mapping);
            out.print("<br>");
            out.print(chats);
            out.print("<br>");
            out.print(booking);
            out.print("<br>");
            out.print(payment);

            Datas.currentBooking.enqueue(booking);
            System.out.println(
                    OutputColor.TEXT_YELLOW
                    + "Datas.currentBooking.enqueue :"
                    + OutputColor.TEXT_GREEN
                    + booking
                    + OutputColor.TEXT_RESET
            );
            Thread.sleep(3000);
            response.sendRedirect(WebConfig.WEB_URL + "pages/viewBooking.jsp?" + booking.getBooking_id());
        } catch (InterruptedException ex) {
            Logger.getLogger(StartBookingNow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
