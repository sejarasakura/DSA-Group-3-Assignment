package main;

import adt.ArrList;
import adt.Stack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Point;
import java.lang.reflect.Array;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ITSUKA KOTORI
 */
public class JavaApplicationDSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Teacher is golden > _ > !!
         */
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        Stack<Point> i = new Stack<Point>();
        i.push(new Point(2, 4));
        i.push(new Point(7, 1));
        i.push(new Point(0, 9));
        i.push(new Point(5, 7));
        i.push(new Point(12, -4));
        ArrList a = new ArrList(i);
        System.out.println(g.toJson(a, ArrList.class));
    }
    
    public static void ShowDirectory(Object args){
        
    }
}
