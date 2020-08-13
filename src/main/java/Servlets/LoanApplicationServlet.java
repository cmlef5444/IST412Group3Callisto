/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import Data.DBConnection;
import Data.LoanList;
import LoanApplication.LoanApplicationCntl;
import LoanApplication.PdfGenerator;
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
 * @author cjani
 * @author kajunge
 */
public class LoanApplicationServlet extends HttpServlet {

    private int customerId;
    private int loanId;
    
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private int loanOptionId;
    
    private String firstName;
    private String lastName;
    
    private double principalAmount;
    private double loanLength;
    private double annualRate;
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
        request.setAttribute("customerIdentification", getCustomerId());
        RequestDispatcher view = request.getRequestDispatcher("loanApplication.jsp");
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
        response.setContentType("text/html;charset=UTF-8");
        setCustomerId((int) request.getSession().getAttribute("customerId"));
        System.out.println("doGet customerId: " + getCustomerId());
        
        if(request.getParameter("loanAppSubmitButton") != null){
            System.out.println("LoanAppSubmitButton Pressed");
            processLoanApplication(request, response);
            request.getSession().setAttribute("loanId", getLoanId());
            response.sendRedirect(request.getContextPath() + "/PdfGenerator");
            //processRequest(request, response);
        }       
        else if (request.getParameter("customerProfile") != null) {
            request.getSession().setAttribute("customerId", getCustomerId());
            response.sendRedirect(request.getContextPath() + "/CustomerProfile");
        } else if (request.getParameter("loanPayment") != null) {
            request.getSession().setAttribute("customerId", getCustomerId());
            response.sendRedirect(request.getContextPath() + "/LoanPayment");
        } else if (request.getParameter("loanBalance") != null) {
            request.getSession().setAttribute("customerId", getCustomerId());
            response.sendRedirect(request.getContextPath() + "/LoanBalance");
        }
    }
    public void processLoanApplication(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Testing processLoanApplication(): ");
        setConnect(new DBConnection());
        getConnect().init();
        
        LoanApplicationCntl loanApplicationCntl = new LoanApplicationCntl();
        
        try {            
            loanApplicationCntl.createLoan(getCustomerId(), 
                    Double.valueOf(request.getParameter("principalAmountInput")), 
                    Double.valueOf(request.getParameter("principalAmountInput")), //CurrentTotal
                    Double.valueOf(request.getParameter("loanLength")),
                    2.0,    //Annual Rate
                    0,
                    request.getParameter("loanType")); //Single Payment
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getConnect().killConnections();
        }
        setupCustomerInfo();
        setupLoanInfo(request);
        setLoanId(loanApplicationCntl.getNewLoanId());
        
        
    }
    public void setupCustomerInfo(){
        System.out.println("Testing setupCustomerInfo(): ");
        setConnect(new DBConnection());
        getConnect().init();
        
        try {
            String selectSql = "select customerFirstName, "
                    + "customerLastName "
                    + "from customer "
                    + "where customerId = " + getCustomerId();

            setMyStmt(getConnect().getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));

            while (getMyRs().next()) {
                setFirstName(getMyRs().getString("customerFirstName"));
                setLastName(getMyRs().getString("customerLastName"));
            }
            } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getConnect().killConnections();
        }
    }
    public void setupLoanInfo(HttpServletRequest request){
        setPrincipalAmount((double) Double.valueOf(request.getParameter("principalAmountInput")));
        setLoanLength((double) Double.valueOf(request.getParameter("loanLength")));
        setLoanType(request.getParameter("loanType"));
        System.out.println("Parameter loanType: " + request.getParameter("loanType"));
        setAnnualRate(2.0);
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

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
