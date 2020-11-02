/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass;
import adtClass.interfaces.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class ArrList<T> implements InterfaceArrayList<T>, Cloneable, java.io.Serializable{

    final private static int INITIAL_CAPACITY = 1;
    private T[] data;
    private int index = -1;
    
    
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public void add(T e) {
        add(index, e);
    }

    @Override
    public void add(int _index, T element) {
        CheckRangeForAdd(_index); 
        
        if (data.length == index) {
            ensureCapacity(index + 1); 
        }
        for (int i = index; i > _index; i--) {
            data[i] = data[i - 1]; 
        }
        data[_index] = element;
        index++;
    }

    @Override
    public void addAll(T[] c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAll(int index, T[] c) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(int index, T element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(T o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (index >= minCapacity) {
            return;
        }

        T[] old = data; 
        data = (T[]) new Object[minCapacity];

        for (int i = 0; i < index; i++) {
            data[i] = old[i];
        }

    }

    private void CheckRange(int _index) {
        if (_index < 0 || _index >= index) 
            throw new IndexOutOfBoundsException();
        
    }

    private void CheckRangeForAdd(int _index) {
        if (_index < 0 || _index > index) 
            throw new IndexOutOfBoundsException();
        
    }
    
    private class ListIterator implements Iterator<T> {

        private int iteratorIndex = -1;
        @Override
        public boolean hasNext() {
            return iteratorIndex < index;
        }

        @Override
        public T next() {
            if (!hasNext()) 
                throw new NoSuchElementException();

            return data[iteratorIndex++];
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> cnsmr) {
            Iterator.super.forEachRemaining(cnsmr);
        }

    }
    
}
