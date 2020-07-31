
package Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is the Customer List class. It contains an ArrayList of all Callisto customers
 * and methods to manipulate this ArrayList.
 * @author Chris Lefebvre
 * @author kristinamaantha
 */
public class CustomerList {
    
    private ArrayList<Customer> customerArray;
    private static CustomerList instance;
    
    private String customerFile = "src/main/resources/SerFiles/Customer.ser";
    
    private Customer currentUser;
    
    /**
     *Constructor for the CustomerList array
     * 
     * 7.19.20 10:28 am
     * To Reset Ser file Comment out line 31(readCustomerFile();) and uncomment the three next lines (Customer c1, customerArray, writeCustomerFile())
     * Once the program has been run immediately close it then uncomment/comment the respective lines to run as normal
     */
    public CustomerList(){
        customerArray = new ArrayList();
        readCustomerFile();
//        Customer c1 = new Customer("kam6564@psu.edu",  "MyPa$$w0rd", 1, "Kristina", "Mantha", "313 Nittany Lane", "352-123-5555",  555512L);
//        customerArray.add(c1);//FIX_ME right now the above (or below) instance is repeatedly being added to the ser file. Needs to be cleared
//        writeCustomerFile();
//        //addCustomer("kam6564@psu.edu",  "MyPa$$w0rd", "Kristina", "Mantha", "313 Nittany Lane", "352-123-5555",  555512L);
        
    }
    
    public static CustomerList getInstance(){
        if(instance == null){
            instance = new CustomerList();
        }
        return instance;
    }
    public Customer setupCurrentUser(String userEmail, String inputPassword){
        
        for (int i = 0; i < getCustomerArray().size(); i++) {                       
            if (userEmail.equals(getCustomerArray().get(i).getEmail())) {
                int p = i;

                if (inputPassword.equals(getCustomerArray().get(p).getPassword())) {
                    currentUser = new Customer(getCustomerArray().get(p).getEmail(),
                    getCustomerArray().get(p).getPassword(),
                    getCustomerArray().get(p).getCustomerId(),
                    getCustomerArray().get(p).getFirstName(),
                    getCustomerArray().get(p).getLastName(),
                    getCustomerArray().get(p).getAddress(),
                    getCustomerArray().get(p).getPhoneNumber(),
                    getCustomerArray().get(p).getLoanID());
                }
            } else {
            }       
        }
        return currentUser;
    }
    /**
     *  Creates a customer profile based on the inputted data and adds it to the ArrayList
     * @param email - a String representing the email in a customer profile
     * @param password - a String representing the password in a customer profile
     * @param customerId - a long representing the customer id in a customer profile
     * @param firstName - a String representing the customer's first name in the customer profile
     * @param lastName - a String representing the customer's last name in the customer profile
     * @param address - a String representing the customer's address in the customer profile
     * @param phoneNumber - a String representing the customer's phone number in the customer profile
     * @param loanID - a String representing the customer's loan id in the customer profile
     */
    public void addCustomer(String email, String password, String firstName, String lastName, String address, String phoneNumber, long loanID){
        getCustomerArray();

        this.readCustomerFile();
        Customer c1 = new Customer (email, password, customerArray.size() + 1, firstName, lastName, address, phoneNumber, 0L);
        customerArray.add(c1);
        
        this.writeCustomerFile();
        this.readCustomerFile();
        //System.out.println("Testing: AddCustomer");
    }

     /**
     *  Edits a customer profile based on the inputted data in the arrayList
     * @param email - a String representing the email in a customer profile
     * @param password - a String representing the password in a customer profile
     * @param customerId - a long representing the customer id in a customer profile
     * @param firstName - a String representing the customer's first name in the customer profile
     * @param lastName - a String representing the customer's last name in the customer profile
     * @param address - a String representing the customer's address in the customer profile
     * @param phoneNumber - a String representing the customer's phone number in the customer profile
     * @param loanID - a String representing the customer's loan id in the customer profile
     */
    public void editCustomer(String email, String password, long customerId, String firstName, String lastName, String address, String phoneNumber, long loanID){
        for(int i = 0; i < customerArray.size(); i++){
            if(customerArray.get(i).getCustomerId() == 1){
                customerArray.get(i).setEmail(email);
                customerArray.get(i).setPassword(password);
                customerArray.get(i).setFirstName(firstName);
                customerArray.get(i).setLastName(lastName);
                customerArray.get(i).setAddress(address);
                customerArray.get(i).setPhoneNumber(phoneNumber);
                customerArray.get(i).setLoanID(loanID);
            }
            else{
                //edit fail message
            }
//            if(customerArray.get(i).getCustomerId() == customerId){
//                customerArray.get(i).setEmail(email);
//                customerArray.get(i).setPassword(password);
//                customerArray.get(i).setFirstName(firstName);
//                customerArray.get(i).setLastName(lastName);
//                customerArray.get(i).setAddress(address);
//                customerArray.get(i).setPhoneNumber(phoneNumber);
//                customerArray.get(i).setLoanID(loanID);
//            }
//            else{
//                //edit fail message
//            }
        }
        this.writeCustomerFile();
        this.readCustomerFile();
    }

    /**
     *Deletes a customer from the customerArray
     * @param customerID - a long representing the costumer id in a customer profile to be deleted
     */
    public void deleteCustomer(long customerID){
        getCustomerArray().remove(customerID);
    }
    
    /**
     *Reads the (persistent) customerFile
     */
    public void readCustomerFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(customerFile);
            in = new ObjectInputStream(fis);
            setCustomerArray((ArrayList<Customer>) in.readObject());          //needs a serialVersionUID, will reaserch this
            in.close();

            if (!customerArray.isEmpty()) {
                //System.out.println("There are Parents on the list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * writes the current customer array to the customer file (persistent)
     */
    
    public void writeCustomerFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(customerFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(getCustomerArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * prints the customer file (primarily for debugging
     */
    public void printCustomerFile() {
        System.out.println("The Customer List has these Customers");
        for (int i = 0; i < getCustomerArray().size(); i++) {
            Customer currentCustomer = (Customer) getCustomerArray().get(i);
            System.out.println(currentCustomer.toString());
        }
    }

    //==========================================================================
    //Getter and Setters
    //==========================================================================
    /**
     * Returns an ArrayList of customers
     * @return An ArrayList representing the customers
     */
    public ArrayList<Customer> getCustomerArray() {
        return customerArray;
    }

    /**
     * Sets the CustomerArray
     * @param customerArray the customerArray to set
     */
    public void setCustomerArray(ArrayList<Customer> customerArray) {
        this.customerArray = customerArray;
    }


    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Customer currentUser) {
        this.currentUser = currentUser;
    }
}
