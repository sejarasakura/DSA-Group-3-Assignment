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
public interface InterList<T> extends Iterable<T> {

    /**
     * add the value
     *
     * @param element
     */
    void add(T element);

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
     * @param element
     */
    void addAll(T[] element);

    /**
     * add the all value to index n
     *
     * @param index
     * @param element
     */
    void addAll(int index, T[] element) throws IndexOutOfBoundsException;

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
     * @param element
     * @return
     */
    int indexOf(T element);

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

    /**
     * Clear all the element in the list
     *
     * @return
     */
    public abstract T[] clear();

    /**
     * Check is empty or not
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * display in html format
     *
     * @return String
     */
    public abstract String toHtml();

    /**
     * display in console format
     *
     * @return String
     */
    @Override
    public abstract String toString();
}
