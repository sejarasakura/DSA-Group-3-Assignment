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
public class HT_DoubleHashingHashTable<K, V> implements InterDictionary<K, V> {

    public static void main(String[] args) {
        int hash_2_prime = 7;
        HT_DoubleHashingHashTable dic = new HT_DoubleHashingHashTable(11, 1);
        dic.setYeMXpC_1(1, 0);
        dic.setYeMXpC_2(1, 0, hash_2_prime);
        dic.header();
        dic.add(65, null);
        dic.add(42, null);
        dic.add(85, null);
        dic.add(67, null);
        dic.add(23, null);
        dic.add(51, null);
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
    public HT_DoubleHashingHashTable() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructor
     *
     * @param initCapacity
     * @param load
     */
    public HT_DoubleHashingHashTable(int initCapacity, float load) {

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

    public void setYeMXpC_1(int m, int c) {
        this.m = m;
        this.c = c;
    }
    private int m2 = 1;
    private int c2 = 0;
    private int prime = 0;

    public void setYeMXpC_2(int m2, int c2, int prime) {
        this.m2 = m2;
        this.c2 = c2;
        this.prime = prime;
    }

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    /**
     * Add new key and v
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V add(K key, V value) {

        // if hash table is full
        if (isFull()) {
            return null;
        }

        int hash = key.hashCode();
        int y1 = (m * indexFor(hash, table.length)) + c;
        int y2 = this.prime - ((m2 * indexFor(hash, this.prime)) + c2);
        for (TableEntry<K, V> e = table[y1]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        // if collision occurs
        if (table[y1] != null) {
            // get index2 from second hash
            int i = 1;
            while (true) {
                // get newIndex
                int newIndex = (y1 + i * y2) % this.table.length;

                // if no collision occurs, store
                // the key
                if (table[newIndex] == null) {
                    addEntry(y2, key, value, newIndex);
                    break;
                }
                i++;
            }
        } else {
            addEntry(hash, key, value, y1);
        }
        hashModCount++;
        sb1.append(String.format("│%-20s│%-20s│\n", (" h1(i) = " + key + " % " + table.length), (" i = " + y1)));
        sb2.append(String.format("│%-20s│%-20s│\n", (" h2(i) = " + prime + "-(" + key + " % " + prime) + ")", ("i = " + y2)));
        return null;
    }

    public void header() {
        sb1.append("Please set the [y = m(x) + c] value to manipulate the hash function!!\n\n");
        sb1.append(xenum.OutputColor.TEXT_RED);
        sb1.append(String.format("current using hash 1 fnction is: [hash = {%d}(hash %% {%d}) + {%d}]\n", m, table.length, c));
        sb1.append(xenum.OutputColor.TEXT_RESET);
        header(sb1);
        sb2.append(xenum.OutputColor.TEXT_RED);
        sb2.append(String.format("current using hash 2 fnction is: {%d} - [hash = {%d}(hash %% {%d}) + {%d}]\n", prime, m, prime, c));
        sb2.append(xenum.OutputColor.TEXT_RESET);
        header(sb2);
    }

    public void footer() {
        footer(sb1);
        footer(sb2);
    }

    public void header(StringBuilder sb) {
        sb.append(String.format("┌%-20s┬%-20s┐\n", "────────────────────", "────────────────────"));
        sb.append(String.format("│%-20s│%-20s│\n", " Hashing Algorithm", " Return Index"));
        sb.append(String.format("├%-20s┼%-20s┤\n", "────────────────────", "────────────────────"));
    }

    public void footer(StringBuilder sb) {
        sb.append(String.format("└%-20s┴%-20s┘\n\n", "────────────────────", "────────────────────"));
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
        outputStr.append(sb1);
        outputStr.append(sb2);
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
        return (size == table.length);
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
