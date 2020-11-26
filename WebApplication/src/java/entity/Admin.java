/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Lim Sai Keat
 */
public class Admin extends User {

    /**
     * constructor
     */
    public Admin() {
        this.setRole("a");
    }

    /**
     * constructor
     *
     * @param user_id
     * @param ic
     * @param name
     * @param email
     * @param phoneNumber
     * @param role
     * @param username
     * @param password
     */
    public Admin(String user_id, String ic, String name, String email, String phoneNumber, String role, String username, String password) {
        super(user_id, ic, name, email, phoneNumber, "a", username, password);
    }

    @Override
    public User login(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User register(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNotNull() {
        return super.isNotNull();
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return super.id_equals(obj);
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
