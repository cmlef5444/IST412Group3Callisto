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
        
//        FontFactory.register("src/main/resources/Fonts/Popsies.ttf");
//        textFont = FontFactory.getFont("Popsies", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size
        FontProgram fontProgram = FontProgramFactory.createFont("C:/Users/cjani/OneDrive/Documents/GitHub/IST412Group3Callisto/src/main/resources/Fonts/Popsies.ttf");
        font = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);
        
        String pdfFile = "src/main/resources/OutputFiles/loanApplicationLoanId" + loanId + ".pdf";
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
//        generatePageTwo(document);
//        generatePageThree(document);

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

        // Top image
//        Image logo = new Image(ImageDataFactory.create(PATH_IMG_LOGO));
//        document.add(new Paragraph().add(logo.scale(0.50f, 0.50f)).setTextAlignment(TextAlignment.CENTER));

        // Title
        document.add(new Paragraph("Important Contract").setMultipliedLeading(2).setFontSize(FONT_TITLE).setBold().setTextAlignment(TextAlignment.CENTER));

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

        // Draw table
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porttitor ligula mauris, eu hendrerit sem ultrices et. Curabitur sit amet urna lorem. Vivamus rhoncus nisi et nulla efficitur, at ullamcorper risus ultricies. Nulla eget eros id elit auctor tincidunt.").setMarginTop(15));
//        document.add(drawTable("Lorem ipsum", "42"));

        // Signature
        document.add(new Paragraph("Please add your full name, date and signature below.").setMarginTop(15).setMarginBottom(15));
        document.add(drawSignature(customerFirstName, customerLastName, currentDate));

        return document;
    }

    private Table drawTable(String title, String content) {
        Table table = new Table(1).setWidthPercent(50).setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addHeaderCell(new Cell().add(new Paragraph(title).setMultipliedLeading(2).setFontSize(FONT_TABLE).setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(content).setMultipliedLeading(2).setFontSize(FONT_TABLE + 6).setBold().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY)).setPaddings(0, 0, 0, 0));
        return table;
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
    
 

//    private static Document generatePageTwo(Document document) throws Exception {
//        // Break page rotate
//        AreaBreak breakPage = new AreaBreak(AreaBreakType.NEXT_PAGE);
//        breakPage.setPageSize(PageSize.A4.rotate());
//        document.add(breakPage);
//
//        Image IMG_HALL = new Image(ImageDataFactory.create(PATH_IMG_HALL));
//        document.add(new Paragraph().add(IMG_HALL.setAutoScale(true)));
//
//        return document;
//    }
//
//    private static Document generatePageThree(Document document) throws Exception {
//        // Break page rotate
//        AreaBreak breakPage = new AreaBreak(AreaBreakType.NEXT_PAGE);
//        breakPage.setPageSize(PageSize.A4);
//        document.add(breakPage);
//
//        // Paragraphs
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris faucibus ipsum in quam tincidunt tristique. Phasellus ut volutpat libero, a dapibus libero. Ut ac porttitor sapien. Quisque non egestas libero. Praesent luctus consequat placerat. Nulla nec pulvinar tellus, gravida hendrerit velit. Integer vitae scelerisque nisi. Sed quis arcu et sapien rhoncus iaculis. Quisque non rutrum quam, quis aliquam nisi. Integer nec velit eget neque porta luctus. Mauris placerat velit sit amet hendrerit porta. Pellentesque rutrum, dui at blandit ullamcorper, risus nisi maximus arcu, a condimentum urna felis eget magna. Nunc sollicitudin dui at mi sodales aliquam.").setFontSize(FONT_SMALL));
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc convallis purus facilisis magna scelerisque lobortis. Curabitur nibh neque, aliquam vel fermentum a, condimentum nec lectus. Aliquam vehicula sem sed mollis varius. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce eu neque tortor. Vivamus ultricies quis nulla non posuere. Aenean pharetra risus eu euismod hendrerit. Suspendisse ac convallis metus. Duis vitae ipsum venenatis, viverra neque eget, pretium libero. Maecenas blandit et dolor id euismod. Nam egestas lectus eget tellus bibendum, ut dapibus magna laoreet. Praesent mauris ex, sodales id erat sit amet, facilisis scelerisque ipsum").setFontSize(FONT_SMALL));
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam faucibus lectus sed rhoncus consectetur. Nullam aliquet orci non erat sollicitudin cursus. In hac habitasse platea dictumst. Duis sed sagittis est, pharetra molestie erat. Sed ullamcorper, tortor sed faucibus elementum, lacus felis porttitor nisi, at luctus tellus felis sed odio. Ut laoreet tortor in imperdiet commodo. Cras lacinia feugiat lectus non vulputate. Vestibulum nec sodales metus, at maximus erat. Phasellus egestas vitae erat mollis egestas.").setFontSize(FONT_SMALL));
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id sollicitudin leo. Aenean convallis, justo et pharetra condimentum, lacus quam dignissim erat, a porttitor mauris mi ut mauris. Nullam rutrum fermentum efficitur. Nulla faucibus semper justo sit amet lacinia. Morbi commodo risus orci, ut finibus lorem sagittis a. Vestibulum bibendum commodo odio, sit amet efficitur leo maximus eu. Mauris sollicitudin urna luctus, fringilla felis ut, aliquam nunc. Pellentesque aliquam metus eget pellentesque facilisis. Morbi at diam aliquet, mattis arcu et, lobortis elit. Nam maximus egestas mauris et venenatis. Nulla in leo non lorem consectetur congue. Praesent vitae erat eu augue accumsan porta. Vestibulum consectetur aliquam sapien, at congue est elementum sit amet. Quisque suscipit blandit pellentesque. Nam nec justo quis felis iaculis ultricies ut at neque. Donec dapibus faucibus magna vitae porta.").setFontSize(FONT_SMALL));
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus bibendum pellentesque diam, eu convallis diam molestie id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus metus sem, dignissim maximus molestie a, fermentum nec sapien. Mauris facilisis at dui non vestibulum. Vestibulum at tempus orci. Duis sit amet ullamcorper magna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut pharetra imperdiet dolor eget rutrum. Vivamus mollis sit amet ex eu condimentum. Mauris nunc dui, condimentum mollis mollis elementum, vehicula vitae libero.").setFontSize(FONT_SMALL));
//        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget felis pretium, vehicula mauris eget, blandit massa. In purus augue, blandit sed diam eu, tincidunt convallis orci. Proin eu augue vel ante accumsan sagittis. Vivamus nec lectus quam. Integer fermentum condimentum eros sit amet finibus. Integer sed condimentum enim. Nullam consequat dolor ut velit congue aliquet. Suspendisse aliquam lacinia magna, eu consectetur massa tempus eget. Etiam id turpis metus. Phasellus vel vulputate arcu. Cras accumsan magna arcu, a pharetra mi efficitur nec. Quisque at tortor pellentesque, posuere mi eget, lacinia lectus. Cras dapibus rhoncus tellus ac pharetra.").setFontSize(FONT_SMALL));
//
//        return document;
//    }
}