/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.XArrayList;
import adt.XArraySortList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        text_one();
//        System.out.println();
//        text_two();
//        System.out.println();
//        text_three();
//        System.out.println();
        text_four();
//        System.out.println();
//        try {
//            text_five();
//            System.out.println();
//            text_six();
//        } catch (ParseException ex) {
//            Logger.getLogger(TestSortList.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println();
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
        XArrayList list = new XArrayList();
        list.add(new Haha(-1, "H"));
        list.add(new Haha(-7, "M"));
        list.add(new Haha(20, "G"));
        list.add(new Haha(-20, "K"));
        list.add(new Haha(-30, "Z"));
        list.add(new Haha(-20, "G"));
        list.add(new Haha(-30, "G"));
        list.add(new Haha(10, "A"));
        list.add(new Haha(-20, "M"));
        list.add(new Haha(-30, "A"));
        list.add(new Haha(-10, "G"));
        System.out.println("Slot Comparable to is call the method compareTo in the class\n=================");
        System.out.println("Before slot : " + list);
        System.out.println("After slot(field, Haha.class) : " + list);
        list = list.binarySearchAndSort("field", -20, Haha.class);
        System.out.println("After binary search : " + list);
    }

    public static void text_five() throws ParseException {
        XArraySortList list = new XArraySortList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        list.add(dateFormat.parse("2021-10-31"));
        list.add(dateFormat.parse("1999-12-3"));
        list.add(dateFormat.parse("2006-7-1"));
        list.add(dateFormat.parse("2009-2-22"));
        list.add(dateFormat.parse("2023-10-1"));
        list.add(new Date());
        System.out.println("Slot Comparable to is call the method compareTo in the class\n=================");
        System.out.println("Before slot : " + list);
        list.sort("month", Date.class);
        System.out.println("After slot(month, Date.class) : " + list);
        list.sort("year", Date.class);
        System.out.println("After slot(year, Date.class) : " + list);
        list.sortDesc("month", Date.class);
        System.out.println("After desc slot(month, Date.class) : " + list);
        list.sortDesc("year", Date.class);
        System.out.println("After desc slot(year, Date.class) : " + list);
        list.sort();
        System.out.println("After slot() : " + list);
        list.sortDesc();
        System.out.println("After desc slot() : " + list);
    }

    public static void text_six() throws ParseException {
        XArraySortList list = new XArraySortList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        list.add(new GGa(dateFormat.parse("2021-10-31")));
        list.add(new GGa(dateFormat.parse("1999-12-3")));
        list.add(new GGa(dateFormat.parse("1999-10-3")));
        list.add(new GGa(dateFormat.parse("1999-5-29")));
        list.add(new GGa(dateFormat.parse("2006-7-1")));
        list.add(new GGa(dateFormat.parse("2009-2-22")));
        list.add(new GGa(dateFormat.parse("2023-10-1")));
        list.add(new GGa(new Date()));
        System.out.println("Slot Comparable to is call the method compareTo in the class\n=================");
        System.out.println("Before slot : " + list);
        System.out.println(list.sort("date", GGa.class));
        System.out.println("After slot(date, GGa.class) : " + list);
        System.out.println(list.sortDesc("date", GGa.class));
        System.out.println("After sortDesc(date, GGa.class) : " + list);
    }

    /**
     * Example of search by fields
     */
    public static void text_seven() {
        XArraySortList list = new XArraySortList();
        list.add(new Haha(-1, "H"));
        list.add(new Haha(-7, "M"));
        list.add(new Haha(20, "G"));
        list.add(new Haha(-20, "G"));
        list.add(new Haha(-30, "Z"));
        list.add(new Haha(-30, "A"));
        list.add(new Haha(10, "A"));
        System.out.println("Slot Comparable to is call the method searchByField in the class\n=================");
        XArrayList<Haha> x = new XArrayList<Haha>(list);
        System.out.println(x.searchByField("field2", "A", Haha.class));
    }

    public static class GGa implements Comparable {

        static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private Date date;

        public GGa(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public int compareTo(Object t) {
            return this.date.compareTo(((GGa) t).getDate());
        }

        @Override
        public String toString() {
            return "GGa{" + "date=" + dateFormat.format(date) + '}';
        }
    }

}
