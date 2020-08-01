/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cjani
 */
public class CustomerListTest {
    
    public CustomerListTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

//    /**
//     * Test of getInstance method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        CustomerList expResult = null;
//        CustomerList result = CustomerList.getInstance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setupCurrentUser method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetupCurrentUser() {
//        System.out.println("setupCurrentUser");
//        String userEmail = "";
//        String inputPassword = "";
//        CustomerList instance = new CustomerList();
//        Customer expResult = null;
//        Customer result = instance.setupCurrentUser(userEmail, inputPassword);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addCustomer method, of class CustomerList.
     */
//    @org.junit.jupiter.api.Test
//    public void testAddCustomer() {
//        System.out.println("addCustomer");
//        String email = "Erin@example.com";
//        String password = "ThisP$$sw0rd";
//        String firstName = "Erin";
//        String lastName = "Fever";
//        String address = "456 Second Way";
//        String phoneNumber = "098-765-4321";
//        CustomerList instance = new CustomerList();
//        instance.addCustomer(email, password, firstName, lastName, address, phoneNumber);
//
//    }
//
//    /**
//     * Test of editCustomer method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    
//    public void testEditCustomer() {
//        System.out.println("editCustomer");
//        String email = "";
//        String password = "";
//        long customerId = 0L;
//        String firstName = "";
//        String lastName = "";
//        String address = "";
//        String phoneNumber = "";
//
//        CustomerList instance = new CustomerList();
//        instance.editCustomer(email, password, customerId, firstName, lastName, address, phoneNumber);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCustomerArray method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetCustomerArray() {
//        System.out.println("getCustomerArray");
//        CustomerList instance = new CustomerList();
//        ArrayList<Customer> expResult = null;
//        ArrayList<Customer> result = instance.getCustomerArray();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCustomerArray method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetCustomerArray() {
//        System.out.println("setCustomerArray");
//        ArrayList<Customer> customerArray = null;
//        CustomerList instance = new CustomerList();
//        instance.setCustomerArray(customerArray);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCurrentUser method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetCurrentUser() {
//        System.out.println("setCurrentUser");
//        Customer currentUser = null;
//        CustomerList instance = new CustomerList();
//        instance.setCurrentUser(currentUser);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setInstance method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetInstance() {
//        System.out.println("setInstance");
//        CustomerList aInstance = null;
//        CustomerList.setInstance(aInstance);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCustomerFile method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetCustomerFile() {
//        System.out.println("getCustomerFile");
//        CustomerList instance = new CustomerList();
//        String expResult = "";
//        String result = instance.getCustomerFile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCustomerFile method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetCustomerFile() {
//        System.out.println("setCustomerFile");
//        String customerFile = "";
//        CustomerList instance = new CustomerList();
//        instance.setCustomerFile(customerFile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCurrentUser method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetCurrentUser() {
//        System.out.println("getCurrentUser");
//        CustomerList instance = new CustomerList();
//        Customer expResult = null;
//        Customer result = instance.getCurrentUser();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMyConnection method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetMyConnection() {
//        System.out.println("getMyConnection");
//        CustomerList instance = new CustomerList();
//        Connection expResult = null;
//        Connection result = instance.getMyConnection();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMyConnection method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetMyConnection() {
//        System.out.println("setMyConnection");
//        Connection myConnection = null;
//        CustomerList instance = new CustomerList();
//        instance.setMyConnection(myConnection);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMyStmt method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetMyStmt() {
//        System.out.println("getMyStmt");
//        CustomerList instance = new CustomerList();
//        Statement expResult = null;
//        Statement result = instance.getMyStmt();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMyStmt method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetMyStmt() {
//        System.out.println("setMyStmt");
//        Statement myStmt = null;
//        CustomerList instance = new CustomerList();
//        instance.setMyStmt(myStmt);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMyRs method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testGetMyRs() {
//        System.out.println("getMyRs");
//        CustomerList instance = new CustomerList();
//        ResultSet expResult = null;
//        ResultSet result = instance.getMyRs();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMyRs method, of class CustomerList.
//     */
//    @org.junit.jupiter.api.Test
//    @Ignore
//    public void testSetMyRs() {
//        System.out.println("setMyRs");
//        ResultSet myRs = null;
//        CustomerList instance = new CustomerList();
//        instance.setMyRs(myRs);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
