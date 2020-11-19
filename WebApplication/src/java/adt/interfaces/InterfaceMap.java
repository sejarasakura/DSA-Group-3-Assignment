/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

import java.util.*;

/**
 *
 * @author ITSUKA KOTORI
 * @param <K>
 * @param <V>
 */
public interface InterfaceMap<K, V> extends Map<K, V> {

    /**
     * @return the number of key-value mappings in this map
     */
    @Override
    int size();

    /**
     * @return ture if this map contains no key-value mappings
     */
    @Override
    boolean isEmpty();

    /**
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    boolean containsKey(Object key);

    /**
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     */
    @Override
    boolean containsValue(Object value);

    /**
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null}
     * if this map contains no mapping for the key
     */
    @Override
    V get(Object key);

    /**
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return
     */
    @Override
    V put(K key, V value);

    /**
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no
     * mapping for key.
     */
    @Override
    V remove(Object key);

    /**
     * @param m mappings to be stored in this map
     */
    void putAll(InterfaceMap<? extends K, ? extends V> m);

    /**
     * Removes all of the mappings from this map (optional operation). The map
     * will be empty after this call returns.
     */
    @Override
    void clear();

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    Set<K> keySet();

    /**
     * @return a collection view of the values contained in this map
     */
    @Override
    Collection<V> values();

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    Set<Map.Entry<K, V>> entrySet();

    /**
     * A map entry (key-value pair).
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        K getKey();

        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        V getValue();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
        V setValue(V value);

        /**
         * Compares the specified object with this entry for equality.
         *
         * @param o object to be compared for equality with this map entry
         * @return
         */
        @Override
        boolean equals(Object o);

        /**
         * Returns the hash code value for this map entry.
         *
         * @return the hash code value for this map entry
         */
        @Override
        int hashCode();
    }

    /**
     * Compares the specified object with this map for equality.
     *
     * @param o object to be compared for equality with this map
     * @return <tt>true</tt> if the specified object is equal to this map
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the hash code value for this map.
     *
     * @return
     */
    @Override
    int hashCode();
}
