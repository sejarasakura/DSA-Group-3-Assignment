/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.XArrayList;
import entity.AbstractEntity;
import entity.Admin;
import entity.Customer;
import entity.Driver;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import xenum.OutputColor;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Login {

    private String username;
    private String password;
    private User user = null;

    public Login(HttpServletRequest request) {
        username = request.getParameter("username");
        password = request.getParameter("password");
        login();
        main.Functions.setUserSession(request, user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public final void login() {
        if (username == null || password == null) {
            return;
        }
        XArrayList u;
        main.Datas.admins = (XArrayList<Admin>) AbstractEntity.readDataFormCsv(new Admin());
        main.Datas.drivers = (XArrayList<Driver>) AbstractEntity.readDataFormCsv(new Driver());
        main.Datas.customers = (XArrayList<Customer>) AbstractEntity.readDataFormCsv(new Customer());
        user = checkUserLogin(main.Datas.customers);
        if (user == null) {
            user = checkUserLogin(main.Datas.drivers);
        }
        if (user == null) {
            user = checkUserLogin(main.Datas.admins);
        }
    }

    public User checkUserLogin(XArrayList<? extends User> l) {
        for (int i = 0; i < l.size(); i++) {
            if (((l.get(i).getUsername().equals(username)) || l.get(i).getEmail().equals(username))
                    && (l.get(i).getPassword() == null ? password == null : l.get(i).getPassword().equals(password))) {
                System.out.println(xenum.OutputColor.TEXT_GREEN + ">> User login : " + xenum.OutputColor.TEXT_YELLOW + l.get(i).getUsername() + ',' + l.get(i).getEmail() + ',' + l.get(i).getPassword() + OutputColor.TEXT_RESET);
                return l.get(i);
            }
        }
        return null;
    }

}
