/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import csv.converter.CarTypeConverter;
import java.util.Date;
import xenum.CarType;

/**
 *
 * @author Lim Sai Keat
 */
public class Car extends AbstractEntity<Car> {

    /*
    * Car License
     */
    @CsvBindByName
    private String plate_id;

    /*
    * Car License
     */
    @CsvBindByName
    private String license;

    /*
    * To calculate the year using the car?
     */
    @CsvBindByName
    @CsvDate(main.WebConfig.SAVING_DATE_FORMAT)
    private Date regDate;

    /*
    * CarType
     */
    @CsvCustomBindByName(converter = CarTypeConverter.class, column = "car_type_code")
    private CarType carType;

    /*
    * To calculate the year using the car?
     */
    @CsvBindByName
    private String driver_id;

    public Car() {
    }

    public Car(String plate_id, String license, Date regDate, CarType carType, String driver_id) {
        this.plate_id = plate_id;
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

    public String getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(String plate_id) {
        this.plate_id = plate_id;
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

    public boolean isTaxi() {
        if (this == null) {
            return false;
        }
        if (this.carType == null) {
            return false;
        }
        return this.carType == CarType.TAXI_RENT_6SEAT || this.carType == CarType.TAXI_rent;
    }

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.plate_id.equals(((Car) obj).plate_id);
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Car{" + "plate" + this.plate_id + ", license=" + license + ", regDate=" + regDate + ", carType=" + carType + ", driver_id=" + driver_id + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return this.plate_id;
    }
}
