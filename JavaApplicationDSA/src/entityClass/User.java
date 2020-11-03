/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

/**
 *
 * @author ITSUKA KOTORI
 */
public abstract class User {
    private String id;
    private String ic;
    private String name;
    private String email;
    private String phoneNumber;

    public User() {
    }
    
    public User(String id, String ic, String name, String email, String phoneNumber) {
        this.id = id;
        this.ic = ic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
}
