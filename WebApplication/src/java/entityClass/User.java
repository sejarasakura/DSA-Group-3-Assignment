/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public abstract class User<T> extends AbstractEntity<User>{
    
    /**
     * User Id primary key 
     */
    @CsvBindByName
    private String id;
    
    /**
     * User IC if have
     */
    @CsvBindByName
    private String ic;
    
    /**
     * User Registered name
     */
    @CsvBindByName
    private String name;
    
    /**
     * User register email
     */
    @CsvBindByName
    private String email;
    
    /**
     * User phone number 
     */
    @CsvBindByName
    private String phoneNumber;
    
    
    /**
     * User role
     */
    @CsvBindByName
    private String role;

    public User() {
    }
    
    public User(String id, String ic, String name, String email, String phoneNumber) {
        this.id = id;
        this.ic = ic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getId() {
        return id;
    }

    public String getIc() {
        return ic;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void login(){
        
    }
    
    public void register(){
        
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", ic=" + ic + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }
    
}
