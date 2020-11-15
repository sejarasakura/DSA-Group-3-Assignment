/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.univocity.parsers.csv.CsvWriterSettings;

/**
 *
 * @author ITSUKA KOTORI
 */
public class WebConfig {
    
    //ignore
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
    public static String ADMIN_URL = (WEB_URL + "admin/index.jsp");

    /**
     *  Web Site Header URL
     */
    public static String HEADER_URL = "../theme/header.jsp";
    
    /**
     *  Web Site Footer URL
     */
    public static String FOOTER_URL = "../theme/footer.jsp";

    /**
     *  Web Site Header URL
     */
    public static String ADMIN_HEADER_URL = "../theme/admin_header.jsp";
    
    /**
     *  Web Site Footer URL
     */
    public static String ADMIN_FOOTER_URL = "../theme/admin_footer.jsp";
    
    /**
     *  Web Site Meta URL
     */
    public static String META_URL =  "../theme/meta.jsp";  
    
    /**
     *  Web Site Meta URL
     */
    public static String API_KEY_URL = WebConfig.PROJECT_URL + "data/api.dat";  
    
    /**
     *  GOOGLE Map API Key
     */
    public static String api_key = Functions.getApiKey();
}
