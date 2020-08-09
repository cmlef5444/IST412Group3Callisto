/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import CustomerProfile.CustomerProfileCntl;
import Data.DBConnection;
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
 */
public class CustomerProfileServlet extends HttpServlet {

    int customerId;
    DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    String firstName, lastName, email, password, password2, address, phoneNumber;
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
        customerId = (int)request.getSession().getAttribute("customerId");
        System.out.println("CPS customerId: " + customerId);
        String selectSql = "select customerFirstName, "
                + "customerLastName, "
                + "customerAddress, "
                + "customerPhoneNumber, "
                + "customerEmail "
                + "from customer "
                + "where customerId = " + customerId;
        connect = new DBConnection();
        connect.init();
        
        try{            
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));            
            while(getMyRs().next()){
                request.setAttribute("customerFirstNameInput", getMyRs().getString("customerFirstName"));
                request.setAttribute("customerLastNameInput", getMyRs().getString("customerlastName") );
                request.setAttribute("customerAddressInput", getMyRs().getString("customerAddress"));
                request.setAttribute("customerPhoneNumberInput", getMyRs().getString("customerPhoneNumber"));
                request.setAttribute("customerEmailInput", getMyRs().getString("customerEmail")); 
            }        
        }catch(Exception e){
            e.printStackTrace();
        }
         finally{
            killConnections();           
        }              
        RequestDispatcher view = request.getRequestDispatcher("customerProfile.jsp");
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
        
        if(request.getParameter("personInfoSubmitButton") != null){                 
            if(request.getParameter("personInfoSubmitButton") != null){//Replace with correct error checks and pass conditions
                processPersonalInfo(request, response);  
            }
            else{//if it fails checks
                request.setAttribute("errorMessage", "Please program correct error checks and pass conditions"); 
            }
            processRequest(request, response);
            //request.getRequestDispatcher("/customerProfile.jsp").forward(request, response);
        }
        else if(request.getParameter("emailSubmitButton") != null){    
            processEmail(request, response);                  
            if(request.getParameter("emailSubmitButton") != null){//Replace with correct error conditions
             
            }
            else{
                  request.setAttribute("errorMessage", "Please program correct error checks and pass conditions");
            }
            processRequest(request, response);
            //request.getRequestDispatcher("/customerProfile.jsp").forward(request, response);
        }
        else if(request.getParameter("passwordSubmitButton") != null){                  
            password = request.getParameter("customerPasswordInput1");
            password2 = request.getParameter("customerPasswordInput2");
            if(password.equals(password2)){
                processPassword(request, response);  
//                if(request.getParameter("passwordSubmitButton") != null){//Replace with correct error conditions
//                     
//                }
//                else{
//                    request.setAttribute("errorMessage", "Please program correct error checks and pass conditions");
//                }                
            }
            else{
                request.setAttribute("errorMessage", "The passwords must match");
            }
            processRequest(request, response);
            //request.getRequestDispatcher("/customerProfile.jsp").forward(request, response);    
        }
    }
    
    public void processPersonalInfo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Testing processPersonalInfo(): ");
        connect = new DBConnection();
        connect.init();

        try{
            String selectSql = "select customerEmail, "
                + "customerPassword "
                + "from customer "
                + "where customerId = " + customerId;
            
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
        while(getMyRs().next()){
           email = getMyRs().getString("customerEmail");
           password = getMyRs().getString("customerPassword");
        }    
        }catch(Exception e){
            e.printStackTrace();
        }
         finally{
            killConnections();
        }
        System.out.println("ProcessPersonInfo Email: " + email + " password: " + password);
        CustomerProfileCntl customerProfileCntl = new CustomerProfileCntl();
        customerProfileCntl.editCustomer(email, 
                password, 
                customerId, 
                request.getParameter("customerFirstNameInput"),
                request.getParameter("customerLastNameInput"), 
                request.getParameter("customerAddressInput"), 
                request.getParameter("customerPhoneNumberInput"));
    }
public void processEmail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Testing processPersonalInfo(): ");
        connect = new DBConnection();
        connect.init();

        try{
            String selectSql = "select customerFirstName, "
                + "customerLastName, "
                + "customerAddress, "
                + "customerPhoneNumber, "
                + "customerPassword "
                + "from customer "
                + "where customerId = " + customerId;
            
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
        while(getMyRs().next()){
           firstName = getMyRs().getString("customerFirstName");
           lastName = getMyRs().getString("customerLastName");
           address = getMyRs().getString("customerAddress");
           phoneNumber = getMyRs().getString("customerPhoneNumber");
           password = getMyRs().getString("customerPassword");
        }    
        }catch(Exception e){
            e.printStackTrace();
        }
         finally{
            killConnections();
        }
        System.out.println("ProcessPersonInfo Email: " + email + " password: " + password);
        CustomerProfileCntl customerProfileCntl = new CustomerProfileCntl();
        customerProfileCntl.editCustomer(request.getParameter("customerEmailInput"), 
                password, 
                customerId, 
                firstName,
                lastName, 
                address, 
                phoneNumber);
    }
public void processPassword(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Testing processPersonalInfo(): ");
        connect = new DBConnection();
        connect.init();

        try{
            String selectSql = "select customerFirstName, "
                + "customerLastName, "
                + "customerAddress, "
                + "customerPhoneNumber, "
                + "customerEmail "
                + "from customer "
                + "where customerId = " + customerId;
            
            setMyStmt(connect.getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
        while(getMyRs().next()){
           firstName = getMyRs().getString("customerFirstName");
           lastName = getMyRs().getString("customerLastName");
           address = getMyRs().getString("customerAddress");
           phoneNumber = getMyRs().getString("customerPhoneNumber");
           email = getMyRs().getString("customerEmail");
        }    
        }catch(Exception e){
            e.printStackTrace();
        }
         finally{
            killConnections();
        }
        System.out.println("ProcessPersonInfo Email: " + email + " password: " + password);
        CustomerProfileCntl customerProfileCntl = new CustomerProfileCntl();
        customerProfileCntl.editCustomer(email, 
                request.getParameter("customerPasswordInput1"), 
                customerId, 
                firstName,
                lastName, 
                address, 
                phoneNumber);
    }
    public void killConnections(){
       try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if(connect.getMyConnection()!=null){
                        connect.closeMyConnection();
                }
            }catch(SQLException e){
                e.printStackTrace();
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

}