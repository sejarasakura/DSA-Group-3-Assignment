/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import adtClass.HashedDictionary;
import com.univocity.parsers.csv.CsvWriterSettings;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static CsvWriterSettings basic_setting (){
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setNullValue("?");
        settings.getFormat().setComment('-');
        settings.setEmptyValue("!");
        settings.setSkipEmptyLines(false);
        return settings;
    }

    public static String getApiKey() {
        String api_key = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(WebConfig.API_KEY_URL);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            api_key = (String) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return api_key;
    }

    public static void setApiKey(String api_key) {
        main.WebConfig.api_key = api_key;
        try
        {
            FileOutputStream fileOut = new FileOutputStream(WebConfig.API_KEY_URL);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(api_key);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
