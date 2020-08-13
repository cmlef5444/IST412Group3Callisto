/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanApplication;

/**
 *
 * @author cjani
 */
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Logger;

public class PdfGenerator {

    private static Logger LOG = Logger.getLogger(PdfGenerator.class.getName());
    

    //BaseFont base = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI);
    //Font font = new Font(base, 11f, Font.BOLD);
    
    PdfFont font;
    private static Float FONT_TITLE = 26F;
    private static Float FONT_TABLE = 16F;
    private static Float FONT_SMALL = 10F;
                    //calls the file from Strign pdfFile
    public void generatePdf(int loanId,
            String customerFirstName, 
            String customerLastName, 
            double principalAmount, 
            double annualRate,
            double loanLength,
            Date currentDate,
            String loanType) throws Exception {
        
        String basePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("pdfGenerator path: " + basePath);
        basePath = basePath.replace("target/IST412Group3Callisto-1.0-SNAPSHOT/WEB-INF/classes/LoanApplication/PdfGenerator.class", "");
        System.out.println("pdfGenerator path 2: " + basePath);
        
        String fontPath = basePath + "src/main/resources/Fonts/Popsies.ttf";
        String pdfPath = basePath + "src/main/resources/OutputFiles/loanApplicationLoanId" + loanId + ".pdf";
        
        
        //"C:/Users/cjani/OneDrive/Documents/GitHub/IST412Group3Callisto/src/main/resources/Fonts/Popsies.ttf"
        FontProgram fontProgram = FontProgramFactory.createFont(fontPath);
        font = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);
        
        String pdfFile = pdfPath;
        //"C:/Users/cjani/OneDrive/Documents/GitHub/IST412Group3Callisto/target/pdf/loanApplicationId" + getLoanId() + ".pdf"
        File file = new File(pdfFile);
        if (file.exists())
            file.delete();
        file.getParentFile().mkdirs();

        generateDocument(pdfFile, 
             customerFirstName, 
             customerLastName, 
             principalAmount, 
             annualRate,
             loanLength,
             currentDate,
             loanType);

        LOG.info("PDF has been generated");
    }

    private  void generateDocument(String pdfFile, 
            String customerFirstName, 
            String customerLastName, 
            double principalAmount, 
            double annualRate,
            double loanLength,
            Date currentDate,
            String loanType) throws Exception {
        OutputStream fos = new FileOutputStream(pdfFile);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Adds metadata to the document
        addDocumentMetadata(pdf);
        //==========================================================================================================================================

        // Adds a footer to each page of the document
//        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler(document));

        //==========================================================================================================================================
        // Generates three pages for this document
        generatePageOne(document, 
             customerFirstName,
             customerLastName, 
             principalAmount, 
             annualRate,
             loanLength,
             currentDate,
             loanType);
        
        pdf.close();
    }

    private  void addDocumentMetadata(PdfDocument pdf) {
        pdf.getCatalog().put(PdfName.Lang, new PdfString("EN"));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        info.setTitle("Callisto Finance Loan Application");
        info.setSubject("Loan Application");
        info.setAuthor("Callisto Finance");
        info.setCreator("Callisto Finance");
        info.setKeywords("contract, business, 2020");
    }

    private  Document generatePageOne(Document document, String customerFirstName, 
            String customerLastName, 
            double principalAmount, 
            double annualRate,
            double loanLength,
            Date currentDate,
            String loanType) throws Exception {

        // Title
        document.add(new Paragraph("Callisto Finance Loan Application").setMultipliedLeading(2).setFontSize(FONT_TITLE).setBold().setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("This conract is a loan application, if the lender aggrees to permit this loan the holder will automatically hold a contract for the loan and a copy of the official loan will be extended, under the same conditions").setMarginTop(15));

        // Paragraph
        document.add(new Paragraph("The holder of this contract, " + customerFirstName + " " + customerLastName + ", agrees to a loan with the following conditions:").setMarginTop(15));

        // Create a List
        List list = new List().setSymbolIndent(12).setListSymbol("\u2022");
        list.add(new ListItem("The Holder will recieve a loan of $" + principalAmount))
            .add(new ListItem("At a rate of " + annualRate + "% annual interest"))
            .add(new ListItem("The Holder will pay the " + loanType + " loan back in a " + loanLength + " month term"))
            .add(new ListItem("The Holder will make mininum payments on the first of each month"))
            .add(new ListItem("Failure to pay on time may result in Late Fees"))
            .add(new ListItem("Three missed payments or Five late payments will result in the loan going to Collections"));
        document.add(list);

        // Signature
        document.add(new Paragraph("Please add your full name, date and signature below.").setMarginTop(15).setMarginBottom(15));
        document.add(drawSignature(customerFirstName, customerLastName, currentDate));

        return document;
    }
    
    private Table drawSignature(String customerFirstName, String customerLastName, Date currentDate) {   
        Table table = new Table(2).setWidthPercent(60).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(new Cell().add(new Paragraph("Name").setMultipliedLeading(3).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(customerFirstName + " " + customerLastName).setMultipliedLeading(3)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Date").setMultipliedLeading(2).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(currentDate.toString()).setMultipliedLeading(2)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Signature").setMultipliedLeading(5).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(customerFirstName + " " + customerLastName).setMultipliedLeading(5)).setBorder(Border.NO_BORDER).setFont(font));
        return table;
    }
}