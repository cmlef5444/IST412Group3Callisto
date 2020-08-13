
package Register;

import Data.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This is the Register Controller class. It handles registering new users
 * to mFinance and provides username and password validity checks.
 * @author Chris Lefebvre
 * @author Kristina Mantha
 */
public class RegisterCntl {
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    /**
     * The is the initial constructor for the RegisterCntl
     * https://www.c-sharpcorner.com/article/how-to-send-activation-link-in-email-after-user-registration-details-in-mvc/
     * https://medium.com/@angela.amarapala/sending-email-confirmation-for-account-activation-with-spring-java-cc3f5bb1398e
     */

    protected String userEmail;
    protected String password;
    protected String key;

    public RegisterCntl(){

    }
    
    public void registerCustomer(String customerFirstName, String customerLastName, String customerEmail, String address,
            String phoneNum, String password){
        setConnect(new DBConnection());
        getConnect().init();
        
        try{
            String query = "insert into customer"
                    + " values ("
                    + "'" + customerFirstName + "', "
                    + "'" +customerLastName + "', "
                    + "'" +customerEmail + "', " 
                    + "'" +password + "', " 
                    + "'" +address  + "', " 
                    + "'" +phoneNum + "')";
            
            setMyStmt(getConnect().getMyConnection().createStatement());
            getMyStmt().executeUpdate(query); 
            System.out.println(query);
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            connect.killConnections();            
            }
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
