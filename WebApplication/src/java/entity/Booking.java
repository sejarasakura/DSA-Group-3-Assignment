/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.*;
import csv.converter.BookingStatusConverter;
import java.util.Date;
import xenum.BookingStatus;
import xenum.CarType;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Booking extends AbstractEntity<Booking> {

    @CsvBindByName
    private String booking_id;

    @CsvBindByName
    private String booking_description;

    @CsvBindByName
    private CarType booking_type;

    @CsvBindByName
    @CsvDate(main.WebConfig.SAVING_DATE_FORMAT)
    private Date booking_date;

    @CsvBindByName
    private String driver_id;

    @CsvBindByName
    private String customer_id;

    @CsvBindByName
    private String chats_id;

    @CsvBindByName
    private String mapping_id;

    @CsvBindByName
    private String paymentNumber;

    @CsvCustomBindByName(converter = BookingStatusConverter.class, column = "booking_status_code")
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_description() {
        return booking_description;
    }

    public void setBooking_description(String booking_description) {
        this.booking_description = booking_description;
    }

    public CarType getBooking_type() {
        return booking_type;
    }

    public void setBooking_type(CarType booking_type) {
        this.booking_type = booking_type;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getChats_id() {
        return chats_id;
    }

    public void setChats_id(String chats_id) {
        this.chats_id = chats_id;
    }

    public String getMapping_id() {
        return mapping_id;
    }

    public void setMapping_id(String mapping_id) {
        this.mapping_id = mapping_id;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

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
        return "Booking{" + "booking_id=" + booking_id + ", booking_description=" + booking_description + ", booking_type=" + booking_type + ", booking_date=" + booking_date + ", driver_id=" + driver_id + ", customer_id=" + customer_id + ", chats_id=" + chats_id + ", mapping_id=" + mapping_id + ", paymentNumber=" + paymentNumber + ", bookingStatus=" + bookingStatus + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.booking_id.equals(((Booking) obj).booking_id);
    }

}
