/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass;

import adtClass.interfaces.InterfaceStack;
import adtClass.node.SingleNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class Stack<T> implements InterfaceStack<T>, Iterable<T>, Cloneable, java.io.Serializable{

    private int n;          // size of the stack
    private SingleNode<T> first;     // top of stack

    public Stack() {
        first = null;
        n = 0;
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
        if (isEmpty()) 
            throw new NoSuchElementException("Stack underflow");
        
        T item = first.item;       
        first = first.next;
        n--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) 
            throw new NoSuchElementException("Stack underflow");
        
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
            s.append(' ');
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
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }
    }
    
}
