/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;


/**
 *
 * @author ITSUKA KOTORI
 */
interface List<T> {

}

class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}
