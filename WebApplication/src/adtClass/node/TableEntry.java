/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass.node;

/**
 *
 * @author ITSUKA KOTORI
 */

public class TableEntry<S, T> {

    private S key;
    private T value;
    private boolean inTable; // true if entry is in table

    public TableEntry(S searchKey, T dataValue) {
      key = searchKey;
      value = dataValue;
      inTable = true;
    } // end constructor

    public S getKey() {
      return key;
    } // end getKey

    public T getValue() {
      return value;
    } // end getValue

    public void setValue(T newValue) {
      value = newValue;
    } // end setValue

    public boolean isIn() {
      return inTable;
    } // end isIn

    public boolean isRemoved() { // opposite of isIn
      return !inTable;
    } // end isRemoved

    // state = true means entry in use; false means entry not in use, ie deleted
    public void setToRemoved() {
      key = null;
      value = null;
      inTable = false;
    } // end setToRemoved

    public void setToIn() { // not used
      inTable = true;
    } // end setToIn
    
}
