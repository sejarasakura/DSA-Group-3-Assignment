/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterStack;
import adt.node.SingleNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Chun Phang Chew
 * @param <T>
 * @paraimport adt.interfaces.InterStack;
 */
public final class XStack<T> implements InterStack<T>, Iterable<T> {

    private int n;
    private SingleNode<T> first;

    /**
     * constructor
     */
    public XStack() {
        first = null;
        n = 0;
    }

    /**
     * constructor
     *
     * @param ib iterator
     */
    public XStack(Iterable<T> ib) {
        this(ib.iterator());
    }

    /**
     * constructor
     *
     * @param is iterator
     */
    public XStack(Iterator<T> is) {
        this();
        XStack s = new XStack();
        while (is.hasNext()) {
            try {
                s.push(is.next());
            } catch (Exception ex) {

            }
        }
        while (!s.isEmpty()) {
            this.push((T) s.pop());
        }
    }

    @Override
    public void push(T item) {
        SingleNode oldfirst = first;
        first = new SingleNode();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        T item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return first.item;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(", ");
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class StackIterator implements Iterator<T> {

        private SingleNode<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }

}
