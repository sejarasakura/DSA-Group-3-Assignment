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
import entity.help.Range;
import xenum.AbstractEnum;

/**
 *
 * @author Lim sai keat
 * @param <T>
 */
public class XArrayList<T> implements InterAdvanceList<T>, Cloneable, java.io.Serializable {

    /**
     * Serializable version ids
     */
    private static final long serialVersionUID = -4481180579250750351L;

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
     * Record is is sorted or not
     */
    boolean sorted = false;

    /**
     * Record is is sorted or not
     */
    String sort_by = "";

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
    @SuppressWarnings("unchecked")
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
        sorted = false;
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
        sorted = false;
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
        sorted = false;
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
        sorted = false;
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
    @SuppressWarnings("unchecked")
    public T remove(int _index) {
        CheckRange(_index);
        Object x = data[_index];
        for (int i = _index; i < index - 1; i++) {
            data[i] = data[i + 1];
        }
        index--;
        sorted = false;
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
        sorted = false;
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
    @SuppressWarnings("unchecked")
    public T[] clear() {
        T[] x = data.clone();
        data = (T[]) new Object[INITIAL_CAPACITY];
        index = 0;
        sorted = false;
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
        return private_toString("\n", ", ");
    }

    /**
     * display in html format
     *
     * @return String
     */
    @Override
    public String toHtml() {
        return private_toString("<br/>", ", ");
    }

    private String private_toString(String line_break, String prefix) {
        StringBuilder sb = new StringBuilder();
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
    @SuppressWarnings("all")
    public boolean removeSameElement() {
        if (index <= 1) {
            return true;
        }
        XArraySortList<?> a = new XArraySortList(this);
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
        sorted = true;
        this.sort_by = "compareTo";
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
            Method method = _class.getDeclaredMethod(method_n, _class);
            for (T d : data) {
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
    public boolean find_AbstractEntity(AbstractEntity<?> x) {

        for (int i = 0; i < index; i++) {
            if (((AbstractEntity<?>) data[i]).id_equals(x)) {
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
    @SuppressWarnings("unchecked")
    public void formInput(String input) {
        this.clear();
        if (!input.contains("%")) {
            this.add((T) input);
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
     * sort the element in the List according to the field name
     *
     * @param field
     * @param _class
     * @return success sort or not
     */
    @Override
    public boolean sort(String field, Class<?> _class) {
        try {
            Method f = _class.getMethod(Functions.fieldToGetter(field));
            boolean r = sort(f);
            XStack<T> s = new XStack<T>(this);
            int i = this.index;
            while (!s.isEmpty()) {
                i--;
                data[i] = (T) s.pop();
            }
            this.sorted = r;
            this.sort_by = field;
            return r;
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
            sorted = false;
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
            Method f = _class.getMethod(Functions.fieldToGetter(field));
            this.sorted = sort(f);
            this.sort_by = field;
            return this.sorted;
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
            this.sorted = false;
            return false;
        }
    }

    @Override
    public XArrayList<T> binarySearch(String field, Comparable<?> value, Class<?> _class) {
        try {
            return this.notsecure_binarySearch(field, value, _class, false);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new XArrayList<T>();
    }

    @Override
    public T binarySearchOnce(String field, Comparable<?> value, Class<?> _class) {
        try {
            return this.notsecure_binarySearch_once(field, value, _class, false);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public XArrayList<T> binarySearchAndSort(String field, Comparable<?> value, Class<?> _class) {
        try {
            this.sort_by = field;
            return this.notsecure_binarySearch(field, value, _class, true);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(XArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sorted = false;
        return new XArrayList<T>();
    }

    public boolean isSorted() {
        return sorted;
    }

    public String sortedBy() {
        if (sorted) {
            return this.sort_by;
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

    private void no_rec_quicksort(T arr[], int l, int h, Method sort_method, Method getter) {

        if (arr == null) {
            return;
        }
        if (arr.length <= 1) {
            return;
        }
        int[] stack = new int[h - l + 1];

        int top = -1;

        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];

            int p = partition_m(arr, l, h, sort_method, getter);

            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    /**
     * @param getter reflection methods to call getter dynamic
     * @param sort_method to get the default sorting methods Object
     * compareTo(Object) | null for int
     */
    @SuppressWarnings("unchecked")
    private int partition_m(T[] arr, int low, int high, Method sort_method, Method getter) {
        T pivot = arr[high];
        int i = (low - 1); // index of smaller element
        int result;
        for (int j = low; j < high; j++) {
            try {
                // If current element is smaller than the pivot
                result = this.compare(arr[j], (Comparable<T>) getter.invoke(pivot), sort_method, getter);
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

    private XArrayList<T> notsecure_binarySearch(String field, Comparable<?> value, Class<?> _class, boolean sort_for_me) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        XArrayList<T> r;
        Method getter = _class.getMethod(Functions.fieldToGetter(field));
        Method sort_m = getClassType(getter.getReturnType());
        Integer delta;
        if (sort_m == null ? true : sort_m.getReturnType() == int.class) {
            Range<Integer> range = binary_search_range(data, value, sort_m, getter, sort_for_me);
            delta = range.getHigher() - range.getLower();
            System.out.print(delta + ",");
            System.out.print(range.getHigher() + ",");
            System.out.println(range.getLower() + ",");
            if (range.getLower() >= 0) {
                r = new XArrayList<T>(delta + 1);
                System.arraycopy(this.data, range.getLower(), r.data, 0, delta + 1);
                r.index = delta + 1;
                return r;
            }
        }
        return new XArrayList<T>();
    }

    private T notsecure_binarySearch_once(String field, Comparable<?> value, Class<?> _class, boolean sort_for_me) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method getter = _class.getMethod(Functions.fieldToGetter(field));
        Method sort_m = getClassType(getter.getReturnType());
        if (sort_m == null ? true : sort_m.getReturnType() == int.class) {
            Integer x = this.simple_BinarySearch(data, value, sort_m, getter, sort_for_me);
            if (x > 0) {
                return data[x];
            }
        }
        return null;
    }

    private Method getClassType(Class<?> cls) throws NoSuchMethodException {
        if (cls.getName().contains("xenum.")) {
            return cls.getMethod("compare", AbstractEnum.class);
        } else if (cls != int.class) {
            return cls.getMethod("compareTo", cls);
        } else {
            return null;
        }
    }

    private Range<Integer> binary_search_range(T arr[], Comparable<?> value, Method sort_method, Method getter, boolean sort_for_me) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int r = this.simple_BinarySearch(arr, value, sort_method, getter, sort_for_me);
        int first = r;
        int last = r;
        if (r >= 0) {
            while (first > 0 && compare(data[first - 1], value, sort_method, getter) == 0) {
                first--;
            }
            while (last < data.length - 1 && compare(data[last + 1], value, sort_method, getter) == 0) {
                last++;
            }
        }
        return new Range<Integer>(first, last);
    }

    private int compare(T this_value, Comparable<?> value, Method sort_method, Method getter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (sort_method != null) {
            return (int) sort_method.invoke(getter.invoke(this_value), value);
        } else {
            return Integer.compare((int) getter.invoke(this_value), (Integer) value);
        }
    }

    // -1 null value, -2 not sorted, -3 not found
    private int simple_BinarySearch(T arr[], Comparable<?> value, Method sort_method, Method getter, boolean sort_for_me)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // block using empty object
        if ((value == null || arr == null ? true : arr.length == 0)) {
            return -1;
        }
        // only one element
        if (arr.length == 1 || index == 1) {
            return 0;
        }
        if (!sorted ? !sort_for_me : false) { // not sort
            return -2;
        }
        if (sort_for_me) {  // sort for me
            this.no_rec_quicksort(arr, 0, index - 1, sort_method, getter);
        }
        int l = 0, r = arr.length - 1, result;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // invoke compareto result
            result = this.compare(arr[m], value, sort_method, getter);
            // Check if x is present at mid
            if (result == 0) {
                return m;
            }
            // If x greater, ignore left half comparetTo():1
            if (result < 0) {
                l = m + 1;
            } // If x is smaller, ignore right half comparetTo():-1
            else {
                r = m - 1;
            }
        }
        // if we reach here, then element was
        // not present
        return -3;
    }

    private boolean sort(Method methods) {
        Class<?> cls = methods.getReturnType();
        try {
            if (cls.getName().contains("xenum.")) {
                Method m = cls.getMethod("compare", AbstractEnum.class);
                if (m.getReturnType() != int.class) {
                    return false;
                }
                this.no_rec_quicksort(data, 0, this.index - 1, m, methods);
            } else if (cls != int.class) {
                Method m = cls.getMethod("compareTo", cls);
                if (m.getReturnType() != int.class) {
                    return false;
                }
                this.no_rec_quicksort(data, 0, this.index - 1, m, methods);
            } else {
                this.no_rec_quicksort(data, 0, this.index - 1, null, methods);
            }
            sorted = true;
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(XArraySortList.class.getName()).log(Level.SEVERE, null, ex);
        }
        sorted = false;
        return false;
    }
}
