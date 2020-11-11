
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ITSUKA KOTORI
 */
public class okey {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

        int count = 1000;
        int yVal = 100;
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            yVal += rand.nextInt(11) - 5;
            map = new HashMap<Object, Object>();
            map.put("x", i + 1);
            map.put("y", yVal);
            list.add(map);
        }

        String dataPoints = gsonObj.toJson(list);

        Gson gsonObj2 = new Gson();
        Map<Object, Object> map2 = null;
        List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();

        map2 = new HashMap<Object, Object>();
        map2.put("x", 10);
        map2.put("y", 31);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 20);
        map2.put("y", 65);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 30);
        map2.put("y", 40);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 40);
        map2.put("y", 84);
        map2.put("indexLabel", "Highest");
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 50);
        map2.put("y", 68);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 60);
        map2.put("y", 64);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 70);
        map2.put("y", 38);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 80);
        map2.put("y", 71);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 90);
        map2.put("y", 54);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 100);
        map2.put("y", 60);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 110);
        map2.put("y", 21);
        map2.put("indexLabel", "Lowest");
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 120);
        map2.put("y", 49);
        list2.add(map2);
        map2 = new HashMap<Object, Object>();
        map2.put("x", 130);
        map2.put("y", 41);
        list2.add(map2);
        String dataPoints2 = gsonObj2.toJson(list2);
        System.out.println(dataPoints2);
        System.out.println(dataPoints);
    }

}
