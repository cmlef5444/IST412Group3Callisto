
package LoanBalance;

import java.util.Date;
import Data.Customer;
/**
 * This is the Loan Balance Controller Class. This provides methods to display payment
 * and balance information about a loan.
 * @author cjani
 * @author kristinamantha
 * @author gkhalil
 */
public class BalanceCntl {

     private Customer customer;

    public BalanceCntl(Customer customer){
        this.customer = customer;
    }

    BalanceCntl() {

    }



    /**
     * A method to calculate compound interest
     * @param principleAmount An double representing the principle amount.
     * @param loanLength An double representing the amount of time money is borrowed for.
     * @param annualRate A double representing the annual interest rate.
     * @param compoudNum An double representing the number of times that interest is compounded per unit loanLength.
     * @return Returns a double representing the compounded interest over time.
     */
    public double calculateInterest(double principleAmount, double loanLength, double annualRate, double compoudNum){
        double amount = principleAmount * Math.pow(1 + (annualRate/compoudNum), compoudNum * loanLength);
        double cinterest = amount - principleAmount;
        return cinterest;
    }

    /**
     * A method to show customer payment history
     * @param payment A double showing the payment.
     * @param date A Date showing when the payment was made.
     * @return returns a double representing the payment
     */
    public String paymentHistory(double payment, Date date){
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
    public double calculateNextDue(double currentAmount, double currentInterest){
        double nextDue = 0.0;
        nextDue = currentAmount * currentInterest;
        return nextDue;
    }

}
