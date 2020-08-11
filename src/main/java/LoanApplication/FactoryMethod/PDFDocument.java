/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanApplication.FactoryMethod;

import Data.Customer;
import com.itextpdf.text.Document;
import com.digisigner.client.DigiSignerClient;
import com.digisigner.client.data.SignatureRequest;
import com.digisigner.client.data.Signer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

//import com.groupdocs.signature.Signature;
//import com.groupdocs.signature.domain.SignatureFont;
//import com.groupdocs.signature.domain.enums.TextSignatureImplementation;
//import com.groupdocs.signature.options.sign.TextSignOptions;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Chris Lefebvre
 * 
 * Use Case: 1
 * overrides Document methods
 */

public class PDFDocument{

    Document document;
    /**
     * A method to create a pdf and then add the inputted fields to the document
     * @param id - represents the data of the current user
     *           - it will take data such as name, address, phone number, etc
     * 
     */
    
    
//    public void digiMethod(){
//        DigiSignerClient client = new DigiSignerClient("85f7d775-6226-40d7-9a09-e0ebeb271a3a5");
//        
//        SignatureRequest request = new SignatureRequest();
//        request.setEmbedded(true);
//        request.setSendEmails(false);
//
//        Document document = new Document(new File("document.pdf")); 
//        request.addDocument(document);
//
//        Signer signer = new Signer("cml5444@psu.edu");  //testing email
//        document.addSigner(signer);
//
//        SignatureRequest response = client.sendSignatureRequest(request);
//    }
    //change to PDDocument
//    @Override
    public void createDocument(int currentUser) {
//        //Creating the PDF document object
//        document = new Document();
//         
//        try{                   
//            PDPage my_page = new PDPage();
//            document.addPage(my_page);            
//            
//            //Saving the document
//            String pdfName = (String.valueOf(currentUser.getCustomerId()) + currentUser.getLastName() + currentUser.getFirstName());
////            electronicSignature(pdfName);
//            //Creating the PDDocumentInformation object 
//      PDDocumentInformation pdd = document.getDocumentInformation();
//
//      //Setting the author of the document
//      pdd.setAuthor("Callisto Finanace");
//       
//      // Setting the title of the document
//      pdd.setTitle("Callisto Loan Application"); 
//       
//      //Setting the creator of the document 
//      pdd.setCreator("Callisto Finanace"); 
//       
//      //Setting the subject of the document 
//      pdd.setSubject("Callisto Loan Application");
//
//
//
//
//
//
//
//            document.save("src/main/resources/OutputFiles/" + pdfName + ".pdf");
//            System.out.println("PDF created");
//            
//            
//            //Closing the document
//            document.close();
//        }catch(IOException e){            
//        } 
    }
    
    public void writePDFDocument(String pdfName, String customerFirstName, String customerLastName, double principalAmount, double annualRate){
//       try{
//            File file = new File("src/main/resources/OutputFiles/" + pdfName + ".pdf"); 
//            PDDocument doc = document.load(file);
//            PDPage page = doc.getPage(1); 
//            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//            
//            contentStream.beginText(); 
//            //Setting the position for the line 
//            contentStream.newLineAtOffset(25, 700);
//            contentStream.setFont(PDType1Font.TIMES_ROMAN, 20);
//           
//            String header = "Callisto Finance:";   
//            contentStream.showText(header);  
//            contentStream.newLine(); 
//            
//            
//             //Setting the font to the Content stream  
//            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//            contentStream.setLeading(14.5f);
//            
//            
//        String subject = "Callisto Finance Loan Application";
//        String text1 = "By signing this document you, " + customerFirstName + " " + customerLastName + ", henceforth be simply refered to as customer,";
//        String text2 = "will recieve a loan for the following amount, $" + principalAmount + ". In return the customer agrees to the following conditions";
//        String text3 = "    > The customer will repay the loan at an interest rate of " + annualRate + "%.";
//        String text4 = "    > The customer will make payments on the loan by the first of each month.";
//        String text5 = "    > Failure to pay the loan will result in late fees and/or collections.";
//        String text6 = "    > The customer is informed that these consequences will affect their credit score and future financial decisions.";
//
//            contentStream.showText(subject);  
//            contentStream.newLine(); 
//            contentStream.showText(text1);  
//            contentStream.newLine(); 
//            contentStream.showText(text2);
//            contentStream.newLine(); 
//            contentStream.showText(text3);
//            contentStream.newLine(); 
//            contentStream.showText(text4);
//            contentStream.newLine(); 
//            contentStream.showText(text5);
//            contentStream.newLine(); 
//            contentStream.showText(text6);
//
//            contentStream.endText();    
//            document.save("src/main/resources/OutputFiles/" + pdfName + ".pdf");
//            System.out.println("PDF written");
//            
//            //Closing the document
//            document.close();
//            
//       }catch(IOException e){
//           e.printStackTrace();
//           
//       }
//       
    }

    /**
     * A method to initialize the signature object, preparing it to verify a signature.
     * @return - return type will vary depending on future implemntation (most likely a pdf)
     * //@param publicKey A PublicKey for thee users electronic signature.
     * @param pdfName - A String representing the name of the pdf file that the user signs
     */
//    @Override
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
    
//    @Override
    public void uploadDocToUser(String documentName){
        //to be implemented later
    }
    
}
