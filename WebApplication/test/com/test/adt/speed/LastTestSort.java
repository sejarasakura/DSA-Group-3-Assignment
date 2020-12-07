/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.speed;

import adt.XStack;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author ITSUKA KOTORI
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class LastTestSort {

    @Param({"10000"})
    private int N;
    int[] unsortedArray;
    int[] r;

    public static void main(String[] args) throws RunnerException {
//
//        Options opt = new OptionsBuilder()
//                .include(LastTestSort.class.getSimpleName())
//                .forks(1)
//                .build();
//
//        new Runner(opt).run();
        LastTestSort d = new LastTestSort();
        int[] ref = d.unsortedArray.clone();
        d.sort_no_rec2(ref, 0, ref.length - 1);
        printArray(ref);
    }

    public LastTestSort() {
        unsortedArray = createArrayWithRandomInts(50);
    }

    @Setup
    public void setup() {

    }

    @Benchmark
    public void QuickSloting_while_loop(Blackhole bh) {
        benchmarkQuickSort(unsortedArray.clone());
    }

    @Benchmark
    public void QuickSloting_with_Recursion(Blackhole bh) {
        sort_rec(unsortedArray.clone(), 0, unsortedArray.length - 1);
    }

    @Benchmark
    public void QuickSloting_no_Recursion_my(Blackhole bh) {
        sort_norec(unsortedArray.clone());
    }

    @Benchmark
    public void QuickSloting_no_Recursion_geek(Blackhole bh) {
        sort_no_rec2(unsortedArray.clone(), 0, unsortedArray.length - 1);
    }

    /**
     * Sorting an array of ints in ascending order using quickSort Best-Case
     * Complexity: O(n log(n)), Average Complexity: O(n log(n)), Worst-Case
     * Complexity: O(n^2))
     *
     * @param array
     * @return
     */
    static void quickSort(int[] array, int low, int high) {
        int pivot = array[low + ((high - low) / 2)];
        int i = low;
        int j = high;

        while (i <= j) {

            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
        }

        if (i < high) {
            quickSort(array, i, high);
        }
    }

    /**
     * Helping method to benchmark quick sort's execution time
     *
     * @param array
     */
    static void benchmarkQuickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Creates and returns an array with random ints
     *
     * @param size the size of the array to be created
     * @return
     */
    static int[] createArrayWithRandomInts(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Math.random() * 10000);
        }
        return array;
    }

    /**
     * Prints the elements of one dimensional array of type int
     *
     * @param array
     */
    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * with recursion
     *
     * @param arr
     * @param low
     * @param high
     */
    void sort_rec(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort_rec(arr, low, pi - 1);
            sort_rec(arr, pi + 1, high);
        }
    }

    void sort_no_rec2(int arr[], int l, int h) {

        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];

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
        }
    }

    /**
     * Without recursion
     *
     * @param arr
     */
    private void sort_norec(int[] arr) {

        XStack<Integer> s = new XStack();

        int pivotIndex = 0;
        int low = 0;
        int high = arr.length - 1;

        s.push(pivotIndex);
        s.push(high);

        while (!s.isEmpty()) {
            high = s.pop();
            low = s.pop();

            if (low > high) {
                continue;
            }

            pivotIndex = partition(arr, low, high);

            if (low < high) {
                s.push(low);
                s.push(pivotIndex - 1);
                s.push(pivotIndex + 1);
                s.push(high);
            }
        }
    }

}
