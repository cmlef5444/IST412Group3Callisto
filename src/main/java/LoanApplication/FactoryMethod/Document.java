/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanApplication.FactoryMethod;

import Data.Customer;

/**
 *
 * @author Chris Lefebvre
 */
public interface Document {
    
    public void createDocument(Customer currentUser);
    public void eSignature(String documentName);
    public void uploadDocToUser(String documentName);
    
}
