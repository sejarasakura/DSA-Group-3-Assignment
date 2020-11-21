/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adt.ArrList;
import adt.XHashedDictionary;
import adt.XQueue;
import entity.Booking;
import entity.Customer;
import entity.Driver;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Datas {

    /**
     *
     * @author ITSUKA KOTORI web page setting in the dictionary
     */
    public static XHashedDictionary<String, Object> settings;

    // That's right.
    public static final XHashedDictionary<String, Class> TYPE_SWITCH = new XHashedDictionary<String, Class>();

    static {
        TYPE_SWITCH.add("int", Integer.TYPE);
        TYPE_SWITCH.add("long", Long.TYPE);
        TYPE_SWITCH.add("double", Double.TYPE);
        TYPE_SWITCH.add("float", Float.TYPE);
        TYPE_SWITCH.add("bool", Boolean.TYPE);
        TYPE_SWITCH.add("char", Character.TYPE);
        TYPE_SWITCH.add("byte", Byte.TYPE);
        TYPE_SWITCH.add("void", Void.TYPE);
        TYPE_SWITCH.add("short", Short.TYPE);
    }

    /**
     *
     * @author ITSUKA KOTORI all the online driver
     */
    public static ArrList<Driver> drivers;

    /**
     *
     * @author ITSUKA KOTORI all the online customer
     */
    public static ArrList<Customer> customers;

    /**
     *
     * @author ITSUKA KOTORI all the current request booking
     */
    public static XQueue<Booking> currentBooking;

    /**
     *
     * @author ITSUKA KOTORI doing start up
     */
    public static int stsrtUp = main.Functions.startUpInitialData();

}
