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
public class TestSortListSlotAlgorithmSpeeds {

    @Param({"10000"})
    private int N;
    int[] unsortedArray;
    int[] r;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestSortListSlotAlgorithmSpeeds.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    public TestSortListSlotAlgorithmSpeeds() {
        unsortedArray = createArrayWithRandomInts(100000);
    }

    @Setup
    public void setup() {

    }

    @Benchmark
    public void CountSloting(Blackhole bh) {
        bh.consume(countSort(unsortedArray.clone()));
    }

    @Benchmark
    public void CountSloting_1ms(Blackhole bh) {
        bh.consume(countSort2_1ms(unsortedArray.clone()));
    }

    @Benchmark
    public void BubberSloting(Blackhole bh) {
        bh.consume(bubbleSort(unsortedArray.clone()));
    }

    @Benchmark
    public void SelectionSloting(Blackhole bh) {
        bh.consume(selectionSort(unsortedArray.clone()));
    }

    @Benchmark
    public void InsertSloting(Blackhole bh) {
        bh.consume(insertionSort(unsortedArray.clone()));
    }

    @Benchmark
    public void QuickSloting(Blackhole bh) {
        benchmarkQuickSort(unsortedArray.clone());
    }

    @Benchmark
    public void QuickSloting_with_Recursion(Blackhole bh) {
        sort_rec(unsortedArray.clone(), 0, unsortedArray.length - 1);
    }

    @Benchmark
    public void QuickSloting_no_Recursion(Blackhole bh) {
        sort_norec(unsortedArray.clone());
    }

    @Benchmark
    public void MergeSloting(Blackhole bh) {
        benchmarkMergeSort(unsortedArray.clone());
    }

    /**
     * Sorting an array of ints in ascending order using bubbleSort Best-Case
     * Complexity: O(n), Average Complexity: O(n^2), Worst-Case Complexity:
     * O(n^2) O(n) is achieved in Best-Case (already sorted array) using the
     * alreadySorted flag
     *
     * @param array
     * @return
     */
    static int[] bubbleSort(int[] array) {
        int temp;
        boolean alreadySorted = true;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - 1; j++) {

                if (array[j] > array[j + 1]) {
                    alreadySorted = false;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }

            if (alreadySorted == true) {
                break;
            }
        }

        return array;
    }

    /**
     * Sorting an array of ints in ascending order using selectionSort Best-Case
     * Complexity: O(n^2), Average Complexity: O(n^2), Worst-Case Complexity:
     * O(n^2)
     *
     * @param array
     * @return
     */
    static int[] selectionSort(int[] array) {
        int min;
        int pos = 0;
        for (int i = 0; i < array.length - 1; i++) {

            min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    pos = j;
                }
            }
            array[pos] = array[i];
            array[i] = min;
        }

        return array;
    }

    /**
     * Sorting an array of ints in ascending order using insertionSort Best-Case
     * Complexity: O(n), Average Complexity: O(n^2), Worst-Case Complexity:
     * O(n^2)
     *
     * @param array
     * @return
     */
    static int[] insertionSort(int[] array) {
        int j;

        for (int i = 1; i < array.length; i++) {

            int key = array[i];

            for (j = i - 1; (j >= 0) && (key < array[j]); j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }

        return array;
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
     * Sorting an array of ints in ascending order using mergeSort Best-Case
     * Complexity: O(n log(n)), Average Complexity: O(n log(n)), Worst-Case
     * Complexity: O(n log(n)))
     *
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }

        int[] array1 = new int[(array.length / 2)];
        int[] array2 = new int[(array.length - array1.length)];

        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, array1.length, array2, 0, array2.length);

        mergeSort(array1);
        mergeSort(array2);

        merge(array1, array2, array);
        return array;
    }

    public int[] countSort2_1ms(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] arr = new int[max - min + 1];
        for (int num : nums) {
            arr[num - min]++;
        }

        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                nums[k] = i + min;
                k++;
            }
        }
        return nums;
    }

    public int[] countSort(int[] nums) {
        int min_ = Integer.MAX_VALUE, max_ = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (min_ > ele) {
                min_ = ele;
            }
            if (max_ < ele) {
                max_ = ele;
            }
        }
        int[] freq = new int[max_ - min_ + 1];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - min_]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        int[] ans = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = freq[nums[i] - min_] - 1;
            ans[idx] = nums[i];
            freq[nums[i] - min_]--;
        }
        return ans;
    }

    /**
     * Merges 2 sorted arrays of ints
     *
     * @param array1
     * @param array2
     * @param mergedArray
     * @return
     */
    static void merge(int[] array1, int[] array2, int[] mergedArray) {
        int array1Index = 0;
        int array2Index = 0;
        int pos = 0;
        while ((array1Index < array1.length) && (array2Index < array2.length)) {
            if (array1[array1Index] < array2[array2Index]) {
                mergedArray[pos] = array1[array1Index];
                array1Index++;
                pos++;
            } else {
                mergedArray[pos] = array2[array2Index];
                array2Index++;
                pos++;
            }
        }

        if (array1Index < array2Index) {
            System.arraycopy(array1, array1Index, mergedArray, pos, array1.length - array1Index);
        } else if (array2Index < array1Index) ;
        {
            System.arraycopy(array2, array2Index, mergedArray, pos, array2.length - array2Index);
        }
    }

    /**
     * Helping method to benchmark merge sort's execution time
     *
     * @param array
     */
    static void benchmarkMergeSort(int[] array) {
        mergeSort(array);
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
