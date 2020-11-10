/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumClass;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import java.util.Arrays;

/**
 *
 * @author ITSUKA KOTORI
 */
public enum BookingStatus implements AbstractEnum{
    
    WATING_ACCEPTED("B1", "Pairing", "We are finding driver for you"), 
    PENDING_RENTING("B2", "Waiting Driver", "Waiting a while, the driver is comming"), 
    PICK_UPKING("B3", "Aready pickup", "You aready in pickup by the driver"), 
    CANCELED_BOOKING("B4", "Cancel entier booking", "You have cancled the booking!!"), 
    PADDING_PAYMENT("B5", "Padding For Payment", "You have complete done your booking, please procide your payment"), 
    EXPIRED("B6", "Booking Expired", "Your booking is expired and not available at now"),
    COMPLETED("B7", "Booking Completed", "Thank for your bookihn and payment");
    
    private final static BookingStatus[] vs = BookingStatus.values();;

    /**
     * Booking Status Saving ID
     */
    @CsvBindByName(column = "booking_status_code")
    private final String code;
    
    /**
     * Booking Status Name / Title
     */
    private final String name;
    
    /**
     * Booking Status Description or output
     */
    private final String decription;
    
    private BookingStatus(String code, String name, String des){
        this.code = code;
        this.name = name;
        this.decription = des;
    }
    
    public static BookingStatus getValue(String string) {
        for(BookingStatus v: vs){
            if(Arrays.asList(v.code.toLowerCase(), v.name.toLowerCase()).contains(string.toLowerCase()))
                return v;
        }
        return null;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getDecription() {
        return decription;
    }
     public static BookingStatus getValue(Object booking_status){
         if(booking_status instanceof BookingStatus)
            return (BookingStatus)booking_status;
        else if(booking_status instanceof String)
            return BookingStatus.getValue((String)booking_status);
        else{
            return null;
        }
     }

    @Override
    public String getStringCode() {
        return this.getCode();
    }
    
}
