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
import static exam.Recursion.repeat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import xenum.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class SortGetAllPass {
//type=String&order=asc:RSD2,RIT2,RSF2,REI2,RST2,RIS2,RMM2

    static Scanner s = new Scanner(System.in);

    public static void main(String args[]) {
        System.out.println("Please enter the array splited by \',\' \n"
                + "eg [type=int&order=desc:1,4,2,5],\n"
                + "   [type=String&order=asc:fdd,fdg,df] : ");
        String data = s.nextLine();
        Map<String, Object> x = extract(data);
        SortGetAllPass sort = new SortGetAllPass();
        if (sort.isAscOrder()) {
            System.out.println(x);
        }
        sort.sort(x);
        System.out.println(sort);
    }

    public static Map<String, Object> extract(String data) {
        Map<String, Object> r = new HashMap();
        Comparable[] x = null;
        if (data.contains(":")) {
            String[] data2 = data.split(":");
            Map<String, String> map = extractQuery(data2[0]);
            String[] temp = data2[1].split(",");
            switch (map.get("type")) {
                case "int":
                    x = new Integer[temp.length];
                    for (int i = 0; i < temp.length; i++) {
                        x[i] = Integer.parseInt(temp[i]);
                    }
                    break;
                case "double":
                    x = new Double[temp.length];
                    for (int i = 0; i < temp.length; i++) {
                        x[i] = Double.parseDouble(temp[i]);
                    }
                    break;
                default:
                    x = temp.clone();
                    break;
            }
            r.putAll(map);
        } else {
            x = data.split(",");
            r.put("type", "String");
            r.put("order", "asc");
        }
        r.put("data", x);
        return r;
    }

    public static Map<String, String> extractQuery(String q) {
        Map<String, String> r = new HashMap();
        if (!q.contains("=")) {
            return r;
        }

        if (!q.contains("&")) {
            String[] x = q.split("=");
            r.put(x[0], x[1]);
            return r;
        }

        String[] queries = q.split("&");
        for (String query : queries) {
            String[] x = query.split("=");
            r.put(x[0], x[1]);
        }

        return r;
    }

    private boolean ascOrder;

    public boolean isAscOrder() {
        return ascOrder;
    }

    public void setAscOrder(boolean ascOrder) {
        this.ascOrder = ascOrder;
    }

    private String line;

    void sort(Map<String, Object> data) {
        this.setAscOrder(data.get("order").toString().toLowerCase().equals("asc"));
        sort((Comparable[]) data.get("data"));
    }

    void sort(Comparable arr[]) {

        sb.setLength(0);

        int l = getTotalLenght(arr);
        line = repeat(l - 2, "─");

        sb.append(" Original array value\n");
        sb.append('┌').append(line).append("┐\n");
        sb.append(String.format("│ %-" + (line.length() - 1) + "s│\n", "Original -> " + appendArray(arr)));
        sb.append('└').append(line).append("┘\n");

        sb.append(" Insertion Sort\n");
        sb.append('┌').append(line).append("┐\n");
        insertionSort(arr.clone());
        sb.append('└').append(line).append("┘\n");

        sb.append(" Selection Sort\n");
        sb.append('┌').append(line).append("┐\n");
        selectionSort(arr.clone());
        sb.append('└').append(line).append("┘\n");

        sb.append(" Quick Sort\n");
        sb.append('┌').append(line).append("┐\n");
        quickSortIterative(arr.clone(), 0, arr.length - 1);
        sb.append('└').append(line).append("┘\n");

    }

    void selectionSort(Comparable arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                int cmp = arr[j].compareTo(arr[min_idx]);
                if (ascOrder ? cmp < 0 : cmp >= 0) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first
            // element
            Comparable temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            appendOutput(i, arr);
        }
    }

    void insertionSort(Comparable arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            Comparable key = arr[i];
            int j = i - 1;
            while (j >= 0 && (ascOrder ? arr[j].compareTo(key) > 0 : arr[j].compareTo(key) <= 0)) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            appendOutput(i, arr);
        }
    }

    int partition(Comparable arr[], int low, int high) {
        Comparable pivot = arr[high];

        // index of smaller element
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            int cmp = arr[j].compareTo(pivot);
            if (ascOrder ? cmp <= 0 : cmp >= 0) {
                i++;

                // swap arr[i] and arr[j]
                Comparable temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Comparable temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    void quickSortIterative(Comparable arr[], int l, int h) {
        // Create an auxiliary stack
        int[] stack = new int[h - l + 2];
        int i = 0;

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
            appendOutput(i, arr);
            i++;
        }
    }

    private StringBuilder sb = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();

    /* A utility function to print array of size n*/
    @Override
    public String toString() {
        return sb.toString();
    }

    public void appendOutput(int i, Comparable arr[]) {
        sb.append(String.format("│ %-5s%-3d%-4s%-" + (line.length() - 13) + "s│\n",
                "Pass ", (i + 1), " -> ", appendArray(arr)));
    }

    String appendArray(Comparable arr[]) {
        sb2.setLength(0);
        if (arr.length >= 1) {
            int n = arr.length;
            for (int i = 0; i < (n - 1); ++i) {
                sb2.append("[").append(arr[i]).append("], ");
            }
            sb2.append("[").append(arr[n - 1]).append("]");
        }
        return sb2.toString();
    }

    public static int DEFAULT_L = 30;

    int getTotalLenght(Comparable arr[]) {
        int n = 15;
        if (arr.length >= 1) {
            n += (4 * arr.length);
            for (int i = 0; i < (arr.length - 1); ++i) {
                n += arr[i].toString().length();
            }
            n += arr[arr.length - 1].toString().length();
        }
        return n > DEFAULT_L ? n : DEFAULT_L;
    }
}
