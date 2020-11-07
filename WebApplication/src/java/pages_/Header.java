/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages_;
import adtClass.*;
import entityClass.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Header {
    
    private User user;
    
    public Header(HttpServletRequest request){
        Cookie[] cs = request.getCookies();
        for(Cookie c: cs){
            if(c.getName().equals("user"))
                get_user_data(c.getValue());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void get_user_data(String uid) {
        
    }
    
}
