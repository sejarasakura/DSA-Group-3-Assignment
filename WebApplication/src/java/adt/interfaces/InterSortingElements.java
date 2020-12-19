/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author Chew chun pang
 * @param <T>
 */
public interface InterSortingElements<T> extends InterList<T> {

    /**
     * The Sorting Method Only If need Array method pls implement ArrayList
     * technic using quick slot
     */
    void sort();

    /**
     * The Sorting Method Only If need Array method pls implement ArrayList
     * technic using quick slot
     */
    void sortDesc();

    /**
     * The Sorting Method Only If need Array method pls implement ArrayList
     * technic using quick slot
     *
     * @param field
     * @param _class
     * @return
     */
    boolean sort(String field, Class<?> _class);

    /**
     * The Sorting Method Only If need Array method pls implement ArrayList
     * technic using quick slot
     *
     * @param field
     * @param _class
     * @return
     */
    boolean sortDesc(String field, Class<?> _class);

}
