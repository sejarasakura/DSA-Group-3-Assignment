/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import enumClass.CarType;
import java.util.Date;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Car extends AbstractEntity<Car>{
    
    
    /*
    * Car License
    */
    @CsvRecurse
    private Plate plate;
    
    /*
    * Car License
    */
    @CsvBindByName
    private String license;
    
    /*
    * To calculate the year using the car?
    */
    @CsvBindByName
    private Date regDate;
    
    /*
    * CarType
    */
    @CsvRecurse
    private CarType carType;
    
    /*
    * To calculate the year using the car?
    */
    @CsvRecurse
    private String driver_id;
    
    public Car() {
    }

    public Car(String plateAlpha, String plateNumber, String license, Date regDate, CarType carType, String driver_id) {
        this.plate.setPlateAlpha(plateAlpha);
        this.plate.setPlateNumber(plateNumber);
        this.license = license;
        this.regDate = regDate;
        this.carType = carType;
        this.driver_id = driver_id;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getString() {
        return driver_id;
    }

    public void setString(String driver_id) {
        this.driver_id = driver_id;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
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
        return "Car{" + "plate" + this.plate.getFullPlateNumber() + ", license=" + license + ", regDate=" + regDate + ", carType=" + carType + ", driver_id=" + driver_id + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
