
package CustomerProfile;

import Data.Customer;
import Data.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Edit Customer Information Controller class. It has methods for 
 * updating the personal information of a customer.
 * @author Chris Lefebvre
 * @author kristinamantha
 * @author Scott Crowthers
 */
public class CustomerProfileCntl {
    
    private Customer customer;
    
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    public CustomerProfileCntl(){
        
    }
    public void editCustomer(String email, String password, int customerId, String firstName, String lastName, String address, String phoneNumber){
        setConnect(new DBConnection());
        getConnect().init();
        try{                
            String query = "UPDATE customer "
                    + "set customerFirstName = '" + firstName 
                    + "', customerLastName = '" + lastName
                    + "', customerEmail = '" + email
                    + "', customerPassword = '" + password
                    + "', customerAddress = '" + address
                    + "', customerPhoneNumber = '" + phoneNumber
                    + "' WHERE customerId = " + customerId;
            
            setMyStmt(getConnect().getMyConnection().createStatement());            
            getMyStmt().executeUpdate(query);
        }catch(Exception e){ 
            e.printStackTrace();
        }finally{
            killConnections();
        }
    }
    public void killConnections(){
       try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if(getConnect().getMyConnection()!=null){
                        getConnect().closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    /**
     * Constructor for EditCustomerInfoCntl
     * @param customer
     */
    public CustomerProfileCntl(Customer customer){
        this.customer = customer;
        
    }
    
    public Customer getCustomer(){
        return customer;
    }   
    
    public Customer updateCustomer(){
        Customer updatedCustomer = new Customer();
        
        return updatedCustomer;
    }
    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the connect
     */
    public DBConnection getConnect() {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(DBConnection connect) {
        this.connect = connect;
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
