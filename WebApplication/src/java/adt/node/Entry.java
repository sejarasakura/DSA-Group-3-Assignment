/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.node;

/**
 *
 * @author ITSUKA KOTORI
 * @param <K>
 * @param <V>
 */
public interface Entry<K, V> {

    K getKey();

    V getValue();

    V setValue(V value);

    boolean equals(Object o);

    int hashCode();
}
