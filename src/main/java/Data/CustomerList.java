
package Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This is the Customer List class. It contains an ArrayList of all Callisto customers
 * and methods to manipulate this ArrayList.
 * @author Chris Lefebvre
 * @author kristinamaantha
 */
public class CustomerList {
    
    private ArrayList<Customer> customerArray;
    private static CustomerList instance;
    
    private String customerFile = "src/main/resources/SerFiles/Customer.ser";
    
    private Customer currentUser;
    
    private Connection myConnection;
    private Statement myStmt;
    private ResultSet myRs;
    ResultSet rowRs;
    
    private int updateRs;
    private int numRows;
    int count;
    int num;
    //Azure database info
    //===============================================
        String host = "ist412group3server.database.windows.net:1433";
        String database = "Callisto";
        String user = "azureuser@ist412group3server";
        String password = "IST412Pa$$w0rd";
    //==================================================
    //Azure connection Strings
//   jdbc:sqlserver://ist412group3server.database.windows.net:1433;
//        database=Callisto;
//        user=azureuser@ist412group3server;
//        password=IST412Pa$$w0rd;
//        encrypt=true;
//        trustServerCertificate=false;
//        hostNameInCertificate=*.database.windows.net;
//        loginTimeout=30;
        //========================================================
    
    /**
     *Constructor for the CustomerList array
     * 
     * 7.19.20 10:28 am
     * To Reset Ser file Comment out line 31(readCustomerFile();) and uncomment the three next lines (Customer c1, customerArray, writeCustomerFile())
     * Once the program has been run immediately close it then uncomment/comment the respective lines to run as normal
     */
    public CustomerList(){
        customerArray = new ArrayList();
//        
//        String email = "Erin@example.com";
//        String password = "ThisP$$sw0rd";
//        String firstName = "Erin";
//        String lastName = "Fever";
//        String address = "456 Second Way";
//        String phoneNumber = "098-765-4321";

        
        //addCustomer(email, password, firstName, lastName, address, phoneNumber);
        //editCustomer("Connor@example.com", "NotPa$$w0rd", 3, "Connor543", "ThreeYears", "123 Sesame St", "123-123-1234");

        check();
    }
    
