/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adt.XArrayList;
import adt.XHashedDictionary;
import adt.XQueue;
import entity.Admin;
import entity.Booking;
import entity.Customer;
import entity.Driver;
import entity.InfoMessage;

/**
 * To store the environment data
 *
 * @author Lim Sai Keat
 */
public class Datas {

    /**
     *
     * @author Lim Sai Keat web page setting in the dictionary
     */
    public static XHashedDictionary<String, Object> settings;

    // That's right.
    public static final XHashedDictionary<String, Class> TYPE_SWITCH
            = new XHashedDictionary<String, Class>();

    static {
        TYPE_SWITCH.add("int", Integer.TYPE);
        TYPE_SWITCH.add("long", Long.TYPE);
        TYPE_SWITCH.add("boolean", Boolean.TYPE);
        TYPE_SWITCH.add("double", Double.TYPE);
        TYPE_SWITCH.add("float", Float.TYPE);
        TYPE_SWITCH.add("bool", Boolean.TYPE);
        TYPE_SWITCH.add("char", Character.TYPE);
        TYPE_SWITCH.add("byte", Byte.TYPE);
        TYPE_SWITCH.add("void", Void.TYPE);
        TYPE_SWITCH.add("short", Short.TYPE);
        TYPE_SWITCH.add("short", Short.TYPE);
    }

    /**
     *
     * @author Lim Sai Keat all the online driver
     */
    public static XArrayList<Driver> drivers;

    /**
     *
     * @author Lim Sai Keat all the online admin
     */
    public static XArrayList<Admin> admins;

    /**
     *
     * @author Lim Sai Keat all the online customer
     */
    public static XArrayList<Customer> customers;

    /**
     *
     * @author all the current request booking
     */
    public static XQueue<Booking> currentBooking;

    /**
     * Waiting Driver
     */
    public static XQueue<Booking> waitingDriver;

    /**
     *
     * @author Lim Sai Keat all the current request booking
     */
    public static XArrayList<InfoMessage> allMessage;

    /**
     *
     * @author Lim Sai Keat doing start up
     */
    public static int stsrtUp = main.Functions.startUpInitialData();

    /**
     *
     * @author Lim sai keat admin bar status
     */
    public static XArrayList<Boolean> admin_bar_status;

    /**
     * Prevent too many time of reload array
     *
     * @author Lim sai keat use pages footer is fixed
     */
    public static XArrayList<String> pages_footer;

    /**
     * Prevent to many reload of array list
     *
     * @author Lim sai keat admin header is fixed
     */
    public static XArrayList<String> admin_header;

    /**
     * Prevent to many reload of array list
     *
     * @author Lim sai keat admin header is fixed
     */
    public static XHashedDictionary<String, XArrayList> updateparam;

}
