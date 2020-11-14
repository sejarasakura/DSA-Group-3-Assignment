
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.ArrList;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Customer extends User <Customer>{

    /*
    * particular ustomer all booking
    */
    @CsvBindByName
     private ArrList<String> all_booking_id;
    
    /*
    * Customer current booking
    */
    @CsvBindByName
    private String current_booking_id;

    /*
    * Membership of customer Basic, Premium, and Normal
    */
    @CsvBindByName
    private String memberType_id;

    public Customer() {
    }

    public Customer(ArrList<String> Allbooking, String current_booking, String memberType) {
        this.all_booking_id = Allbooking;
        this.current_booking_id = current_booking;
        this.memberType_id = memberType;
    }

    public Customer(ArrList<String> all_booking_id, String current_booking_id, String memberType_id, String user_id, String ic, String name, String email, String phoneNumber, String role, String username, String password) {
        super(user_id, ic, name, email, phoneNumber, role, username, password);
        this.all_booking_id = all_booking_id;
        this.current_booking_id = current_booking_id;
        this.memberType_id = memberType_id;
    }

    public ArrList<String> getAll_booking_id() {
        return all_booking_id;
    }

    public void setAll_booking_id(ArrList<String> all_booking_id) {
        this.all_booking_id = all_booking_id;
    }

    public String getCurrent_booking_id() {
        return current_booking_id;
    }

    public void setCurrent_booking_id(String current_booking_id) {
        this.current_booking_id = current_booking_id;
    }

    public String getMemberType_id() {
        return memberType_id;
    }

    public void setMemberType_id(String memberType_id) {
        this.memberType_id = memberType_id;
    }
    
    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Customer{" + "Allbooking=" + all_booking_id + ", current_booking=" + current_booking_id + ", memberType=" + memberType_id + ", " + '}';
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
    public boolean id_equals(Object obj) {
        return this.getUser_id().equals(((Customer)obj).getUser_id());
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
            x.setUser_id("C0011");
            x.setUser_id("C0011");
            ArrList<String> d = new ArrList<String>();
            d.add("Haha");
            d.add("gG.COM");
            d.add("I CAN FLY");
            x.setAll_booking_id(d); 
            
            ArrList<Customer> employees = new ArrList<>();
            employees.add(x);
            
            Writer writer = Files.newBufferedWriter(Paths.get(x.getStorageFile()));
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

    @Override
    public User login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User register() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
