/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class TestArrList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XArrayList arrList = new XArrayList();
        arrList.add(2);
        arrList.add(5);
        arrList.add(90);
        arrList.add(12);
        arrList.add(22);
        System.out.println("After add\n==================");
        System.out.println(arrList);
        System.out.println("Size" + arrList.size());
        System.out.println();
        //===============================================================
        Object clone[] = arrList.toArray();
        arrList.set(0, 10000);
        arrList.set(1, 20000);
        System.out.println("After set\n==================");
        System.out.println(arrList);
        System.out.println("Size" + arrList.size());
        System.out.println();
        //===============================================================
        arrList.addAll(clone);
        System.out.println("After Add All\n==================");
        System.out.println(arrList);
        System.out.println("Size" + arrList.size());
        System.out.println();
        //===============================================================
        arrList.add(20000);
        arrList.add(20000);
        arrList.removeSameElement();
        System.out.println("After Remove Same Element\n==================");
        System.out.println(arrList);
        System.out.println("Size" + arrList.size());
        System.out.println();
        //===============================================================
        arrList.clear();
        System.out.println("After Clear\n==================");
        System.out.println(arrList);
        System.out.println("Size" + arrList.size());
        System.out.println();
    }

}
