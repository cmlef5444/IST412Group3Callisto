
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%--
    Document   : loanPayment
    Created on : Aug 4, 2020, 9:30:37 AM
    Author     : cjani, kajunge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                width: 70%;
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
            label
            {
                float: left;
                width: 10em;
                margin-right: 1em;
            }
            .topnav {
                overflow: hidden;
                background-color: #FFFFFF;
                border-color: black;
            }

            .topnav a {
                float: left;
                display: block;
                color: white;
                background-color: lightslategrey;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
                border-color: black;
            }

            .topnav a:hover {
                background-color: lightsteelblue;
                color: black;
            }

            .topnav a.active {
                background-color: lightslategrey;
                color: white;
            }

            .topnav .icon {
                display: none;
            }
            @media screen and (max-width: 800px) {
                .topnav a:not(:first-child) {display: none;}
                .topnav a.icon {
                    float: right;
                    display: block;
                }
            }

            @media screen and (max-width: 800px) {
                .topnav.responsive {position: relative;}
                .topnav.responsive .icon {
                    position: absolute;
                    right: 0;
                    top: 0;
                }
                .topnav.responsive a {
                    float: none;
                    display: block;
                    text-align: left;
                }
            }
        </style>
        <title>Loan Payment</title>

    </head>
    <body>
        <div class="topnav" id="myTopnav">
            <a href="http://localhost:8080/IST412Group3Callisto/index.html" class="active" style="background-color: black">Logout</a>
            <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                <i class="fa fa-bars"></i>
            </a>
        </div>
        <script>
            function myFunction() {
                var x = document.getElementById("myTopnav");
                if (x.className === "topnav") {
                    x.className += " responsive";
                } else {
                    x.className = "topnav";
                }
            }
        </script>
        <sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd;"
                           />
        <sql:query var="currentBalancesLoan" dataSource="${snapshot}">
            SELECT t.loanId from (SELECT entryId, loanId, ROW_NUMBER() OVER (PARTITION BY loanId ORDER BY entryId DESC) row_num FROM loan WHERE customerId =${customerIdentification})t WHERE t.row_num = 1
        </sql:query>
        <div align="center">
            <div class="container w3-blue-grey pt-4 bg-warning">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <center>
                            <br>
                            <center>
                                <h1 style="font-family: papyrus;">Loan Payment</h1>
                            </center>
                            <br>
                        </center>
                    </div>
                </div>
                <div class="container w3-light-grey pt-4 bg-warning">
                    <div>
                        <div class="grid-container">
                            <!--, t.entryId -->
                            <form action ="LoanPayment" method="post" onSubmit="JavaScript:ExampleJS()">
                                <label for =" loanLabel">Select your Loan id:</label>
                                <select name ="loanOptions" id=""loans">
                                        <c:forEach var="row" items="${currentBalancesLoan.rowsByIndex}">
                                            <option> <c:out value ="${row[0]}"/> </option>
                                        </c:forEach>
                                </select>
                                <br/><br/>
                                <input type="submit" name="dropSubmit" value="Submit"/>
                            </form>
                        </div>

                        <div class="grid-container">
                            <label for="amountDueInput">Amount Due</label>
                            <input type="text" name="amountDueInput" value="${amountDue}" readonly="readonly"/>
                            <label for="entryIdInput">Entry ID</label>
                            <br/><br/>
                            <input type="text" name="entryIdInput" value="${newEntryId}" readonly="readonly"/>
                            <label for="loanIdInput">Loan ID</label>
                            <input type="text" name="loanIdInput" value="${newLoanId}" readonly="readonly"/>
                            <label for="currentTotalInput">Loan Remaining</label>
                            <input type="text" name="currentTotalInput" value="${currentTotalInput}" readonly="readonly"/>
                            <label for="principalAmountInput">Principal Amount</label>
                            <input type="text" name="principalAmountInput" value="${principalAmountInput}" readonly="readonly"/>
                            <label for="loanLengthInput">Months remaining in Loan</label>
                            <input type="text" name="loanLengthInput" value="${loanLengthInput}" readonly="readonly"/>
                            <label for="currentDateInput">Date of Payment</label>
                            <input type="text" name="currentDateInput" value="${currentDateInput}" readonly="readonly"/>
                            <label for="initialDateInput">Initial Date</label>
                            <input type="text" name="initialDateInput" value="${initialDateInput}" readonly="readonly"/>
                            </form>
                        </div>
                        <div class="grid-container">
                            <form action="LoanPayment" method="post">
                                <div style="color: #FF0000;">${errorMessage}</div>

                                <input type="text" placeholder="Enter payment amount" name="customerPaymentInput""/>
                                <input type="submit" value="Submit" name="paymentSubmitButton" />
                                <div style="color: #FF0000;">${errorMessage}</div>
                                <div style="color: #078417;">${confirmationMessage}</div>
                            </form>
                        </div>
                    </div>
                    <br>
                    <div>
                        <form action="LoanPayment" method="post">
                            <input type="submit" name="loanApplication" value="Loan Application" disabled/>
                            <input type="submit" name="customerProfile" value="Customer Profile" />
                            <input type="submit" name="loanBalance" value="Loan Balance" />
                        </form>
                    </div>
                </div>
                <div class="container w3-blue-grey pt-3 bg-warning"></div>
            </div>
        </div>
    </body>
</html>
