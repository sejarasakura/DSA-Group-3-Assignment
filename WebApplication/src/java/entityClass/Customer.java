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

    public Customer(ArrList<Booking> Allbooking, Booking current_booking, MemberShip memberType) {
        this.Allbooking = Allbooking;
        this.current_booking = current_booking;
        this.memberType = memberType;
    }

    public Customer(ArrList<Booking> Allbooking, Booking current_booking, MemberShip memberType, String id, String ic, String name, String email, String phoneNumber) {
        super(id, ic, name, email, phoneNumber);
        this.Allbooking = Allbooking;
        this.current_booking = current_booking;
        this.memberType = memberType;
    }

    public ArrList<Booking> getAllbooking() {
        return Allbooking;
    }

    public void setAllbooking(ArrList<Booking> Allbooking) {
        this.Allbooking = Allbooking;
    }

    public Booking getCurrent_booking() {
        return current_booking;
    }

    public void setCurrent_booking(Booking current_booking) {
        this.current_booking = current_booking;
    }

    public MemberShip getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberShip memberType) {
        this.memberType = memberType;
    }
    
    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean split(String rowData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Customer{" + "Allbooking=" + Allbooking + ", current_booking=" + current_booking + ", memberType=" + memberType + ", " + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
