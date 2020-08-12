/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Data.DBConnection;
import Data.ErrorChecks;
import Register.RegisterCntl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
public class RegistrationServlet extends HttpServlet {

    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerPassword;

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
        
        RequestDispatcher view = request.getRequestDispatcher("registration.jsp");
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

            setValues(request);
            ErrorChecks errorChecks = new ErrorChecks();
            String password = request.getParameter("customerPasswordInput1");
            String password2 = request.getParameter("customerPasswordInput2");
            if(request.getAttribute("registrationButton") != null){
                if(errorChecks.isValidName(request.getParameter("customerFirstNameInput"))|| errorChecks.isValidName(request.getParameter("customerLastNameInput"))){
                    request.setAttribute("errorMessageName", "Names may not have any numbers or special characters save - and '.");
                }
                if (errorChecks.isValidAddress(request.getParameter("customerAddressInput"))) {
                    request.setAttribute("errorMessageAddress", "Addresses may not have any special characters save - and '.");
                }
                if (!errorChecks.isValidPhoneNumber(request.getParameter("customerPhoneNumberInput"))) {
                    request.setAttribute("errorMessagePhoneNumber", "Phone number must be in the following format 123-456-7890");
                }
                if (!errorChecks.isValidEmail(request.getParameter("customerEmailInput"))) {
                    request.setAttribute("errorMessageEmail", "Your email must include an @ and a domain name (.com, .net, .edu, etc.).");
                }
                if (errorChecks.isValidPassword(password) || errorChecks.isValidPassword(password2)) {
                    if (!password.equals(password2)) {//pass
                        request.setAttribute("errorMessagePassword", "The passwords must match.");
                    }                    
                }
                else{
                    RegisterCntl registerCntl = new RegisterCntl();
                    registerCntl.registerCustomer(getCustomerFirstName(),
                            getCustomerLastName(),
                            getCustomerEmail(),
                            getCustomerAddress(),
                            getCustomerEmail(),
                            getCustomerPassword());

                    processRequest(request, response);                
                }                
        }
    }
    
    public void setValues(HttpServletRequest request){
            setCustomerFirstName(request.getParameter("customerFirstNameInput"));
            setCustomerLastName(request.getParameter("customerLastNameInput"));
            setCustomerEmail(request.getParameter("customerEmailInput"));
            setCustomerAddress(request.getParameter("customerAddressInput"));
            setCustomerPhoneNumber(request.getParameter("customerPhoneNumberInput"));
            setCustomerPassword(request.getParameter("customerPasswordInput2"));   
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
     * @return the customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * @param customerEmail the customerEmail to set
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @param customerPhoneNumber the customerPhoneNumber to set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * @return the customerPassword
     */
    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * @param customerPassword the customerPassword to set
     */
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

}
