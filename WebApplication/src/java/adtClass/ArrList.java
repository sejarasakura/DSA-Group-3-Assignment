/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass;
import adtClass.interfaces.*;
import entityClass.AbstractEntity;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class ArrList<T> implements InterfaceArrayList<T>, Iterable<T>, Cloneable, java.io.Serializable{

    final private static int INITIAL_CAPACITY = 1;
    protected T[] data;
    protected int index = -1;
    
    public ArrList(){
        this(1);
    }
    
    public ArrList(int inicap){
        index = 0;
        data = (T[]) new Object[inicap];
    }
    
    public ArrList(T[] arr){
        index = arr.length;
        data = (T[]) arr;
    }
    
    public ArrList(Iterable<T> ib){
        this(ib.iterator());
    }
    
    public ArrList(Iterator<T> is){
        this();
        while(is.hasNext()){
            try{
                this.add(is.next());
            }catch(Exception ex){
                
            }
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public final void add(T e) {
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
        for(T x: c){
            add(index, x);
        }
    }

    @Override
    public void addAll(int _index, T[] c) {
        int count = 0;
        for(T x: c){
            add(_index + count, x);
            count ++;
        }
    }

    @Override
    public T get(int _index) {
        return data[_index];
    }

    @Override
    public T remove(int _index) {
        CheckRange(_index); 
        Object x = data[_index];
        for (int i = _index; i < index - 1; i++) {
            data[i] = data[i + 1]; 
        }
        index--;
        return (T)x;
    }

    @Override
    public void set(int _index, T element) {
        CheckRange(_index); 
        data[_index] = element;
    }

    @Override
    public int indexOf(T o) {
        for(int i = 0; i < index; i ++){
            if(data[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public T[] toArray() {
        return data.clone();
    }
    
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (index >= minCapacity) {
            return;
        }

        T[] old = data; 
        data = (T[]) new Object[minCapacity];

        System.arraycopy(old, 0, data, 0, index);

    }

    private void CheckRange(int _index) {
        if (_index < 0 || _index >= index) 
            throw new IndexOutOfBoundsException();
        
    }

    private void CheckRangeForAdd(int _index) {
    }

    public boolean find_AbstractEntity(AbstractEntity x) {
        
        for(T d: data){
            if(((AbstractEntity)d).id_equals(x))
                return true;
        }
        return false;
    }
    
    private class ListIterator implements Iterator<T> {

        private int iteratorIndex = -1;
        @Override
        public boolean hasNext() {
            return iteratorIndex < index - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            iteratorIndex++;
            return data[iteratorIndex];
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
    
    @Override
    public String toString(){
        String r = "";
        for(T d: (data)){
            r += ""+  d + ", ";
        }
        return r;
    }
}
