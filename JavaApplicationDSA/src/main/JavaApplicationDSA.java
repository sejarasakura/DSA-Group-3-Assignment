package main;

import adtClass.Queue;
import adtClass.Stack;

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
        // TODO code application logic here
        System.out.print(isPalindrome("haah"));
        System.out.print(isPalindrome("Harah"));
        System.out.print(isPalindrome("race a car"));
        System.out.print(isPalindrome("A man, a plan, a canal: Panama"));
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
