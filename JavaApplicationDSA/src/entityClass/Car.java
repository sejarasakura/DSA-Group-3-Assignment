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
public class Car implements Comparable{
    
    /*
    * Car pplate alphablate
    */
    private String plateAlpha;
    
    /*
    * Car plate number
    */
    private String plateNumber;
    
    /*
    * Car License
    */
    private Object license;
    
    /*
    * To calculate the year using the car?
    */
    private Date regDate;

    public Car() {
    }

    public Car(String plateAlpha, String plateNumber, Object license, Date regDate) {
        this.plateAlpha = plateAlpha;
        this.plateNumber = plateNumber;
        this.license = license;
        this.regDate = regDate;
    }

    public String getPlateAlpha() {
        return plateAlpha;
    }

    public void setPlateAlpha(String plateAlpha) {
        this.plateAlpha = plateAlpha;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
