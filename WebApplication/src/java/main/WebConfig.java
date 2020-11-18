/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adt.ArrList;
import java.text.SimpleDateFormat;
import entity.json.ClassSaving;
import entity.json.CreateDisplayClassJson;

/**
 *
 * @author ITSUKA KOTORI
 */
public class WebConfig {
    

    /**
     *  Project directory URL
     */
    public static String PROJECT_URL = "C:/Users/ITSUKA KOTORI/Documents/GitHub/DSA-Group-3-Assignment/WebApplication/";
    
    /**
     *  Web Site URL
     */
    public static String WEB_URL = "http://localhost:8080/WebApplication/";
    
    /**
     *  Web Site Admin URL
     */
    public final static String ADMIN_URL = (WebConfig.WEB_URL + "admin/");

    /**
     *  Web Site Header URL
     */
    public final static String HEADER_URL = "../theme/header.jsp";
    
    /**
     *  Web Site Footer URL
     */
    public final static String FOOTER_URL = "../theme/footer.jsp";

    /**
     *  Web Site Header URL
     */
    public final static String ADMIN_HEADER_URL = "../theme/admin_header.jsp";
    
    /**
     *  Web Site Footer URL
     */
    public final static String ADMIN_FOOTER_URL = "../theme/admin_footer.jsp";
    
    /**
     *  Web Site Meta URL
     */
    public final static String META_URL =  "../theme/meta.jsp";  
    
    /**
     *  Web Site Meta URL
     */
    public final static String API_KEY_URL = WebConfig.PROJECT_URL + "data/api.dat";  
    
    /**
     *  GOOGLE Map API Key
     */
    public static String api_key = Functions.getApiKey();

    /**
     *  Saving details 
     */
    public final static ArrList<ClassSaving> CLASS_SAVING = new CreateDisplayClassJson().readData(WebConfig.PROJECT_URL + "data/access.json"); 
    
    /**
     *  Session key details 
     */
    public final static String LOGIN_SEESION_KEY = "rent-user";
    
    /**
     *  Image storing directory
     */
    public final static String IMG_URL = WebConfig.WEB_URL + "img/";
    
    /**
     *  Profile image storing directory
     */
    public final static String PROFILE_IMG_URL = WebConfig.IMG_URL + "profile/";
    
    /**
     *  Date format
     */
    public final static SimpleDateFormat LOCAL_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
}
