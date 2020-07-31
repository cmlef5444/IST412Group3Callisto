
package EditCustomerInfo;

import Data.Customer;

/**
 * This is the Edit Customer Information Controller class. It has methods for 
 * updating the personal information of a customer.
 * @author Chris Lefebvre
 * @author kristinamantha
 * @author Scott Crowthers
 */
public class EditCustomerInfoCntl {
    
    private Customer customer;
    
    public EditCustomerInfoCntl(){
        
    }
    /**
     * Constructor for EditCustomerInfoCntl
     * @param customer
     */
    public EditCustomerInfoCntl(Customer customer){
        this.customer = customer;
        
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    
    public Customer updateCustomer(){
        Customer updatedCustomer = new Customer();
        
        return updatedCustomer;
    }
    
    
    /**
     *updates the email in a user profile
     * @param email - a string representing the email in a customer profile
     */
    public void updateEmail(String email){
        getCustomer().setEmail(email);
    }
    
    /**
     * Updates the password in a user profile
     * @param password - a String representing the password in a customer profile
     */
    public void updatePassword(String password){
        getCustomer().setPassword(password);
    }
    
    /**
     * Updates the first name in a user profile
     * @param firstName - a String representing the customer's first name in the customer
     */
    public void updatefirstName(String firstName){
        getCustomer().setFirstName(firstName);
    }
    
    /**
     * Updates the last name in a user profile
     * @param lastName - a String representing the customer's last name in the customer profile
     */
    public void updateLastName(String lastName){
        getCustomer().setLastName(lastName);
    }
    
    /**
     * Updates the address in a user profile
     * @param address - a String representing the customer's address in the customer profile
     */
    public void updateAddress(String address){
        getCustomer().setAddress(address);
    }
    
    /**
     * Updates the phone number in a user profile
     * @param phoneNumber - a String representing the customer's phone number in the customer profile
     */
    public void updatePhoneNumber(String phoneNumber){
        getCustomer().setPhoneNumber(phoneNumber);
    }

    /**
     * @return the customer
     */


    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
