/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public interface InterfaceArrayList<T> {
    
    // add new
    void add(T e);
    void add(int index, T element);
    void addAll(T[] c);
    void addAll(int index, T[] c) throws IndexOutOfBoundsException;
    
    // get
    T get(int index) throws IndexOutOfBoundsException;
    
    // remove
    T remove(int index) throws IndexOutOfBoundsException;

    // modify
    void set(int index, T element);

    // Others
    int indexOf(T o);
    int size();
    T[] toArray();
}
