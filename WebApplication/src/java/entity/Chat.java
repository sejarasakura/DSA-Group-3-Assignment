/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Chat extends AbstractEntity{

    
    /*
    * Chat details id
    */
    @CsvBindByName
    private String chat_details_id;
    
    /*
    * Chat message
    */
    @CsvBindByName
    private String message;
    
    /*
    * Sending date
    */
    @CsvBindByName
    private Date send_date;
    
    /*
    * reading status
    */
    @CsvBindByName
    private boolean read;

    /*
    * No param construstor
    */
    public Chat() {
    }

    /*
    * Param construstor
    * @param read
    * @param message
    * @param send_date
    * @param chat_details_id
    */
    public Chat(String chat_details_id, String message, Date send_date, boolean read) {
        this.chat_details_id = chat_details_id;
        this.message = message;
        this.send_date = send_date;
        this.read = read;
    }

    public String getChat_details_id() {
        return chat_details_id;
    }

    public void setChat_details_id(String chat_details_id) {
        this.chat_details_id = chat_details_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
    @Override
    public boolean isNotNull() {
        return this.chat_details_id != null && !this.chat_details_id.isEmpty();
    }
    
    @Override
    public boolean id_equals(Object obj) {
        return Objects.equals(this.chat_details_id, ((Chat)obj).chat_details_id);
    }

    @Override
    public int compareTo(Object t) {
        return ((Chat)t).chat_details_id.compareTo(this.chat_details_id);
    }

    @Override
    public String toString() {
        return "Chat{" + "chat_details_id=" + chat_details_id + ", message=" + message + ", send_date=" + send_date + ", read=" + read + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chat other = (Chat) obj;
        if (!Objects.equals(this.chat_details_id, other.chat_details_id)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.send_date, other.send_date);
    }
    
}
