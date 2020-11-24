/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import adt.interfaces.InterArrayList;
import adt.interfaces.InterSortingElements;

/**
 *
 * @author Chun Phang Chew
 * @param <T>
 *
 */
public class XArraySortList<T extends Comparable<T>> implements InterSortingElements<T>, InterArrayList<T>, Iterable<T>, Cloneable, java.io.Serializable {

    final private static int INITIAL_CAPACITY = 0;
    protected T[] data;
    protected int index = -1;

    public XArraySortList() {
        this((T[]) new Comparable[INITIAL_CAPACITY]);
    }

    public XArraySortList(T[] array) {
        this.data = array;
        this.index = array.length;
    }

    public XArraySortList(Iterator<T> arrList) {
        this();
        while (arrList.hasNext()) {
            this.add(arrList.next());
        }
    }

    public XArraySortList(Iterable<T> arrList) {
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
        for (T x : c) {
            add(index, x);
        }
    }

    @Override
    public void addAll(int _index, T[] c) {
        int count = 0;
        for (T x : c) {
            add(_index + count, x);
            count++;
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
        return x;
    }

    @Override
    public void set(int _index, T element) {
        CheckRange(_index);
        data[_index] = element;
    }

    @Override
    public int indexOf(T o) {
        for (int i = 0; i < index; i++) {
            if (data[i].equals(o)) {
                return i;
            }
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

    @Override
    public void sort() {
        this.sort(data, 0, this.index - 1);
        XStack s = new XStack(this);
        int i = this.index;
        while (!s.isEmpty()) {
            i--;
            data[i] = (T) s.pop();
        }
    }

    @Override
    public void sortDesc() {
        this.sort(data, 0, this.index - 1);
    }

    @Override
    public boolean sort(String field, Class _class) {
        try {
            Method f = _class.getMethod(fieldToGetter(field));
            boolean r = sort(f);
            XStack s = new XStack(this);
            int i = this.index;
            while (!s.isEmpty()) {
                i--;
                data[i] = (T) s.pop();
            }
            return r;
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean sortDesc(String field, Class _class) {
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
            r += "" + d + ", ";
        }
        return r;
    }

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

    private int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            try {
                // If current element is smaller than the pivot
                if (arr[j].compareTo(pivot) > 0) {
                    i++;

                    // swap arr[i] and arr[j]
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            } catch (Exception ex) {
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        T temp = arr[i + 1];
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

    private boolean sort(Method methods) {
        Class<?> cls = methods.getReturnType();
        try {
            if (cls != int.class) {
                Method m = cls.getDeclaredMethod("compareTo", cls);
                if (m.getReturnType() != int.class) {
                    return false;
                }
                this.sort_m(data, 0, this.index - 1, m, methods);
                return true;
            }
            this.sort_m(data, 0, this.index - 1, null, methods);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
}
