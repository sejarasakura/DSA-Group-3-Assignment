/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtClass.interfaces;

import adtClass.*;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public interface InterfaceLinkedList<T> {
    
    ////**
    /// * For the no-args constructor, the data and next will be null (empty list)
    /// */
    
    ////**
    /// * Construct a list with a single node
    /// * @param inputData The item to start the list off with
    /// */
    
    ////**
    /// * Construct a list with an array of nodes
    /// * @param inputList the nodes to start with
    /// */
    
    ////**
    /// * Return a string representation of the list
    /// * @return 
    /// */
    @Override
    public String toString();
    
    /**
     * Add a node to the list
     * @param inputData The data for the node
     */
    public void add(T inputData);
    
    /**
     * Add an array of nodes to the list
     * @param inputArray The nodes to add
     */
    public void add(T [] inputArray);

    /**
     * Find a node in the linked list
     * This relies on the fact that the specific class of inputData will have
     * a meaningful equals() method.
     * @param inputData
     * @return 
     */
    public int indexOf(T inputData);
    /**
     * Get the size of the list
     * @return The number of items in the list
     */
    public int size();
    
    /**
     * Delete a node from the linked list
     * This relies on the fact that the specific class of inputData will have
     * a meaningful equals() method. If the object to remove is not found, 
     * nothing will happen.
     * @param inputData
     */
    public void remove(T inputData);

    /**
     * Remove an array of items from the list
     * @param inputArray The items to remove from the list
     */
    public void remove(T [] inputArray);

    /**
     * Get the element at a specific position
     * @param inputPosition The position of the element
     * @return The element in the given position
     */
    public T elementAt(int inputPosition);
}
