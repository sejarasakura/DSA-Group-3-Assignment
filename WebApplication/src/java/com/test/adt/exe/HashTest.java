/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.MapConverter;
import adt.XHashedDictionary;
import adt.node.TableEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author ITSUKA KOTORI
 */
public class HashTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map map = gson.fromJson(reader, MapConverter.class);
        map = (Map) map.get("admin-nav");
        System.out.print(map);
    }

    public static void main2(String[] args) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        XHashedDictionary<String, String> test = new XHashedDictionary<String, String>();
        for (int i = 0; i < 100; i++) {
            test.add("" + i, "" + i + "v");
        }
        Iterator i = test.newEntryIterator();
        while (i.hasNext()) {
            TableEntry x = (TableEntry) i.next();
            sb.append(x.key).append(',').append(x.value).append(',').append(x.hash).append('\n');
        }
        System.out.print(sb.toString());
        Type typeMyType = new TypeToken<XHashedDictionary<String, String>>() {
        }.getType();
        String json = gson.toJson(test, typeMyType);
        System.out.print(json);
        gson.fromJson(json, typeMyType);
        i = test.newEntryIterator();
        while (i.hasNext()) {
            TableEntry x = (TableEntry) i.next();
            sb.append(x.key).append(',').append(x.value).append(',').append(x.hash).append('\n');
        }
        System.out.print(sb.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main3(String[] args) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> test = new HashMap<String, String>();
        for (int i = 0; i < 100; i++) {
            test.put("" + i, "" + i + "v");
        }
        Iterator i = test.keySet().iterator();
        while (i.hasNext()) {
            String x = (String) i.next();
            sb.append(x).append(',').append(test.get(x)).append('\n');
        }
        System.out.print(sb.toString());
    }

}
