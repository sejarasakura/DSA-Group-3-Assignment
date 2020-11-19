package main;

import adt.ArrList;
import adt.XStack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Point;


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
        XStack<Point> stack = new XStack<Point>();
        stack.push(new Point(2, 4));
        stack.push(new Point(7, 1));
        stack.push(new Point(0, 9));
        stack.push(new Point(5, 7));
        stack.push(new Point(12, -4));
        ArrList a = new ArrList(stack);
        System.out.println(g.toJson(a, ArrList.class));
        System.out.println(main.WebConfig.api_key);
    }

    public static void ShowDirectory(Object args) {

    }
}
