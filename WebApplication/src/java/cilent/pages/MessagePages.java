/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

/**
 * Local project import
 */
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static main.WebConfig.CHAT_FORMAT;
import xenum.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public final class MessagePages extends AbstractPage {

    /**
     * Current login user
     */
    User user;
    /**
     * All chat
     */
    XArrayList<Chat> all_chat;
    /**
     * Current user chat list
     */
    XArrayList<Chats> used_chat;
    /**
     * Used id
     */
    XArrayList<User> used_users;
    /**
     * Chat id that target
     */
    String target;
    boolean target_changed = false;
    /**
     * Chat list string builder
     */
    StringBuilder chatListSB;

    HttpServletResponse response;

    public MessagePages(HttpServletRequest request, HttpServletResponse response, String target) {
        super(request);
        this.response = response;
        this.target = target;
        retiveThisFormSession();
        try {
            boolean success;
            if (user == null) {
                success = init();
            }
        } catch (IOException ex) {
            Logger.getLogger(MessagePages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean init() throws IOException {
        user = main.Functions.getUserSession(request);
        if (!response.isCommitted()) {
            if (!main.Functions.checkLogin(response, user)) {
                return false;
            }
        }

        switch (user.getRole().toUpperCase()) {
            case "C":
                search_message(Customer.class, new Driver());
                break;
            case "D":
                search_message(Driver.class, new Customer());
                break;
            default:
                return false;
        }
        return true;
    }

    private void search_message(Class<?> _user, User _reference) {
        boolean is_customer = _user != Customer.class;
        String field = getField(_user);
        // read user data eg. user is customer read driver data
        XArrayList<User> users = (XArrayList<User>) AbstractEntity.readDataFormCsv(_reference);

        // read booking data
        XArrayList<Booking> booking = (XArrayList<Booking>) AbstractEntity.readDataFormCsv(new Booking());
        XArrayList<Chats> all_chat = (XArrayList<Chats>) AbstractEntity.readDataFormCsv(new Chats());

        // Filter out the not related booking
        booking = (XArrayList<Booking>) booking.searchByField(field, user.getId(), Booking.class);

        // Convert to sort list and sort by date
        XArraySortList sort_booking = new XArraySortList(booking);
        sort_booking.sortDesc("booking_date", Booking.class);

        // Assign new checking
        used_users = new XArrayList<User>();
        XArrayList<String> temp_users = new XArrayList<String>();
        InterList<User> temp_search_result;
        XArrayList<Booking> used_booking = new XArrayList<Booking>();
        used_chat = new XArrayList();

        String id;
        for (int i = 0; i < sort_booking.size(); i++) {
            id = is_customer ? ((Booking) sort_booking.get(i)).getCustomer_id() : ((Booking) sort_booking.get(i)).getDriver_id();
            if (temp_users.search_once(id) < 0) {
                temp_users.add(id);
                temp_search_result = users.searchByField("user_id", id, _user);
                used_users.add((User) temp_search_result.get(0));
                used_booking.add((Booking) sort_booking.get(i));
                used_chat.add(
                        all_chat.searchByField("chats_id", ((Booking) sort_booking.get(i)).getChats_id(), Chats.class).get(0)
                );
            }
        }
        saveThisToSession();
    }

    private void saveThisToSession() {
        request.getSession().setAttribute("MESSAGE_PAGE", this);
    }

    private void retiveThisFormSession() {
        MessagePages mp = (MessagePages) request.getSession().getAttribute("MESSAGE_PAGE");
        if (mp != null) {
            this.used_chat = mp.used_chat;
            this.used_users = mp.used_users;
            this.user = mp.user;
        }
    }

    public static MessagePages retiveFormSession(HttpServletRequest request, HttpServletResponse response, String target) {
        MessagePages r = (MessagePages) request.getSession().getAttribute("MESSAGE_PAGE");
        if (r == null) {
            return null;
        }
        r.request = request;
        r.response = response;
        r.target_changed = (r.target == null ? target != null : !r.target.equals(target));
        r.target = target;
        return r;
    }

    private String getField(Class<?> _user) {
        if (_user == Customer.class) {
            return "customer_id";
        } else if (_user == Driver.class) {
            return "driver_id";
        }
        return null;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getChatList() {
        createChatList();
        return chatListSB.toString();
    }

    @Override
    public String getHtml() {
        return stringBuilder.toString();
    }

    @Override
    public XArrayList<String> getHtmls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createChatList() {
        if (target == null) {
            target = this.used_chat.get(0).getChats_id();
        }
        all_chat = null;
        // Start write message
        chatListSB = new StringBuilder();
        chatListSB.append("<div class=\"inbox_chat\">");
        for (int i = 0; i < this.used_users.size(); i++) {
            if (used_users.get(i) != null) {
                getSinggleChat(i);
            }
        }
        chatListSB.append("</div>");
    }

    private void getSinggleChat(int i) {
        if (all_chat == null) {
            all_chat = (XArrayList<Chat>) AbstractEntity.readDataFormCsv(new Chat());
        }
        XArrayList ref = used_chat.get(i).getChats_details_id();
        String last_chat = (String) ref.get(ref.size() - 1);
        XArrayList temp_list = (XArrayList) all_chat.searchByField("chat_details_id", last_chat, Chat.class);
        Chat temp = temp_list == null ? null : (Chat) temp_list.get(0);

        //<a href="">
        chatListSB.append("<a href=\"");
        chatListSB.append(WebConfig.WEB_URL);
        chatListSB.append("pages/chat.jsp?target=");
        chatListSB.append(used_chat.get(i).getChats_id());
        chatListSB.append("\" class=\"deco-none\">");

        //<div class="chat_list active_chat">
        chatListSB.append("<div class=\"chat_list ");
        chatListSB.append(used_chat.get(i).getChats_id().equals(target) ? "active_chat" : "");
        chatListSB.append(" \">");

        //   <div class="chat_people">
        chatListSB.append("<div class=\"chat_people\">");

        //       <div class="chat_img"> <img src="{image}" alt="{user_name}">
        chatListSB.append("<div class=\"chat_img\"> ");
        chatListSB.append("<img class=\"img-circle\" src=\"");
        chatListSB.append(Functions.getProfilePic_byid(used_users.get(i).getId()));
        chatListSB.append("\" alt=\"");
        chatListSB.append(used_users.get(i).getName());
        chatListSB.append("\" width=\"40\" height=\"40\" >");

        //      </div>
        //      <div class="chat_ib">
        chatListSB.append("</div><div class=\"chat_ib\">");

        //          <h5>{user_name}<span class="chat_date">{chat_date}</span></h5>
        chatListSB.append("<h5>");
        chatListSB.append(used_users.get(i).getName());
        chatListSB.append("<span class=\"chat_date\">");
        chatListSB.append(temp == null ? "" : CHAT_FORMAT.format(temp.getSend_date()));
        chatListSB.append("</span></h5>");

        //          <p>{last message}</p>
        chatListSB.append("<p>");
        chatListSB.append(temp == null ? "" : temp.getMessage());
        chatListSB.append("</p>");

        //      </div>
        //  </div>
        //</div>
        //</a>
        chatListSB.append("</div></div></div></a>");
    }

}
