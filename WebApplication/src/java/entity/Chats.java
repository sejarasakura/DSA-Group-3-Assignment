/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.ArrList;
import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Chats {
    
    @CsvBindByName
    private String chats_id;
    
    @CsvBindByName
    private ArrList<String> chats_details_id;

    public String getChats_id() {
        return chats_id;
    }

    public void setChats_id(String chats_id) {
        this.chats_id = chats_id;
    }

    public ArrList<String> getChats_details_id() {
        return chats_details_id;
    }

    public void setChats_details_id(ArrList<String> chats_details_id) {
        this.chats_details_id = chats_details_id;
    }
    
    
}
