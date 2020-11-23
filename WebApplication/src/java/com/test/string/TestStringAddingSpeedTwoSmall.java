/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.string;

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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class TestStringAddingSpeedTwoSmall {

    @Param({"1000"})
    private int N;

    private List<String> DATA_FOR_TESTING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestStringAddingSpeedTwoSmall.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
    }

    @Benchmark
    public void plus_string(Blackhole bh) {
        String str_test;
        for (String str : DATA_FOR_TESTING) {
            str_test = str + str;
        }
    }

    @Benchmark
    public void string_concate(Blackhole bh) {
        String str_test;
        for (String str : DATA_FOR_TESTING) {
            str_test = str.concat(str);
        }
    }

    @Benchmark
    public void string_buffer(Blackhole bh) {
        String str_test;
        for (String str : DATA_FOR_TESTING) {
            str_test = new StringBuffer().append(str).append(str).toString();
        }
    }

    @Benchmark
    public void string_builder(Blackhole bh) {
        String str_test;
        for (String str : DATA_FOR_TESTING) {
            str_test = new StringBuilder().append(str).append(str).toString();
        }
    }

    @Benchmark
    public void string_format(Blackhole bh) {
        String str_test = new String();
        for (String str : DATA_FOR_TESTING) {
            str_test = String.format("%s%s", str, str);
        }
    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("Number : " + i);
        }
        return data;
    }
}
