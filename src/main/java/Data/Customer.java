package Data;

import java.io.Serializable;

/**
 * This is the Customer class. It provides fields to store personal information
 * about the customer as well as getters/setters for these fields.
 * @author Chris Lefebvre
 * @author kristinamantha
 * @author Scott Crowthers
 */
public class Customer implements Serializable{

    private String email;
    private String password;
    private long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private long loanID; //FIX_ME we may want to remove loanID since users may have multiple loans

    /**
     * This is the constructor for the Customer class
     * @param customerEmail - a String representing the email in a customer profile
     * @param customerPassword - a String representing the password in a customer profile
     * @param id - a long representing the customer id in a customer profile
     * @param firstName - a String representing the customer's first name in the customer profile
     * @param lastName - a String representing the customer's last name in the customer profile
     * @param address - a String representing the customer's address in the customer profile
     * @param phoneNumber - a String representing the customer's phone number in the customer profile
     * @param loanID  - a String representing the customer's loan id in the customer profile
     */
    public Customer(String customerEmail, String customerPassword, long id,
            String firstName, String lastName, String address, String phoneNumber,
            long loanID) {
        this.email = customerEmail;
        this.password = customerPassword;
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.loanID = loanID;
    }

    public Customer() {

    }

    public String toString(){
      return  "Customer [Customer ID: " + customerId + ", Name: " + firstName + " " + lastName + ","
              + " Email: " + email + ", Phone: " + phoneNumber + ", Address: " + address + ", Loan ID: " + loanID + "]";
    }
    /**
     * Returns an email address for the customer
     * @return A String representing the email address for the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the customer
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the customer
     * @return A String representing the password for the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the customer password
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the customer id number for the customer
     * @return A long representing the the customerId
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer id number
     * @param customerId the customerId to set
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /** 
     * Returns the first name of the customer
     * @return A String representing the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the customer
     * @return A String representing the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the physical address of the customer
     * @return A String representing the physical address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's physical address
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the customer's phone number
     * @return A String representing the customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the customer's phone number
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the customer's loan id number
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

}
