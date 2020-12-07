
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.XArrayList;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import csv.converter.MemberShipConverter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import xenum.MemberShip;

/**
 *
 * @author Lai Hong Wah
 */
public class Customer extends User<Customer> {

    /*
    * Customer current booking
     */
    @CsvBindByName
    private String current_booking_id;

    /*
    * Membership of customer Basic, Premium, and Normal
     */
    @CsvCustomBindByName(converter = MemberShipConverter.class, column = "memberType_id")
    private MemberShip memberType;

    public Customer() {
    }

    public Customer(String current_booking_id, String memberType_id,
            String user_id, String ic, String name, String email,
            String phoneNumber, String username, String password) {
        super(user_id, ic, name, email, phoneNumber, "c", username, password);
        this.current_booking_id = current_booking_id;
        this.memberType = MemberShip.getValue(memberType_id);
    }

    public String getCurrent_booking_id() {
        return current_booking_id;
    }

    public void setCurrent_booking_id(String current_booking_id) {
        this.current_booking_id = current_booking_id;
    }

    public MemberShip getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberShip memberType) {
        this.memberType = memberType;
    }

    @Override
    public boolean isNotNull() {
        return super.isNotNull();
    }

    @Override
    public String toString() {
        return "Customer{" + "current_booking_id=" + current_booking_id + ", memberType_id=" + memberType + ", " + super.toString() + '}';
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
        return this.getUser_id().equals(((Customer) obj).getUser_id());
    }

    public static void main(String[] args) {
        try {
            main3(Customer.class);
        } catch (IOException ex) {
            Logger.getLogger(Withdraw.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main3(Class cls) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Customer x = new Customer();
        x.setName("Lim sai keat");
        x.setRole("c");
        x.setIc("991004091029");
        x.setUser_id("94032523");
        x.setEmail("94032523");
        x.setPhoneNumber("94032523");
        x.setUsername("94032523");
        x.setCurrent_booking_id("94032523");
        x.setPassword("94032523");
        XArrayList<Field> field = new XArrayList(cls.getDeclaredFields());
        Class<?> current = cls;
        Class c = Class.forName("java.lang.String");
        while (current.getSuperclass() != null) {
            current = current.getSuperclass();
            field.addAll(current.getDeclaredFields());
        }
        for (Field f : field) {
            if (f.isAnnotationPresent(CsvBindByName.class)) {

            }
        }
    }

    public static void main2() throws IOException {

        try {
            // name of generated csv
            Customer x = new Customer();
            x.setUser_id("C0011");
            x.setUser_id("C0011");
            XArrayList<String> d = new XArrayList<String>();
            d.add("Haha");
            d.add("gG.COM");
            d.add("I CAN FLY");

            XArrayList<Customer> employees = new XArrayList<>();
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
    public User login(User user) {
        return null;
    }

    @Override
    public User register(User user) {
        return null;
    }
}
