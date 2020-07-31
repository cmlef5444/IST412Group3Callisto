/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanApplication.FactoryMethod;

import Data.Customer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

//import com.groupdocs.signature.Signature;
//import com.groupdocs.signature.domain.SignatureFont;
//import com.groupdocs.signature.domain.enums.TextSignatureImplementation;
//import com.groupdocs.signature.options.sign.TextSignOptions;

import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Chris Lefebvre
 * 
 * Use Case: 1
 * overrides Document methods
 */

public class PDFDocument implements Document{

    
    /**
     * A method to create a pdf and then add the inputted fields to the document
     * @param id - represents the data of the current user
     *           - it will take data such as name, address, phone number, etc
     * 
     */
    //change to PDDocument
    @Override
    public void createDocument(Customer currentUser) {
        //Creating the PDF document object
        PDDocument document = new PDDocument();
         
        try{                   
            PDPage my_page = new PDPage();
            document.addPage(my_page);            
            
            //Saving the document
            String pdfName = (String.valueOf(currentUser.getCustomerId()) + currentUser.getLastName() + currentUser.getFirstName());
//            electronicSignature(pdfName);
            
            document.save("src/main/resources/OutputFiles/" + pdfName + ".pdf");
            System.out.println("PDF created");
            
            
            //Closing the document
            document.close();
        }catch(IOException e){            
        } 
    }

    /**
     * A method to initialize the signature object, preparing it to verify a signature.
     * @return - return type will vary depending on future implemntation (most likely a pdf)
     * //@param publicKey A PublicKey for thee users electronic signature.
     * @param pdfName - A String representing the name of the pdf file that the user signs
     */
    @Override
    public void eSignature(String documentName) {
//       Signature signature = new Signature("src/main/resources/OutputFiles/" + documentName);
//
//        TextSignOptions options = new TextSignOptions("John Smith");
//        // set signature position
//        options.setLeft(100);
//        options.setTop(100);
//
//        // set signature rectangle
//        options.setWidth(100);
//        options.setHeight(30);
//
//        // set text color and Font
//        options.setForeColor(Color.RED);
//        SignatureFont signatureFont = new SignatureFont();
//        signatureFont.setSize(12);
//        signatureFont.setFamilyName("Comic Sans MS");
//        options.setFont(signatureFont);
//        options.setSignatureImplementation(TextSignatureImplementation.Sticker);
//
//        try{
//             // sign document to file
//        signature.sign(documentName, options);
//        }catch(Exception e){            
//        }
    }
    
    @Override
    public void uploadDocToUser(String documentName){
        //to be implemented later
    }
    
}
