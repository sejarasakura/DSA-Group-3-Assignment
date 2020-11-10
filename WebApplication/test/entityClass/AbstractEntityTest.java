/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import adtClass.*;
import entityClass.*;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AbstractEntityTest {
    
    public AbstractEntityTest() {
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
     * Test of isNotNull method, of class AbstractEntity.
     */
    @Test
    public void testIsNotNull() {
        System.out.println("isNotNull");
        AbstractEntity instance = new AbstractEntityImpl();
        boolean expResult = false;
        boolean result = instance.isNotNull();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of split method, of class AbstractEntity.
     */
    @Test
    public void testSplit() {
        System.out.println("split");
        String rowData = "";
        AbstractEntity instance = new AbstractEntityImpl();
        boolean expResult = false;
        boolean result = instance.split(rowData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class AbstractEntity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        AbstractEntity instance = new AbstractEntityImpl();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class AbstractEntity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        AbstractEntity instance = new AbstractEntityImpl();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AbstractEntity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AbstractEntity instance = new AbstractEntityImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class AbstractEntity.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object t = null;
        AbstractEntity instance = new AbstractEntityImpl();
        int expResult = 0;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStorageDir method, of class AbstractEntity.
     */
    @Test
    public void testGetStorageDir() {
        System.out.println("getStorageDir");
        AbstractEntity instance = new AbstractEntityImpl();
        String expResult = "";
        String result = instance.getStorageDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readDataToCsv method, of class AbstractEntity.
     */
    @Test
    public void testReadDataToCsv() {
        System.out.println("readDataToCsv");
        Iterable<? extends AbstractEntity> expResult = null;
        Iterable<? extends AbstractEntity> result = AbstractEntity.readDataToCsv();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataToCsv method, of class AbstractEntity.
     */
    @Test
    public void testCreateDataToCsv() {
        System.out.println("createDataToCsv");
        boolean expResult = false;
        boolean result = AbstractEntity.createDataToCsv();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDataToCsv method, of class AbstractEntity.
     */
    @Test
    public void testUpdateDataToCsv() {
        System.out.println("updateDataToCsv");
        boolean expResult = false;
        boolean result = AbstractEntity.updateDataToCsv();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDataToCsv method, of class AbstractEntity.
     */
    @Test
    public void testDeleteDataToCsv() {
        System.out.println("deleteDataToCsv");
        boolean expResult = false;
        boolean result = AbstractEntity.deleteDataToCsv();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractEntityImpl extends AbstractEntity {

        public boolean isNotNull() {
            return false;
        }

        public boolean split(String rowData) {
            return false;
        }

        public boolean equals(Object obj) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "";
        }

        public int compareTo(Object t) {
            return 0;
        }
    }
    
}
