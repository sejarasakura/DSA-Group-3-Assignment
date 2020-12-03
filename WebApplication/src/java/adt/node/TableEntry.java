/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.node;

import adt.XHashedDictionary;
import java.util.Map;

/**
 *
 * @author ITSUKA KOTORI
 * @param <K>
 * @param <V>
 */
public class TableEntry<K, V> implements Entry<K, V>, Map.Entry<K, V> {

    /**
     * Table key
     */
    public final K key;

    /**
     * Table value
     */
    public V value;

    /**
     * next pointer
     */
    public TableEntry<K, V> next;

    /**
     * hash code.
     */
    public final int hash;

    /**
     * Creates new entry.
     *
     * @param h
     * @param k
     * @param v
     * @param n
     */
    public TableEntry(int h, K k, V v, TableEntry<K, V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    @Override
    public final K getKey() {
        return key;
    }

    @Override
    public final V getValue() {
        return value;
    }

    @Override
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof TableEntry)) {
            return false;
        }
        TableEntry e = (TableEntry) o;
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return (key == null ? 0 : key.hashCode())
                ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public final String toString() {
        return getKey() + "=" + getValue();
    }

    /**
     * This method is invoked whenever the value in an entry is overwritten by
     * an invocation of put(k,v) for a key k that's already in the
     * XHashedDictionary.
     *
     * @param m
     */
    public void recordAccess(XHashedDictionary<K, V> m) {
    }

    /**
     * This method is invoked whenever the entry is removed from the table.
     *
     * @param m
     */
    public void recordRemoval(XHashedDictionary<K, V> m) {
    }
}
