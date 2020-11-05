/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import java.util.Date;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Taxi extends Car{
    
    private String taxiLicense;
    private String taxiComapany;

    public Taxi() {
    }
    
    public Taxi(String taxiLicense, String taxiComapany) {
        this.taxiLicense = taxiLicense;
        this.taxiComapany = taxiComapany;
    }

    public Taxi(String taxiLicense, String taxiComapany, String plateAlpha, String plateNumber, Object license, Date regDate) {
        super(plateAlpha, plateNumber, license, regDate);
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
