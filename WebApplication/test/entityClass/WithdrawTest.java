/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adt.XArrayList;
import entity.AbstractEntity;
import entity.Withdraw;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ITSUKA KOTORI
 */
public class WithdrawTest {

    public WithdrawTest() {
        Map map = new Map() {
            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEmpty() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsKey(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsValue(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object get(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object put(Object k, Object v) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void putAll(Map map) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Set keySet() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Collection values() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Set entrySet() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
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
     * Test of getWithdraw_id method, of class Withdraw.
     */
    @Test
    public void testGetWithdraw_id() {
        System.out.println("getWithdraw_id");
        Withdraw instance = new Withdraw();
        String expResult = "";
        String result = instance.getWithdraw_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWithdraw_id method, of class Withdraw.
     */
    @Test
    public void testSetWithdraw_id() {
        System.out.println("setWithdraw_id");
        String withdraw_id = "";
        Withdraw instance = new Withdraw();
        instance.setWithdraw_id(withdraw_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser_id method, of class Withdraw.
     */
    @Test
    public void testGetUser_id() {
        System.out.println("getUser_id");
        Withdraw instance = new Withdraw();
        String expResult = "";
        String result = instance.getUser_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser_id method, of class Withdraw.
     */
    @Test
    public void testSetUser_id() {
        System.out.println("setUser_id");
        String user_id = "";
        Withdraw instance = new Withdraw();
        instance.setUser_id(user_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAmout method, of class Withdraw.
     */
    @Test
    public void testGetAmout() {
        System.out.println("getAmout");
        Withdraw instance = new Withdraw();
        double expResult = 0.0;
        double result = instance.getAmount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAmout method, of class Withdraw.
     */
    @Test
    public void testSetAmout() {
        System.out.println("setAmout");
        double amout = 0.0;
        Withdraw instance = new Withdraw();
        instance.setAmount(amout);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Withdraw.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Withdraw instance = new Withdraw();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Withdraw.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        Withdraw instance = new Withdraw();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class Withdraw.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        Withdraw instance = new Withdraw();
        String expResult = "";
        String result = instance.getNote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNote method, of class Withdraw.
     */
    @Test
    public void testSetNote() {
        System.out.println("setNote");
        String note = "";
        Withdraw instance = new Withdraw();
        instance.setNote(note);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNotNull method, of class Withdraw.
     */
    @Test
    public void testIsNotNull() {
        System.out.println("isNotNull");
        Withdraw instance = new Withdraw();
        boolean expResult = false;
        boolean result = instance.isNotNull();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Withdraw.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Withdraw instance = new Withdraw();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Withdraw.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Withdraw instance = new Withdraw();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Withdraw.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Withdraw instance = new Withdraw();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Withdraw.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object t = null;
        Withdraw instance = new Withdraw();
        int expResult = 0;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of id_equals method, of class Withdraw.
     */
    @Test
    public void testId_equals() {
        System.out.println("id_equals");
        Object obj = null;
        Withdraw instance = new Withdraw();
        boolean expResult = false;
        boolean result = instance.id_equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    private static void testing_one() {

        Withdraw x = new Withdraw();

        XArrayList<Withdraw> employees = (XArrayList<Withdraw>) AbstractEntity.readDataFormCsv(x);

    }

    @Test
    public void main2() {

        // name of generated csv
        //Withdraw x = new Withdraw("WINISBEST", "HAII PEI", 7.88, new Date(), PaymentStatus.Completed.getCode(), "Note");
        //Withdraw x2 = new Withdraw("HAHAAAA", "HAHA GG", 9.99, new Date(), PaymentStatus.Completed.getCode(), "Note");
    }

}
