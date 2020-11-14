/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.*;
import xenum.BookingStatus;
import java.util.Date;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Booking extends AbstractEntity<Booking> {

    @CsvBindByName
    private String booking_id;
    
    @CsvBindByName
    private Date booking_date;
    
    @CsvBindByName
    @CsvDate("dd.MM.yyyy.hh.mm.ss")
    private Date fetch_date;
    
    @CsvBindByName(column = "booking_status_code")
    private String bookingStatus;

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean id_equals(Object obj) {
        return this.booking_id.equals(((Booking)obj).booking_id);
    }

}
