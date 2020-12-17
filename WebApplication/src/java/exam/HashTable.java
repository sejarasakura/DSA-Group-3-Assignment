/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ITSUKA KOTORI
 */
public class HashTable {

    static List<String> student_ids = new ArrayList<String>();

    static void init() {

        student_ids.add("222222");
        student_ids.add("222345");
        student_ids.add("222454");
        student_ids.add("223454");
        student_ids.add("233452");
        student_ids.add("223452");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Node<String, String>[] hashTable = new Node[83];
        init();

        student_ids.forEach((x) -> {
            int hash = x.hashCode();
            int index = hash % 83;
            hashTable[index].key = x;
            hashTable[index].prev = hashTable[index];
        });

        for (int i = 0; i < hashTable.length; i++) {

        }
    }

    private class Node<K, V> {

        K key;
        V value;
        Node prev;
    }

    /**
     * @param args the command line arguments
     */
    public static void main2(String[] args) {
        String[] hashTable = new String[83];
        // TODO code application logic here
        init();
        student_ids.forEach((x) -> {
            int hash = x.hashCode();
            int index = hash % 83;
            hashTable[index] = x;
        });

        for (String x : hashTable) {
            System.out.println(x);
        }
    }

}
