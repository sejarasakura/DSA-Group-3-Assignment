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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "reload_chat_api", urlPatterns = {"/reload_chat_api"})
public class reload_chat_api extends HttpServlet {

    /**
     * Current open chat queue
     */
    User user;
    Chats chats;
    XQueue<Chat> chat;
    XArrayList temp;
    private String target;
    private StringBuilder sb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sb = new StringBuilder();
        user = main.Functions.getUserSession(request);
        if (response.isCommitted()) {
            return;
        }
        if (!main.Functions.checkLogin(response, user)) {
            return;
        }
        target = request.getParameter("target");
        if (target == null ? true : target.isEmpty()) {
            return;
        }
        XArrayList<Chat> details_record = (XArrayList<Chat>) AbstractEntity.readDataFormCsv(new Chat());
        XArrayList<Chats> chat_record = (XArrayList<Chats>) AbstractEntity.readDataFormCsv(new Chats());
        details_record.sort("chat_details_id", Chat.class);
        chat_record = (XArrayList<Chats>) chat_record.searchByField("chats_id", target, Chats.class);

        if (chat_record == null ? true : chat_record.isEmpty()) {
            return;
        }
        chats = chat_record.get(0);
        XArrayList details = chats.getChats_details_id();
        XArrayList temp_result;
        temp = new XArrayList();
        for (int i = 0; i < details.size(); i++) {
            temp_result = (XArrayList) details_record.binarySearch("chat_details_id", (Comparable) details.get(i), Chat.class);
            if (temp_result != null) {
                if (!temp_result.isEmpty()) {
                    temp.add((Chat) temp_result.get(0));
                }
            }
        }
        temp.sort("send_date", Chat.class);
        chat = new XQueue(temp);
        start_write_mesage();
        System.out.print(temp.toString());
        response.getWriter().write(sb.toString());
    }

    private void start_write_mesage() {
        boolean is_u1 = user.getUser_id().equals(chats.getUser_id_1()), send;
        for (Object x : temp.toArray()) {
            Chat temp_chat = (Chat) x;
            if (is_u1 ? temp_chat.getSend_by_u1() : !temp_chat.getSend_by_u1()) {
                outgoing_message(temp_chat);
            } else {
                income_message(chats.getUser_id_1(), temp_chat);
            }
        }
    }

    private void income_message(String img_id, Chat temp_chat) {
        //<div class="outgoing_msg">
        sb.append("<div class=\"incoming_msg\">");

        //<div class="incoming_msg_img">
        //   <img src="" alt="">
        //</div>
        sb.append("<div class=\"incoming_msg_img\"> ");
        sb.append("<img class='img-circle' src=\"");
        sb.append(Functions.getProfilePic_byid(img_id));
        sb.append("\" alt=\"profile picture\"> ");
        sb.append("</div>");

        //    <div class="sent_msg">
        sb.append("<div class=\"received_msg\"><div class=\"received_withd_msg\">");

        //        <p>Apollo University, Delhi, India Test</p>
        sb.append("<p>");
        sb.append(temp_chat.getMessage());
        sb.append("</p>");

        //        <span class="time_date"> 11:01 AM | Today</span>
        sb.append("</div><span class=\"time_date\">");
        sb.append(WebConfig.CHAT_FORMAT_LONG.format(temp_chat.getSend_date()));
        sb.append(" | ");
        sb.append(Functions.getTimeAgo(temp_chat.getSend_date().getTime()));
        sb.append("</span>");

        //    </div>
        //</div>
        sb.append("</div></div>");
    }

    private void outgoing_message(Chat temp_chat) {
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
