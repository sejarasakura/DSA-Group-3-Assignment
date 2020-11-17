/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import xenum.PaymentStatus;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Payment extends AbstractEntity<Payment> {
    
    DecimalFormat df = new DecimalFormat("###.##");
    
    @CsvBindByName
    private String payment_id;
    
    @CsvBindByName(column = "payment_status_code")
    private int payment_status;
    
    @CsvBindByName
    private Date payment_date;
    
    @CsvBindByName
    private Date payment_due_date;
    
    @CsvBindByName
    private double payment_amount;
    
    public Payment(String payment_id, int payment_status, Date payment_date, double payment_amount, Date payment_due_date) {
        this.payment_id = payment_id;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
        this.payment_amount = payment_amount;
        this.payment_due_date = payment_due_date;
    }
    
    public Payment() {
        this("", PaymentStatus.Voided.getCode(), null, 0.00, new Date());
    }
    
    public String getPayment_id() {
        return payment_id;
    }
    
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
    
    public int getPayment_status() {
        return payment_status;
    }
    
    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }
    
    public Date getPayment_date() {
        return payment_date;
    }
    
    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }
    
    public double getPayment_amount() {
        return payment_amount;
    }
    
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public Date getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(Date payment_due_date) {
        this.payment_due_date = payment_due_date;
    }
    
    @Override
    public boolean isNotNull() {
        return this.payment_id != null;
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.payment_id.equals(((Payment)obj).payment_id);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.payment_id == null ? ((Payment)obj).payment_id == null : this.payment_id.equals(((Payment)obj).payment_id));
    }

    @Override
    public String toString() {
        return "Payment{" + "df=" + df + ", payment_id=" + payment_id + ", payment_status=" + payment_status + ", payment_date=" + payment_date + ", payment_due_date=" + payment_due_date + ", payment_amount=" + payment_amount + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
