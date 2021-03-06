/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.node.DoubleNode;
import java.util.Iterator;
import java.util.NoSuchElementException;
import adt.interfaces.InterQuene;

/**
 * module Chatting, Booking
 *
 * @author Lai Hong Wah
 * @param <T>
 */
public class XQueue<T> implements InterQuene<T>, Cloneable {

    private final DoubleNode<T> head;
    private final DoubleNode<T> tail;
    private int size;

    /**
     * constructor
     */
    public XQueue() {
        head = new DoubleNode<T>(null);
        tail = new DoubleNode<T>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * constructor
     *
     * @param ib iterator
     */
    public XQueue(Iterable<T> ib) {
        this(ib.iterator());
    }

    /**
     * constructor
     *
     * @param is iterator
     */
    public XQueue(Iterator<T> is) {
        this();
        XStack<T> s = new XStack<T>();
        while (is.hasNext()) {
            try {
                s.push(is.next());
            } catch (Exception ex) {

            }
        }
        while (!s.isEmpty()) {
            this.enqueue((T) s.pop());
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements contained in the Queue.
     *
     * @return the number of elements contained in the Queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts the item to the back of the Queue.
     *
     * @param item, the item to be inserted
     */
    @Override
    public final void enqueue(T item) {

        DoubleNode<T> newNode = new DoubleNode<>(item);

        newNode.next = head.next;
        newNode.prev = head;

        head.next.prev = newNode;
        head.next = newNode;

        size++;
    }

    /**
     * Removes the item at the front of the Queue, and returns it.
     *
     * @return the item at the front of the Queue
     */
    @Override
    public final T dequeue() {

        if (tail.prev == null) {
            return null;
        }

        DoubleNode<T> last = tail.prev;

        last.prev.next = tail;
        tail.prev = last.prev;

        T value = last.value;
        last = null;

        size--;

        return value;
    }

    /**
     * Returns the item at the front of the Queue.
     *
     * @return the item at the front of the Queue
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return head.next.value;
    }

    /**
     * Return new queue iterator
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    /**
     * List string concatenation
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public XQueue<T> clone() {
        return new XQueue<T>(this);
    }

    /**
     * Queue item iterator
     */
    private class QueueIterator implements Iterator<T> {

        private DoubleNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current.next != tail;
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
            T item = current.next.value;
            current = current.next;
            return item;
        }
    }
}
