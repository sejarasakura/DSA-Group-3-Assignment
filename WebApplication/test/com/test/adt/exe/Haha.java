/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.adt.exe;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Haha implements Comparable {

    private int field;
    private String field2;

    public Haha(int field, String field2) {
        this.field = field;
        this.field2 = field2;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public int compareTo(Object t) {
        return Integer.compare(field, ((Haha) t).field);
    }

    @Override
    public String toString() {
        return "Haha{" + field + "," + field2 + '}';
    }

}
