/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

/**
 *
 * @author ITSUKA KOTORI
 */
public class NewMain {

    public static void main(String[] args) {
        System.out.println(gcd(24, 18));
    }

    public static int gcd(int num1, int num2) {
        return num2 > 0 ? gcd(num2, num1 % num2) : num1;
    }

}
