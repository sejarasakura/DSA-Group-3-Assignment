/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Functions {
    
    public static String getWebpageTitle(HttpServletRequest request){
        return "Rent Car" + (request.getParameter("title") == null?"":(" - " + request.getParameter("title")));
    }
    
}
