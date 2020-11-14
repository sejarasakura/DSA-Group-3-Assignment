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
public class Taxi extends Car{
    
    @CsvBindByName
    private String taxiLicense;
    
    @CsvBindByName
    private String taxiComapany;

    public Taxi() {
    }
    
    public Taxi(String taxiLicense, String taxiComapany) {
        this.taxiLicense = taxiLicense;
        this.taxiComapany = taxiComapany;
    }

    public Taxi(String taxiLicense, String taxiComapany, String plate_id, String license, Date regDate, String carType, String driver) {
        super(plate_id, license, regDate, carType, driver);
        this.taxiLicense = taxiLicense;
        this.taxiComapany = taxiComapany;
    }

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

    
}
