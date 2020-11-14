/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterfaceQuene;
import adt.node.DoubleNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class Queue<T>  implements InterfaceQuene<T>, Iterable<T>, Cloneable, java.io.Serializable{

    private final DoubleNode<T> head;
    private final DoubleNode<T> tail;
    private int size;
    
    public Queue() {
        head = new DoubleNode<T>(null);
        tail = new DoubleNode<T>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    public Queue(Iterable<T> ib) {
        this(ib.iterator());
    }
    
    public Queue(Iterator<T> is) {
        this();
        Stack s = new Stack();
        while(is.hasNext()){
            try{
                s.push(is.next());
            }catch(Exception ex){
                
            }
        }
        while(!s.isEmpty()){
            this.enqueue((T) s.pop());
        }
    }
        
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public final void enqueue(T item) {
        
        DoubleNode<T> newNode = new DoubleNode<>(item);

        newNode.next = head.next;
        newNode.prev = head;

        head.next.prev = newNode;
        head.next = newNode;

        size++;
    }

    @Override
    public final T dequeue() {

        if(tail.prev == null) {
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

    @Override
    public T peek() {
        if(isEmpty()) {
                return null;
        }

        return head.next.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
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
    
    // an iterator, doesn't implement remove() since it's optional
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
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.next.value;
            current = current.next; 
            return item;
        }
    }
}
