
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
     
     DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;

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
    
    
    public List<Map<String, Object>> getSearchRecords() {
        ResultSet rs = null;
        List<Map<String, Object>> rows = null;
        connect = new DBConnection();
        connect.init();
        
        try {
            
            if (connect.getMyConnection() != null) {
 
                java.sql.PreparedStatement ps = connect.getMyConnection().prepareStatement(
                        "select * from loan");
                rs = ps.executeQuery();
 
                if (rs != null) {
                    rows = new ArrayList<Map<String, Object>>();
                    //Code get resultset metadata information
                    java.sql.ResultSetMetaData metaData = rs.getMetaData();
                    //To get column count in result set
                    int columnCount = metaData.getColumnCount();
 
                    while (rs.next()) {
                        Map<String, Object> columns = new LinkedHashMap<String, Object>();
                        System.out.println("=======Row Start Here===========");
                        for (int i = 1; i <= columnCount; i++) {                             //To get Column Name                            System.out.println(metaData.getColumnLabel(i)+"->"+rs.getObject(i));
                            columns.put(metaData.getColumnLabel(i), rs.getObject(i));
                        }
 
                        rows.add(columns);
                    }
                }
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                {
                rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connect.closeMyConnection();
        }
 
        return rows;
    }

}
