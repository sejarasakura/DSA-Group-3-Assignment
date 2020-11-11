package main;

import adtClass.ArrList;
import adtClass.Stack;
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
        Stack<Integer> i = new Stack<Integer>();
        i.push(1);
        i.push(5);
        i.push(3);
        i.push(10);
        i.push(-1);
        adtClass.ArraySlotList as = new adtClass.ArraySlotList(i);
        as.sort();
        ArrList a = new ArrList(as);
        for(Object x: a){
            System.out.println(x);
        }
    }
    
    public static void ShowDirectory(Object args){
        
    }
}
