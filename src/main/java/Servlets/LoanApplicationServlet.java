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
        if(request.getParameter("dropSubmit") != null){
    
            
        
        response.setContentType("text/html;charset=UTF-8");
        customerId = (int) request.getSession().getAttribute("customerId");
        System.out.println("doGet customerId: " + customerId);
        if (request.getParameter("customerProfile") != null) {
            request.getSession().setAttribute("customerId", customerId);
            response.sendRedirect(request.getContextPath() + "/CustomerProfile");
        } else if (request.getParameter("loanPayment") != null) {
            request.getSession().setAttribute("customerId", customerId);
            response.sendRedirect(request.getContextPath() + "/LoanPayment");
        } else if (request.getParameter("loanBalance") != null) {
            request.getSession().setAttribute("customerId", customerId);
            response.sendRedirect(request.getContextPath() + "/LoanBalance");
        }
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
}
