package main;

import adtClass.ArrList;
import adtClass.ArraySlotList;
import adtClass.Queue;
import adtClass.Stack;
import java.util.Iterator;

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
        ArraySlotList<Integer> list3 = new ArraySlotList<Integer>(new Integer[4]);
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
