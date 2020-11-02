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
    
    // add new
    abstract void add(T e);
    abstract void add(int index, T element);
    abstract void addAll(T[] c);
    abstract void addAll(int index, T[] c) throws IndexOutOfBoundsException;
    
    // get
    abstract T get(int index) throws IndexOutOfBoundsException;
    
    // remove
    abstract T remove(int index) throws IndexOutOfBoundsException;

    // modify
    abstract void set(int index, T element);

    // Others
    abstract int indexOf(T o);
    abstract int size();
    abstract T[] toArray();
}
