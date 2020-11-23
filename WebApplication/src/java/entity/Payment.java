/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import csv.converter.PaymentMethodTypeConverter;
import csv.converter.PaymentStatusConverter;
import java.text.DecimalFormat;
import java.util.Date;
import xenum.PaymentMethodType;
import xenum.PaymentStatus;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Payment extends AbstractEntity<Payment> {

    DecimalFormat df = new DecimalFormat("###.##");

    @CsvBindByName
    private String payment_id;

    @CsvCustomBindByName(converter = PaymentStatusConverter.class, column = "payment_status_code")
    private PaymentStatus payment_status;

    @CsvCustomBindByName(converter = PaymentMethodTypeConverter.class, column = "payment_method_code")
    private PaymentMethodType payment_method;

    @CsvBindByName
    @CsvDate(main.WebConfig.saving_date_format)
    private Date payment_date;

    @CsvBindByName
    @CsvDate(main.WebConfig.saving_date_format)
    private Date payment_due_date;

    @CsvBindByName
    private double payment_amount;

    public Payment(String payment_id, PaymentStatus payment_status, PaymentMethodType payment_method, Date payment_date, Date payment_due_date, double payment_amount) {
        this.payment_id = payment_id;
        this.payment_status = payment_status;
        this.payment_method = payment_method;
        this.payment_date = payment_date;
        this.payment_due_date = payment_due_date;
        this.payment_amount = payment_amount;
    }

    public Payment() {
        this("", PaymentStatus.Voided, PaymentMethodType.NOT_YET_PAID, new Date(), null, 0.00);
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

    public PaymentMethodType getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethodType payment_method) {
        this.payment_method = payment_method;
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
        return this.payment_id.equals(((Payment) obj).payment_id);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.payment_id == null ? ((Payment) obj).payment_id == null : this.payment_id.equals(((Payment) obj).payment_id));
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
