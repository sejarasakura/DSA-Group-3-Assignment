/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass.interfaces;

import adtClass.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public interface InterfaceArrayList<T> {
    
    void add(T e);
    void add(int index, T element);
    void addAll(T[] c);
    void addAll(int index, T[] c) throws IndexOutOfBoundsException;
    T get(int index) throws IndexOutOfBoundsException;
    T remove(int index) throws IndexOutOfBoundsException;

    void set(int index, T element);

    int indexOf(T o);

    int size();
    T[] toArray();
}
