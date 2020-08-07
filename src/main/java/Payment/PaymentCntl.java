
package Payment;

import Data.Loan;
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
}
