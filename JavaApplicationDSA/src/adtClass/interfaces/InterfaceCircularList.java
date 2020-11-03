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
public interface InterfaceCircularList<T> {
    
	/**
	 * Inserts a element into a linked list at beginning.
	 * 
	 * @param value
	 */
	public void insertAtBeginning(T value);

	/**
	 * Inserts a element into a linked list at tail position.
	 * 
	 * @param value
	 */
	public void insertAtTail(T value);

	/**
	 * Inserts a element into a linked list at a given position.
	 * 
	 * @param value
	 * @param position
	 */
	public void insertAtPosition(T value, int position);
        
	/**
	 * Method to delete an element from the 
	 * beginning of the circular linked list
	 */
	public void deleteFromBeginning();
        
	/**
	 * Method to delete an item from the circular
	 * linked list at a given position
	 * 
	 * @param position
	 */
	public void deleteFromPosition(int position);

	/**
	 * Method to check size of the circular linked list
	 * 
	 * @return {@link int}
	 */
	public int size();
        
	/**
	 * Method to check if circular linked list is empty
	 * 
	 * @return {@link boolean}
	 */
	public boolean isEmpty();
}
