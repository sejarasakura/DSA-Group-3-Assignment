/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import java.util.Date;
import xenum.CarType;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Taxi extends Car {

    @CsvBindByName
    private String taxiId;

    @CsvBindByName
    private String taxiLicense;

    @CsvBindByName
    private String taxiCompany;

    public String getTaxiLicense() {
        return taxiLicense;
    }

    public void setTaxiLicense(String taxiLicense) {
        this.taxiLicense = taxiLicense;
    }

    public String getTaxiCompany() {
        return taxiCompany;
    }

    public void setTaxiCompany(String taxiCompany) {
        this.taxiCompany = taxiCompany;
    }

    public String getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(String taxiId) {
        this.taxiId = taxiId;
    }

    public Taxi() {
    }

    public Taxi(String taxiId, String taxiLicense, String taxiComapany, String plate_id, String license, Date regDate, CarType carType, String driver_id) {
        super(plate_id, license, regDate, carType, driver_id);
        this.taxiId = taxiId;
        this.taxiLicense = taxiLicense;
        this.taxiCompany = taxiComapany;
    }

    @Override
    public boolean id_equals(Object obj) {
        return (this.taxiId == null ? ((Taxi) obj).taxiId == null : this.taxiId.equals(((Taxi) obj).taxiId));
    }

    @Override
    public boolean isNotNull() {
        return this.taxiId == null ? false : !this.taxiId.isEmpty();
    }

}
