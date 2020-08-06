
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

    private Customer currentUser;

    private Statement myStmt;
    private ResultSet myRs;

    
    DBConnection connect;
 
    
    /**
     *Constructor for the CustomerList array
     * 
     * 7.19.20 10:28 am
     * To Reset Ser file Comment out line 31(readCustomerFile();) and uncomment the three next lines (Customer c1, customerArray, writeCustomerFile())
     * Once the program has been run immediately close it then uncomment/comment the respective lines to run as normal
     */
    public CustomerList(){
        connect = new DBConnection();
        connect.init();
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
            String selectSql = "select * from customer";

            myStmt = connect.getMyConnection().createStatement();
            myRs = myStmt.executeQuery(selectSql);
            
            while (myRs.next()){
                System.out.println(myRs.getString("customerId") + ", " + myRs.getString("customerLastName") + ", " + myRs.getString("customerFirstName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
//         catch(ClassNotFoundException e){
//            e.printStackTrace();
//            System.out.println("Could not find class");
//        }
            finally{
            try{
                if(getMyRs() != null){
                    getMyRs().close();
                }
                if(getMyStmt() != null){
                        getMyStmt().close();
                }
                if(connect.getMyConnection()!=null){
                        connect.closeMyConnection();
                }
                }catch(SQLException e){
                    e.printStackTrace();
                }
        }               
		System.out.println("Execution finished.");
    }    
    public void addCustomer(String email, String password, String firstName, String lastName, String address, String phoneNumber){
        try{
            System.out.println("Testing addCustomer()");

            String query = "insert into customer"
                    + " values ('"
                    + firstName + "', '"
                    + lastName + "', '"
                    + email + "', '" 
                    + password + "', '" 
                    + address + "', '" 
                    + phoneNumber + "')";
            
            myStmt = connect.getMyConnection().createStatement();
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
               if(connect.getMyConnection()!=null){
                        connect.closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }            
        }
    }
    public void editCustomer(String email, String password, long customerId, String firstName, String lastName, String address, String phoneNumber){
        try{                
            String query = "UPDATE customer "
                    + "set customerFirstName = '" + firstName 
                    + "', customerLastName = '" + lastName
                    + "', customerEmail = '" + email
                    + "', customerPassword = '" + password
                    + "', customerAddress = '" + address
                    + "', customerPhoneNumber = '" + phoneNumber
                    + "' WHERE customerId = " + customerId;
            
            myStmt = connect.getMyConnection().createStatement();            
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
                if(connect.getMyConnection()!=null){
                        connect.closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    //==========================================================================
    //Getter and Setters
    //==========================================================================


    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Customer currentUser) {
        this.currentUser = currentUser;
    }



    /**
     * @return the currentUser
     */
    public Customer getCurrentUser() {
        return currentUser;
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
}
