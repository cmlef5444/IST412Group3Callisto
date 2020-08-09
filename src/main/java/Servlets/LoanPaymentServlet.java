//<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Data.DBConnection;
import Data.LoanList;
import Payment.PaymentCntl;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Lefebvre
 */
public class LoanPaymentServlet extends HttpServlet {

    private int customerId;
    
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int loanOptionId;
    
    private double staticPrincipalAmount;
    private double staticCurrentTotal;
    private double staticLoanLength;
    private double staticAnnualRate;
    private double staticCompoundNum;
    private String staticCurrentDate;
    private String staticInitialDate;
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
        request.setAttribute("customerIdentification", getCustomerId());

        RequestDispatcher view = request.getRequestDispatcher("loanPayment.jsp");
        view.forward(request, response);
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
        if(request.getParameter("dropSubmit") != null){
            setLoanOptionId(Integer.parseInt(request.getParameter("loanOptions")));           
            System.out.println("The selected loan id is: " + getLoanOptionId());
            request.setAttribute("newLoanId", getLoanOptionId());

            PaymentCntl paymentCntl = new PaymentCntl();
            paymentCntl.getEntrySQL(getLoanOptionId());
            int entryId = paymentCntl.getEntryId();
            
            selectPaymentInfo(entryId);
            request.getSession().setAttribute("customerIdentification",customerId);
            request.getSession().setAttribute("newEntryId", entryId); 
            request.getSession().setAttribute("newLoanId", getLoanOptionId());
            request.getSession().setAttribute("currentTotalInput", getStaticCurrentTotal());
            request.getSession().setAttribute("principalAmountInput", getStaticPrincipalAmount() );
            request.getSession().setAttribute("loanLengthInput", getStaticLoanLength());
            request.getSession().setAttribute("currentDateInput", getStaticCurrentDate());
            request.getSession().setAttribute("initialDateInput", getStaticInitialDate()); 
            
            RequestDispatcher view = request.getRequestDispatcher("loanPayment.jsp");
            view.include(request, response);
        }
        else if(request.getParameter("paymentSubmitButton") != null){
                System.out.println("paymentSubmitButton Pressed");
                LoanList loanList = new LoanList();
                loanList.makePayment(getLoanOptionId(), getCustomerId(), Double.valueOf(request.getParameter("customerPaymentInput")));
                
                request.setAttribute("confirmationMessage", "Payment request recieved. Please give a few minutes to process payment."); 
                processRequest(request, response);
                }
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
    
    public void selectPaymentInfo(int entryId){
        setConnect(new DBConnection());
        getConnect().init();
        try{
           String selectSql = "select principalAmount, "
                   + "currentTotal, "
                   + "loanLength, "
                   + "annualRate, "
                   + "compoundNum, "
                   + "currentDate, "
                   + "initialDate from loan where entryId =" + entryId; 
           
            setMyStmt(getConnect().getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                setStaticCurrentTotal(getMyRs().getInt("currentTotal"));
                setStaticPrincipalAmount(getMyRs().getInt("principalAmount"));                
                setStaticLoanLength(getMyRs().getInt("loanLength"));
                setStaticAnnualRate(getMyRs().getInt("annualRate"));
                setStaticCompoundNum(getMyRs().getInt("compoundNum"));
                setStaticCurrentDate(getMyRs().getString("currentDate"));
                setStaticInitialDate(getMyRs().getString("initialDate"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            killConnections();
        }
    }
    public void killConnections(){
       try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if(getConnect().getMyConnection()!=null){
                        getConnect().closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
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
     * @return the staticPrincipalAmount
     */
    public double getStaticPrincipalAmount() {
        return staticPrincipalAmount;
    }

    /**
     * @param staticPrincipalAmount the staticPrincipalAmount to set
     */
    public void setStaticPrincipalAmount(double staticPrincipalAmount) {
        this.staticPrincipalAmount = staticPrincipalAmount;
    }

    /**
     * @return the staticCurrentTotal
     */
    public double getStaticCurrentTotal() {
        return staticCurrentTotal;
    }

    /**
     * @param staticCurrentTotal the staticCurrentTotal to set
     */
    public void setStaticCurrentTotal(double staticCurrentTotal) {
        this.staticCurrentTotal = staticCurrentTotal;
    }

    /**
     * @return the staticLoanLength
     */
    public double getStaticLoanLength() {
        return staticLoanLength;
    }

    /**
     * @param staticLoanLength the staticLoanLength to set
     */
    public void setStaticLoanLength(double staticLoanLength) {
        this.staticLoanLength = staticLoanLength;
    }

    /**
     * @return the staticAnnualRate
     */
    public double getStaticAnnualRate() {
        return staticAnnualRate;
    }

    /**
     * @param staticAnnualRate the staticAnnualRate to set
     */
    public void setStaticAnnualRate(double staticAnnualRate) {
        this.staticAnnualRate = staticAnnualRate;
    }

    /**
     * @return the staticCompoundNum
     */
    public double getStaticCompoundNum() {
        return staticCompoundNum;
    }

    /**
     * @param staticCompoundNum the staticCompoundNum to set
     */
    public void setStaticCompoundNum(double staticCompoundNum) {
        this.staticCompoundNum = staticCompoundNum;
    }

    /**
     * @return the staticInitialDate
     */
    public String getStaticInitialDate() {
        return staticInitialDate;
    }

    /**
     * @param staticInitialDate the staticInitialDate to set
     */
    public void setStaticInitialDate(String staticInitialDate) {
        this.staticInitialDate = staticInitialDate;
    }

    /**
     * @return the staticCurrentDate
     */
    public String getStaticCurrentDate() {
        return staticCurrentDate;
    }

    /**
     * @param staticCurrentDate the staticCurrentDate to set
     */
    public void setStaticCurrentDate(String staticCurrentDate) {
        this.staticCurrentDate = staticCurrentDate;
    }

    /**
     * @return the loanOptionId
     */
    public int getLoanOptionId() {
        return loanOptionId;
    }

    /**
     * @param loanOptionId the loanOptionId to set
     */
    public void setLoanOptionId(int loanOptionId) {
        this.loanOptionId = loanOptionId;
    }

}