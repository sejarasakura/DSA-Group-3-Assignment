/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterList;
import adt.node.DoubleNode;
import java.util.Iterator;

/**
 *
 * @author Woo Wei Qian
 * @param <E>
 */
public class XLinkedList<E> implements InterList<E> {

    transient int size = 0, modCount = 0;

    /**
     * Pointer to first node. Invariant: (first == null && last == null) ||
     * (first.prev == null && first.item != null)
     */
    transient DoubleNode<E> first;

    /**
     * Pointer to last node. Invariant: (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    transient DoubleNode<E> last;

    /**
     * Constructs an empty list.
     */
    public XLinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified collection, in
     * the order they are returned by the collection's iterator.
     *
     * @param datas the collection whose elements are to be placed into this
     * list
     * @throws NullPointerException if the specified collection is null
     */
    public XLinkedList(Iterable<E> datas) {
        this();
        for (Object x : datas) {
            add((E) x);
        }
    }

    @Override
    public final void add(E e) {
        linkLast(e);
    }

    @Override
    public void add(int index, E element) {

        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public void addAll(E[] c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, E[] c) throws IndexOutOfBoundsException {

        checkPositionIndex(index);

        Object[] a = c;
        int numNew = a.length;
        if (numNew == 0) {
            return;
        }

        DoubleNode<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked")
            E e = (E) o;
            DoubleNode<E> newNode = new DoubleNode<>(pred, e, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkElementIndex(index);
        return node(index).value;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {

        checkElementIndex(index);
        return unlink(node(index));

    }

    public boolean remove(Object o) {
        if (o == null) {
            for (DoubleNode<E> x = first; x != null; x = x.next) {
                if (x.value == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (DoubleNode<E> x = first; x != null; x = x.next) {
                if (o.equals(x.value)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void set(int index, E element) {

        checkElementIndex(index);
        DoubleNode<E> x = node(index);
        E oldVal = x.value;
        x.value = element;

    }

    @Override
    public int indexOf(E o) {

        int index = 0;
        if (o == null) {
            for (DoubleNode<E> x = first; x != null; x = x.next) {
                if (x.value == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (DoubleNode<E> x = first; x != null; x = x.next) {
                if (o.equals(x.value)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        return new XArrayList<E>(this).toArray();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E x : this) {
            sb.append(x).append(',').append('\n');
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        InterList<Integer> list = new XLinkedList<Integer>();
        Object[] data;
        list.add(1);
        list.add(12);
        list.add(3);
        list.add(8);
        list.add(12);
        list.add(20);
        list.add(9);
        data = list.toArray();
        System.out.print(list);
    }

    @Override
    public E[] clear() {
        for (DoubleNode<E> x = first; x != null;) {
            DoubleNode<E> next = x.next;
            x.value = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
        return null;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class LinkedListIterator implements Iterator<E> {

        private DoubleNode<E> x = first;

        @Override
        public boolean hasNext() {
            return x != null;
        }

        @Override
        public E next() {
            E r = x.value;
            x = x.next;
            return r;
        }
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        final DoubleNode<E> f = first;
        final DoubleNode<E> newNode = new DoubleNode<E>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Links e as last element.
     */
    private void linkLast(E e) {
        final DoubleNode<E> l = last;
        final DoubleNode<E> newNode = new DoubleNode<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private DoubleNode<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            DoubleNode<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            DoubleNode<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    private void linkBefore(E e, DoubleNode<E> succ) {
        // assert succ != null;
        final DoubleNode<E> pred = succ.prev;
        final DoubleNode<E> newNode = new DoubleNode<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(DoubleNode<E> f) {
        // assert f == first && f != null;
        final E element = f.value;
        final DoubleNode<E> next = f.next;
        f.value = null;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(DoubleNode<E> l) {
        // assert l == last && l != null;
        final E element = l.value;
        final DoubleNode<E> prev = l.prev;
        l.value = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(DoubleNode<E> x) {
        // assert x != null;
        final E element = x.value;
        final DoubleNode<E> next = x.next;
        final DoubleNode<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.value = null;
        size--;
        modCount++;
        return element;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
}
