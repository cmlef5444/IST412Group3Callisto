/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanApplication.FactoryMethod;

import Data.Customer;
//import com.groupdocs.signature.Signature;
//import com.groupdocs.signature.domain.SignatureFont;
//import com.groupdocs.signature.domain.enums.TextSignatureImplementation;
//import com.groupdocs.signature.options.sign.TextSignOptions;
import java.awt.Color;

/**
 *
 * @author Chris Lefebvre
 */
public class WordDocument /*implements Document*/{

//    @Override
    public void createDocument(Customer currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void eSignature(String documentName) {//currenty identical to PDFDocument eSign, will change during implementation stage
//        Signature signature = new Signature("src/main/resources/OutputFiles/" + documentName);
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
//    @Override
    public void uploadDocToUser(String documentName){
        //to be implemented later
    }
    
}
