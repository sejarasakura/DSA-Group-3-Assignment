
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adtClass.ArrList;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import enumClass.*;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Customer extends User<Customer>{

    /*
    * particular ustomer all booking
    */
    @CsvBindByName
     private ArrList<String> add_booking_id;
    
    /*
    * Customer current booking
    */
    @CsvBindByName
    private Booking current_booking;

    /*
    * Membership of customer Basic, Premium, and Normal
    */
    @CsvBindByName
    private MemberShip memberType;

    public Customer() {
    }

    public Customer(ArrList<String> Allbooking, Booking current_booking, MemberShip memberType) {
        this.add_booking_id = Allbooking;
        this.current_booking = current_booking;
        this.memberType = memberType;
    }

    public Customer(ArrList<String> Allbooking, Booking current_booking, MemberShip memberType, String id, String ic, String name, String email, String phoneNumber) {
        super(id, ic, name, email, phoneNumber);
        this.add_booking_id = Allbooking;
        this.current_booking = current_booking;
        this.memberType = memberType;
    }

    public ArrList<String> getAdd_booking_id() {
        return add_booking_id;
    }

    public void setAdd_booking_id(ArrList<String> add_booking_id) {
        this.add_booking_id = add_booking_id;
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
        return "Customer{" + "Allbooking=" + add_booking_id + ", current_booking=" + current_booking + ", memberType=" + memberType + ", " + '}';
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
    
    public static void main(String[] args) {
        try {
            main2();
        } catch (IOException ex) {
            Logger.getLogger(Withdraw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main2() throws IOException {

        try {
            // name of generated csv
            Customer x = new Customer();
            x.setId("C0011");
            x.setId("C0011");
            ArrList<String> d = new ArrList<String>();
            d.add("Haha");
            d.add("gG.COM");
            d.add("I CAN FLY");
            x.setAdd_booking_id(d); 
            
            ArrList<Customer> employees = new ArrList<>();
            employees.add(x);
            
            Writer writer = Files.newBufferedWriter(Paths.get(x.getStorageDir()));
            StatefulBeanToCsv<Customer> csvWriter = new StatefulBeanToCsvBuilder<Customer>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .withOrderedResults(false)
                    .build();
            csvWriter.write(employees.iterator());
            writer.close();
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            Logger.getLogger(Withdraw.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
