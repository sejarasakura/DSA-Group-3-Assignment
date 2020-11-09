/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import adtClass.HashedDictionary;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Functions {
    
    public static String getWebpageTitle(HttpServletRequest request){
        return "Rent Car" + (request.getParameter("title") == null?"":(" - " + request.getParameter("title")));
    }
    
    public static int startUpInitialData(){
        Datas.settings = new HashedDictionary<String, Object>(256);
        Datas.settings.add("image/logo", WebConfig.WEB_URL + "img/logo.png");
        Datas.settings.add("image/user", WebConfig.WEB_URL + "img/user.png");
        Datas.settings.add("text/title", "Rentcars.com");
        
        return 1;
    }
    
}
