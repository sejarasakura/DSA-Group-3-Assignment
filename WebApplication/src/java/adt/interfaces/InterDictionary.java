/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

import adt.node.Entry;
import java.util.Iterator;

/**
 *
 * @author Lim Sai Keat, Lai hong wah
 * @param <K>
 * @param <V>
 */
public interface InterDictionary<K, V> {

    /**
     * Task: Adds a new entry to the dictionary. If the given search key already
     * exists in the dictionary, replaces the corresponding value.
     *
     * @param key an object search key of the new entry
     * @param value an object associated with the search key
     * @return either null if the new entry was added to the dictionary or the
     * value that was associated with key if that value was replaced
     */
    public abstract V add(K key, V value);

    /**
     * Task: Removes a specific entry from the dictionary.
     *
     * @param key an object search key of the entry to be removed
     * @return either the value that was associated with the search key or null
     * if no such object exists
     */
    public abstract V remove(K key);

    /**
     * Task: Retrieves the value associated with a given search key.
     *
     * @param key an object search key of the entry to be retrieved
     * @return either the value that is associated with the search key or null
     * if no such object exists
     */
    public abstract V getValue(K key);

    /**
     * Task: Sees whether a specific entry is in the dictionary.
     *
     * @param key an object search key of the desired entry
     * @return true if key is associated with an entry in the dictionary
     */
    public abstract boolean contains(K key);

    /**
     * Task: Sees whether the dictionary is empty.
     *
     * @return true if the dictionary is empty
     */
    public abstract boolean isEmpty();

    /**
     * Task: Sees whether the dictionary is full.
     *
     * @return true if the dictionary is full
     */
    public abstract boolean isFull();

    /**
     * Task: Gets the size of the dictionary.
     *
     * @return the number of entries (key-value pairs) currently in the
     * dictionary
     */
    public abstract int getSize();

    /**
     * Task: Removes all entries from the dictionary.
     */
    public abstract void clear();

    /**
     * Task: return all key in general format
     *
     * @return keys
     */
    public abstract Iterator<K> newKeyIterator();

    /**
     * Task: return all value in general format
     *
     * @return values
     */
    public abstract Iterator<V> newValueIterator();

    /**
     * Task: return all entry in general format
     *
     * @return table entry
     */
    public abstract Iterator<? extends Entry<K, V>> newEntryIterator();

    /**
     * Task: return all key in general format
     *
     * @return keys
     */
    public abstract InterList<K> getKeyList();

    /**
     * Task: return all value in general format
     *
     * @return values
     */
    public abstract InterList<V> getValueList();

    /**
     * Task: return all entry in general format
     *
     * @return table entry
     */
    public abstract InterList<? extends Entry<K, V>> getEntryList();
}
