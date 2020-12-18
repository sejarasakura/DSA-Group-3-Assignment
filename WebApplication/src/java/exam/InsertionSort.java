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
public class InsertionSort {

    public static void main(String args[]) {
        int[] x = {87, 76, 98, 55, 34, 69, 74};
        InsertionSort sort = new InsertionSort();
        sort.insertionsort(x);
    }

    void selectionsort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        System.out.print("Original -> ");
        printArray(arr);
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            System.out.print("pass " + (i + 1) + " -> ");
            printArray(arr);
        }
    }

    void insertionsort(int arr[]) {
        int n = arr.length;
        System.out.print("Original -> ");
        printArray(arr);
        for (int i = 0; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            System.out.print("pass " + (i + 1) + " -> ");
            printArray(arr);
        }
    }

    /* A utility function to print array of size n*/
    void printArray(int arr[]) {
        if (arr.length > 0) {
            int n = arr.length;
            for (int i = 0; i < (n - 1); ++i) {
                System.out.print(arr[i] + ", ");
            }
            System.out.println(arr[n - 1]);
        }
    }
}
