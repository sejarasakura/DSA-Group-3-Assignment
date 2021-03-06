/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterSortingElements;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import xenum.AbstractEnum;

/**
 *
 * @author Chun Phang Chew
 * @param <T>
 *
 */
public class XArraySortList<T extends Comparable<?>> implements InterSortingElements<T> {

    /**
     * Initial capacity of the list
     */
    final private static int INITIAL_CAPACITY = 0;

    /**
     * The comparable data
     */
    protected T[] data;

    /**
     * the sizes of the dynamic list
     */
    protected int index = -1;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public XArraySortList() {
        this((T[]) new Comparable[INITIAL_CAPACITY]);
    }

    /**
     * Constructor
     *
     * @param array
     */
    public XArraySortList(T[] array) {
        this.data = array;
        this.index = array.length;
    }

    /**
     * Constructor
     *
     * @param arrList
     */
    public XArraySortList(Iterator<T> arrList) {
        this();
        while (arrList.hasNext()) {
            this.add(arrList.next());
        }
    }

    /**
     * Constructor
     *
     * @param arrList
     */
    public XArraySortList(Iterable<T> arrList) {
        this(arrList.iterator());
    }

    //=============================================================================
    /**
     * iterator
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * add new
     *
     * @param e
     */
    @Override
    public final void add(T e) {
        add(index, e);
    }

    /**
     * add new in index _index
     *
     * @param _index
     * @param element
     */
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

    /**
     * add all element to the dynamic list
     *
     * @param c
     */
    @Override
    public void addAll(T[] c) {
        for (T x : c) {
            add(index, x);
        }
    }

    /**
     * add all in index _index
     *
     * @param _index
     * @param c
     */
    @Override
    public void addAll(int _index, T[] c) {
        int count = 0;
        for (T x : c) {
            add(_index + count, x);
            count++;
        }
    }

    /**
     * get the element _index
     *
     * @param _index
     * @return
     */
    @Override
    public T get(int _index) {
        return data[_index];
    }

    /**
     * Remove the index at _index
     *
     * @param _index
     * @return
     */
    @Override
    public T remove(int _index) {
        CheckRange(_index);
        T x = data[_index];
        for (int i = _index; i < index - 1; i++) {
            data[i] = data[i + 1];
        }
        index--;
        return x;
    }

    /**
     * Set the value in index _index
     *
     * @param _index
     * @param element
     */
    @Override
    public void set(int _index, T element) {
        CheckRange(_index);
        data[_index] = element;
    }

    /**
     * get the element index
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(T o) {
        for (int i = 0; i < index; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    //=============================================================================
    /**
     * get the element size
     *
     * @return
     */
    @Override
    public int size() {
        return index;
    }

    /**
     * return the array element
     *
     * @return
     */
    @Override
    public T[] toArray() {
        return data.clone();
    }

    /**
     * to fix the capacity problem
     *
     * @param minCapacity
     */
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (index >= minCapacity) {
            return;
        }

        T[] old = data;
        data = (T[]) new Comparable[minCapacity];

        System.arraycopy(old, 0, data, 0, index);

    }

    /**
     * sort the element in the List
     */
    @Override
    public void sort() {
        this.sort(data, 0, this.index - 1);
        XStack<T> s = new XStack<T>(this);
        int i = this.index;
        while (!s.isEmpty()) {
            i--;
            data[i] = s.pop();
        }
    }

    /**
     * sort desc order the element in the List
     */
    @Override
    public void sortDesc() {
        this.sort(data, 0, this.index - 1);
    }

    /**
     * sort the element in the List according to the field name
     *
     * @param field
     * @param _class
     * @return success sort or not
     */
    @Override
    public boolean sort(String field, Class<?> _class) {
        try {
            Method f = _class.getMethod(fieldToGetter(field));
            boolean r = sort(f);
            XStack<T> s = new XStack<T>(this);
            int i = this.index;
            while (!s.isEmpty()) {
                i--;
                data[i] = s.pop();
            }
            return r;
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * sort the element in the List descending to the field name
     *
     * @param field
     * @param _class
     * @return
     */
    @Override
    public boolean sortDesc(String field, Class<?> _class) {
        try {
            Method f = _class.getMethod(fieldToGetter(field));
            return sort(f);
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String toString() {
        String r = "";
        for (T d : (data)) {
            r += "" + d + "\n";
        }
        return r;
    }

    @Override
    public String toHtml() {
        String r = "";
        for (T d : (data)) {
            r += "" + d + "<br> ";
        }
        return r;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] clear() {
        T[] x = data.clone();
        data = (T[]) new Object[INITIAL_CAPACITY];
        index = 0;
        return x;
    }

    @Override
    public boolean isEmpty() {
        return index <= 0;
    }

    /**
     * *********************************************************************
     *
     * Private Method
     *
     *********************************************************************
     */
    private void CheckRange(int _index) {
        if (_index < 0 || _index >= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    private String fieldToGetter(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private void CheckRangeForAdd(int _index) {
        // cilent is golden
    }

    private class ListIterator implements Iterator<T> {

        private int iteratorIndex = -1;

        @Override
        public boolean hasNext() {
            return iteratorIndex < index - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
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

    @SuppressWarnings("all")
    private int partition(Comparable[] arr, int low, int high) {
        Comparable pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            try {
                // If current element is smaller than the pivot
                if (pivot.compareTo(arr[j]) > 0) {
                    i++;

                    // swap arr[i] and arr[j]
                    Comparable temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            } catch (Exception ex) {
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Comparable temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private void sort(T[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    /**
     * @param getter reflection methods to call getter dynamic
     * @param sort_method to get the default sorting methods Object
     * compareTo(Object) | null for int
     */
    private int partition_m(T[] arr, int low, int high, Method sort_method, Method getter) {
        T pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            try {
                // If current element is smaller than the pivot
                int result;
                if (sort_method != null) {
                    result = (int) sort_method.invoke(getter.invoke(arr[j]), getter.invoke(pivot));
                } else {
                    result = Integer.compare((int) getter.invoke(arr[j]), (int) getter.invoke(pivot));
                }
                if (result > 0) {
                    i++;
                    // swap arr[i] and arr[j]
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private void sort_m(T[] arr, int low, int high, Method sort_method, Method getter) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition_m(arr, low, high, sort_method, getter);

            // Recursively sort elements before
            // partition and after partition
            sort_m(arr, low, pi - 1, sort_method, getter);
            sort_m(arr, pi + 1, high, sort_method, getter);
        }
    }

    private boolean sort(Method getter) {
        Class<?> cls = getter.getReturnType();
        try {
            if (cls.getName().contains("xenum.")) {
                Method compare_function = cls.getMethod("compare", AbstractEnum.class);
                if (compare_function.getReturnType() != int.class) {
                    return false;
                }
                this.sort_m(data, 0, this.index - 1, compare_function, getter);
            } else if (cls != int.class) {
                Method compare_function = cls.getMethod("compareTo", cls);
                if (compare_function.getReturnType() != int.class) {
                    return false;
                }
                this.sort_m(data, 0, this.index - 1, compare_function, getter);
            } else {
                this.sort_m(data, 0, this.index - 1, null, getter);
            }
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
