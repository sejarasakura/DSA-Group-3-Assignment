/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author Lai Hong Wah
 * @param <T>
 */
public interface InterQuene<T> extends Iterable<T> {

    /**
     * Returns true if the Queue contains no elements, otherwise false.
     *
     * @return true if the Queue contains no elements, otherwise false
     */
    boolean isEmpty();

    /**
     * Returns the number of elements contained in the Queue.
     *
     * @return the number of elements contained in the Queue
     */
    int size();

    /**
     * Inserts the item to the back of the Queue.
     *
     * @param item, the item to be inserted
     */
    void enqueue(T item);

    /**
     * Removes the item at the front of the Queue, and returns it.
     *
     * @return the item at the front of the Queue
     */
    T dequeue();

    /**
     * Returns the item at the front of the Queue.
     *
     * @return the item at the front of the Queue
     */
    T peek();
}
