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

    InterDictionary refences;

    public MapConverter() {
        refences = new XTreeDictionary();
    }

    public MapConverter(XTreeDictionary dic) {
        refences = dic;
    }

    public MapConverter(XHashedDictionary dic) {
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
    public boolean containsKey(Object o) {
        return refences.contains((K) o);
    }

    @Override
    public boolean containsValue(Object o) {
        return refences.getValue(o) != null;
    }

    @Override
    public V get(Object o) {
        return (V) refences.getValue((K) o);
    }

    @Override
    public Object put(Object k, Object v) {
        return refences.add(k, v);
    }

    @Override
    public V remove(Object o) {
        return (V) refences.remove((K) o);
    }

    @Override
    public void putAll(Map map) {
        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            Object k = it.next();
            refences.add(k, map.get(k));
        }
    }

    @Override
    public void clear() {
        refences.clear();
    }

    //<editor-fold defaultstate="collapsed" desc="useless Functions">
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection values() {
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
        public Iterator iterator() {
            return refences.newEntryIterator();
        }

        @Override
        public boolean contains(Object o) {
            return refences.contains(o);
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
