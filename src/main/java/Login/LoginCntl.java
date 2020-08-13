
package Login;

import Data.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is the Loan Controller Class. It contains methods that allow a user
 * to login to the Callisto mFinance app.
 * @author Chris Lefebvre
 * @author Kristina Mmantha
 * @author Scott Crowthers
 */
public class LoginCntl {
    
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
    }
    /**
     * A method to verify if the inputted email is associated with a user account
     * @param userEmail A String representing customer's inputted username
     * @return A boolean return depending on whether username is valid.
     */
    
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
            connect.killConnections();        
        }    
        return currentId;
    }
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
                setMyRs(getPs().executeQuery());
                setBoolResult(getMyRs().next());         
            }catch(Exception e){      
                e.printStackTrace();
            }finally{
                connect.killConnections();      
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
