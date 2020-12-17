/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

/**
 * Local project import
 */
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import xenum.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class InsertionSort1String {

    public static void main(String args[]) {
        String[] x = {"The Abundance of Less", "Clutter-Less", "The Year of Less", "The Simplicity of Less"};
        InsertionSort1String sort = new InsertionSort1String();
        sort.sort(x);
    }

    void sort(String arr[]) {
        int n = arr.length;
        System.out.print("Original -> ");
        printArray(arr);
        for (int i = 1; i < n; ++i) {
            String key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j].compareTo(key) < 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            System.out.print("Pass " + (i + 1) + "   -> ");
            printArray(arr);
        }
    }

    /* A utility function to print array of size n*/
    void printArray(String arr[]) {
        if (arr.length >= 1) {
            int n = arr.length;
            for (int i = 0; i < (n - 1); ++i) {
                System.out.print("\"" + arr[i] + "\", ");
            }
            System.out.println("\"" + arr[n - 1] + "\"");
        }
    }
}
