
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
    private int newEntityId;
    private double staticPrincipalAmount;
    private double staticCurrentTotal;
    private double staticLoanLength;
    private double staticAnnualRate;
    private double staticCompoundNum;
    private String staticPrincipalDate;
    
    
    
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
                    + "'" + date + "'"
                    + "'" + date + "'"
                    + "'True')";
            
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
    public void makePayment(int loanId, int customerId, double princiaplAmount, 
            double currentTotal, double loanLength, double annualRate, 
            double compoundNum, double singlePayment){
        selectMaxEntityFromLoan(loanId, customerId);
        updateCurrentLoan(newEntityId);
        selectStaticData(newEntityId);
        newEntityId = 0;
        
        connect = new DBConnection();
        connect.init();
        try{
            System.out.println("Testing makePayment()");

            String date;
            //DateTimeFormatter dtf = new DateTimeFormatter("MM-dd-uuuu");
            LocalDateTime now = LocalDateTime.now();
            date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(now);
            System.out.println("The current date: " + date);
            //date = dtf.format(now);

            //loan id is auto generated by sql database
            String query = "insert into loan"
                    + " values ("
                    + loanId + ", "
                    + customerId + ", "
                    + getStaticPrincipalAmount() + ", " 
                    + (getStaticCurrentTotal() - singlePayment) + ", " 
                    + getStaticLoanLength() + ", " 
                    + getStaticAnnualRate() + ", "
                    + getStaticCompoundNum() + ", "
                    + singlePayment + ", "
                    + "'" + getStaticPrincipalDate() + "'"
                    + "'" + date + "'"
                    + "'True')";
            
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
        
        setStaticPrincipalAmount(0);
        setStaticCurrentTotal(0);
        setStaticLoanLength(0);
        setStaticAnnualRate(0);
        setStaticCompoundNum(0);
        setStaticPrincipalDate("");
        
        //long methods, long parameters, large class
    }
    public void selectStaticData(int entityId){
        connect = new DBConnection();
        connect.init();
        try{
           String selectSql = "select principalAmount, "
                   + "currentTotal, "
                   + "loanLength, "
                   + "annualRate, "
                   + "compoundNum, "
                   + "principalDate from loan where entityId =" + entityId; 
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setStaticPrincipalAmount(getMyRs().getInt("principalAmount"));
                setStaticCurrentTotal(getMyRs().getInt("currentTotal"));
                setStaticLoanLength(getMyRs().getInt("loanLength"));
                setStaticAnnualRate(getMyRs().getInt("annualRate"));
                setStaticCompoundNum(getMyRs().getInt("compoundNum"));
                setStaticPrincipalDate(getMyRs().getString("principalDate"));
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
    }
    public void selectMaxEntityFromLoan(int currentLoanId, int currentUserId){
        connect = new DBConnection();
        connect.init();
        try{
           String selectSql = "select max(entityId) from loan where loanId =" + currentLoanId; 
           
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setNewEntityId(getMyRs().getInt("entityId"));
                
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
    }
     public void updateCurrentLoan(int entityId){
        connect = new DBConnection();
        connect.init();
        try{    
            
            String query = "UPDATE loan "
                    + "set isCurrectBalance = 'false'"                    
                    + " WHERE entityId = " + entityId;
            
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

    /**
     * @return the newEntityId
     */
    public int getNewEntityId() {
        return newEntityId;
    }

    /**
     * @param newEntityId the newEntityId to set
     */
    public void setNewEntityId(int newEntityId) {
        this.newEntityId = newEntityId;
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
     * @return the staticPrincipalDate
     */
    public String getStaticPrincipalDate() {
        return staticPrincipalDate;
    }

    /**
     * @param staticPrincipalDate the staticPrincipalDate to set
     */
    public void setStaticPrincipalDate(String staticPrincipalDate) {
        this.staticPrincipalDate = staticPrincipalDate;
    }
}
