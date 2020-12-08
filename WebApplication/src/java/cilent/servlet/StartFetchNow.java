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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xenum.*;

/**
 *
 * @author ITSUKA KOTORI
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

        // find the have you registed the car type
        cars = cars.binarySearchAndSort("carType", b.getCar_type(), Car.class);

        // cannot fetch
        if (cars.size() <= 0) {
            response.sendRedirect(WebConfig.WEB_URL + "pages/driverFetch.jsp?I=I-0023");
        }

        b.setBookingStatus(BookingStatus.PENDING_RENTING);
        b.setDriver_id(user_id);
        b.updateThisToCsv();

        try (PrintWriter out = response.getWriter()) {
            out.print(cars.get(0).toString());
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
