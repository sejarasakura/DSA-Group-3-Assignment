/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterArrayList;
import entity.AbstractEntity;
import java.lang.reflect.Field;
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
public class ArrList<T> implements InterArrayList<T>, Iterable<T>, Cloneable, java.io.Serializable {

    /**
     * Starting capacity
     */
    final private static int INITIAL_CAPACITY = 1;

    /**
     * Data inside
     */
    protected T[] data;

    /**
     * Index
     */
    protected int index = -1;

    /**
     * Constructor
     */
    public ArrList() {
        this(1);
    }

    /**
     * constructor
     *
     * @param initialCapacity
     */
    public ArrList(int initialCapacity) {
        index = 0;
        data = (T[]) new Object[initialCapacity];
    }

    /**
     * constructor
     *
     * @param array
     */
    public ArrList(T[] array) {
        index = array.length;
        data = array;
    }

    /**
     * constructor
     *
     * @param iterable to make good integrate to other Iterable object
     * eg.(Arrlist, Queue, Stack, ..)
     */
    public ArrList(Iterable<T> iterable) {
        this(iterable.iterator());
    }

    /**
     * constructor
     *
     * @param iterator to make to integrate to other Iterable.iterator object
     */
    public ArrList(Iterator<T> iterator) {
        this();
        if (iterator != null) {
            while (iterator.hasNext()) {
                try {
                    this.add(iterator.next());
                } catch (Exception ex) {

                }
            }
        }
    }

    /**
     * iterator() function
     *
     * @return back this Iterator reference of this class
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * adding function at last
     *
     * @param e pass the object need to add to array list it must match the T
     * type
     */
    @Override
    public final void add(T e) {
        add(index, e);
    }

    /**
     * adding in index x function
     *
     * @param _index of adding
     * @param e pass the object need to add to array list it must match the T
     * type
     */
    @Override
    public void add(int _index, T e) {

        if (data.length == index) {
            expandCapacity(index + 1);
        }
        for (int i = index; i > _index; i--) {
            data[i] = data[i - 1];
        }
        data[_index] = e;
        index++;
    }

    /**
     * adding all function
     *
     * @parma e all the elements
     */
    @Override
    public void addAll(T[] c) {
        for (T x : c) {
            add(index, x);
        }
    }

    /**
     * adding all form index function
     *
     * @param _index
     * @parma e all the elements
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
     * get in index
     *
     * @param _index
     * @return elements
     */
    @Override
    public T get(int _index) {
        return data[_index];
    }

    /**
     * remove element in the list
     *
     * @param _index
     * @return elements
     */
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

    /**
     * Set in index x
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
     * get index by value
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

    /**
     * get the size of list
     *
     * @return
     */
    @Override
    public int size() {
        return index;
    }

    /**
     * convert this to array
     *
     * @return
     */
    @Override
    public T[] toArray() {
        return data.clone();
    }

    /**
     * clear this list
     *
     * @return array data back
     */
    public T[] clear() {
        T[] x = data.clone();
        data = (T[]) new Object[INITIAL_CAPACITY];
        index = 0;
        return x;
    }

    /**
     * search item
     *
     * @return ture that if list no same element
     */
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

