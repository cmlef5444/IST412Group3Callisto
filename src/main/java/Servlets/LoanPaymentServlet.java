/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Data.DBConnection;
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
 */
public class LoanPaymentServlet extends HttpServlet {

    int customerId;
    DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
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
        request.setAttribute("customerIdentification", customerId);
//        
//        String selectSql = "SELECT loanId, entryId from"
//                            + "(SELECT entryId, loanId, "
//                            + "ROW_NUMBER() OVER (PARTITION BY "
//                            + "loanId ORDER BY entryId DESC) "
//                            + "row_num FROM loan) table "
//                            + "WHERE row_num = 1, customerId = " + customerId;
//        
//        connect = new DBConnection();
//        connect.init();
        RequestDispatcher view = request.getRequestDispatcher("loanPayment.jsp");
        view.forward(request, response);
        
        //SELECT entryId, currentDate, loanId, customerId, currentTotal, singlePayment, loanLength, annualRate, PrincipalAmount FROM loan WHERE customerId=${customerIdentification}
        
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
            int loanOptionId = Integer.parseInt(request.getParameter("loanOptions"));           
            System.out.println("The selected loan id is: " + loanOptionId);
            request.setAttribute("newLoanId", loanOptionId);
            request.getRequestDispatcher("payment.jsp").include(request, response);
            //int entryId = Integer.parseInt(request.getParameter("currentBalancesEntry"));
            //System.out.println("The selected entry id is: " + entryId);
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

}

//
// <form action ="/loanPayment.jsp">
//                            <label for =" loas">Select your loan:</label>
//                            <select name ="loans">
//                                <c:forEach var="row" items="${currentBalances.rowsByIndex}">
//                                    <option> value ="${curentBalances}"> ${currentBalances}</option>
//                                </c:forEach>
//                            </select>
//                        </form>         


//<sql:query var="currentBalancesEntry" dataSource="${snapshot}">
//                SELECT t.entryId from (SELECT entryId, loanId, ROW_NUMBER() OVER (PARTITION BY entryId ORDER BY loanId DESC) row_num FROM loan WHERE loanId =${newLoanId})t WHERE t.row_num = 1
//        </sql:query> 
