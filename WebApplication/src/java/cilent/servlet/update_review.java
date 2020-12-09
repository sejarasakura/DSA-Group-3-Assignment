/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.XArrayList;
import cilent.IDManager;
import entity.AbstractEntity;
import entity.Review;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Functions;
import main.WebConfig;

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "update_review", urlPatterns = {"/update_review"})
public class update_review extends HttpServlet {

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
            String query = request.getParameter("query");
            String cid = request.getParameter("cid");
            String did = request.getParameter("did");
            String id = request.getParameter("pid");
            String c_size = request.getParameter("c_size");
            String star = request.getParameter("star");
            String comment = request.getParameter("u_comment");
            String rtitle = request.getParameter("rtitle");
            if (!query.equals("add")) {
                XArrayList<Review> reviews;
                reviews = (XArrayList<Review>) AbstractEntity.readDataFormCsv(new Review());
                reviews.sort("paymentNumber", Review.class);
                reviews = reviews.binarySearch("paymentNumber", id, Review.class);
                if (reviews.size() <= 0) {
                    response.sendRedirect("pages/customer-review.jsp");
                    return;
                }
                Review review = reviews.get(0);
                if (comment != null) {
                    XArrayList x = new XArrayList(review.getComments());
                    x.add(comment);
                    review.setComments(x);
                }
                review.setReviewDate(Functions.currentDate());
                review.setDriverRating(Integer.parseInt(star));
                review.setCustomerUserName(cid);
                review.setDriverUserName(did);
                review.setTitle(rtitle);
                review.updateThisToCsv();
                out.print("The review is updated successful");
                out.print(review);
                out.print("<br>");
            } else {
                Review review = new Review();
                XArrayList ar = new XArrayList();
                if (comment != null) {
                    ar.add(comment);
                    review.setComments(ar);
                }
                review.setComments(ar);
                review.setReviewDate(Functions.currentDate());
                review.setDriverRating((int) Math.round(Double.parseDouble(star)));
                review.setCustomerUserName(cid);
                review.setDriverUserName(did);
                review.setTitle(rtitle);
                review.setPaymentNumber(id);
                review.addThisToCsv();
                out.print("The review is added successful");
                out.print(review);
                out.print("<br>");
            }
            out.printf("<a href=\"%s/pages/customer-review.jsp\">Return back</a>", WebConfig.WEB_URL);
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
