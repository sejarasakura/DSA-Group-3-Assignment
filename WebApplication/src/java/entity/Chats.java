/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.XArrayList;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import csv.converter.ArrListConverter;

/**
 *
 * @author Lai Hong Wah
 */
public class Chats extends AbstractEntity {

    @CsvBindByName
    private String chats_id;

    @CsvBindByName
    private String user_id_1;

    @CsvBindByName
    private String user_id_2;

    @CsvCustomBindByName(converter = ArrListConverter.class)
    private XArrayList<String> chats_details_id;

    public Chats() {
    }

    public Chats(String chats_id, String user_id_1, String user_id_2, XArrayList<String> chats_details_id) {
        this.chats_id = chats_id;
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
        this.chats_details_id = chats_details_id;
    }

    public String getUser_id_1() {
        return user_id_1;
    }

    public void setUser_id_1(String user_id_1) {
        this.user_id_1 = user_id_1;
    }

    public String getUser_id_2() {
        return user_id_2;
    }

    public void setUser_id_2(String user_id_2) {
        this.user_id_2 = user_id_2;
    }

    public String getChats_id() {
        return chats_id;
    }

    public void setChats_id(String chats_id) {
        this.chats_id = chats_id;
    }

    public XArrayList<String> getChats_details_id() {
        return chats_details_id;
    }

    public void setChats_details_id(XArrayList<String> chats_details_id) {
        this.chats_details_id = chats_details_id;
    }

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.chats_id.equals(((Chats) obj).chats_id);
    }

    @Override
    public String toString() {
        return "Chats{" + "chats_id=" + chats_id + ", chats_details_id=" + chats_details_id + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return this.chats_id;
    }
}
