/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages_;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditAdmin {

    private String json_data = "", title, base;
    private boolean status = false;
    private Map map;

    public EditAdmin(HttpServletRequest request) {
        json_data = System.getProperty("user.dir") + "/data/"
                + request.getParameter("edit") + ".json";
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(json_data));
            Gson gson = new Gson();
            map = gson.fromJson(reader, Map.class);
            title = (String) map.get("e-title");
            base = (String) map.get("base");
            map = (Map) map.get(base);
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generateHtml() {
        if (!status) {
            return "<h1>Error found</h1>";
        }

        switch (base) {
            case "nav":
                break;
            case "admin-nav":
                break;
            case "footer":
                break;
        }
        return "<h1>Error found</h1>";
    }
    
    public String generateAdminNav(){
        
    }
    
    public String generateClient(){
        
    }
    
    public String editProperties(Map prop){
        
    }

}
