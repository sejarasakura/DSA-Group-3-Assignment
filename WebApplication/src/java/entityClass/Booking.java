/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import com.opencsv.bean.*;
import enumClass.BookingStatus;
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
    
    @CsvRecurse
    private BookingStatus bookingStatus;

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
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
