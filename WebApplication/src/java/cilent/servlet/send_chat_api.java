/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.servlet;

import adt.XArrayList;
import cilent.IDManager;
import entity.AbstractEntity;
import entity.Chat;
import entity.Chats;
import entity.User;
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

/**
 *
 * @author ITSUKA KOTORI
 */
@WebServlet(name = "send_chat_api", urlPatterns = {"/send_chat_api"})
public class send_chat_api extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private User user;
    private String target;
    private Chats chats;
    private HttpServletRequest request;
    private HttpServletResponse response;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.request = request;
        this.response = response;
        StringBuilder sb = add_chat();
        try (PrintWriter out = response.getWriter()) {
            if (sb == null) {
                out.print("error");
                return;
            }
            out.print(sb.toString());
        }
    }

    private StringBuilder add_chat() throws IOException {
        user = main.Functions.getUserSession(request);
        if (response.isCommitted()) {
            return null;
        }
        if (!main.Functions.checkLogin(response, user)) {
            return null;
        }

        target = request.getParameter("target");
        String message = request.getParameter("message");

        if (target == null ? target.isEmpty() : false) {
            return null;
        }
        if (message == null ? message.isEmpty() : false) {
            return null;
        }

        if (!tryGetSession(request)) {
            XArrayList<Chats> chat_record = (XArrayList<Chats>) AbstractEntity.readDataFormCsv(new Chats());
            chat_record = (XArrayList<Chats>) chat_record.searchByField("chats_id", target, Chats.class);
            if (chat_record == null ? true : chat_record.isEmpty()) {
                return null;
            }

            chats = chat_record.get(0);
        }
        saveToSession(request, chats);

        boolean check = chats.getUser_id_1().equals(user.getId());
        Chat chat = new Chat();

        chat.setChat_details_id((String) IDManager.generateId(chat, true));
        chat.setMessage(message);
        chat.setSend_date(new Date());
        chat.setRead(false);
        chat.setSend_by_u1(check);

        chats.addChats_details_id(chat.getChat_details_id());

        chat.addThisToCsv();
        chats.updateThisToCsv();

        return outgoing_message(chat);
    }

    private boolean tryGetSession(HttpServletRequest request) {
        Chats c = (Chats) request.getSession().getAttribute("CHAT_ROOM_01");
        if (c == null) {
            return false;
        }
        if (c.getChats_id() == null ? false : !c.getChats_id().equals(target)) {
            return false;
        }
        // found is same use back to reduce complexcity
        this.chats = c;
        return true;
    }

    private boolean saveToSession(HttpServletRequest request, Chats c) {
        if (c == null) {
            return false;
        }
        request.getSession().setAttribute("CHAT_ROOM_01", c);
        return true;
    }

    private StringBuilder outgoing_message(Chat temp_chat) {
        StringBuilder sb = new StringBuilder();

        //<div class="outgoing_msg">
        //    <div class="sent_msg">
        sb.append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">");

        //        <p>Apollo University, Delhi, India Test</p>
        sb.append("<p>");
        sb.append(temp_chat.getMessage());
        sb.append("</p>");

        //        <span class="time_date"> 11:01 AM | Today</span>
        sb.append("<span class=\"time_date\">");
        sb.append(WebConfig.CHAT_FORMAT_LONG.format(temp_chat.getSend_date()));
        sb.append(" | ");
        sb.append(Functions.getTimeAgo(temp_chat.getSend_date().getTime()));
        sb.append("</span>");

        //    </div>
        //</div>
        sb.append("</div></div>");

        return sb;
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
