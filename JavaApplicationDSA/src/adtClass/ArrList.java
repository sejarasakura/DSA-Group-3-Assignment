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

    final private static int INITIAL_CAPACITY = 50;
    private T[] data;
    private int index = -1;
    
    
    public Iterator<T> iterator() {
        return new ListIterator();
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
