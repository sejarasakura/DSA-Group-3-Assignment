/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.XHashedDictionary;
import adt.XHashedDictionaryBackup;
import java.util.Random;

/**
 *
 * @author ITSUKA KOTORI
 */
public class TestHashDictionary {

    static Random random = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main2(String[] args) {
        XHashedDictionary<String, String> test = new XHashedDictionary<String, String>();
        for (int i = 0; i < 100; i++) {
            test.add("" + i, "" + i + "v");
        }
        System.out.println(test);
    }

    public static void main(String[] args) {
        XHashedDictionaryBackup<String, String> test = new XHashedDictionaryBackup<String, String>();
        for (int i = 0; i < 100; i++) {
            test.add("" + i, "" + i + "v");
        }
        System.out.println(test);
    }

}
