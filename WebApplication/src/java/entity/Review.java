/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.ArrList;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import csv.converter.ArrListConverter;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Review extends AbstractEntity {

    @CsvBindByName
    private String driverUserName;

    @CsvBindByName
    private String customerUserName;

    @CsvBindByName
    private String paymentNumber;

    @CsvCustomBindByName(converter = ArrListConverter.class)
    private ArrList<String> comments;

    @CsvBindByName
    private int driverRating;

    @CsvBindByName
    @CsvDate(main.WebConfig.saving_date_format)
    private Date reviewDate;

    @CsvBindByName
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDriverUserName() {
        return driverUserName;
    }

    public void setDriverUserName(String driverUserName) {
        this.driverUserName = driverUserName;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public ArrList<String> getComments() {
        return comments;
    }

    public void setComments(ArrList<String> comments) {
        this.comments = comments;
    }

    public int getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(int driverRating) {
        this.driverRating = driverRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.paymentNumber, other.paymentNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean id_equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        if (!Objects.equals(this.paymentNumber, other.paymentNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Review{" + "driverUserName=" + driverUserName + ", customerUserName=" + customerUserName + ", paymentNumber=" + paymentNumber + ", comments=" + comments + ", driverRating=" + driverRating + ", reviewDate=" + reviewDate + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
