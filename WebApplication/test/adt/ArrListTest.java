/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import entity.AbstractEntity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ITSUKA KOTORI
 */
public class ArrListTest {

    public ArrListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of iterator method, of class ArrList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        ArrList instance = new ArrList();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Object e = null;
        ArrList instance = new ArrList();
        instance.add(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int _index = 0;
        Object element = null;
        ArrList instance = new ArrList();
        instance.add(_index, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class ArrList.
     */
    @Test
    public void testAddAll_GenericType() {
        System.out.println("addAll");
        Object[] c = null;
        ArrList instance = new ArrList();
        instance.addAll(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class ArrList.
     */
    @Test
    public void testAddAll_int_GenericType() {
        System.out.println("addAll");
        int _index = 0;
        Object[] c = null;
        ArrList instance = new ArrList();
        instance.addAll(_index, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class ArrList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int _index = 0;
        ArrList instance = new ArrList();
        Object expResult = null;
        Object result = instance.get(_index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int _index = 0;
        ArrList instance = new ArrList();
        Object expResult = null;
        Object result = instance.remove(_index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class ArrList.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int _index = 0;
        Object element = null;
        ArrList instance = new ArrList();
        instance.set(_index, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ArrList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object o = null;
        ArrList instance = new ArrList();
        int expResult = 0;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class ArrList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        ArrList instance = new ArrList();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class ArrList.
     */
    @Test
    public void testToArray() {
        System.out.println("toArray");
        ArrList instance = new ArrList();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class ArrList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        ArrList instance = new ArrList();
        Object[] expResult = null;
        Object[] result = instance.clear();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSameElement method, of class ArrList.
     */
    @Test
    public void testRemoveSameElement() {
        System.out.println("removeSameElement");
        ArrList instance = new ArrList();
        boolean expResult = false;
        boolean result = instance.removeSameElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class ArrList.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Object element = null;
        ArrList instance = new ArrList();
        ArrList<Integer> expResult = null;
        ArrList<Integer> result = instance.search(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchItem method, of class ArrList.
     */
    @Test
    public void testSearchItem() {
        System.out.println("searchItem");
        Object element = null;
        ArrList instance = new ArrList();
        Object expResult = null;
        Object result = instance.searchItem(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByMethod method, of class ArrList.
     */
    @Test
    public void testSearchByMethod() {
        System.out.println("searchByMethod");
        Method method = null;
        Object element = null;
        ArrList instance = new ArrList();
        ArrList expResult = null;
        ArrList result = instance.searchByMethod(method, element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByField method, of class ArrList.
     */
    @Test
    public void testSearchByField_Field_Object() {
        System.out.println("searchByField");
        Field field = null;
        Object element = null;
        ArrList instance = new ArrList();
        ArrList expResult = null;
        ArrList result = instance.searchByField(field, element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByField method, of class ArrList.
     */
    @Test
    public void testSearchByField_3args() {
        System.out.println("searchByField");
        String field = "";
        Object element = null;
        Class _class = null;
        ArrList instance = new ArrList();
        ArrList expResult = null;
        ArrList result = instance.searchByField(field, element, _class);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ArrList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ArrList instance = new ArrList();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find_AbstractEntity method, of class ArrList.
     */
    @Test
    public void testFind_AbstractEntity() {
        System.out.println("find_AbstractEntity");
        AbstractEntity x = null;
        ArrList instance = new ArrList();
        boolean expResult = false;
        boolean result = instance.find_AbstractEntity(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class ArrList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrList instance = new ArrList();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toHtml method, of class ArrList.
     */
    @Test
    public void testToHtml() {
        System.out.println("toHtml");
        ArrList instance = new ArrList();
        String expResult = "";
        String result = instance.toHtml();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toInput method, of class ArrList.
     */
    @Test
    public void testToInput() {
        System.out.println("toInput");
        ArrList instance = new ArrList();
        String expResult = "";
        String result = instance.toInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formInput method, of class ArrList.
     */
    @Test
    public void testFormInput() {
        System.out.println("formInput");
        String input = "";
        ArrList instance = new ArrList();
        instance.formInput(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concateField method, of class ArrList.
     */
    @Test
    public void testConcateField() {
        System.out.println("concateField");
        String field1 = "";
        String field2 = "";
        Class _class = null;
        ArrList instance = new ArrList();
        ArrList<String> expResult = null;
        ArrList<String> result = instance.concateField(field1, field2, _class);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getField method, of class ArrList.
     */
    @Test
    public void testGetField_String_String() {
        System.out.println("getField");
        String field = "map_id";
        String class_name = "entity.Mapping";
        ArrList instance = new ArrList();
        ArrList<String> expResult = null;
        ArrList<String> result = instance.getField(field, class_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
