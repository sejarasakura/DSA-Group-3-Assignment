/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import xenum.CarType;
import java.util.Date;

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
    private String taxiComapany;

    public String getTaxiLicense() {
        return taxiLicense;
    }

    public void setTaxiLicense(String taxiLicense) {
        this.taxiLicense = taxiLicense;
    }

    public String getTaxiComapany() {
        return taxiComapany;
    }

    public void setTaxiComapany(String taxiComapany) {
        this.taxiComapany = taxiComapany;
    }

    public String getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(String taxiId) {
        this.taxiId = taxiId;
    }

    public Taxi() {
    }

    public Taxi(String taxiId, String taxiLicense, String taxiComapany, String plate_id, String license, Date regDate, String carType, String driver_id) {
        super(plate_id, license, regDate, carType, driver_id);
        this.taxiId = taxiId;
        this.taxiLicense = taxiLicense;
        this.taxiComapany = taxiComapany;
    }

    @Override
    public boolean id_equals(Object obj) {
        return (this.taxiId == null ? ((Taxi) obj).taxiId == null : this.taxiId.equals(((Taxi) obj).taxiId));
    }

    @Override
    public boolean isNotNull() {
        return this.taxiId == null ? false:!this.taxiId.isEmpty();
    }

}
