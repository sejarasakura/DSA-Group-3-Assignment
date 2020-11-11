/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass;

import adtClass.interfaces.InterfaceArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import adtClass.interfaces.InterfaceSlotingElements;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 * 
 */
public class ArraySlotList <T extends Comparable<T>> implements InterfaceSlotingElements <T>, InterfaceArrayList<T>, Iterable<T>, Cloneable, java.io.Serializable{

    final private static int INITIAL_CAPACITY = 0;
    protected T[] data;
    protected int index = -1;
    
    public ArraySlotList() {
        this((T[])new Comparable[INITIAL_CAPACITY]);
    }

    public ArraySlotList(T[] array) {
        this.data = array;
        this.index = array.length;
    }
    
    public ArraySlotList(Iterator<T> arrList) {
        this();
        while(arrList.hasNext()){
            this.add(arrList.next());
        }
    }
    
    public ArraySlotList(Iterable<T> arrList) {
        this(arrList.iterator());
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
        T x = data[_index];
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
        data = (T[]) new Comparable[minCapacity];

        System.arraycopy(old, 0, data, 0, index);

    }

    private void CheckRange(int _index) {
        if (_index < 0 || _index >= index) 
            throw new IndexOutOfBoundsException();
        
    }

    private void CheckRangeForAdd(int _index) {
    }
    
    private class ListIterator implements Iterator<T> {

        private int iteratorIndex = -1;
        @Override
        public boolean hasNext() {
            return iteratorIndex < index -1;
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
    
    private int partition(T arr[], int low, int high) 
    { 
        T pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            try{
            // If current element is smaller than the pivot 
            if (arr[j].compareTo(pivot) > 0) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                T temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
            }catch(Exception ex){}
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        T temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
    private void sort(T arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
    
    @Override
    public void sort(){
        this.sort(data, 0, this.index - 1);
        Stack s = new Stack(this);
        int i = this.index;
        while(!s.isEmpty()){
            i--;
            data[i] = (T) s.pop();
        }
    }
    
    @Override
    public void sortDesc(){
        this.sort(data, 0, this.index - 1);
    }
    
    @Override
    public String toString(){
        String r = "";
        for(T d: (data)){
            r +=""+  d + ", ";
        }
        return r;
    }

}
