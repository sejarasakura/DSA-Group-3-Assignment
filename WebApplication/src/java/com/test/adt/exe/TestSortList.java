/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.XArraySortList;

/**
 *
 * @author ITSUKA KOTORI
 */
public class TestSortList {

    /**
     * Main drive to test the functions
     *
     * @param args
     */
    public static void main(String args[]) {
        text_one();
        System.out.println();
        text_two();
        System.out.println();
        text_three();
        System.out.println();
        text_four();
        System.out.println();
    }

    /**
     * Example of sorting Integer
     */
    public static void text_one() {
        XArraySortList list = new XArraySortList();
        list.add(1);
        list.add(-1);
        list.add(9);
        list.add(-4);
        list.add(20);
        list.add(15);
        list.add(33);
        System.out.println("Slot Int\n=================");
        System.out.println("Before slot : " + list);
        list.sort();
        System.out.println("After slot : " + list);
        list.sortDesc();
        System.out.println("After desc slot : " + list);
    }

    /**
     * Example of sorting String
     */
    public static void text_two() {

        XArraySortList<String> list = new XArraySortList<String>();
        list.add("Haha");
        list.add("Gegeo");
        list.add("Jojo");
        list.add("Alice");
        list.add("John");
        list.add("Xueba");
        list.add("Hong Wah");
        System.out.println("Slot String\n=================");
        System.out.println("Before slot : " + list);
        list.sort();
        System.out.println("After slot : " + list);
        list.sortDesc();
        System.out.println("After desc slot : " + list);
    }

    /**
     * Example of sorting Comparable
     */
    public static void text_three() {

        XArraySortList list = new XArraySortList();
        list.add(new Haha(-1, "H"));
        list.add(new Haha(-7, "M"));
        list.add(new Haha(20, "G"));
        list.add(new Haha(-20, "G"));
        list.add(new Haha(-30, "Z"));
        list.add(new Haha(-30, "A"));
        System.out.println("Slot Comparable to is call the method compareTo in the class\n=================");
        System.out.println("Before slot : " + list);
        list.sort();
        System.out.println("After slot : " + list);
        list.sortDesc();
        System.out.println("After desc slot : " + list);

    }

    /**
     * Example of sorting Comparable
     */
    public static void text_four() {
        XArraySortList list = new XArraySortList();
        list.add(new Haha(-1, "H"));
        list.add(new Haha(-7, "M"));
        list.add(new Haha(20, "G"));
        list.add(new Haha(-20, "G"));
        list.add(new Haha(-30, "Z"));
        list.add(new Haha(-30, "A"));
        System.out.println("Slot Comparable to is call the method compareTo in the class\n=================");
        System.out.println("Before slot : " + list);
        list.sort("field", Haha.class);
        System.out.println("After slot(field, Haha.class) : " + list);
        list.sort("field2", Haha.class);
        System.out.println("After slot(field2, Haha.class) : " + list);
        list.sortDesc("field", Haha.class);
        System.out.println("After desc slot(field, Haha.class) : " + list);
        list.sortDesc("field2", Haha.class);
        System.out.println("After desc slot(field2, Haha.class) : " + list);
    }

    public static class Haha implements Comparable {

        private int field;
        private String field2;

        public Haha(int field, String field2) {
            this.field = field;
            this.field2 = field2;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        @Override
        public int compareTo(Object t) {
            return Integer.compare(field, ((Haha) t).field);
        }

        @Override
        public String toString() {
            return "Haha{" + field + "," + field2 + '}';
        }

    }
}
