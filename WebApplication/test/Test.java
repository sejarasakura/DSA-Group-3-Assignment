/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import adt.ArrList;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Integer[] intarr = {0, 4, 2, 6 ,9};
        adt.XArraySortList<Integer> arrslotlist = new adt.XArraySortList<Integer> (intarr);
        adt.ArrList<Integer> arrlist = new adt.ArrList<Integer> (arrslotlist);
        adt.XQueue<Integer> qlist = new adt.XQueue<Integer> (arrslotlist);
        adt.XStack<Integer> slist = new adt.XStack<Integer> (arrslotlist);
        System.out.println(arrlist);
        System.out.println(arrslotlist);
        arrslotlist.sort();
        System.out.println(arrslotlist);
        arrslotlist.sortDesc();
        System.out.println(arrslotlist);
        System.out.println(qlist);
        System.out.println(slist);
        String jsonString;
        jsonString = "{'employee.name':'Bob','employee.salary':[{'x':2, 'y':4}, {'x':10}, {'x':5}]}";
        Gson gson = new Gson();
        Map map = gson.fromJson(jsonString, Map.class);
        Iterable<Object> ig1 = (Iterable<Object>) map.get("employee.salary");
        adt.ArrList<Object> xlist  = new ArrList<Object>(ig1);
        Map m2 = (Map) xlist.get(0);
        System.out.println(m2);
    }
    
}
