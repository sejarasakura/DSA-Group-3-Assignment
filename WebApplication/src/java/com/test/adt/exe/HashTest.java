/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

import adt.MapConverter;
import adt.XHashedDictionary;
import adt.XTreeDictionary;
import adt.node.TableEntry;
import cilent.pages.EditAdmin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.WebConfig;
import org.openjdk.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.codegen.CompilerConstants.__DIR__;

/**
 * @author ITSUKA KOTORI
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class HashTest {

    /**
     * @param args the command line arguments
     */
    public static void main3(String[] args) throws FileNotFoundException {

        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        XHashedDictionary map = new XHashedDictionary((Map) gson.fromJson(reader, Map.class));
        map = new XHashedDictionary(map.getValue("admin-nav"));
        System.out.print(map);
        System.out.print(new XHashedDictionary(map));
    }

    @Param({"100"})
    private int N;

    public static void main(String[] args) throws RunnerException {

        System.out.println(__DIR__.toString());
    }

    public static void mainx(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(HashTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    public static void main_x_order() throws FileNotFoundException {

        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        XTreeDictionary map = new XTreeDictionary(
                gson.fromJson(reader, WebConfig.WRITING_CLASS)
        );
        map = new XTreeDictionary(map.getValue("admin-nav"));
        //System.out.print(map);
        MapConverter mapConverter = new MapConverter(map);
    }

    @Benchmark
    public void testMyMap() {
        EditAdmin x = new EditAdmin();
    }

    public static void main_no_xorder() throws FileNotFoundException {

        String x = System.getProperty("user.dir") + "/data/adminNav.json";
        JsonReader reader = new JsonReader(new FileReader(x));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map map = (Map) gson.fromJson(reader, WebConfig.WRITING_CLASS);
        map = (Map) map.get("admin-nav");
        //System.out.print(map);
        gson.toJson(map, Map.class);
        //System.out.print(gson.toJson(map, Map.class));
    }

    public static void main2(String[] args) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        XHashedDictionary<String, String> test = new XHashedDictionary<String, String>();
        for (int i = 0; i < 100; i++) {
            test.add("" + i, "" + i + "v");
        }
        Iterator i = test.newEntryIterator();
        while (i.hasNext()) {
            TableEntry x = (TableEntry) i.next();
            sb.append(x.key).append(',').append(x.value).append(',').append(x.hash).append('\n');
        }
        System.out.print(sb.toString());
        Type typeMyType = new TypeToken<XHashedDictionary<String, String>>() {
        }.getType();
        String json = gson.toJson(test, typeMyType);
        System.out.print(json);
        gson.fromJson(json, typeMyType);
        i = test.newEntryIterator();
        while (i.hasNext()) {
            TableEntry x = (TableEntry) i.next();
            sb.append(x.key).append(',').append(x.value).append(',').append(x.hash).append('\n');
        }
        System.out.print(sb.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main2f(String[] args) {
        StringBuilder sb = new StringBuilder();
        XHashedDictionary<String, String> test = new XHashedDictionary<String, String>();
        for (int i = 0; i < 100; i++) {
            test.add("" + i, "" + i + "v");
            System.out.print(test.toString() + "\n\n");
        }
    }

}
