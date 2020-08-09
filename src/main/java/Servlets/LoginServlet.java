/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris Lefebvre
 */

public class LoginServlet extends HttpServlet {

    HttpSession session;
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        
        response.setContentType("text/html");  
        
        if(request.getParameter("loginButton") != null){
            PrintWriter out = response.getWriter();  

            System.out.println("Login button pressed");

                String email = request.getParameter("customerEmail");
                String password = request.getParameter("customerPassword");

                Login.LoginCntl loginCntl = new Login.LoginCntl();
                if(loginCntl.authenticator(email, password)){
                    System.out.println("LoginServlet: authenticator passed");
                    request.getSession().setAttribute("customerId",loginCntl.setupCurrentUser(email, password));
                    response.sendRedirect(request.getContextPath() + "/Navigation");
                }
                else{
                    request.setAttribute("errorMessage", "Email or Password is incorrect.");
                    processRequest(request, response);
                } 
           out.close();                
        }
        else if(request.getParameter("registerButton") != null)
        {
            response.sendRedirect(request.getContextPath() + "/Registration");
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
