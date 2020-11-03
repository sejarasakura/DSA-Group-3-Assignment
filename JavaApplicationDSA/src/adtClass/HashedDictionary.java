/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass;

import adtClass.interfaces.InterfaceHashDictionary;
import adtClass.node.TableEntry;

/**
 *
 * @author ITSUKA KOTORI
 */
public class HashedDictionary<K,V> implements InterfaceHashDictionary<K,V>, Cloneable, java.io.Serializable{


    private TableEntry<K, V>[] hashTable;
    private int numberOfEntries;
    private static final int DEFAULT_SIZE = 101; 


    public HashedDictionary() {
      this(DEFAULT_SIZE);
    }

    public HashedDictionary(int tableSize) {
      hashTable = new TableEntry[tableSize];
      numberOfEntries = 0;
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
    //	    locationsUsed++;
          oldValue = null;
        } else { // key found; get old value for return and then replace it
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
    V result = null;

    int index = getHashIndex(key);
    index = locate(index, key);

    if (index != -1) {
      result = hashTable[index].getValue(); // key found; get value
    }
		// else not found; result is null

    return result;
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
