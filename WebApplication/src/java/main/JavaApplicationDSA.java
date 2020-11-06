package main;

import adtClass.ArrList;
import adtClass.ArraySlotList;
import adtClass.HashedDictionary;
import adtClass.Queue;
import adtClass.Stack;
import java.awt.Font;

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

    public static HashedDictionary<String, Object> setting;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setting = new HashedDictionary<String, Object>(300);
        setting.add("MAP_API_KEY", "AIzaSyCG24EIIq1K0uhrWm2_LUTF3W3KeVcDqWw");
        setting.add("DEFUALT_FRAME_W", 1200);
        setting.add("DEFUALT_FRAME_H", 800);
        setting.add("FONT_STYLE", new Font(Font.SERIF, Font.PLAIN,  10));
        
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
        System.out.print(setting+"\n");
        ArraySlotList<Integer> list3 = new ArraySlotList<Integer>(list2);
        for(Integer x: list3){
            System.out.println(x);
        }
        // TODO code application logic here
    }
    
    public static boolean isPalindrome(String args){
        Stack stack = new Stack();
        Queue queue = new Queue();
        for(char arg: args.toCharArray()){
            
            if(!Character.isLetterOrDigit(arg))
                continue;
            
            stack.push(Character.toLowerCase(arg));
            queue.enqueue(Character.toLowerCase(arg));
        }
        
        while(!stack.isEmpty()){
            if( ( ( char ) queue.dequeue()) != ( ( char ) stack.pop()) )
                return false;
        }
        
        return true;
    }
}
