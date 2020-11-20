/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.speed;

import adt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class TestSortListAddingSpeeds {

    @Param({"100"})
    private int N;

    Random rand;
    private adt.XArraySortList<String> DATA_FOR_TESTING_1;
    private ArrayList<String> DATA_FOR_TESTING_0;
    private ArrayList<String> DATA_FOR_TESTING_2;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestSortListAddingSpeeds.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        rand = new Random();
        DATA_FOR_TESTING_0 = (ArrayList<String>) createData();
        DATA_FOR_TESTING_2 = new ArrayList<String>();
        DATA_FOR_TESTING_1 = new XArraySortList<String>();
    }

    @Benchmark
    public void sortArrList(Blackhole bh) {
        for (int i = 0; i < N; i++) {
            DATA_FOR_TESTING_1.add(DATA_FOR_TESTING_0.get(i));
            bh.consume(DATA_FOR_TESTING_1);
        }
        this.DATA_FOR_TESTING_1.sort();
    }

    @Benchmark
    public void sortCollection(Blackhole bh) {
        for (int i = 0; i < N; i++) {
            DATA_FOR_TESTING_2.add(DATA_FOR_TESTING_0.get(i));
            bh.consume(DATA_FOR_TESTING_1);
        }
        Collections.sort(this.DATA_FOR_TESTING_2, Collections.reverseOrder());
    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("" + rand.nextInt(1000));
        }
        return data;
    }
}
