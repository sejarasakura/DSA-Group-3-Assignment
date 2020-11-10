/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import java.io.Serializable;

/**
 *
 * @author ITSUKA KOTORI
 */
public abstract class AbstractEntity implements Comparable, Serializable {

    /**
     * Data storing directory
     */
    public static final String STORING_DIR = System.getProperty("user.dir") + "\\data";
    
    /**
     * 
     */
    public AbstractEntity() {
        
    }
    
    /**
     * Check is null or not
     * @return 
     */
    public abstract boolean isNotNull();
    
    /**
     * Split row of string to object
     * @param rowData
     * @return 
     */
    public abstract boolean split(String rowData);
    
    /**
     * Check is same or not
     * @param obj
     * @return 
     */
    @Override public abstract boolean equals(Object obj);
    
    /**
     * Hashing
     * @return 
     */
    @Override public abstract int hashCode();
    
    /**
     * Convert to string 
     * @return 
     */
    @Override public abstract String toString();

    /**
     * To support comparable
     * @param t
     * @return 
     */
    @Override
    public abstract int compareTo(Object t);
    
    
    public String getStorageDir(){
        return STORING_DIR + "\\" + this.getClass().getSimpleName() + ".csv";
    }
    
    
}
