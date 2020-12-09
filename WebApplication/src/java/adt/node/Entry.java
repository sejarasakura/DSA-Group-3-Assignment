/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.node;

/**
 *
 * @author Lim Sai Keat
 * @param <K>
 * @param <V>
 */
public interface Entry<K, V> {

    /**
     * Get key
     *
     * @return
     */
    K getKey();

    /**
     * get Value
     *
     * @return
     */
    V getValue();

    /**
     * get value
     *
     * @param value
     * @return
     */
    V setValue(V value);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
