/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.*;
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import entity.help.Range;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xenum.*;

/**
 *
 * @author Lim Sai Keat
 */
@WebServlet(name = "StartFetchNow", urlPatterns = {"/start-fetch-now"})
public class StartFetchNow extends HttpServlet {

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
            String booking_id = request.getParameter("id");
            String user_id = request.getParameter("uid");

            XArrayList<Booking> booking = (XArrayList<Booking>) AbstractEntity.readDataFormCsv(new Booking());
            booking = booking.binarySearchAndSort("booking_id", booking_id, Booking.class);
            if (booking == null ? true : booking.isEmpty()) {
                // wrong booking id
                return;
            }

            Booking b = booking.get(0);
            XArrayList<Car> cars;
            if (b.getCar_type().isTaxi()) {
                cars = (XArrayList<Car>) AbstractEntity.readDataFormCsv(new Taxi());
                cars = cars.binarySearchAndSort("driver_id", user_id, Taxi.class);
            } else {
                cars = (XArrayList<Car>) AbstractEntity.readDataFormCsv(new Car());
                cars = cars.binarySearchAndSort("driver_id", user_id, Car.class);
            }

            System.out.print(cars.toHtml());
            // find the have you registed the car type
            Car found = null;
            for (Car c : cars) {
                if (c.getCarType().equals(b.getCar_type())) {
                    found = c;
                }
            }

            // cannot fetch
            if (found == null) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/driverFetch.jsp?I=I-0023"
                        + "&eq=Customer request car type is <b>" + b.getCar_type().getName() + "</b>");
                return;
            }

            b.setBookingStatus(BookingStatus.PENDING_RENTING);
            b.setDriver_id(user_id);
            b.setCar_id(found.getPlate_id());
            b.updateThisToCsv();

            out.print(found.toString());
            out.print("<br>");
            out.print("Booking Updated : ");
            out.print("<br>");
            out.print(b.toString());
            out.print("<a href=\"");
            out.print(WebConfig.WEB_URL + "pages/driverFetch.jsp");
            out.print("\">");
            out.print("Return back");
            out.print("</a>");

            Datas.waitingDriver.enqueue(b);
            System.out.println(
                    OutputColor.TEXT_YELLOW
                    + "Datas.waitingDriver.enqueue :"
                    + OutputColor.TEXT_GREEN
                    + b
                    + OutputColor.TEXT_RESET
            );
            XArrayList<Booking> ar = new XArrayList(Datas.currentBooking);
            Booking r = ar.remove(ar.indexOf(b));
            Mapping m = (Mapping) AbstractEntity.readDataFormCsv(
                    new Mapping()
            ).binarySearchAndSort(
                    "map_id", b.getMapping_id(), Mapping.class
            ).get(0);
            System.out.println(
                    OutputColor.TEXT_YELLOW
                    + "Datas.waitingDriver.denqueue :"
                    + OutputColor.TEXT_GREEN
                    + r
                    + OutputColor.TEXT_RESET
            );
            Datas.currentBooking = new XQueue(ar);

            Payment p = new Payment();
            p.setPayment_id((String) IDManager.generateId(p, true));
            p.setPayment_method(PaymentMethodType.NOT_YET_PAID);
            p.setPayment_status(PaymentStatus.Created);
            p.setPayment_due_date(new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000));
            p.setPayment_date(new Date());
            double amount = (double) m.getPriceRange(b.getCar_type()).getHigher();
            p.setPayment_amount(amount);
            p.addThisToCsv();
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
