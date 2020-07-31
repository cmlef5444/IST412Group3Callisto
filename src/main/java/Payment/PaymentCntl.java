
package Payment;

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
    
    private double amountDue;
    private Date dueDate;
    
    /**
     * This is a method to make a payment
     * @param payment A Double representing the amount the user chooses to pay
     * @param previousTotal - a double representing the loan's previous total
     * @return - represents the new total after payment has been made
     */
    public double makePayment(double payment, double previousTotal){
        previousTotal = 30000;
        payment = 5000;
        double newTotal = previousTotal - payment;
        return newTotal;
    }
    
    /**
     * A method that returns true/false depending on whether payment is late.
     * @return A boolean state representing late payment.
     */
    public boolean isLate(Date dueDate, Date currentDate){
        boolean value = false;
        if (dueDate.after(currentDate)) {
            System.out.println("Payment is not late");
            value = true;
        } else if (dueDate.before(currentDate)) {
            System.out.println("Payment is Late");
            value = false;
        } else if (dueDate.equals(currentDate)) {
            System.out.println("Payment is not late");
            value = true;
        } else {
            System.out.println("How to get here?");
        }       
        
        return value;
    }
    /**
     * A method that returns the (double) late fee amount based on that loan's interest
     * @param loanInterest - a double representing the interest associated with the loan
     * @param previousTotal - a double representing the total amount left in the loan
     * @return a double the represents the late fee amount
     */
    public double lateFee(double loanInterest, double previousTotal){
        double lateFeeAmount;
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
    public double amountDue(double loanInterest, double previousTotal){
        loanInterest = .15;
        previousTotal = 30000;
        double currentAmountDue = loanInterest * previousTotal;
        return currentAmountDue;
    }
}
