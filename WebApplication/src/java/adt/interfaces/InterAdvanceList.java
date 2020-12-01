/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

import entity.AbstractEntity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Lim sai keat
 * @param <T>
 */
public interface InterAdvanceList<T> extends InterList<T> {

    /**
     * Remove the duplicate element in the list
     *
     * @return
     */
    public abstract boolean removeSameElement();

    /**
     * Search the same element in the list and return the list of matching data
     *
     * @param element
     * @return
     */
    public abstract InterList<Integer> search(T element);

    /**
     * Search have the list consider the item or not <br>
     * if excite return back the item <br>
     * else will return null value
     *
     * @param element
     * @return
     */
    public abstract T searchItem(T element);

    /**
     * Search the object by using method<br>
     * Example: Customer have getMember() will return "N", "P", "G"<br>
     * By using search by method it eg searchByMethod("getMember", "P")<br>
     * Note that: "getMember" must be Method type.<br>
     * result will return back all "P" member in the list.
     *
     * @param method that you need to compare
     * @param element of matching unit
     * @return
     */
    public abstract InterList<T> searchByMethod(Method method, Object element);

    /**
     * Search the object by using field.<br>
     * Example: Customer have field call id such as {1, 2, 3, 4, 5, 6}.<br>
     * By using search by field function they can search the field under the T
     * type.<br>
     * eg. searchByField("id", 2);<br>
     * it will return all the customer with id 2.
     *
     * @param field type to be the reference
     * @param element that need to compare
     * @return
     */
    public abstract InterList<T> searchByField(Field field, Object element);

    /**
     * Search the object by using field.<br>
     * Example: Customer have field call id such as {1, 2, 3, 4, 5, 6}.<br>
     * By using search by field function they can search the field under the T
     * type.<br>
     * eg. searchByField("id", 2);<br>
     * it will return all the customer with id 2.
     *
     * @param field name to be the reference
     * @param element that need to compare
     * @param _class reference that need to search eg. Customer.class
     * @return
     */
    public abstract InterList<T> searchByField(String field, Object element, Class<?> _class);

    /**
     * find a abstract entity that is id match to the parameter x
     *
     * @param x
     * @return Boolean
     */
    public abstract boolean find_AbstractEntity(AbstractEntity x);

    /**
     * For support reading CSV to array list
     *
     * @param input from CSV file
     */
    public void formInput(String input);

    /**
     * For support writing to CSV to string
     *
     * @return String write to CSV
     */
    public String toInput();

    /**
     * concatenate two field value to one string in the list return the list of
     * concatenated value
     *
     * @param field1
     * @param field2
     * @param _class
     * @return
     */
    public abstract InterList<String> concateField(String field1, String field2, Class<?> _class);

    /**
     * Get the list Classes field in a String List
     *
     * @param field
     * @param class_name
     * @return
     */
    public abstract InterList<String> getField(String field, String class_name);

    /**
     * Get the list Classes field in a String List
     *
     * @param f
     * @param _class
     * @return
     */
    public abstract InterList<String> getField(Field f, Class<?> _class);

}
