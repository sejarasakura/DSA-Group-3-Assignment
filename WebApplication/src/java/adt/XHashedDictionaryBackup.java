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
public class XHashedDictionaryBackup<K, V> implements InterfaceHashDictionary<K, V>, Cloneable, java.io.Serializable {

    private TableEntry<K, V>[] hashTable;
    private int numberOfEntries;

    /**
     * Default size of the array
     */
    private static final int DEFAULT_SIZE = 16;

    public XHashedDictionaryBackup() {
        this(DEFAULT_SIZE);
    }

    public XHashedDictionaryBackup(int tableSize) {
        hashTable = new TableEntry[tableSize];
        numberOfEntries = 0;
    }

    public XHashedDictionaryBackup(Map data) {
        this(data.size());
        data.keySet().forEach((_item) -> {
            this.add((K)_item, (V)data.get(_item));
        });
    }

    @Override
    public V add(K key, V value) {

        V oldValue; // value to return

        if (isFull()) {
            rehash();
        }

        int index = getHashIndex(key);

        if ((hashTable[index] == null) || hashTable[index].isRemoved()) { // key not found, so insert new entry
            hashTable[index] = new TableEntry<K, V>(key, value);
            numberOfEntries++;
            oldValue = null;
        } else {
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);
        } // end if

        return oldValue;

    }

    @Override
    public V remove(K key) {
        V removedValue;

        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            removedValue = hashTable[index].getValue();
            hashTable[index].setToRemoved();
            numberOfEntries--;
            return removedValue;
        }
        return null;
    }

    @Override
    public V getValue(K key) {

        V result;

        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            result = hashTable[index].getValue();
            return result;
        }

        return null;

    }
    
    public Map getMap(){
        Map map = new HashMap();
        for (TableEntry<K, V> hashTable1 : hashTable) {
            if ((hashTable1 != null) && hashTable1.isIn()) {
                map.put(hashTable1.getKey(), hashTable1.getValue());
            }
        }
        return map;
    }

    private int locate(int index, K key) {
        if (hashTable[index] == null || !key.equals(hashTable[index].getKey())) {
            return -1;
        } else {
            return index;
        }
    }

    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        for (int index = 0; index < hashTable.length; index++) {
            hashTable[index] = null;
        }

        numberOfEntries = 0;
    }

    private int getHashIndex(K key) {

        int hashIndex = key.hashCode() % hashTable.length;

        if (hashIndex < 0) {
            hashIndex = hashIndex + hashTable.length;
        } // end if

        return hashIndex;
    }

    /**
     * Task: Increases the size of the hash table to twice its old size.
     */
    private void rehash() {
        TableEntry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = 2 * oldSize;

        hashTable = new TableEntry[newSize];
        numberOfEntries = 0;

        for (int index = 0; index < oldSize; index++) {
            if ((oldTable[index] != null) && oldTable[index].isIn()) {
                add(oldTable[index].getKey(), oldTable[index].getValue());
            }
        }

    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < hashTable.length; index++) {
            outputStr += String.format("%4d. ", index);
            if (hashTable[index] == null) {
                outputStr += "null " + "\n";
            } else if (hashTable[index].isRemoved()) {
                outputStr += "notIn " + "\n";
            } else {
                outputStr += hashTable[index].getKey() + " " + hashTable[index].getValue() + "\n";
            }
        } // end for
        outputStr += "\n";
        return outputStr;
    }
}
