/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author Lim Sai Keat
 * @param <T>
 */
public interface InterArrayList<T> {

    /**
     * add the value
     *
     * @param e
     */
    void add(T e);

    /**
     * add the value in index n
     *
     * @param index
     * @param element
     */
    void add(int index, T element);

    /**
     * add the all value
     *
     * @param c
     */
    void addAll(T[] c);

    /**
     * add the all value to index n
     *
     * @param index
     * @param c
     */
    void addAll(int index, T[] c) throws IndexOutOfBoundsException;

    /**
     * get the value of index n
     *
     * @param index
     * @return
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * remove the value of index n
     *
     * @param index
     * @return
     */
    T remove(int index) throws IndexOutOfBoundsException;

    /**
     * modify the value of index n
     *
     * @param index
     * @param element
     */
    void set(int index, T element);

    /**
     * get the index of in value
     *
     * @param o
     * @return
     */
    int indexOf(T o);

    /**
     * get the size of array
     *
     * @return
     */
    int size();

    /**
     * get the cloned array
     *
     * @return
     */
    T[] toArray();
}
