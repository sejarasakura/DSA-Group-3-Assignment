/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterAdvanceList;
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
import adt.interfaces.InterList;

/**
 *
 * @author Lim sai keat
 * @param <T>
 */
public class XArrayList<T> implements InterAdvanceList<T>, Cloneable, java.io.Serializable {

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * =========================== Field declaration ===========================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
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
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================== Constructor ==============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * Constructor
     */
    public XArrayList() {
        this(1);
    }

    /**
     * constructor with can set initial capacity
     *
     * @param initialCapacity
     */
    public XArrayList(int initialCapacity) {
        index = 0;
        data = (T[]) new Object[initialCapacity];
    }

    /**
     * constructor
     *
     * @param array
     */
    public XArrayList(T[] array) {
        index = array.length;
        data = array;
    }

    /**
     * constructor
     *
     * @param iterable to make good integrate to other Iterable object
     * eg.(Arrlist, Queue, Stack, ..)
     */
    public XArrayList(Iterable<T> iterable) {
        this();
        internal_addAll(iterable.iterator());
    }

    /**
     * constructor
     *
     * @param iterator to make to integrate to other Iterable.iterator object
     */
    public XArrayList(Iterator<T> iterator) {
        this();
        internal_addAll(iterator);
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ======================= InterList Override Method =======================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
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
     * @param c all the elements
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
     * @param c all the elements
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
     * get the value at index
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
    @Override
    public T[] clear() {
        T[] x = data.clone();
        data = (T[]) new Object[INITIAL_CAPACITY];
        index = 0;
        return x;
    }

    /**
     * check is empty or not
     *
     * @return Boolean
     */
    @Override
    public boolean isEmpty() {
        return index <= 0;
    }

    /**
     * to string method
     *
     * @return string
     */
    @Override
    public String toString() {
        return private_toString("\n");
    }

    /**
     * display in html format
     *
     * @return String
     */
    @Override
    public String toHtml() {
        return private_toString("<br/>");
    }

    private String private_toString(String line_break) {
        StringBuilder sb = new StringBuilder();
        String prefix = ", ";
        boolean check = data[0] == null ? false : data[0].toString().length() > 10;
        if (index <= 0) {
            return sb.toString();
        }
        if (check) {
            prefix += line_break;
        }
        for (int i = 0; i < (index - 1); i++) {
            sb.append(data[i]).append(prefix);
            if (i > 1 ? i % 10 == 0 : false) {
                sb.append(line_break);
            }
        }
        sb.append(data[index - 1]);
        if (check) {
            sb.append(line_break);
        }
        return sb.toString();
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ==================== InterAdvanceList Override Method ===================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    /**
     * search item
     *
     * @return ture that if list no same element
     */
    @Override
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
    @Override
    public InterList<Integer> search(T element) {
        return private_search(element, false);
    }

    public int search_once(T element) {
        InterList<Integer> r = private_search(element, true);
        if (r.isEmpty()) {
            return -1;
        }
        return r.get(0);
    }

    /**
     * search item
     *
     * @param element
     * @return Same item or null
     */
    @Override
    public T searchItem(T element) {
        InterList<Integer> r = private_search(element, true);
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
    @Override
    public InterList<T> searchByMethod(Method method, Object element) {
        XArrayList<T> result = new XArrayList<T>();
        try {
            for (T d : data) {
                if (method.invoke(d).equals(element)) {
                    result.add(d);
                }
            }
        } catch (SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public InterList<T> searchByMethod(String method_n, Object element, Class<?> _class) {
        XArrayList<T> result = new XArrayList<T>();
        try {
            for (T d : data) {
                Method method = _class.getDeclaredMethod(method_n, _class);
                if (method.invoke(d).equals(element)) {
                    result.add(d);
                }
            }
        } catch (SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    public InterList<T> searchByField(Field field, Object element) {
        XArrayList<T> result = new XArrayList<T>();
        try {
            for (T d : data) {
                if (field.get(d).equals(element)) {
                    result.add(d);
                }
            }
        } catch (SecurityException
                | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    public InterList<T> searchByField(String field, Object element, Class<?> _class) {
        XArrayList<T> result = new XArrayList<T>();
        try {
            Field f = null;
            try {
                f = _class.getDeclaredField(field);
            } catch (NoSuchFieldException | SecurityException noSuchFieldException) {
            }
            if (f == null ? false : f.isAccessible()) {
                return searchByField(f, element);
            } else {
                Method m = _class.getMethod(Functions.fieldToGetter(field));
                return searchByMethod(m, element);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * find a abstract entity that is id match purpose
     *
     * @param x
     * @return Boolean
     */
    @Override
    public boolean find_AbstractEntity(AbstractEntity x) {

        for (int i = 0; i < index; i++) {
            if (((AbstractEntity) data[i]).id_equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * For support writing to CSV to string
     *
     * @return String
     */
    @Override
    public String toInput() {
        StringBuilder sb = new StringBuilder();
        if (index <= 0) {
            return sb.toString();
        }
        for (int i = 0; i < (index - 1); i++) {
            sb.append(data[i]).append("%");
        }
        sb.append(data[index - 1]);
        return sb.toString();
    }

    /**
     * For support reading CSV to array list
     *
     * @param input
     */
    @Override
    public void formInput(String input) {
        this.clear();
        if (!input.contains("%")) {
            return;
        }
        this.data = (T[]) input.split("%");
        this.index = data.length;
    }

    /**
     * get the string list that had concat two field in class
     *
     * @param field1
     * @param field2
     * @param _class
     * @return ArrayList of concat field
     */
    @Override
    public InterList<String> concateField(String field1, String field2, Class<?> _class) {
        try {
            return notsecure_concateField(field1, field2, _class, "%");
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * get the string list that had concat two field in class with custom
     * separator
     *
     * @param field1
     * @param separator
     * @param field2
     * @param _class
     * @return
     */
    public InterList<String> concateField(String field1, String separator, String field2, Class<?> _class) {
        try {
            return notsecure_concateField(field1, field2, _class, separator);
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * removed throws class
     *
     * @param field Field name
     * @param class_name Class name
     * @return The field list in side the class
     */
    @Override
    public InterList<String> getField(String field, String class_name) {
        try {
            Class<?> _class = Class.forName(class_name);
            Field f = _class.getField(field);
            return this.notsecure_getField(null, f, _class);
        } catch (NoSuchFieldException x) {
            try {
                Class<?> _class = Class.forName(class_name);
                Method f = _class.getMethod(main.Functions.fieldToGetter(field));
                return this.notsecure_getField(f, null, _class);
            } catch (ClassNotFoundException | SecurityException | NoSuchMethodException
                    | IllegalArgumentException | NoSuchFieldException
                    | IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param f Field type
     * @param _class Class type
     * @return The field list in side the class
     */
    @Override
    public InterList<String> getField(Field f, Class<?> _class) {
        try {
            return this.notsecure_getField(null, f, _class);
        } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================= Iterator Class ============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
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

    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     * ============================= private Method ============================
     *
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
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

    public void addAll(Iterable<T> iterable) {
        internal_addAll(iterable.iterator());
    }

    public void addAll(Iterator<T> iterator) {
        internal_addAll(iterator);
    }

    private void internal_addAll(Iterator<T> iterator) {
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
    private InterList<String> notsecure_getField(Method m, Field f, Class<?> _class) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        XArrayList<String> r = new XArrayList<>();

        for (int i = 0; i < index; i++) {
            r.add((m != null ? m.invoke(data[i]).toString() : f.get(data[i]).toString()));
        }

        return r;
    }

    /**
     * get the string list that had concat two field in class not remove throws
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
    private InterList<String> notsecure_concateField(String field1, String field2, Class<?> _class, String seperator) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field f1 = _class.getField(field1);
        Field f2 = _class.getField(field2);
        Method m1 = null;
        Method m2 = null;
        String s1, s2;
        StringBuilder sb = new StringBuilder();
        XArrayList<String> ar = new XArrayList<String>();
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
     * @param element
     * @param once
     * @return
     */
    private InterList<Integer> private_search(T element, boolean once) {

        InterList<Integer> result = new XArrayList<Integer>();

        for (int i = 0; i < index - 1; i++) {
            if (data[i].equals(element)) {
                result.add(i);
                if (once) {
                    break;
                }
            }
        }

        return result;
    }
}
