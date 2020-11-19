/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public interface InterfaceSlotingElements<T> {

    /*
    *  The Sloting Methood Only If need Array method pls implement ArrayList
    *  Tecknic using quick slot
     */
    void sort();

    /*
    *  The Sloting Methood Only If need Array method pls implement ArrayList
    *  Tecknic using quick slot
     */
    void sortDesc();

    /*
    *  The Sloting Methood Only If need Array method pls implement ArrayList
    *  Tecknic using quick slot
     */
    boolean sort(String field, Class _class);

    /*
    *  The Sloting Methood Only If need Array method pls implement ArrayList
    *  Tecknic using quick slot
     */
    boolean sortDesc(String field, Class _class);

}
