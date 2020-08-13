/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Data.DBConnection;
import LoanApplication.PdfGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Lefebvre
 */
public class PdfGeneratorServlet extends HttpServlet {

    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int customerId;
    private int loanId;
    private String customerFirstName;
    private String customerLastName;
    private double principalAmount;
    private double annualRate;
    private double loanLength;
    private Date currentDate;
    private String loanType;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        setCustomerId((int)request.getSession().getAttribute("customerId"));
        setLoanId((int)request.getSession().getAttribute("loanId"));
        request.setAttribute("customerIdentification", getCustomerId());
        setNumValues();
        setPersonValues();
        
        try{
            PdfGenerator pdfGenerator = new PdfGenerator();
            pdfGenerator.generatePdf(getLoanId(),
                    getCustomerFirstName(), 
                    getCustomerLastName(),
                    getPrincipalAmount(), 
                    getAnnualRate(),
                    getLoanLength(),
                    getCurrentDate(),
                    getLoanType());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        String basePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        basePath = basePath.replace("target/IST412Group3Callisto-1.0-SNAPSHOT/WEB-INF/classes/Servlets/PdfGeneratorServlet.class", "");
        basePath = basePath.replace("%", "");
        
        String filePath = basePath + "src/main/resources/OutputFiles/loanApplicationLoanId" + loanId + ".pdf";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void setNumValues(){
        setConnect(new DBConnection());
        getConnect().init();
        
        try{
           String selectSql = "select principalAmount, "
                   + "loanLength, "
                   + "annualRate, "
                   + "currentDate, "
                   + "loanType from loan where loanId =" + getLoanId(); 
           
            setMyStmt(getConnect().getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setPrincipalAmount(getMyRs().getInt("principalAmount"));                
                setLoanLength(getMyRs().getInt("loanLength"));
                setAnnualRate(getMyRs().getInt("annualRate"));
                setCurrentDate(getMyRs().getDate("currentDate"));
                setLoanType(getMyRs().getString("loanType"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            getConnect().killConnections();
        }
    }
    public void setPersonValues(){
        setConnect(new DBConnection());
        getConnect().init();
        
        try{
           String selectSql = "select customerFirstName, "
                   + "customerLastName from customer where customerId =" + getCustomerId(); 
           
            setMyStmt(getConnect().getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setCustomerFirstName(getMyRs().getString("customerFirstName"));                
                setCustomerLastName(getMyRs().getString("customerLastname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            getConnect().killConnections();
        }
    }
    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the loanId
     */
    public int getLoanId() {
        return loanId;
    }

    /**
     * @param loanId the loanId to set
     */
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    /**
     * @return the connect
     */
    public DBConnection getConnect() {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(DBConnection connect) {
        this.connect = connect;
    }

    /**
     * @return the myStmt
     */
    public Statement getMyStmt() {
        return myStmt;
    }

    /**
     * @param myStmt the myStmt to set
     */
    public void setMyStmt(Statement myStmt) {
        this.myStmt = myStmt;
    }

    /**
     * @return the myRs
     */
    public ResultSet getMyRs() {
        return myRs;
    }

    /**
     * @param myRs the myRs to set
     */
    public void setMyRs(ResultSet myRs) {
        this.myRs = myRs;
    }

    /**
     * @return the customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * @param customerFirstName the customerFirstName to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * @return the customerLastName
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * @param customerLastName the customerLastName to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    /**
     * @return the principalAmount
     */
    public double getPrincipalAmount() {
        return principalAmount;
    }

    /**
     * @param principalAmount the principalAmount to set
     */
    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    /**
     * @return the annualRate
     */
    public double getAnnualRate() {
        return annualRate;
    }

    /**
     * @param annualRate the annualRate to set
     */
    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }

    /**
     * @return the loanLength
     */
    public double getLoanLength() {
        return loanLength;
    }

    /**
     * @param loanLength the loanLength to set
     */
    public void setLoanLength(double loanLength) {
        this.loanLength = loanLength;
    }

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

}
