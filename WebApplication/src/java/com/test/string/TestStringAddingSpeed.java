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
public class TestStringAddingSpeed {

    @Param({"1000"})
    private int N;

    private List<String> DATA_FOR_TESTING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(TestStringAddingSpeed.class.getSimpleName())
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
        String str_test = "";
        for (String str : DATA_FOR_TESTING) {
            str_test = str_test + str;
        }
    }

    @Benchmark
    public void string_concate(Blackhole bh) {
        String str_test = "";
        for (String str : DATA_FOR_TESTING) {
            str_test = str_test.concat(str);
        }
    }

    @Benchmark
    public void string_buffer(Blackhole bh) {
        StringBuffer str_test = new StringBuffer();
        for (String str : DATA_FOR_TESTING) {
            str_test.append(str);
        }
    }

    @Benchmark
    public void string_builder(Blackhole bh) {
        StringBuilder str_test = new StringBuilder();
        for (String str : DATA_FOR_TESTING) {
            str_test.append(str).append(str);
        }
    }

    @Benchmark
    public void string_format(Blackhole bh) {
        String str_test = new String();
        for (String str : DATA_FOR_TESTING) {
            str_test = String.format("%s%s", str_test, str);
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
