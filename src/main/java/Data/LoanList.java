
package Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is the Loan List class. It has an ArrayList of all active loans
 * and methods to manipulate this ArrayList.
 * @author Chris Lefebvre
 */
public class LoanList {
    
    private ArrayList<Loan> loanArray;
    private static LoanList instance;
    
    private String loanFile = "src/main/resources/SerFiles/Loan.ser";
    /**
     *Constructor for LoanList
     */
    protected LoanList(){
        loanArray = new ArrayList<Loan>();
    }
    
    public static LoanList getInstance() {
        if(instance == null){
            instance = new LoanList();
        }
        return instance;
    } 
    
    /**
     * Creates a loan profile based on the inputs data and adds it to the loanArray
     * @param loanID - a long representing the load id in a loan profile
     * @param customerID - a long representing the customer id in a loan profile
     * @param amountTotal - a long representing the total amount of the loan in the loan profile
     * @param singlePayment - a long representing the amount payed in a single transaction
     * @param date - the date the loan was created or a payment made
     */
    public void addLoan(long loanID, long customerID,long princiaplAmount, 
            long currentTotal, long loanLength, double annualRate, 
            long compoundNum, long singlePayment, String date){
        Loan L1 = new Loan(1, 1, 20000,20000, 36, 0.05, 1, 0, "27-6-2020");
//        Loan L1 = new Loan(loanID, customerID, princiaplAmount,currentTotal, 
//                loanLength, annualRate, compoundNum, singlePayment, date);
        
        loanArray.add(L1);
        
        this.writeLoanFile();
        this.readLoanFile();
    }
    
    /**
     * Edits a loan profile based on the inputs data and adds it to the loanArray
     * @param loanID - a long representing the load id in a loan profile
     * @param customerID - a long representing the customer id in a loan profile
     * @param amountTotal - a long representing the total amount of the loan in the loan profile
     * @param singlePayment - a long representing the amount payed in a single transaction
     * @param date - the date the loan was created or a payment made
     */
    public void editLoan(long loanID, long customerID,long princiaplAmount, 
            long currentTotal, long loanLength, double annualRate, 
            long compoundNum, long singlePayment, String date){
        for(int i = 0; i < loanArray.size(); i++){
            if(loanArray.get(i).getLoanID() == 1){
                loanArray.get(i).setCustomerID(1);
                loanArray.get(i).setPrincipalAmount(45000);
                loanArray.get(i).setCurrentTotal(45000);
                loanArray.get(i).setLoanLength(36);
                loanArray.get(i).setAnnualRate(0.05);
                loanArray.get(i).setCompoundNum(1);
                loanArray.get(i).setSinglePayment(0);
                loanArray.get(i).setDate("15-8-2021");
            }
//            if(loanArray.get(i).getLoanID() == loanID){
//                loanArray.get(i).setCustomerID(customerID);
//                loanArray.get(i).setAmountTotal(amountTotal);
//                loanArray.get(i).setSinglePayment(singlePayment);
//                loanArray.get(i).setDate(date);
//            }
            else{
                
            }
        }
        this.writeLoanFile();
        this.readLoanFile();
    }
    
    /**
     * Deletes a loan from the loanArray
     * @param loanID A long representing the loan id number.
     */
    public void deleteLoan(long loanID){
        getLoanArray().remove(loanID);
    }
    
    /**
     *Reads the (persistent) loan File
     */
    public void readLoanFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(loanFile);
            in = new ObjectInputStream(fis);
            setLoanArray((ArrayList<Loan>) in.readObject());          //needs a serialVersionUID, will reaserch this
            in.close();

            if (!loanArray.isEmpty()) {
                //System.out.println("There are Parents on the list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * writes the current loan array to the loan file (persistent)
     */
    
    public void writeLoanFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(loanFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(getLoanArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * prints the loan file (primarily for debugging
     */
    public void printLoanFile() {
        System.out.println("The Loan List has these Loans");
        for (int i = 0; i < getLoanArray().size(); i++) {
            Loan currentLoan = (Loan) getLoanArray().get(i);
            System.out.println(currentLoan.toString());
        }
    }
    //==========================================================================
    //Getter and Setters
    //==========================================================================
    /**
     * @return the loanArray
     */
    public ArrayList<Loan> getLoanArray() {
        return loanArray;
    }

    /**
     * @param loanArray the loanArray to set
     */
    public void setLoanArray(ArrayList<Loan> loanArray) {
        this.loanArray = loanArray;
    }
}
