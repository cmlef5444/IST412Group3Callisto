
package Login;

import Data.CustomerList;

/**
 * This is the Loan Controller Class. It contains methods that allow a user
 * to login to the Callisto mFinance app.
 * @author Chris Lefebvre
 * @author kristinamantha
 * @author Scott Crowthers
 */
public class LoginCntl {
    
    CustomerList customerList;

    /**
     * This is the initial constructor
     */
    public LoginCntl(){
        
        customerList = new CustomerList();
    }
    /**
     * A method to verify if the inputted email is associated with a user account
     * @param userEmail A String representing customer's inputted username
     * @return A boolean return depending on whether username is valid.
     */
    private boolean isValidEmail(String userEmail){
        // TODO: check to make sure username is not taken - incorrect location, this should check if email is associated with a user
        //return userEmail.length() >= 3 && !(userEmail.contains(" "));
        return false;
    }
    
//    /**
//     * A method to verify password
//     * @param password A String representing the user password
//     * @return This returns the validity of the password
//     */
//    public boolean isValidPassword(String password){
//        // TODO: checks if password is the correct password associated with the given email
//        return password.length() >= 8;
//    }
    /**
     * A method to verify email and password
     * @param userEmail A String representing customer's email
     * @param password A String representing the customers password
     * @return A boolean return depending on whether combination of user name and password are correct
     * Maybe we should re add test username and passowrd as seperate entites
     */
//    public boolean authenticator(String userEmail, String inputPassword){
//        boolean value = false;
//        String storedUserPassword = "";
//        if(inputPassword.equals(storedUserPassword)){
//            value = true;
//        }
//        else{
//            value = false;
//        }       
//        
//        return value;
//    }
    
    public boolean authenticator(String userEmail, String inputPassword) {
           
        boolean value = false;
        for (int i = 0; i < customerList.getCustomerArray().size(); i++) {                       
            if (userEmail.equals(customerList.getCustomerArray().get(i).getEmail())) {
                int p = i;

                if (inputPassword.equals(customerList.getCustomerArray().get(p).getPassword())) {
                    value = true;
                }
            } else {
                value = false;
            }
        }
        System.out.println("authentictor value: " + value);
        return value;
        
    }
    
    public void forgottenPassword(String userEmail){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
    }
    public void forgottenEmail(String securityQuestionResponse, int ssNum){
        //JUnit Test is not yet created, will change once registration email system is set up(they use same basic techniques)
    }
}