    /**
     * search methods
     *
     * @param element
     * @return Array List
     */
    public ArrList<Integer> search(T element) {

        ArrList<Integer> result = new ArrList<Integer>();

        for (int i = 0; i < index - 1; i++) {
            if (data[i].equals(data[i + 1])) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * search item
     *
     * @param element
     * @return Same item or null
     */
    public T searchItem(T element) {
        ArrList<Integer> r = search(element);
        if (r.isEmpty()) {
            return null;
        }
        return data[r.get(0)];
    }

    /**
     * search methods
     *
     * @param method
     * @param element
     * @return Array List
     */
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

    /**
     * search methods
     *
     * @param field
     * @param element
     * @return Array List
     */
    public ArrList<T> searchByField(Field field, Object element) {
        ArrList<T> result = new ArrList<T>();
        try {
            for (T d : data) {
                if (field.get(d).equals(element)) {
                    result.add(d);
                }
            }
        } catch (SecurityException
                | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * search by element field name
     *
     * @param field
     * @param element
     * @param _class
     * @return Array List
     */
    public ArrList<T> searchByField(String field, Object element, Class<?> _class) {
        ArrList<T> result = new ArrList<T>();
        try {
            Field f = _class.getDeclaredField(field);
            if (f.isAccessible()) {
                return searchByField(f, element);
            } else {
                Method m = _class.getDeclaredMethod(Functions.fieldToGetter(field));
                return searchByMethod(m, element);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | NoSuchFieldException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * to string method
     *
     * @return string
     */
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

    /**
     * find a abstract entity that is id match one
     *
     * @param x
     * @return Boolean
     */
    public boolean find_AbstractEntity(AbstractEntity x) {

        for (int i = 0; i < index; i++) {
            if (((AbstractEntity) data[i]).id_equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check is empty or not
     *
     * @return Boolean
     */
    public boolean isEmpty() {
        return index <= 0;
    }

    /**
     * display in html format
     *
     * @return String
     */
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

    /**
     * For support writing to CSV -> string
     *
     * @return String
     */
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

    /**
     * For support reading CSV -> array list
     *
     * @param input
     */
    public void formInput(String input) {
        this.clear();
        if (!input.contains("%")) {
            return;
        }
        this.data = (T[]) input.split("%");
        this.index = data.length;
    }

    /**
     *
     * @param field1
     * @param field2
     * @param _class
     * @return ArrayList of concat field
     */
    public ArrList<String> concateField(String field1, String field2, Class<?> _class) {
        try {
            return notsecure_concateField(field1, field2, _class, "%");
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * 
     * @param field1
     * @param field2
     * @param _class
     * @param seperator
     * @return ArrayList of concat field with seperator
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private ArrList<String> notsecure_concateField(String field1, String field2, Class<?> _class, String seperator) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field f1 = _class.getField(field1);
        Field f2 = _class.getField(field2);
        Method m1 = null;
        Method m2 = null;
        String s1, s2;
        StringBuilder sb = new StringBuilder();
        ArrList<String> ar = new ArrList<String>();
        if (!f1.isAccessible()) {
            m1 = _class.getDeclaredMethod(main.Functions.fieldToGetter(field1));
        }
        if (!f2.isAccessible()) {
            m2 = _class.getDeclaredMethod(main.Functions.fieldToGetter(field2));
        }
        for (int i = 0; i < this.index; i++) {
            sb.setLength(0);
            s1 = m1 != null ? m1.invoke(data[i]).toString() : f1.get(data[i]).toString();
            s2 = m2 != null ? m2.invoke(data[i]).toString() : f2.get(data[i]).toString();
            ar.add(sb.append(s1).append(seperator).append(s2).toString());
        }
        return ar;
    }

    /**
     *
     * @param field Field name
     * @param class_name Class name
     * @return The field list in side the class
     */
    public ArrList<String> getField(String field, String class_name) {
        try {
            Class<?> _class = Class.forName(class_name);
            Field f = _class.getField(field);
            return this.notsecure_getField(null, f, _class);
        } catch (NoSuchFieldException x) {
            try {
                Class<?> _class = Class.forName(class_name);
                Method f = _class.getMethod(main.Functions.fieldToGetter(field));
                return this.notsecure_getField(f, null, _class);
            } catch (ClassNotFoundException | SecurityException | NoSuchMethodException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param f Field type
     * @param _class Class type
     * @return The field list in side the class
     */
    public ArrList<String> getField(Field f, Class<?> _class) {
        try {
            return this.notsecure_getField(null, f, _class);
        } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ArrList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * 
     * @param m
     * @param f
     * @param _class
     * @return
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private ArrList<String> notsecure_getField(Method m, Field f, Class<?> _class) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrList<String> r = new ArrList<>();

        for (int i = 0; i < index; i++) {
            r.add((m != null ? m.invoke(data[i]).toString() : f.get(data[i]).toString()));
        }

        return r;
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
}
