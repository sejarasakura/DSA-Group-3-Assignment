/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.speed;

import adt.XArraySortList;
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
public class TestSortListSlotAlgorithmSpeedsMyAdt {

    @Param({"100"})
    private int N;
    int[] unsortedArray;
    int[] ua;
    int[] r;

    XArraySortList a = new XArraySortList();
    XArraySortList b = new XArraySortList();

    public void testResult() {
        ua = createArrayWithRandomInts(10);
        unsortedArray = createArrayWithRandomInts(1000);
        for (int i = 0; i < ua.length; i++) {
            a.add(ua[i]);
            b.add(ua[i]);
        }
        printArray(ua);
        a.sort();
        b.sort();
        System.out.println("\nQuicksort recursion");
        System.out.println(b);

        System.out.println("\nQuicksort no recursion");
        System.out.println(a);
    }

    public static void main2(String[] args) throws RunnerException {
        new TestSortListSlotAlgorithmSpeedsMyAdt().testResult();
    }

    public static void main(String[] args) throws RunnerException {

        new TestSortListSlotAlgorithmSpeedsMyAdt().testResult();
        Options opt = new OptionsBuilder()
                .include(TestSortListSlotAlgorithmSpeedsMyAdt.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    public TestSortListSlotAlgorithmSpeedsMyAdt() {
        for (int i = 0; i < ua.length; i++) {
            a.add(ua[i]);
            b.add(ua[i]);
        }
    }

    @Setup
    public void setup() {
        ua = unsortedArray.clone();
    }

    @Benchmark
    public void QuickSortWithRecursion(Blackhole bh) {
        sort(unsortedArray, 0, unsortedArray.length - 1);
    }

    @Benchmark
    public void QuickSortNoRecursion(Blackhole bh) {
        sort(unsortedArray);
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
    void sort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    /**
     * Without recursion
     *
     * @param arr
     */
    private void sort(int[] arr) {

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

    /**
     * Without recursion
     *
     * @param arr
     */
    private void sort_f(int[] arr) {

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

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
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

}
