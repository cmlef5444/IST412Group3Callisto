
package Payment;

import Data.DBConnection;
import Data.Loan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the Payment Controller class. It handles payments being made on a 
 * loan.
 * @author cjani
 * @author kristinamantha
 * @author scott
 */
public class PaymentCntl {
    
    private long amountDue;
    private Date dueDate;
    
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int entryId;
    
    /**
     * This is a method to make a payment
     * @param currentTotal A long representing the current balance of the loan
     * @param payment A Long representing the amount the user chooses to pay
     * @return - represents the new total after payment has been made
     */
    public long makePayment(long currentTotal, long payment){
        return currentTotal - payment;
    }
    
    /**
     * A method that returns true/false depending on whether payment is late.
     * @return A boolean state representing late payment.
     */
    public boolean isLate(Date dueDate, Date currentDate){      
        return dueDate.before(currentDate);
    }
    /**
     * A method that returns the (double) late fee amount based on that loan's interest
     * @param loanInterest - a double representing the interest associated with the loan
     * @param previousTotal - a double representing the total amount left in the loan
     * @return a double the represents the late fee amount
     */
    public long lateFee(long loanInterest, long previousTotal){
        long lateFeeAmount;
//        loanInterest = .2;
//        loanInterest = loanInterest + .05;
//        lateFeeAmount = previousTotal * loanInterest;
        lateFeeAmount = 500;
        return lateFeeAmount;
    }
    /**
     * A method that returns the current amount due for that pay period
     * @param loanInterest - a double representing the loan's associated interest rate
     * @param previousTotal - a double representing the loan's current total
     * @return - a double representing the current amount base on the interest rate and current total
     */
    public long amountDue(long loanInterest, long previousTotal){
        long currentAmountDue = (loanInterest * previousTotal) + previousTotal;
        return currentAmountDue;
    }
    
    public void getEntrySQL(int loanId){
        setConnect(new DBConnection());
        getConnect().init();
        
        try{
            String selectSql = "SELECT t.entryId from (SELECT entryId, loanId, "
                    + "ROW_NUMBER() OVER (PARTITION BY entryId ORDER BY "
                    + "loanId DESC) row_num FROM loan WHERE loanId =" + loanId + ")t WHERE t.row_num = 1";
            setMyStmt(getConnect().getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setEntryId(getMyRs().getInt("entryId"));
                System.out.println("The entryId: " + getEntryId());
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
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

    /**
     * @return the entryId
     */
    public int getEntryId() {
        return entryId;
    }

    /**
     * @param entryId the entryId to set
     */
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }
}
