
package Login;

import Data.CustomerList;
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

    private Connection myConnection;
    private Statement myStmt;
    private ResultSet myRs;
    
    private PreparedStatement ps;
    
    private boolean boolResult;
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
//    public boolean authenticator(String userEmail, String inputPassword){
//        boolean value = false;
//        String storedUserPassword = "";
//        if(inputPassword.equals(storedUserPassword)){
//            value = true;
//        }
//        else{
//            value = false;
//        }       
//        
//        return value;
//    }
    
    public boolean authenticator(String userEmail, String inputPassword) {
        setBoolResult(false);
        try{
            String connectionUrl = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            setMyConnection(DriverManager.getConnection(connectionUrl));
            
            setPs(myConnection.prepareStatement("select customerEmail, customerPassword "
                    + "from customer "
                    + "where customerEmail = '" + userEmail + "' and customerPassword = '" + inputPassword + "'"));
//            getPs().setString(1, userEmail);
//            getPs().setString(2, inputPassword);
            System.out.println("Input: " + userEmail + ", " + inputPassword);
            myRs = getPs().executeQuery();
            setBoolResult(myRs.next());
            while(myRs.next()){
                System.out.println("Email Input: " + userEmail + "Email SQL: " + myRs.getString("customerEmail"));
                System.out.println("Password Input: " + inputPassword + "Password SQL: " + myRs.getString("customerPassword"));
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
            try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getPs() != null){
                        getPs().close();
                }
                if( getMyConnection() !=null){
                        getMyConnection().close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }          
        }  
        return isBoolResult();
    }
    
    public void forgottenPassword(String userEmail){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
    }
    public void forgottenEmail(String securityQuestionResponse, int ssNum){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
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
    
}
