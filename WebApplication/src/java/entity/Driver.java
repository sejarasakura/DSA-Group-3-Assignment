/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Driver extends User<Driver> {

    /*
    * Driver accepted booking
     */
    @CsvBindByName
    private String driver_license;

    public Driver() {
        super();
    }

    public Driver(String driver_license, String user_id, String ic,
            String name, String email, String phoneNumber,
            String username, String password) {
        super(user_id, ic, name, email, phoneNumber, "d", username, password);
        this.driver_license = driver_license;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    @Override
    public boolean isNotNull() {
        return super.isNotNull();
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.getUser_id().equals(((Driver) obj).getUser_id());
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
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User register(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
