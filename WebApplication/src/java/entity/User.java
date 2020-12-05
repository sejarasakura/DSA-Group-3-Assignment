/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.XArrayList;
import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author Chew Chun Pang
 * @param <T>
 */
public abstract class User<T> extends AbstractEntity<User> {

    /**
     * User Id primary key
     */
    @CsvBindByName
    private String user_id;

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

    /**
     * User username User profile picture will save by the ${username}.png
     */
    @CsvBindByName
    private String username;

    /**
     * User password
     */
    @CsvBindByName
    private String password;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Parameter constructor
     *
     * @param user_id
     * @param ic
     * @param name
     * @param email
     * @param role
     * @param phoneNumber
     * @param password
     * @param username
     */
    public User(String user_id, String ic, String name, String email, String phoneNumber, String role, String username, String password) {
        this.user_id = user_id;
        this.ic = ic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    /**
     * User login functions that will return user or null for not found
     *
     * @param data
     * @return
     */
    public abstract User login(User data);

    public boolean login(String username, String password) {
        boolean username_c, email_c, pass_c, id_c;
        username_c = this.username == null ? username == null : this.username.equals(username);
        email_c = this.email == null ? username == null : this.email.equals(username);
        id_c = this.user_id == null ? username == null : this.user_id.equals(username);
        pass_c = this.password == null ? password == null : this.password.equals(password);
        return (email_c || username_c || id_c) && pass_c;
    }

    /**
     * User registration function that success will return user and null for
     * fail
     *
     * @param data
     * @return
     */
    public abstract User register(User data);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
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

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public static boolean searchUsername(XArrayList<? extends User> users, String data) {
        for (User user : users) {
            if (user.username == null ? data == null : user.username.equals(data)) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchEmail(XArrayList<? extends User> users, String data) {
        for (User user : users) {
            if (user.email == null ? data == null : user.email.equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + user_id + ", ic=" + ic + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public boolean isNotNull() {
        if (this.user_id == null) {
            return false;
        }
        if (user_id.isEmpty()) {
            return false;
        }

        return this.role != null;
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.user_id.equals(((User) obj).user_id);
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return this.user_id;
    }

    public boolean isCustomer() {
        if (role == null) {
            return false;
        }
        return "c".equals(this.role.toLowerCase());
    }

    public boolean isDriver() {
        if (role == null) {
            return false;
        }
        return "d".equals(this.role.toLowerCase());
    }

    public boolean isAdmin() {
        if (role == null) {
            return false;
        }
        return "a".equals(this.role.toLowerCase());
    }
}