    public void check(){
        try{
            String connectionUrl = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd";
            String selectSql = "select * from customer";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myConnection = DriverManager.getConnection(connectionUrl);
            
            myStmt = myConnection.createStatement();
            myRs = myStmt.executeQuery(selectSql);
            
            while (myRs.next()){
                System.out.println(myRs.getString("customerId") + ", " + myRs.getString("customerLastName") + ", " + myRs.getString("customerFirstName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
         catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Could not find class");
        }finally{
            try{
                if(getMyRs() != null){
                    getMyRs().close();
                }
                if(getMyStmt() != null){
                        getMyStmt().close();
                }
                if(getMyConnection() !=null){
                        getMyConnection().close();
                }
                }catch(SQLException e){
                    e.printStackTrace();
                }
        }               
		System.out.println("Execution finished.");
    }
    //unsure if these two methods (getInstance() and setupCurrentUser) are still relevant in web app build
    //==============================================================================
    public static CustomerList getInstance(){
        if(instance == null){
            instance = new CustomerList();
        }
        return instance;
    }
    public Customer setupCurrentUser(String userEmail, String inputPassword){
        //FIX_ME This is from the old Java build, It will need to be modified or discarded for the web app build
        for (int i = 0; i < getCustomerArray().size(); i++) {                       
            if (userEmail.equals(getCustomerArray().get(i).getEmail())) {
                int p = i;

                if (inputPassword.equals(getCustomerArray().get(p).getPassword())) {
                    setCurrentUser(new Customer(getCustomerArray().get(p).getEmail(),
                            getCustomerArray().get(p).getPassword(),
                            getCustomerArray().get(p).getCustomerId(),
                            getCustomerArray().get(p).getFirstName(),
                            getCustomerArray().get(p).getLastName(),
                            getCustomerArray().get(p).getAddress(),
                            getCustomerArray().get(p).getPhoneNumber()));
                }
            } else {
            }       
        }
        return getCurrentUser();
    }
    //=========================================================================
    
    public void addCustomer(String email, String password, String firstName, String lastName, String address, String phoneNumber){
        try{
            System.out.println("Testing addCustomer()");
            
            String connectionUrl = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myConnection = DriverManager.getConnection(connectionUrl);

            String query = "insert into customer"
                    + " values ('"
                    + firstName + "', '"
                    + lastName + "', '"
                    + email + "', '" 
                    + password + "', '" 
                    + address + "', '" 
                    + phoneNumber + "')";
            
            myStmt = myConnection.createStatement();
            myStmt.executeUpdate(query);        
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if( getMyConnection() !=null){
                        getMyConnection().close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }            
        }
    }
    public void editCustomer(String email, String password, long customerId, String firstName, String lastName, String address, String phoneNumber){
        try{    
            
            String connectionUrl = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myConnection = DriverManager.getConnection(connectionUrl);
            
            String query = "UPDATE customer "
                    + "set customerFirstName = '" + firstName 
                    + "', customerLastName = '" + lastName
                    + "', customerEmail = '" + email
                    + "', customerPassword = '" + password
                    + "', customerAddress = '" + address
                    + "', customerPhoneNumber = '" + phoneNumber
                    + "' WHERE customerId = " + customerId;
            
            myStmt = myConnection.createStatement();            
            myStmt.executeUpdate(query);
        }catch(Exception e){ 
            e.printStackTrace();
        }finally{
            try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if( getMyConnection() !=null){
                        getMyConnection().close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    
    
    
//    /**
//     *  Creates a customer profile based on the inputted data and adds it to the ArrayList
//     * @param email - a String representing the email in a customer profile
//     * @param password - a String representing the password in a customer profile
//     * @param customerId - a long representing the customer id in a customer profile
//     * @param firstName - a String representing the customer's first name in the customer profile
//     * @param lastName - a String representing the customer's last name in the customer profile
//     * @param address - a String representing the customer's address in the customer profile
//     * @param phoneNumber - a String representing the customer's phone number in the customer profile
//     * @param loanID - a String representing the customer's loan id in the customer profile
//     */
//    public void addCustomerToArray(String email, String password, String firstName, String lastName, String address, String phoneNumber, long loanID){
//        getCustomerArray();
//
//        this.readCustomerFile();
//        Customer c1 = new Customer (email, password, customerArray.size() + 1, firstName, lastName, address, phoneNumber, 0L);
//        customerArray.add(c1);
//        
//        this.writeCustomerFile();
//        this.readCustomerFile();
//        //System.out.println("Testing: AddCustomer");
//    }
//
//     /**
//     *  Edits a customer profile based on the inputted data in the arrayList
//     * @param email - a String representing the email in a customer profile
//     * @param password - a String representing the password in a customer profile
//     * @param customerId - a long representing the customer id in a customer profile
//     * @param firstName - a String representing the customer's first name in the customer profile
//     * @param lastName - a String representing the customer's last name in the customer profile
//     * @param address - a String representing the customer's address in the customer profile
//     * @param phoneNumber - a String representing the customer's phone number in the customer profile
//     * @param loanID - a String representing the customer's loan id in the customer profile
//     */
//    public void editCustomerToArray(String email, String password, long customerId, String firstName, String lastName, String address, String phoneNumber, long loanID){
//        for(int i = 0; i < customerArray.size(); i++){
//            if(customerArray.get(i).getCustomerId() == 1){
//                customerArray.get(i).setEmail(email);
//                customerArray.get(i).setPassword(password);
//                customerArray.get(i).setFirstName(firstName);
//                customerArray.get(i).setLastName(lastName);
//                customerArray.get(i).setAddress(address);
//                customerArray.get(i).setPhoneNumber(phoneNumber);
//                customerArray.get(i).setLoanID(loanID);
//            }
//            else{
//                //edit fail message
//            }
////            if(customerArray.get(i).getCustomerId() == customerId){
////                customerArray.get(i).setEmail(email);
////                customerArray.get(i).setPassword(password);
////                customerArray.get(i).setFirstName(firstName);
////                customerArray.get(i).setLastName(lastName);
////                customerArray.get(i).setAddress(address);
////                customerArray.get(i).setPhoneNumber(phoneNumber);
////                customerArray.get(i).setLoanID(loanID);
////            }
////            else{
////                //edit fail message
////            }
//        }
//        this.writeCustomerFile();
//        this.readCustomerFile();
//    }
//
//    /**
//     *Deletes a customer from the customerArray
//     * @param customerID - a long representing the costumer id in a customer profile to be deleted
//     */
//    public void deleteCustomer(long customerID){
//        getCustomerArray().remove(customerID);
//    }
//    
//    /**
//     *Reads the (persistent) customerFile
//     */
//    public void readCustomerFile(){
//        FileInputStream fis = null;
//        ObjectInputStream in = null;
//
//        try {
//            fis = new FileInputStream(customerFile);
//            in = new ObjectInputStream(fis);
//            setCustomerArray((ArrayList<Customer>) in.readObject());          //needs a serialVersionUID, will reaserch this
//            in.close();
//
//            if (!customerArray.isEmpty()) {
//                //System.out.println("There are Parents on the list");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * writes the current customer array to the customer file (persistent)
//     */
//    
//    public void writeCustomerFile() {
//        FileOutputStream fos = null;
//        ObjectOutputStream out = null;
//
//        try {
//            fos = new FileOutputStream(customerFile);
//            out = new ObjectOutputStream(fos);
//            out.writeObject(getCustomerArray());
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * prints the customer file (primarily for debugging
//     */
//    public void printCustomerFile() {
//        System.out.println("The Customer List has these Customers");
//        for (int i = 0; i < getCustomerArray().size(); i++) {
//            Customer currentCustomer = (Customer) getCustomerArray().get(i);
//            System.out.println(currentCustomer.toString());
//        }
//    }

    //==========================================================================
    //Getter and Setters
    //==========================================================================
    /**
     * Returns an ArrayList of customers
     * @return An ArrayList representing the customers
     */
    public ArrayList<Customer> getCustomerArray() {
        return customerArray;
    }

    /**
     * Sets the CustomerArray
     * @param customerArray the customerArray to set
     */
    public void setCustomerArray(ArrayList<Customer> customerArray) {
        this.customerArray = customerArray;
    }


    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Customer currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(CustomerList aInstance) {
        instance = aInstance;
    }

    /**
     * @return the customerFile
     */
    public String getCustomerFile() {
        return customerFile;
    }

    /**
     * @param customerFile the customerFile to set
     */
    public void setCustomerFile(String customerFile) {
        this.customerFile = customerFile;
    }

    /**
     * @return the currentUser
     */
    public Customer getCurrentUser() {
        return currentUser;
    }

    /**
     * @return the myConnection
     */
    public Connection getMyConnection() {
        return myConnection;
    }

    /**
     * @param myConnection the myConnection to set
     */
    public void setMyConnection(Connection myConnection) {
        this.myConnection = myConnection;
    }

    /**
     * @return the myStmt
     */
    public Statement getMyStmt() {
        return myStmt;
    }

    /**
     * @param myStmt the myStmt to set
     */
    public void setMyStmt(Statement myStmt) {
        this.myStmt = myStmt;
    }

    /**
     * @return the myRs
     */
    public ResultSet getMyRs() {
        return myRs;
    }

    /**
     * @param myRs the myRs to set
     */
    public void setMyRs(ResultSet myRs) {
        this.myRs = myRs;
    }

    /**
     * @param numRows the numRows to set
     */
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    /**
     * @return the numRows
     */
    public int getNumRows() {
        return numRows;
    }
}
