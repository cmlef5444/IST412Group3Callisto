package Data;

import java.io.Serializable;

/**
 * This is the Loan details class. It has fields containing information
 * about the loan and getters/setters for these fields.
 * @author Chris Lefebvre
 * @author kristinamantha
 */
public class Loan implements Serializable{

    private long loanID;
    private long customerID;
    private long principalAmount;
    private long currentTotal;
    private long loanLength;
    private double annualRate;
    private long compoundNum;
    private long singlePayment;
    private String date;    //change to a proper date field once implementation is started
    
    /**
     * The loan constructor
     * @param loanID - a long representing the id in a loan profile
     * @param customerID - a long representing the customer id in a loan profile
     * @param princiapAmount - a long representing the initial amount when the loan was first given
     * @param currentTotal - a long representing the current amount remaining to pay in the loan
     * @param loanLength - a double representing the time (in months) given to pay off the loan (at min payments) (field type may change as progress is made)
     * @param annualRate - a long representing the interest added per year
     * @param compoundNum - a long representing the number of times the interest is compounded (per unit length in balanceCNtl) (this may change)
     * @param singlePayment - a long representing the amount of a single payment in the current loan
     * @param date - the date that the loan is started/payment is made
     */
    protected Loan(long loanID, long customerID, long principalAmount, long currentTotal,
            long loanLength, double annualRate, long compoundNum, long singlePayment, String date){
    this.loanID = loanID;
    this.customerID = customerID;
    this.principalAmount = principalAmount;
    this.currentTotal = currentTotal;
    this.loanLength = loanLength;
    this.annualRate = annualRate;
    this.compoundNum = compoundNum;
    this.singlePayment = singlePayment;
    this.date = date;
    
}

    /**
     * Returns the loan id 
     * @return A long representing the customer's loan id number
     */
    public long getLoanID() {
        return loanID;
    }

    /**
     * Sets the customer's loan id number
     * @param loanID the loanID to set
     */
    public void setLoanID(long loanID) {
        this.loanID = loanID;
    }
    
    /**
     * returns the customer id
     * @return the customerID
     */
    public long getCustomerID() {
        return customerID;
    }

    /**
     * sets the customer id
     * @param customerID the customerID to set
     */
    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }


    /**
     * returns the value of a single payment
     * @return the singlePayment
     */
    public long getSinglePayment() {
        return singlePayment;
    }

    /**
     * sets the value of a single payment
     * @param singlePayment the singlePayment to set
     */
    public void setSinglePayment(long singlePayment) {
        this.singlePayment = singlePayment;
    }
    
    /**
     * Returns the date
     * @return A String representing the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the principalAmount
     */
    public long getPrincipalAmount() {
        return principalAmount;
    }

    /**
     * @param principalAmount the principalAmount to set
     */
    public void setPrincipalAmount(long principalAmount) {
        this.principalAmount = principalAmount;
    }

    /**
     * @return the currentTotal
     */
    public long getCurrentTotal() {
        return currentTotal;
    }

    /**
     * @param currentTotal the currentTotal to set
     */
    public void setCurrentTotal(long currentTotal) {
        this.currentTotal = currentTotal;
    }

    /**
     * @return the loanLength
     */
    public long getLoanLength() {
        return loanLength;
    }

    /**
     * @param loanLength the loanLength to set
     */
    public void setLoanLength(long loanLength) {
        this.loanLength = loanLength;
    }

    /**
     * @return the annualRate
     */
    public double getAnnualRate() {
        return annualRate;
    }

    /**
     * @param annualRate the annualRate to set
     */
    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }

    /**
     * @return the compoundNum
     */
    public long getCompoundNum() {
        return compoundNum;
    }

    /**
     * @param compoundNum the compoundNum to set
     */
    public void setCompoundNum(long compoundNum) {
        this.compoundNum = compoundNum;
    }
}
