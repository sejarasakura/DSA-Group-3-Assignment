/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adtClass.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Driver extends User<Driver>{
    
    /*
    * Driver car
    */
    private ArrList<Car> cars;    
    
    /*
    * Driver accepted booking
    */
    private adtClass.Queue<Booking> accepted_booking;
    
    public Driver() {
        this(new ArrList(), new Queue(), "", "", "", "", "");
    }
    
    public Driver(ArrList<Car> cars, Queue<Booking> accepted_booking) {
        this(cars, accepted_booking, "", "", "", "", "");
    }
    
    public Driver(ArrList<Car> cars, Queue<Booking> accepted_booking, String id, String ic, String name, String email, String phoneNumber) {
        super(id, ic, name, email, phoneNumber);
        this.cars = cars;
        this.accepted_booking = accepted_booking;
    }
    
    private boolean driver_accept_booking(Booking booking){
        try{
            accepted_booking.enqueue(booking);
            return true;
        }catch(Exception e){
            return false;
        }
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
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
