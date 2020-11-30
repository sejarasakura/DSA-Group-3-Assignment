/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.*;
import csv.converter.BookingStatusConverter;
import java.util.Date;
import java.util.Objects;
import xenum.BookingStatus;
import xenum.CarType;

/**
 *
 * @author Lai Hong Wah
 * @author Chun Chew Pang
 * @author Lim Sai Keat
 * @author Woo Qian Hui
 */
public class Booking extends AbstractEntity<Booking> {

    /**
     * customer booking id
     */
    @CsvBindByName
    private String booking_id;

    /**
     * customer note
     */
    @CsvBindByName
    private String booking_description;

    /**
     * customer booking type
     */
    @CsvBindByName
    private CarType booking_type;

    /**
     * customer booking date
     */
    @CsvBindByName
    @CsvDate(main.WebConfig.SAVING_DATE_FORMAT)
    private Date booking_date;

    /**
     * driver id
     */
    @CsvBindByName
    private String driver_id;

    /**
     * customer id
     */
    @CsvBindByName
    private String customer_id;

    /**
     * chats id
     */
    @CsvBindByName
    private String chats_id;

    /**
     * mapping id
     */
    @CsvBindByName
    private String mapping_id;

    /**
     * customer payment number
     */
    @CsvBindByName
    private String paymentNumber;

    /**
     * booking status
     */
    @CsvCustomBindByName(converter = BookingStatusConverter.class, column = "booking_status_code")
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Booking(String booking_id, String booking_description,
            CarType booking_type, Date booking_date, String driver_id,
            String customer_id, String chats_id, String mapping_id,
            String paymentNumber, BookingStatus bookingStatus) {
        this.booking_id = booking_id;
        this.booking_description = booking_description;
        this.booking_type = booking_type;
        this.booking_date = booking_date;
        this.driver_id = driver_id;
        this.customer_id = customer_id;
        this.chats_id = chats_id;
        this.mapping_id = mapping_id;
        this.paymentNumber = paymentNumber;
        this.bookingStatus = bookingStatus;
    }

    /**
     * booking id
     *
     * @return
     */
    public String getBooking_id() {
        return booking_id;
    }

    /**
     *
     * @param booking_id
     */
    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    /**
     * booking description
     *
     * @return
     */
    public String getBooking_description() {
        return booking_description;
    }

    /**
     *
     * @param booking_description
     */
    public void setBooking_description(String booking_description) {
        this.booking_description = booking_description;
    }

    /**
     * booking type
     *
     * @return
     */
    public CarType getBooking_type() {
        return booking_type;
    }

    /**
     *
     * @param booking_type
     */
    public void setBooking_type(CarType booking_type) {
        this.booking_type = booking_type;
    }

    /**
     * booking date
     *
     * @return
     */
    public Date getBooking_date() {
        return booking_date;
    }

    /**
     *
     * @param booking_date
     */
    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    /**
     * driver id
     *
     * @return
     */
    public String getDriver_id() {
        return driver_id;
    }

    /**
     *
     * @param driver_id
     */
    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    /**
     * customer id
     *
     * @return
     */
    public String getCustomer_id() {
        return customer_id;
    }

    /**
     *
     * @param customer_id
     */
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * chats id
     *
     * @return
     */
    public String getChats_id() {
        return chats_id;
    }

    /**
     *
     * @param chats_id
     */
    public void setChats_id(String chats_id) {
        this.chats_id = chats_id;
    }

    /**
     * mapping id
     *
     * @return
     */
    public String getMapping_id() {
        return mapping_id;
    }

    /**
     *
     * @param mapping_id
     */
    public void setMapping_id(String mapping_id) {
        this.mapping_id = mapping_id;
    }

    /**
     * customer payment number
     *
     * @return
     */
    public String getPaymentNumber() {
        return paymentNumber;
    }

    /**
     *
     * @param paymentNumber
     */
    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    /**
     * customer booking status
     *
     * @return
     */
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    /**
     *
     * @param bookingStatus
     */
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public boolean isNotNull() {
        if (this.booking_id == null) {
            return false;
        }
        if (this.booking_id.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.booking_id, other.booking_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Booking{" + "booking_id=" + booking_id + ", booking_description=" + booking_description + ", booking_type=" + booking_type + ", booking_date=" + booking_date + ", driver_id=" + driver_id + ", customer_id=" + customer_id + ", chats_id=" + chats_id + ", mapping_id=" + mapping_id + ", paymentNumber=" + paymentNumber + ", bookingStatus=" + bookingStatus + '}';
    }

    @Override
    public int compareTo(Object t) {
        Booking b = (Booking) t;
        return this.booking_id.compareTo(b.booking_id);
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.booking_id.equals(((Booking) obj).booking_id);
    }

    @Override
    public String getId() {
        return this.booking_id;
    }
}
