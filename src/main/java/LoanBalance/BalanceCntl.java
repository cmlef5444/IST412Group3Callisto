
package LoanBalance;

import java.util.Date;
import Data.Customer;
import Data.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * This is the Loan Balance Controller Class. This provides methods to display payment
 * and balance information about a loan.
 * https://facingissuesonit.com/2017/05/16/how-to-create-html-dynamic-table-in-jsp-from-resultset-with-unknown-columns/
 * @author cjani
 * @author kristinamantha
 * @author gkhalil
 */
public class BalanceCntl {

     private Customer customer;
     
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int entryId;

    public BalanceCntl(Customer customer){
        this.customer = customer;
    }

    public BalanceCntl() {

    }
    /**
     * A method to calculate compound interest
     * @param principleAmount An double representing the principle amount.
     * @param loanLength An double representing the amount of time money is borrowed for.
     * @param annualRate A double representing the annual interest rate.
     * @param compoudNum An double representing the number of times that interest is compounded per unit loanLength.
     * @return Returns a double representing the compounded interest over time.
     */
    public long calculateInterest(long principleAmount, long loanLength, long annualRate, long compoudNum){
        long amount = (long) (principleAmount * Math.pow(1 + (annualRate/compoudNum), compoudNum * loanLength));
        long cinterest = amount - principleAmount;
        return cinterest;
    }

    /**
     * A method to show customer payment history
     * @param payment A double showing the payment.
     * @param date A Date showing when the payment was made.
     * @return returns a double representing the payment
     */
    public String paymentHistory(long payment, Date date){
        String payment_information;
        payment_information = "Payment: " + payment + ", Date: " + date;
        return payment_information;
    }
    
    
    /**
     * 
     * @param currentAmount representing the current amount remaining in the loan
     * @param currentInterest representing the interest associated with the loan
     * @return returns a double representing the payment to pay for that month
     */
    public double calculateNextDue(long currentAmount, long currentInterest){
        long nextDue;
        nextDue = (currentAmount * currentInterest) + currentAmount;
        return nextDue;
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
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
