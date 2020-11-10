/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adtClass.*;
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
    private adtClass.Queue<Booking> accepted_booking;
    
    public Driver() {
        this(new ArrList(), new Queue(), "", "", "", "", "");
    }
    
    public Driver(ArrList<String> car_plate, Queue<Booking> accepted_booking) {
        this(car_plate, accepted_booking, "", "", "", "", "");
    }
    
    public Driver(ArrList<String> car_plate, Queue<Booking> accepted_booking, String id, String ic, String name, String email, String phoneNumber) {
        super(id, ic, name, email, phoneNumber);
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
        return this.getId().equals(((Driver)obj).getId());
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
