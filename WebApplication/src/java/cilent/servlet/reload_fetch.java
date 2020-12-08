/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.MapConverter;
import adt.XArrayList;
import adt.XTreeDictionary;
import com.google.gson.Gson;
import entity.AbstractEntity;
import entity.Booking;
import entity.Driver;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Functions;
import main.WebConfig;
import xenum.BookingStatus;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "reload_fetch", urlPatterns = {"/reload_fetch"})
public class reload_fetch extends HttpServlet {

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
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            XTreeDictionary data = new XTreeDictionary();
            MapConverter map = new MapConverter(data);
            Gson gson = new Gson();
            String bid = request.getParameter("bid");
            if (bid == null ? true : bid.isEmpty()) {
                data.add("status", "NO-UPDATE");
                out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
                return;
            }
            XArrayList booking_list = AbstractEntity.readDataFormCsv(new Booking());
            booking_list = booking_list.binarySearchAndSort("booking_id", bid, Booking.class);
            if (booking_list == null ? true : booking_list.isEmpty()) {
                data.add("status", "NO-UPDATE");
                out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
                return;
            }
            Booking b = (Booking) booking_list.get(0);
            if (b == null ? true : b.isNotNull() || b.getDriver_id() == null) {
                data.add("status", "NO-UPDATE");
                out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
                return;
            }
            if (b.getBookingStatus().equals(BookingStatus.WATING_ACCEPTED)) {
                data.add("status", "NO-UPDATE");
                out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
                return;
            }

            XArrayList driver_list = AbstractEntity.readDataFormCsv(new Driver());
            driver_list = driver_list.binarySearchAndSort("user_id", b.getDriver_id(), Driver.class);
            if (driver_list == null ? true : driver_list.isEmpty() || driver_list.get(0) == null) {
                data.add("status", "NO-UPDATE");
                out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
                return;
            }

            // Have update
            Driver d = (Driver) driver_list.get(0);
            // ouser phone, ouser name, ouser profile picture, booking status name
            // ouser_phone, ouser_name, ouser_profile, booking_status
            data.add("status", "OK");
            data.add("ouser_phone", d.getPhoneNumber() == null ? "" : d.getPhoneNumber());
            data.add("ouser_name", d.getName());
            data.add("booking_status", b.getBookingStatus());
            data.add("ouser_profile", Functions.getProfilePic_byid(d.getId()));
            out.print(gson.toJson(map, WebConfig.WRITING_CLASS));
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
