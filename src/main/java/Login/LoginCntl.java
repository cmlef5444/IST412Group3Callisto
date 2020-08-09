
package Login;

import Data.Customer;
import Data.CustomerList;
import Data.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Loan Controller Class. It contains methods that allow a user
 * to login to the Callisto mFinance app.
 * @author Chris Lefebvre
 * @author kristinamantha
 * @author Scott Crowthers
 */
public class LoginCntl {
    
    CustomerList customerList;

    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private PreparedStatement ps;
    
    private boolean boolResult;
    private boolean emailBoolResult;
    
    int currentId;
    /**
     * This is the initial constructor
     */
    public LoginCntl(){
        
        customerList = new CustomerList();
    }
    /**
     * A method to verify if the inputted email is associated with a user account
     * @param userEmail A String representing customer's inputted username
     * @return A boolean return depending on whether username is valid.
     */
    private boolean isValidEmail(String userEmail){
        // TODO: check to make sure username is not taken - incorrect location, this should check if email is associated with a user
        //return userEmail.length() >= 3 && !(userEmail.contains(" "));
        return false;
    }
    private boolean isValidPassword(String inputPassword){
        return false;
    }
    private boolean nonDoubleEmail(String userEmail){   //FIX_ME has not been implemented or tested in servlet
         setEmailBoolResult(false);
        try{
            setConnect(new DBConnection());
            getConnect().init();
            
            setPs(getConnect().getMyConnection().prepareStatement("select customerEmail "
                    + "from customer "
                    + "where customerEmail = '" + userEmail + "'"));
            System.out.println("Input: " + userEmail);
            setMyRs(getPs().executeQuery());
            setEmailBoolResult(getMyRs().next());
            setEmailBoolResult(!isEmailBoolResult());
            while(getMyRs().next()){
                System.out.println("Email Input: " + userEmail + "Email SQL: " + getMyRs().getString("customerEmail"));              
            }
            if(isEmailBoolResult() == false){
                System.out.println("Email is not double");
     
            }
            else{
                System.out.println("Email is double");                
            }            
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            killConnections();        
        }  
        return isEmailBoolResult();
    }
    public int setupCurrentUser(String userEmail, String inputPassword){

        try{
            setConnect(new DBConnection());
            getConnect().init();
            
            setPs(getConnect().getMyConnection().prepareStatement("select customerId "
                    + "from customer "
                    + "where customerEmail = '" + userEmail + "' and customerPassword = '" + inputPassword + "'"));
            setMyRs(getPs().executeQuery());
            if(getMyRs().next()){
                currentId = getMyRs().getInt(1);
            }
          
            }catch(Exception e){      
            e.printStackTrace();
        }finally{
            killConnections();        
        }    
        return currentId;
    }
    
//    /**
//     * A method to verify password
//     * @param password A String representing the user password
//     * @return This returns the validity of the password
//     */
//    public boolean isValidPassword(String password){
//        // TODO: checks if password is the correct password associated with the given email
//        return password.length() >= 8;
//    }
    /**
     * A method to verify email and password
     * @param userEmail A String representing customer's email
     * @param password A String representing the customers password
     * @return A boolean return depending on whether combination of user name and password are correct
     * Maybe we should re add test username and passowrd as seperate entites
     */
    public boolean authenticator(String userEmail, String inputPassword) {
        setBoolResult(false);
        try{
            setConnect(new DBConnection());
            getConnect().init();
            
            setPs(getConnect().getMyConnection().prepareStatement("select customerEmail, customerPassword "
                    + "from customer "
                    + "where customerEmail = '" + userEmail + "' and customerPassword = '" + inputPassword + "'"));
//            getPs().setString(1, userEmail);
//            getPs().setString(2, inputPassword);
            System.out.println("Input: " + userEmail + ", " + inputPassword);
            setMyRs(getPs().executeQuery());
            setBoolResult(getMyRs().next());
            while(getMyRs().next()){
                System.out.println("Email Input: " + userEmail + "Email SQL: " + getMyRs().getString("customerEmail"));
                System.out.println("Password Input: " + inputPassword + "Password SQL: " + getMyRs().getString("customerPassword"));
            }
            if(isBoolResult() == true){
                System.out.println("Login successful");
     
            }
            else{
                System.out.println("Login unsuccessful");                
            }            
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            killConnections();      
        }  
        return isBoolResult();
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
    
    public void forgottenPassword(String userEmail){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
    }
    public void forgottenEmail(String securityQuestionResponse, int ssNum){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
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
     * @param boolResult the boolResult to set
     */
    public void setBoolResult(boolean boolResult) {
        this.boolResult = boolResult;
    }

    /**
     * @return the ps
     */
    public PreparedStatement getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * @return the boolResult
     */
    public boolean isBoolResult() {
        return boolResult;
    }

    /**
     * @return the emailBoolResult
     */
    public boolean isEmailBoolResult() {
        return emailBoolResult;
    }

    /**
     * @param emailBoolResult the emailBoolResult to set
     */
    public void setEmailBoolResult(boolean emailBoolResult) {
        this.emailBoolResult = emailBoolResult;
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
    
}
