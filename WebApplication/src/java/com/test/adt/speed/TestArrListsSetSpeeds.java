/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.speed;

import adt.*;
import java.util.ArrayList;
import java.util.List;
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
public class TestArrListsSetSpeeds {

    @Param({"1000000"})
    private int N;

    private ArrList<Object> DATA_FOR_TESTING_1;
    private List<Object> DATA_FOR_TESTING;
    private XQueue<Object> DATA_FOR_TESTING_2;
    private XStack<Object> DATA_FOR_TESTING_3;
    private Object[] DATA_FOR_TESTING_4;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestArrListsSetSpeeds.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
        DATA_FOR_TESTING_4 = DATA_FOR_TESTING.toArray();
        DATA_FOR_TESTING_1 = new ArrList<Object>(DATA_FOR_TESTING.toArray());
        DATA_FOR_TESTING_2 = new XQueue<Object>(DATA_FOR_TESTING_1);
        DATA_FOR_TESTING_3 = new XStack<Object>(DATA_FOR_TESTING_1);
    }

    @Benchmark
    public void loopArrList(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING_1.size(); i++) {
            String s = (String) DATA_FOR_TESTING_1.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopStack(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING_3.size(); i++) {
            String s = (String) DATA_FOR_TESTING_3.pop(); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopQueue(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING_2.size(); i++) {
            String s = (String) DATA_FOR_TESTING_2.dequeue(); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopArray(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING_4.length; i++) {
            String s = (String) DATA_FOR_TESTING_4[i]; //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopLibArrayList(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
            String s = (String) DATA_FOR_TESTING.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    private List<Object> createData() {
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("Number : " + i);
        }
        return data;
    }
}
