
package Register;

import Data.Customer;
import Data.CustomerList;
import java.util.ArrayList;

/**
 * This is the Register Controller class. It handles registering new users
 * to mFinance and provides username and password validity checks.
 * @author Chris Lefebvre
 * @author kristinamantha
 */
public class RegisterCntl {
    /**
     * The is the initial constructor for the RegisterCntl
     */

    protected ArrayList<Customer> customerArray;
    
    protected String userEmail;
    protected String password;
    protected String key;

    public RegisterCntl(){

    }
    /**
     * This method generates a unique key for the email verification link
     * @return
     */
    public String generateKey(){
        String key = "X3rt725cp9S2X";
        return key;
    }
    /**
     * A void method to send email
     * @param userEmail a String representing the user Email
     */
    public void sendEmail(String userEmail){
        String key = generateKey();


    }
    /**
     * A method to verify if email is already used
     * @param userEmail A String representing the user Email
     * @return This returns the validity of the email
     */
    public boolean verifyEmail(String userEmail){
        boolean value = false;
//        for(int i = 0; i < customerArray.size(); i++){
//            if(customerArray.get(i).getEmail() == userEmail){
//                value = false;
//            }
//            else{
//                value = true;
//            }
//        }
        return value;
    }

    /**
     * A method to verify email meets criteria
     * @param email A String representing the user email
     * @return This returns the validity of the email
     */
    public boolean isValidEmail(String email){
        return false;
    }
    /**
     * A method to verify password
     * @param password A String representing the user password
     * @return This returns the validity of the password
     */
    public boolean isValidPassword(String password){
        return false;
    }
//    /**
//     * A method to verify username
//     * @param username A String representing the username
//     * @return Returns the validity of the username
//     */
//    public boolean isValidUsername(String username){
//        return false;
//    }
    /**
     * A method that once all checks have been made, creates a instance of the customer with the inputted email and password
     * @param userEmail a String representing the customer email
     * @param password a String representing the customer password
     */
    public void createAccount(String userEmail, String password){
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(userEmail, password, "", "", "", "", 0L);
    }
}
