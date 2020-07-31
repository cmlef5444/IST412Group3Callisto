
package LoanApplication;

import LoanApplication.FactoryMethod.Document;
import LoanApplication.FactoryMethod.PDFDocument;
import LoanApplication.FactoryMethod.WordDocument;
//import java.security.PublicKey;
//import com.groupdocs.signature.Signature;
//import com.groupdocs.signature.domain.SignatureFont;
//import com.groupdocs.signature.domain.enums.TextSignatureImplementation;
//import com.groupdocs.signature.options.sign.TextSignOptions;
//import java.awt.Color;
//import java.io.IOException;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;


/**
 * This is the Loan Application Controller. It is used to handle
 * new loan applications and electronic signatures.
 * @author Chirs Lefebvre
 * @author kristinamantha
 * 
 * @apiPdf - https://pdfbox.apache.org/download.cgi#20x
 * @apiPdf tutorial - https://www.tutorialspoint.com/pdfbox/pdfbox_creating_a_pdf_document.htm
 * @apiEsign - https://products.groupdocs.com/signature/java
 * @apiEsign download/pom code - https://downloads.groupdocs.com/signature/java
 */
public class LoanApplicationCntl {
    
   
    
    /**
     * Initial LoanApplicationCntl constructor.
     */
    public LoanApplicationCntl(){
        
    }
    /**
     * The FactoryMethod pattern
     * As the UI is filled out and expanded upon during the implementation phase of 
     * LoanApplication this section will be removed/modified to account for the UI 
     * modifications and interface elements (ex. user choosing to get their document 
     * either as a pdf or word document)
     * 
     * The goal of using this basic setup is to be able to add additional document 
     * types in the future with minimal code changes.
     * @param docID represents a string that indicates what type of document to create
     * @return 
     */
    public Document getInstance(String docID){
        switch(docID){
            case "PDF": return new PDFDocument();
            case "Word": return new WordDocument();
        }
        return null;
    }
//    /**
//     * A method to create a pdf and then add the inputted fields to the document
//     * @param id - represents the user id
//     * @param firstName - a String representing the user's first name
//     * @param lastName - a String representing the user's Last name
//     * @param loanAmount - a long representing the loan amount
//     * @param interest - a double representing the loan interest
//     * @return - returns the pdf document created
//     */
//    //change to PDDocument
//    public PDDocument pdfCreator(long id, String firstName, String lastName, long loanAmount, double interest){
//        
//        //Creating the PDF document object
//        PDDocument document = new PDDocument();
//         
//        try{                   
//            PDPage my_page = new PDPage();
//            document.addPage(my_page);            
//            
//            //Saving the document
//            String pdfName = (String.valueOf(id) + lastName + firstName);
////            electronicSignature(pdfName);
//            
//            document.save("src/main/resources/OutputFiles/" + pdfName + ".pdf");
//            System.out.println("PDF created");
//            
//            
//            //Closing the document
//            document.close();
//        }catch(IOException e){            
//        } 
//        
//        
//        return document;    
//
//    }
//    
//    /**
//     * A method to initialize the signature object, preparing it to verify a signature.
//     * @return - return type will vary depending on future implemntation (most likely a pdf)
//     * //@param publicKey A PublicKey for thee users electronic signature.
//     * @param pdfName - A String representing the name of the pdf file that the user signs
//     */
//    public final void electronicSignature(String pdfName){
//        Signature signature = new Signature("src/main/resources/OutputFiles/" + pdfName);
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
//        signature.sign("my_doc.pdf", options);
//        }catch(Exception e){            
//        }
//    }
}
