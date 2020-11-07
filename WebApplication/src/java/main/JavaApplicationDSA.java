package main;

import adtClass.ArrList;
import adtClass.ArraySlotList;
import adtClass.HashedDictionary;
import adtClass.Queue;
import adtClass.Stack;
import java.awt.Font;
import javax.servlet.http.HttpServletRequest;

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
        
        ArraySlotList<String> list = new ArraySlotList<String>();
        list.add("A");
        list.add("Z");
        list.add("H");
        list.add("k");
        list.add("n");
        ArrList<Integer> list2 = new ArrList<Integer>();
        list2.add(99);
        list2.add(5);
        list2.add(9);
        list2.add(10);
        list2.add(2);
        System.out.print(list.toString()+"\n");
        System.out.print(list2.toString()+"\n");
        ArraySlotList<Integer> list3 = new ArraySlotList<Integer>(list2);
        for(Integer x: list3){
            System.out.println(x);
        }
        // TODO code application logic here
    }
    
    public static void ShowDirectory(Object args){
        if(args instanceof javax.servlet.http.HttpServletRequest ){
            HttpServletRequest r = (HttpServletRequest)args;
            System.out.println("Loaded file : "+r.getRequestURL());
        }else {
            System.out.println("Loaded file : "+System.getProperty("user.dir") + ":" + args );
        }
    }
}
