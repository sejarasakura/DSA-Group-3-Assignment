/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.Queue;
import adt.ArrList;
import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Driver extends User<Driver>{
    
    /*
    * Driver car
    */
    @CsvBindByName
    private ArrList<String> car_plate;    
    
    /*
    * Driver accepted booking
    */
    private adt.Queue<Booking> accepted_booking;
    
    public Driver() {
        super();
    }

    public Driver(ArrList<String> car_plate, Queue<Booking> accepted_booking) {
        super();
        this.car_plate = car_plate;
        this.accepted_booking = accepted_booking;
    }

    public Driver(ArrList<String> car_plate, Queue<Booking> accepted_booking, String user_id, String ic, String name, String email, String phoneNumber, String role, String username, String password) {
        super(user_id, ic, name, email, phoneNumber, role, username, password);
        this.car_plate = car_plate;
        this.accepted_booking = accepted_booking;
    }
    
    
    public ArrList<Plate> getArrListPlate(){
        
        if(car_plate == null)
            return null;
        if(car_plate.size() <= 0)
            return null;
                    
        ArrList<Plate> r = new ArrList<Plate>();
        
        for(String p : car_plate){
            r.add(Plate.getPlate(p, " "));
        }
        
        return r;
    }
    
    public Plate getPlate(int x){
        return Plate.getPlate(car_plate.get(x), " ");
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
    public boolean id_equals(Object obj) {
        return this.getUser_id().equals(((Driver)obj).getUser_id());
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

    @Override
    public User login(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User register(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
