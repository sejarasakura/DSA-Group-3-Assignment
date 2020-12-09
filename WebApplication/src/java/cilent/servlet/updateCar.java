/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.*;
import cilent.IDManager;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.WebConfig;
import xenum.CarType;

/**
 *
 * @author Lim Sai Keat
 */
@WebServlet(name = "updateCar", urlPatterns = {"/updateCar"})
public class updateCar extends HttpServlet {

    private User user;
    private Car car = null;
    private Plate plate = null;
    private XArrayList record;
    private XArrayList p_record;
    private boolean is_taxi = false;
    private String id, action, taxi_id, taxi_license, taxi_company, plate_lic, reg_date, cartype, plate_alpha, plate_num;

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        id = request.getParameter("id");
        action = request.getParameter("action");
        taxi_id = request.getParameter("taxi_id");
        taxi_license = request.getParameter("taxi_license");
        taxi_company = request.getParameter("taxi_company");
        plate_lic = request.getParameter("plate_lic");
        reg_date = request.getParameter("reg_date");
        cartype = request.getParameter("cartype");
        plate_alpha = request.getParameter("plate_alpha");
        plate_num = request.getParameter("plate_num");
        if (taxi_id != null) {
            if (taxi_id.charAt(0) == 'T') {
                is_taxi = true;
            }
        }
        switch (action) {
            case "update":
                if (!read_record()) {
                    break; // No record;
                }
                if (!check_deplicate(response, false)) {
                    return; // assign taxi value and check duplicate
                }
                assign_values(false);
                car.updateThisToCsv();
                plate.updateThisToCsv();
                response.sendRedirect(WebConfig.WEB_URL + "pages/account.jsp#" + car.getPlate_id());
                break;
            case "add":
                if (!read_record()) {
                    break; // No record;
                }
                if (!check_deplicate(response, false)) {
                    return; // assign taxi value and check duplicate
                }
                car = is_taxi ? new Taxi() : new Car();
                plate = new Plate();
                user = main.Functions.getUserSession(request);
                main.Functions.checkLogin(response, user);
                assign_values(true);
                car.addThisToCsv();
                plate.addThisToCsv();
                String callback = request.getParameter("callback");
                response.sendRedirect(WebConfig.WEB_URL + callback == null ? "pages/new_car.jsp" : callback + "#" + car.getPlate_id());
                break;
        }
    }

    /**
     * read the match record to car/taxi and plate number
     *
     * @return true for reading success; false for reading fail
     */
    private boolean read_record() {

        if (is_taxi) {
            record = AbstractEntity.readDataFormCsv(new Taxi());
            if (record == null) {
                return false;
            }
            car = (Taxi) record.searchByField("plate_id", id, Taxi.class).get(0);
        } else {
            record = AbstractEntity.readDataFormCsv(new Car());
            if (record == null) {
                return false;
            }
            car = (Car) record.searchByField("plate_id", id, Car.class).get(0);
        }
        p_record = AbstractEntity.readDataFormCsv(new Plate());
        if (p_record == null) {
            return false;
        }
        plate = (Plate) p_record.searchByField("plate_id", id, Plate.class).get(0);
        return true;
    }

    /**
     *
     * @param response the server response
     * @param add if case to add new;
     * @return true if success; false if fail
     * @throws IOException
     */
    private boolean check_deplicate(HttpServletResponse response, boolean add) throws IOException {
        if (is_taxi) {
            if (car == null ? true : !taxi_license.equals(((Taxi) car).getTaxiLicense())) {
                if (record.searchByField("taxiLicense", taxi_license, Taxi.class).size() > 0) {
                    response.sendRedirect(WebConfig.WEB_URL + "pages/account.jsp?I=I-0013");
                    return false;
                }
            }
            if (car == null ? true : !taxi_id.equals(((Taxi) car).getTaxiId())) {
                if (record.searchByField("taxiId", taxi_id, Taxi.class).size() > 0) {
                    response.sendRedirect(WebConfig.WEB_URL + "pages/account.jsp?I=I-0014");
                    return false;
                }
            }
        }
        String full_alpha = String.join(plate_alpha, " ", plate_num);
        if (plate == null ? true : !plate.getFullPlateNumber().equals(full_alpha)) {
            XArrayList data = (XArrayList) p_record.searchByField("getFullPlateNumber", full_alpha, Plate.class);
            if (data.size() > 0) {
                response.sendRedirect(WebConfig.WEB_URL + "pages/account.jsp?I=I-0015");
                return false;
            }
        }
        return true;
    }

    private void assign_values(boolean add) throws ParseException {
        if (is_taxi) {
            ((Taxi) car).setTaxiLicense(taxi_license);
            ((Taxi) car).setTaxiCompany(taxi_company);
            ((Taxi) car).setTaxiId(taxi_id);
        }
        car.setLicense(plate_lic);
        if (add) {
            car.setRegDate(new Date());
        }
        car.setCarType(CarType.getValue(cartype));
        plate.setPlateNumber(plate_num);
        plate.setPlateAlpha(plate_alpha);

        if (!add) {
            return;
        }

        id = (String) IDManager.generateId(new Plate(), true);
        car.setPlate_id(id);
        plate.setPlate_id(id);
        car.setDriver_id(user.getId());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(updateCar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(updateCar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
