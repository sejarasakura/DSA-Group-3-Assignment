/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterfaceHashDictionary;
import adt.node.TableEntry;
import java.util.*;

/**
 *
 * @author Lim sai keat
 * @param <K> Key
 * @param <V> Value
 */
public class XHashedDictionary<K, V> implements InterfaceHashDictionary<K, V>, Cloneable, java.io.Serializable {

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
    transient int modCount;

    public XHashedDictionary() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public XHashedDictionary(int initialCapacity, float loadFactor) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: "
                    + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: "
                    + loadFactor);
        }

        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        table = new TableEntry[capacity];
    }

    public XHashedDictionary(Map<K, V> data) {
        this(Math.max((int) (data.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_SIZE), DEFAULT_LOAD_FACTOR);
        putAllForCreate(data);
    }

    public XHashedDictionary(Object data) {
        this((Map) data);
    }

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

        modCount++;
        addEntry(hash, key, value, i);
        return null;

    }

    @Override
    public V remove(K key) {

        TableEntry<K, V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);

    }

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

    public MapConverter getMap() {
        MapConverter x = new MapConverter();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                x.put(table[i].key, table[i].value);
            }
        }
        return x;
    }

    private int locate(int index, K key) {
        if (table[index] == null || !key.equals(table[index].getKey())) {
            return -1;
        } else {
            return index;
        }
    }

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
    public boolean contains(K key) {

        return getEntry(key) != null;
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
        modCount++;
        TableEntry[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            tab[i] = null;
        }
        size = 0;

    }

    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        for (int index = 0; index < table.length; index++) {

            if (table[index] == null) {
            } else {
                outputStr.append(index).append(table[index].getKey()).append(" ")
                        .append(table[index].getValue()).append("\n");
            }
        } // end for
        outputStr.append("\n");
        return outputStr.toString();
    }

    public boolean containsValue(Object value) {
        if (value == null) {
            return containsNullValue();
        }

        TableEntry[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            for (TableEntry e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
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
                modCount++;
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
    void addEntry(int hash, K key, V value, int bucketIndex) {
        TableEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new TableEntry<>(hash, key, value, e);
        if (size++ >= threshold) {
            resize(2 * table.length);
        }
    }

    void resize(int newCapacity) {
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
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    void transfer(TableEntry[] newTable) {
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

    private void putForCreate(K key, V value) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);

        /**
         * Look for preexisting entry for key. This will never happen for clone
         * or deserialize. It will only happen for construction if the input Map
         * is a sorted map whose ordering is inconsistent w/ equals.
         */
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

    private void putAllForCreate(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            putForCreate(e.getKey(), e.getValue());
        }
    }

    void createEntry(int hash, K key, V value, int bucketIndex) {
        TableEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new TableEntry<>(hash, key, value, e);
        size++;
    }

    public Iterator<K> newKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> newValueIterator() {
        return new ValueIterator();
    }

    public Iterator<TableEntry<K, V>> newEntryIterator() {
        return new EntryIterator();
    }

    private final class ValueIterator extends HashedIterator<V> {

        @Override
        public V next() {
            return nextEntry().value;
        }
    }

    private final class KeyIterator extends HashedIterator<K> {

        @Override
        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class EntryIterator extends HashedIterator<TableEntry<K, V>> {

        @Override
        public TableEntry<K, V> next() {
            return nextEntry();
        }
    }

    private abstract class HashedIterator<E> implements Iterator<E> {

        TableEntry<K, V> next;        // next entry to return
        int expectedModCount;   // For fast-fail
        int index;              // current slot
        TableEntry<K, V> current;     // current entry

        @SuppressWarnings("empty-statement")
        HashedIterator() {
            expectedModCount = modCount;
            if (size > 0) { // advance to first entry
                TableEntry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }

        @Override
        public final boolean hasNext() {
            return next != null;
        }

        @SuppressWarnings("empty-statement")
        final TableEntry<K, V> nextEntry() {
            if (modCount != expectedModCount) {
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

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Object k = current.key;
            current = null;
            XHashedDictionary.this.removeEntryForKey(k);
            expectedModCount = modCount;
        }

    }
}
