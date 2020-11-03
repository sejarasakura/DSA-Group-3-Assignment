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
    * Customer booking
    */
    private ArrList<Booking> booking;

    /*
    * Membership of customer Basic, Premium, and Normal
    */
    private MemberShip memberType;
    
}
