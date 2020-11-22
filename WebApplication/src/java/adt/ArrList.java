/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterfaceArrayList;
import entity.AbstractEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Functions;

/**
 *
 * @author Lim sai keat
 * @param <T>
 */
public class ArrList<T> implements InterfaceArrayList<T>, Iterable<T>, Cloneable, java.io.Serializable {

    final private static int INITIAL_CAPACITY = 1;
    protected T[] data;
    protected int index = -1;

    public ArrList() {
        this(1);
    }

    public ArrList(int inicap) {
        index = 0;
        data = (T[]) new Object[inicap];
    }

    public ArrList(T[] arr) {
        index = arr.length;
        data = arr;
    }

    public ArrList(Iterable<T> ib) {
        this(ib.iterator());
    }

    public ArrList(Iterator<T> is) {
        this();
        if (is != null) {
            while (is.hasNext()) {
                try {
                    this.add(is.next());
                } catch (Exception ex) {

                }
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

        if (data.length == index) {
            expandCapacity(index + 1);
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
        Object x = data[_index];
        for (int i = _index; i < index - 1; i++) {
            data[i] = data[i + 1];
        }
        index--;
        return (T) x;
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

    public T[] clear() {
        T[] x = data.clone();
        data = (T[]) new Object[INITIAL_CAPACITY];
        index = 0;
        return x;
    }

    public boolean removeSameElement() {
        if (index == 0 || index == 1) {
            return true;
        }
        XArraySortList a = new XArraySortList(this);
        a.sort();
        data = (T[]) a.toArray();
        int j = 0;
        for (int i = 0; i < index - 1; i++) {
            if (!data[i].equals(data[i + 1])) {
                data[j++] = data[i];
            }
        }
        data[j++] = data[index - 1];
        index = j;
        return true;
    }

    public T searchItem(T element) {
        ArrList<Integer> r = search(element);
        if (r.isEmpty()) {
            return null;
        }
        return data[r.get(0)];
    }

    public ArrList<T> searchByMethod(Method method, Object element) {
        ArrList<T> result = new ArrList<T>();
        try {
            for (T d : data) {
                if (method.invoke(d).equals(element)) {
                    result.add(d);
                }
            }
        } catch (SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrList<T> searchByField(String field, Object element, Class<?> _class) {
        ArrList<T> result = new ArrList<T>();
        try {
            Method f = _class.getDeclaredMethod(Functions.fieldToGetter(field));
            return searchByMethod(f, element);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrList<Integer> search(T element) {

        ArrList<Integer> result = new ArrList<Integer>();

        for (int i = 0; i < index - 1; i++) {
            if (data[i].equals(data[i + 1])) {
                result.add(i);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        if (index <= 0) {
            return "Empty";
        }
        String r = "";
        for (int i = 0; i < (index - 1); i++) {
            r += "" + data[i] + ", ";
        }
        r += "" + data[index - 1];
        return r;
    }

    public boolean find_AbstractEntity(AbstractEntity x) {

        for (int i = 0; i < index; i++) {
            if (((AbstractEntity) data[i]).id_equals(x)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return index <= 0;
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity(int minCapacity) {
        if (index >= minCapacity) {
            return;
        }

        T[] old = data;
        data = (T[]) new Object[minCapacity];

        System.arraycopy(old, 0, data, 0, index);

    }

    private void CheckRange(int _index) {
        if (_index < 0 || _index >= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String toInput() {
        if (index <= 0) {
            return "";
        }
        String r = "";
        for (int i = 0; i < (index - 1); i++) {
            r += "" + data[i] + "%";
        }
        r += "" + data[index - 1];
        return r;
    }

    public String toHtml() {
        System.out.println(this);
        if (index <= 0) {
            return "";
        }
        String r = "";
        for (int i = 0; i < (index - 1); i++) {
            r += "" + data[i] + "<br/>";
        }
        r += "" + data[index - 1];
        return r;
    }

    public void formInput(String input) {
        this.clear();
        if (!input.contains("%")) {
            return;
        }
        this.data = (T[]) input.split("%");
        this.index = data.length;
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
}
