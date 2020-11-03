/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adtClass.ArrList;
import enumClass.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Customer extends User{

    /*
    * Customer all booking
    */
    private ArrList<Booking> Allbooking;
    
    /*
    * Customer current booking
    */
    private Booking current_booking;

    /*
    * Membership of customer Basic, Premium, and Normal
    */
    private MemberShip memberType;
    
}
