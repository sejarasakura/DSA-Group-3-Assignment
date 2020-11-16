/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.ArrList;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.univocity.parsers.annotations.Parsed;
import xenum.PaymentStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class Withdraw extends AbstractEntity<Withdraw> {

    /**
     * withdraw id
     */
    @CsvBindByName
    private String withdraw_id;

    /**
     * user_id
     */
    @CsvBindByName
    private String user_id;

    /**
     * amount
     */
    @CsvBindByName
    private double amout;

    /**
     * date
     */
    @CsvBindByName
    @CsvDate("dd.MM.yyyy.hh.mm.ss")
    private Date date;

    /**
     * status
     */
    @CsvBindByName(column = "payment_status_code")
    private int status;

    /**
     * note
     */
    @CsvBindByName
    private String driver_id;

    /**
     * note
     */
    @CsvBindByName
    private String note;

    public Withdraw() {
    }

    public Withdraw(String withdraw_id) {
        this.withdraw_id = withdraw_id;
    }
    
    public String getWithdraw_id() {
        return withdraw_id;
    }

    public void setWithdraw_id(String withdraw_id) {
        this.withdraw_id = withdraw_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public void setNote(String note) {
        this.note = note;
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
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Withdraw{" + "withdraw_id=" + withdraw_id + ", user_id=" + user_id + ", amout=" + amout + ", date=" + date + ", status=" + status + ", note=" + note + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.withdraw_id.equals(((Withdraw)obj).withdraw_id);
    }
}
