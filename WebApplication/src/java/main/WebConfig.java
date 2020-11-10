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
    

    /**
     *  Web Site Header URL
     */
    public static String PROJECT_URL = "C:/Users/ITSUKA KOTORI/Documents/GitHub/DSA-Group-3-Assignment/WebApplication/";
    
    /**
     *  Web Site Header URL
     */
    public static String WEB_URL = "http://localhost:8080/WebApplication/";

    /**
     *  Web Site Header URL
     */
    public static String HEADER_URL = "../theme/header.jsp";
    
    /**
     *  Web Site Footer URL
     */
    public static String FOOTER_URL = "../theme/footer.jsp";
    
    /**
     *  Web Site Meta URL
     */
    public static String META_URL =  "../theme/meta.jsp";  
    
    /**
     *  CSV BASIC SETTING
     */
    public static CsvWriterSettings CSV_SETTING = Functions.basic_setting();
    
    /**
     *  GOOGLE Map API Key
     */
    public static String api_key = Functions.getApiKey();
}
