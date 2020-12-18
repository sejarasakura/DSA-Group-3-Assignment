/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author ITSUKA KOTORI
 */
interface List<T> {

}

class Node<T> {

    T data;
    Node next;

    public Node(T data) {
        this.data = data;
    }
}
