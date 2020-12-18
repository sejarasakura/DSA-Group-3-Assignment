/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import adt.MapConverter;
import adt.XArrayList;
import adt.interfaces.InterDictionary;
import adt.interfaces.InterList;
import adt.node.Entry;
import adt.node.TableEntry;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *
 * @author Lai Hong Wah
 * @param <K> Key
 * @param <V> Value
 */
public class HT_SeperateChainingHashTable<K, V> implements InterDictionary<K, V> {

    public static void main(String[] args) {
        int c = 0;
        int m = 1;
        int t_size = 7;
        HT_SeperateChainingHashTable dic = new HT_SeperateChainingHashTable(t_size, 1);
        dic.setYeMXpC(m, c);
        dic.header();
        dic.add(23, null);
        dic.add(89, null);
        dic.add(58, null);
        dic.add(84, null);
        dic.add(63, null);
        dic.add(72, null);
        dic.footer();
        System.out.println(dic);
    }
    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
    private TableEntry<K, V>[] table;

    /**
     * size
     */
    private int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    int threshold;

    /**
     * The load factor for the hash table.
     */
    final float loadFactor;

    /**
     * Default size of the array
     */
    private static final int DEFAULT_SIZE = 16;

    /**
     * The maximum capacity, used if a higher value specified
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Load factor default value
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The number of times this HashMap has been structurally
     */
    transient int hashModCount;

    /**
     * Constructor
     */
    public HT_SeperateChainingHashTable() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructor
     *
     * @param initCapacity
     * @param load
     */
    public HT_SeperateChainingHashTable(int initCapacity, float load) {

        if (initCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: "
                    + initCapacity);
        }
        if (load <= 0 || Float.isNaN(load)) {
            throw new IllegalArgumentException("Illegal load factor: "
                    + load);
        }

        int capacity = initCapacity;

        this.loadFactor = load;
        threshold = (int) (capacity * load);
        table = new TableEntry[capacity];
    }

    private int m = 1;
    private int c = 0;

    public void setYeMXpC(int m, int c) {
        this.m = m;
        this.c = c;
    }

    /**
     * Add new key and v
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V add(K key, V value) {
        int hash = key.hashCode();
        int y = (m * indexFor(hash, table.length)) + c;
        for (TableEntry<K, V> e = table[y]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        hashModCount++;
        addEntry(hash, key, value, y);
        System.out.printf("│%-20s│%-20s│\n", (" h(i) = " + key + " % " + table.length), (" i = " + y));
        return null;
    }

    public void header() {
        System.out.println("Please set the [y = m(x) + c] value to manipulate the hash function");
        System.out.printf("current using hash fnction is: [hash = {%d}(hash %% {%d}) + {%d}]\n", m, table.length, c);
        System.out.printf("┌%-20s┬%-20s┐\n", "────────────────────", "────────────────────");
        System.out.printf("│%-20s│%-20s│\n", " Hashing Algorithm", " Return Index");
        System.out.printf("├%-20s┼%-20s┤\n", "────────────────────", "────────────────────");
    }

    public void footer() {
        System.out.printf("└%-20s┴%-20s┘\n\n", "────────────────────", "────────────────────");
    }

    /**
     * Change dictionary to string
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        TableEntry[] tab = table;
        outputStr.append("┌────────────────────────────────────────\n");
        outputStr.append("│Table size : ");
        outputStr.append(table.length);
        outputStr.append("\n");
        outputStr.append("├────────────────────────────────────────\n");
        for (int index = 0; index < table.length; index++) {
            outputStr.append("│index: ").append(String.format("%-3d", index)).append(" ");
            if (tab[index] != null) {
                Stack<K> stack = new Stack();
                for (TableEntry e = tab[index]; e != null; e = e.next) {
                    if (e != null) {
                        stack.push((K) e.key);
                    }
                }
                while (!stack.isEmpty()) {
                    outputStr.append(" ─> ");
                    outputStr.append(stack.pop());
                }
            }
            outputStr.append("\n");
        } // end for
        outputStr.append("└────────────────────────────────────────\n");
        return outputStr.toString();
    }

    private int indexFor(int hash, int length) {
        return hash % length;
    }

    private int hash(String courseCode, int length) {
        return courseCode.hashCode() % length;
    }

    private void addEntry(int hash, K key, V value, int bucketIndex) {
        TableEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new TableEntry<>(hash, key, value, e);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V getValue(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<K> newKeyIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<V> newValueIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<? extends Entry<K, V>> newEntryIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InterList<K> getKeyList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InterList<V> getValueList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InterList<? extends Entry<K, V>> getEntryList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
