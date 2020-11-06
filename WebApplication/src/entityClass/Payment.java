/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import enumClass.PaymentStatus;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Payment extends AbstractEntity {
    
    DecimalFormat df = new DecimalFormat("###.##");
    private String payment_id;
    private PaymentStatus payment_status;
    private Date payment_date;
    private double payment_amount;
    
    public Payment(String payment_id, PaymentStatus payment_status, Date payment_date, double payment_amount) {
        this.payment_id = payment_id;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
        this.payment_amount = payment_amount;
    }
    
    public Payment() {
        this("", PaymentStatus.Voided, null, 0.00);
    }
    
    public String getPayment_id() {
        return payment_id;
    }
    
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
    
    public PaymentStatus getPayment_status() {
        return payment_status;
    }
    
    public void setPayment_status(PaymentStatus payment_status) {
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
