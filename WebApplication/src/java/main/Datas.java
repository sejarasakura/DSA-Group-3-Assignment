/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adtClass.*;
import entityClass.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Datas {
    

    /**
     *
     * @author ITSUKA KOTORI
     * web page setting in the dictionary
     */
    public static HashedDictionary<String, Object> settings;
    
    /**
     *
     * @author ITSUKA KOTORI 
     * all the driver
     */
    public static ArrList<Driver> drivers;
    
    /**
     *
     * @author ITSUKA KOTORI 
     * all the car
     */
    public static ArrList<Car> cars;
    
    /**
     *
     * @author ITSUKA KOTORI 
     * all the customer
     */
    public static ArrList<Customer> customers;
    
    /**
     *
     * @author ITSUKA KOTORI 
     * all the customer
     */
    public static Queue<Booking> currentBooking;
    
}
