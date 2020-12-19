/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.InterDictionary;
import java.util.*;

/**
 *
 * This class is to do some type convert only
 *
 * @author Lim sai keat
 * @param <K>
 * @param <V>
 */
public class MapConverter<K, V> implements Map<K, V>, Cloneable, java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1836890223069008680L;

    InterDictionary<K, V> refences;

    public MapConverter() {
        refences = new XTreeDictionary<K, V>();
    }

    public MapConverter(XTreeDictionary<K, V> dic) {
        refences = dic;
    }

    public MapConverter(XHashedDictionary<K, V> dic) {
        refences = dic;
    }

    @Override
    public int size() {
        return refences.getSize();
    }

    @Override
    public boolean isEmpty() {
        return refences.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object o) {
        return refences.contains((K) o);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object o) {
        return refences.getValue((K) o) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object o) {
        return (V) refences.getValue((K) o);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object put(Object k, Object v) {
        return refences.add((K)k, (V)v);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        return (V) refences.remove((K) o);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void putAll(Map<? extends K,? extends V> map) {
        for (Iterator<? extends K> it = map.keySet().iterator(); it.hasNext();) {
            Object k = it.next();
            refences.add((K)k, map.get(k));
        }
    }

    @Override
    public void clear() {
        refences.clear();
    }

    //<editor-fold defaultstate="collapsed" desc="useless Functions">
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet0();
    }

    private Set<Map.Entry<K, V>> entrySet0() {
        return new EntrySet();
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        @SuppressWarnings("all")
        public Iterator iterator() {
            return refences.newEntryIterator();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
            return refences.contains((K)o);
        }

        @Override
        public boolean remove(Object o) {
            return MapConverter.this.remove(o) != null;
        }

        @Override
        public int size() {
            return MapConverter.this.size();
        }

        @Override
        public void clear() {
            MapConverter.this.clear();
        }
    }

    //</editor-fold>
}
