/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.node;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class DoubleNode<T> {

    /**
     * Value of the node
     */
    public T value;

    /**
     * Previous pointer
     */
    public DoubleNode<T> prev;

    /**
     * Next pointer
     */
    public DoubleNode<T> next;

    /**
     * Constructor
     *
     * @param value
     */
    public DoubleNode(final T value) {
        this.value = value;
        this.prev = this.next = null;
    }

    /**
     * Constructor
     *
     * @param prev
     * @param value
     * @param next
     */
    public DoubleNode(DoubleNode<T> prev, T value, DoubleNode<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Clear the double node
     */
    public void clear() {
        prev = null;
        next = null;
    }
}
