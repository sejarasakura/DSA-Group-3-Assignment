/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.speed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
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
public class TestFiboSpeeds {

    @Param({"100"})
    private int N;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestFiboSpeeds.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
    }

    @Benchmark
    public int fibRec() {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fibRec(N - 1) + fibRec(N - 2);
        }
    }

    public int fibRec(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fibRec(N - 1) + fibRec(N - 2);
        }
    }

    @Benchmark
    public int fibArray() {
        int[] a = new int[N + 1];
        if (a.length > 1) {
            a[1] = 1;
            for (int i = 2; i < N + 1; i++) {
                a[i] = a[i - 1] + a[i - 2];
            }
        }
        return a[N];
    }

    @Benchmark
    public int fibLoop() {
        int a = 0;
        int b = 1;
        int c = 0;
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            while (N > 1) {
                c = a + b;
                a = b;
                b = c;
                N--;
            }
        }
        return c;
    }

    private List<Object> createData() {
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("Number : " + i);
        }
        return data;
    }
}
