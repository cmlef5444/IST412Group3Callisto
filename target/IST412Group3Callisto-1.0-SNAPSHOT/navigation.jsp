<%-- 
    Document   : navigation
    Created on : Jul 31, 2020, 3:59:34 PM
    Author     : cjani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is the navigation page! Update 2</h1>
       <!--
        <center><a href="CustomerProfile">Click here for your Customer Profile page</a></center>
        <center><a href="LoanBalance">Click here for Loan Balance</a></center>
        <center><a href="LoanPayment">Click here for Loan Payment</a></center>
        -->
        <form action="Navigation" method="post">
            <input type="submit" name="Customer Profile" value="customerProfileButton" />
            <input type="submit" name="Loan Balance" value="loanBalanceButton" />
            <input type="submit" name="Loan Payment" value="loanPaymentButton" />
        </form>
    </body>
</html>
