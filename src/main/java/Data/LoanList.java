
package Data;

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
    private int newEntryId;
    private double staticPrincipalAmount;
    private double staticCurrentTotal;
    private double staticLoanLength;
    private double staticAnnualRate;
    private double staticCompoundNum;
    private String staticInitialDate;
    
    
    
    /**
     *Constructor for LoanList
     */
    public LoanList(){   
//        makePayment(1,5,2000.00);
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
            killConnections();
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
            killConnections();
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
            String date;
            LocalDateTime now = LocalDateTime.now();
            date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(now);
            System.out.println("The current date: " + date);

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
                    + "'" + date + "'"
                    + "'" + date + "'"
                    + "'True')";
            
            myStmt = connect.getMyConnection().createStatement();
            myStmt.executeUpdate(query);        
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            killConnections();           
        }
    }
    public void makePayment(int loanId, int customerId, double singlePayment){
        selectMaxEntryFromLoan(loanId, customerId);
        updateCurrentLoan(getNewEntryId());
        selectStaticData(getNewEntryId());
        setNewEntryId(0);
        
        connect = new DBConnection();
        connect.init();
        try{
            String date;
            LocalDateTime now = LocalDateTime.now();
            date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(now);
            System.out.println("The current date: " + date);
            
            String query = "insert into loan"
                    + " values ("//entirId is auto generated by sql database
                    + loanId + ", "
                    + customerId + ", "
                    + getStaticPrincipalAmount() + ", " 
                    + (getStaticCurrentTotal() - singlePayment) + ", " 
                    + getStaticLoanLength() + ", " 
                    + getStaticAnnualRate() + ", "
                    + getStaticCompoundNum() + ", "
                    + singlePayment + ", "
                    + "'" + date + "',"
                    + "'" + getStaticInitialDate() + "',"
                    + "'True')";
            
            myStmt = connect.getMyConnection().createStatement();
            myStmt.executeUpdate(query);        
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            killConnections();            
        }
        
        setStaticPrincipalAmount(0);
        setStaticCurrentTotal(0);
        setStaticLoanLength(0);
        setStaticAnnualRate(0);
        setStaticCompoundNum(0);
        setStaticInitialDate("");
        
        //long methods, long parameters, large class
    }
    public void selectStaticData(int entryId){
        connect = new DBConnection();
        connect.init();
        try{
           String selectSql = "select principalAmount, "
                   + "currentTotal, "
                   + "loanLength, "
                   + "annualRate, "
                   + "compoundNum, "
                   + "initialDate from loan where entryId =" + entryId; 
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setStaticPrincipalAmount(getMyRs().getInt("principalAmount"));
                setStaticCurrentTotal(getMyRs().getInt("currentTotal"));
                setStaticLoanLength(getMyRs().getInt("loanLength"));
                setStaticAnnualRate(getMyRs().getInt("annualRate"));
                setStaticCompoundNum(getMyRs().getInt("compoundNum"));
                setStaticInitialDate(getMyRs().getString("initialDate"));
            }
            
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            killConnections();
        }
    }
    public void selectMaxEntryFromLoan(int currentLoanId, int currentUserId){
        connect = new DBConnection();
        connect.init();
        try{
           String selectSql = "select entryId from loan where loanId = " + currentLoanId + ""; 
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                System.out.println("EntryId: " + getMyRs().getInt("entryId"));
                setNewEntryId(getMyRs().getInt("entryId"));
                
            }
            
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            killConnections();
        }
    }
     public void updateCurrentLoan(int entryId){
        connect = new DBConnection();
        connect.init();
        try{    
            
            String query = "UPDATE loan "
                    + "set isCurrentBalance = 'false'"                    
                    + " WHERE entryId = " + entryId;
            
            myStmt = connect.getMyConnection().createStatement();            
            myStmt.executeUpdate(query);
        }catch(Exception e){ 
            e.printStackTrace();
        }finally{
            killConnections();
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
                if(connect.getMyConnection()!=null){
                        connect.closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
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

    /**
     * @return the newEntryId
     */
    public int getNewEntryId() {
        return newEntryId;
    }

    /**
     * @param newEntryId the newEntryId to set
     */
    public void setNewEntryId(int newEntryId) {
        this.newEntryId = newEntryId;
    }

    /**
     * @return the staticPrincipalAmount
     */
    public double getStaticPrincipalAmount() {
        return staticPrincipalAmount;
    }

    /**
     * @param staticPrincipalAmount the staticPrincipalAmount to set
     */
    public void setStaticPrincipalAmount(double staticPrincipalAmount) {
        this.staticPrincipalAmount = staticPrincipalAmount;
    }

    /**
     * @return the staticCurrentTotal
     */
    public double getStaticCurrentTotal() {
        return staticCurrentTotal;
    }

    /**
     * @param staticCurrentTotal the staticCurrentTotal to set
     */
    public void setStaticCurrentTotal(double staticCurrentTotal) {
        this.staticCurrentTotal = staticCurrentTotal;
    }

    /**
     * @return the staticLoanLength
     */
    public double getStaticLoanLength() {
        return staticLoanLength;
    }

    /**
     * @param staticLoanLength the staticLoanLength to set
     */
    public void setStaticLoanLength(double staticLoanLength) {
        this.staticLoanLength = staticLoanLength;
    }

    /**
     * @return the staticAnnualRate
     */
    public double getStaticAnnualRate() {
        return staticAnnualRate;
    }

    /**
     * @param staticAnnualRate the staticAnnualRate to set
     */
    public void setStaticAnnualRate(double staticAnnualRate) {
        this.staticAnnualRate = staticAnnualRate;
    }

    /**
     * @return the staticCompoundNum
     */
    public double getStaticCompoundNum() {
        return staticCompoundNum;
    }

    /**
     * @param staticCompoundNum the staticCompoundNum to set
     */
    public void setStaticCompoundNum(double staticCompoundNum) {
        this.staticCompoundNum = staticCompoundNum;
    }

    /**
     * @return the staticInitialDate
     */
    public String getStaticInitialDate() {
        return staticInitialDate;
    }

    /**
     * @param staticInitialDate the staticInitialDate to set
     */
    public void setStaticInitialDate(String staticInitialDate) {
        this.staticInitialDate = staticInitialDate;
    }
}
