<<<<<<< HEAD
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : loanBalance
    Created on : Aug 4, 2020, 9:30:22 AM
    Author     : cjani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Table needs name for table, need spot for balance for loans</h1>
        
        <sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
         url = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd;"
        />
        
        
    
        <table border="1">
            <!-- column headers -->
            <tr>
                <!--<c:forEach var="columnName" items="${pastLoanBalances.columnNames}">
                    <th><c:out value="${columnName}"/></th>
                    </c:forEach>-->
                    <th>Entry ID</th>
                    <th>Date</th>
                    <th>Loan ID</th>
                    <th>Customer ID</th>
                    <th>Current Total</th>
                    <th>Single Payment</th>
                    <th>Loan Length Remaining</th>
                    <th>Annual Rate</th>
                    <th>Principal Amount</th>
            </tr>
            <!-- column data -->
            <c:forEach var="row" items="${pastLoanBalances.rowsByIndex}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td><c:out value="${column}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
   

        
        
    </body>
</html>
=======
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : loanBalance
    Created on : Aug 4, 2020, 9:30:22 AM
    Author     : cjani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

        <link rel="stylesheet" href="stylesheet.css">
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            form {border: 3px solid #f1f1f1;}

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
            .grid-container {
                display: grid;
                grid-template-columns: auto auto;
                grid-gap: 5px;
                background-color: rgb(240,240,240);
                padding: 80px;
            }

            .grid-container > div {
                background-color: rgba(240,240,240, 1.0);
                border: 2px dark black;
                border: 1px solid #fff;
                text-align: left;
                font-size: 16px;
            }
            .grid-container{
                max-width: 800px;
                border: 2px solid #B0B0B0;
            }
            #balance {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #balance td, #balance th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #balance tr:nth-child(even){background-color: #f2f2f2;}

            #balance tr:hover {background-color: #ddd;}

            #balance th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #5c6670;
                color: white;
            }
        </style>
        <title>Loan Balance</title>
    </head>
    <body>
        <div align="center">
            <div class="container w3-blue-grey pt-4 bg-warning">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <center> 
                            <br>
                            <center>
                                <h1 style="font-family: papyrus;">Current Loan Balance</h1>
                            </center>
                            <br>
                        </center>
                    </div>
                </div>
                <div class="container w3-light-grey pt-4 bg-warning">
                    <div class="grid-container">
                        <!--<table border="1">
                            <thead>
                            <tr>
                                <th>Entry ID</th>
                   <th>Date</th>
                   <th>Loan ID</th>
                   <th>Customer ID</th>
                   <th>Current Total</th>
                   <th>Single Payment</th>
                   <th>Loan Length Remaining</th>
                   <th>Annual Rate</th>
                   <th>Principal Amount</th>
               </tr>
           </thead>
           <tbody>
               <tr>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
               </tr>              
           </tbody>
       </table>-->

                        <table id="balance" border="1">
                            <!-- column headers -->
                            <tr>
                                <!--<c:forEach var="columnName" items="${pastLoanBalances.columnNames}">
                                    <th><c:out value="${columnName}"/></th>
                                </c:forEach>-->
                                <th>Entry ID</th>
                                <th>Date</th>
                                <th>Loan ID</th>
                                <th>Customer ID</th>
                                <th>Current Total</th>
                                <th>Single Payment</th>
                                <th>Loan Length Remaining</th>
                                <th>Annual Rate</th>
                                <th>Principal Amount</th>
                            </tr>
                            <!-- column data -->
                            <c:forEach var="row" items="${pastLoanBalances.rowsByIndex}">
                                <tr>
                                    <c:forEach var="column" items="${row}">
                                        <td><c:out value="${column}"/></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="container w3-blue-grey pt-3 bg-warning"></div> 
            </div>
        </div>
    </body>
</html>
>>>>>>> 1426bbe715428f07dbe2899531e93e4a3dc8d476
