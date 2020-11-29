/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterDictionary;
import adt.node.Entry;
import adt.node.TableEntry;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author Lim sai keat
 * @param <K> Key
 * @param <V> Value
 */
public class XHashedDictionary<K, V> implements InterDictionary<K, V>, Cloneable, java.io.Serializable {

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
    public XHashedDictionary() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }
    /**
     * Constructor
     * @param initCapacity
     * @param load 
     */
    public XHashedDictionary(int initCapacity, float load) {

        if (initCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: "
                    + initCapacity);
        }
        if (initCapacity > MAXIMUM_CAPACITY) {
            initCapacity = MAXIMUM_CAPACITY;
        }
        if (load <= 0 || Float.isNaN(load)) {
            throw new IllegalArgumentException("Illegal load factor: "
                    + load);
        }

        int capacity = 1;
        while (capacity < initCapacity) {
            capacity <<= 1;
        }

        this.loadFactor = load;
        threshold = (int) (capacity * load);
        table = new TableEntry[capacity];
    }
    /**
     * Constructor
     * @param data 
     */
    public XHashedDictionary(Map<K, V> data) {
        this(Math.max((int) (data.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_SIZE), DEFAULT_LOAD_FACTOR);
        TableEntry<K, V> current = null;
        data.entrySet().forEach((x) -> {
            this.add(x.getKey(), x.getValue());
        });
    }
    /**
     * 
     * @param x
     * @param n
     * @return 
     */
    private TableEntry<K, V> addMap(Map.Entry<K, V> x, TableEntry<K, V> n) {
        if (x.getKey() == null) {
            return null;
        }
        int hash = hash(x.getKey().hashCode());
        int i = indexFor(hash, table.length);
        return new TableEntry<K, V>(hash, x.getKey(), x.getValue(), n);
    }
    /**
     * Constructor
     * @param data 
     */
    public XHashedDictionary(XHashedDictionary<K, V> data) {
        this(Math.max((int) (data.size / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_SIZE), DEFAULT_LOAD_FACTOR);
        putAllForCreate(data);
    }
    /**
     * Constructor
     * @param data 
     */
    public XHashedDictionary(Object data) {
        this((Map) data);
    }
    /**
     * Add new key and v
     * @param key
     * @param value
     * @return 
     */
    @Override
    public V add(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        }
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        for (TableEntry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        hashModCount++;
        addEntry(hash, key, value, i);
        return null;

    }
    /**
     * remove value with key
     * @param key
     * @return 
     */
    @Override
    public V remove(K key) {

        TableEntry<K, V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);

    }
    /**
     * Get value with key
     * @param key
     * @return 
     */
    @Override
    public V getValue(K key) {
        if (key == null) {
            return getForNullKey();
        }
        int hash = hash(key.hashCode());
        for (TableEntry<K, V> e = table[indexFor(hash, table.length)];
                e != null;
                e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e.value;
            }
        }
        return null;
    }
    /**
     * Convert map to XOrderedDictionary
     * @return 
     */
    public MapConverter getMap() {
        MapConverter x = new MapConverter();
        for (TableEntry<K, V> table1 : table) {
            if (table1 != null) {
                x.put(table1.key, table1.value);
            }
        }
        return x;
    }
    /**
     * 
     * @param index
     * @param key
     * @return 
     */
    private int locate(int index, K key) {
        if (table[index] == null || !key.equals(table[index].getKey())) {
            return -1;
        } else {
            return index;
        }
    }
    /**
     * Add all the elements to XOrderedDictionary
     * @param m 
     */
    public final void addAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0) {
            return;
        }

        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int) (numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY) {
                targetCapacity = MAXIMUM_CAPACITY;
            }
            int newCapacity = table.length;
            while (newCapacity < targetCapacity) {
                newCapacity <<= 1;
            }
            if (newCapacity > table.length) {
                resize(newCapacity);
            }
        }

        m.entrySet().forEach((e) -> {
            add(e.getKey(), e.getValue());
        });
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public void clear() {
        hashModCount++;
        TableEntry[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            tab[i] = null;
        }
        size = 0;

    }

    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        TableEntry[] tab = table;
        for (int index = 0; index < table.length; index++) {
            if (tab[index] != null) {
                for (TableEntry e = tab[index]; e != null; e = e.next) {
                    outputStr.append("index: ").append(index).append(" value: ")
                            .append(e.getKey()).append(' ')
                            .append(e.getValue());
                    if (e.next != null) {
                        outputStr.append("\n\t >> ");
                    }
                }
                outputStr.append("\n");
            }
        } // end for
        outputStr.append("\n");
        return outputStr.toString();
    }

    @Override
    public boolean contains(Object value) {
        if (value == null) {
            return containsNullValue();
        }

        return this.getValue((K) value) != null;
    }

    @Override
    public ArrList<K> getKeyList() {
        return new ArrList<K>(this.newKeyIterator());
    }

    @Override
    public ArrList<V> getValueList() {
        return new ArrList<V>(this.newValueIterator());
    }

    @Override
    public ArrList<? extends Entry<K, V>> getEntryList() {
        return new ArrList<TableEntry<K, V>>(this.newEntryIterator());
    }

    /**
     * Removes and returns the entry associated with the specified key in the
     * HashMap. Returns null if the HashMap contains no mapping for this key.
     */
    final TableEntry<K, V> removeEntryForKey(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);
        TableEntry<K, V> prev = table[i];
        TableEntry<K, V> e = prev;

        while (e != null) {
            TableEntry<K, V> next = e.next;
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                hashModCount++;
                size--;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    /**
     * Subclass overrides this to alter the behavior of put method.
     */
    private void addEntry(int hash, K key, V value, int bucketIndex) {
        TableEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new TableEntry<>(hash, key, value, e);
        if (size++ >= threshold) {
            resize(2 * table.length);
        }
    }
    /**
     * 
     * @param newCapacity 
     */
    private void resize(int newCapacity) {
        TableEntry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        TableEntry[] newTable = new TableEntry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    /**
     * Offloaded version of put for null keys
     */
    private V putForNullKey(V value) {
        for (TableEntry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        hashModCount++;
        addEntry(0, null, value, 0);
        return null;
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    private void transfer(TableEntry[] newTable) {
        TableEntry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            TableEntry<K, V> e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    TableEntry<K, V> next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    /**
     * Applies a supplemental hash function to a given hashCode
     */
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hash code h.
     */
    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private V getForNullKey() {
        for (TableEntry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                return e.value;
            }
        }
        return null;
    }
    /**
     * Get table entity
     * @param key
     * @return 
     */
    final TableEntry<K, V> getEntry(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        for (TableEntry<K, V> e = table[indexFor(hash, table.length)];
                e != null;
                e = e.next) {
            Object k;
            if (e.hash == hash
                    && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e;
            }
        }
        return null;
    }

    /**
     * Special-case code for containsValue with null argument
     */
    private boolean containsNullValue() {
        TableEntry[] tab = table;
        for (TableEntry tab1 : tab) {
            for (TableEntry e = tab1; e != null; e = e.next) {
                if (e.value == null) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Put dictionary to create table
     * @param key
     * @param value 
     */
    private void putForCreate(K key, V value) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);

        for (TableEntry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash
                    && ((k = e.key) == key || (key != null && key.equals(k)))) {
                e.value = value;
                return;
            }
        }

        createEntry(hash, key, value, i);
    }
    /**
     * Put all dictionary to create table
     * @param m 
     */
    private void putAllForCreate(XHashedDictionary<? extends K, ? extends V> m) {
        TableEntry<? extends K, ? extends V> e = null;
        Iterator<? extends TableEntry<? extends K, ? extends V>> es
                = m.newEntryIterator();
        while (es.hasNext()) {
            e = es.next();
            putForCreate(e.getKey(), e.getValue());
        }
    }
    /**
     * Create table entry
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex 
     */
    private void createEntry(int hash, K key, V value, int bucketIndex) {
        TableEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new TableEntry<>(hash, key, value, e);
        size++;
    }

    @Override
    public Iterator<K> newKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> newValueIterator() {
        return new ValueIterator();
    }

    @Override
    public Iterator<TableEntry<K, V>> newEntryIterator() {
        return new EntryIterator();
    }
    /**
     * next entry with value iterator
     */
    private final class ValueIterator extends HashedIterator<V> {

        @Override
        public V next() {
            return nextEntry().value;
        }
    }
    /**
     * Key iterator
     */
    private final class KeyIterator extends HashedIterator<K> {

        @Override
        public K next() {
            return nextEntry().getKey();
        }
    }
    /**
     * Table entry iterator
     */
    private final class EntryIterator extends HashedIterator<TableEntry<K, V>> {

        @Override
        public TableEntry<K, V> next() {
            return nextEntry();
        }
    }
    /**
     * XHashedDictionary iterator
     * @param <E> 
     */
    private abstract class HashedIterator<E> implements Iterator<E> {

        TableEntry<K, V> next;        // next entry to return
        int expectedModCount;   // For fast-fail
        int index;              // current slot
        TableEntry<K, V> current;     // current entry

        @SuppressWarnings("empty-statement")
        HashedIterator() {
            expectedModCount = hashModCount;
            if (size > 0) { // advance to first entry
                TableEntry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }
        /**
         * Check hasNext value or not
         * @return 
         */
        @Override
        public final boolean hasNext() {
            return next != null;
        }
        /**
         * Get next table entry value
         * @return 
         */
        @SuppressWarnings("empty-statement")
        final TableEntry<K, V> nextEntry() {
            if (hashModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            TableEntry<K, V> e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }

            if ((next = e.next) == null) {
                TableEntry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
            current = e;
            return e;
        }
        /**
         * Remove table entry
         */
        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException();
            }
            if (hashModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Object k = current.key;
            current = null;
            XHashedDictionary.this.removeEntryForKey(k);
            expectedModCount = hashModCount;
        }

    }
}
