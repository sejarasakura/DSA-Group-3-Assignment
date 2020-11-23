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

    public final T value;
    public DoubleNode<T> prev;
    public DoubleNode<T> next;

    public DoubleNode(final T value) {
        this.value = value;
        this.prev = this.next = null;
    }

    public void clear() {
        prev = null;
        next = null;
    }
}
