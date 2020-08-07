
package Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This is the Loan List class. It has an ArrayList of all active loans
 * and methods to manipulate this ArrayList.
 * @author Chris Lefebvre
 */
public class LoanList {
    
    private static LoanList instance;
    
    DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int newLoanId;
    
    /**
     *Constructor for LoanList
     */
    public LoanList(){   
        check();
    }
    
    public static LoanList getInstance() {
        if(instance == null){
            instance = new LoanList();
        }
        return instance;
    } 
    public void check(){
        connect = new DBConnection();
        connect.init();
        
        try{
            String selectSql = "select * from loan";
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                System.out.println(getMyRs().getString("loanId") + ", " + getMyRs().getString("customerId") + ", " + getMyRs().getString("principalAmount")+ ", " + getMyRs().getString("currentTotal")+ ", " + getMyRs().getString("singlePayment") + ", " + getMyRs().getString("currentDate"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
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

    public void currentMaxLoanId(int currentUserId){
        connect = new DBConnection();
        connect.init();
        try{
           String selectSql = "select max(loanId) from loan"; 
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setNewLoanId(getMyRs().getInt("loanId"));
                
            }
            setNewLoanId(getNewLoanId() + 1);
            
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
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
    }
    /**
     * Creates a loan profile based on the inputs data and adds it to the loanArray
     * @param loanID - a long representing the load id in a loan profile (deprcate)
     * @param customerID - a long representing the customer id in a loan profile
     * @param amountTotal - a long representing the total amount of the loan in the loan profile
     * @param singlePayment - a long representing the amount payed in a single transaction
     * @param date - the date the loan was created or a payment made
     * 
     * Note: This is only used for the creation of a new loan, while payments are
     * stored in the same DB they use a different method to ensure loanId is correct
     */
    public void createLoan(int customerId, double princiaplAmount, 
            double currentTotal, double loanLength, double annualRate, 
            double compoundNum, double singlePayment){
        currentMaxLoanId(customerId);
        connect = new DBConnection();
        connect.init();
        try{
            System.out.println("Testing createLoan()");

            String date;
            //DateTimeFormatter dtf = new DateTimeFormatter("MM-dd-uuuu");
            LocalDateTime now = LocalDateTime.now();
            date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(now);
            System.out.println("The current date: " + date);
            //date = dtf.format(now);

            //loan id is auto generated by sql database
            String query = "insert into loan"
                    + " values ("
                    + getNewLoanId() + ", "
                    + customerId + ", "
                    + princiaplAmount + ", " 
                    + currentTotal + ", " 
                    + loanLength + ", " 
                    + annualRate + ", "
                    + compoundNum + ", "
                    + singlePayment + ", "
                    + "'" + date + "')";
            
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
    
    /**
     * Edits a loan profile based on the inputs data and adds it to the loanArray
     * @param loanID - a long representing the load id in a loan profile
     * @param customerID - a long representing the customer id in a loan profile
     * @param amountTotal - a long representing the total amount of the loan in the loan profile
     * @param singlePayment - a long representing the amount payed in a single transaction
     * @param date - the date the loan was created or a payment made
     */
    public void editLoan(int loanId, int customerId, double principalAmount, 
            double currentTotal, double loanLength, double annualRate, 
            double compoundNum, double singlePayment){
        connect = new DBConnection();
        connect.init();
        try{    
            
            String query = "UPDATE loan "
                    + "set customerId = " + customerId 
                    + ", principalAmount = " + principalAmount
                    + ", currentTotal = " + currentTotal
                    + ", loanLength = " + loanLength
                    + ", annualRate = " + annualRate
                    + ", compoundNum = " + compoundNum
                    + ", singlePayment = " + singlePayment
                    //+ ", currentDate = '" + date
                    + " WHERE loanId = " + loanId;
            
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
     * @return the newtLoanId
     */
    public int getNewLoanId() {
        return newLoanId;
    }

    /**
     * @param newtLoanId the newtLoanId to set
     */
    public void setNewLoanId(int newLoanId) {
        this.newLoanId = newLoanId;
    }
}
