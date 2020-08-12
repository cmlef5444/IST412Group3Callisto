<%-- 
    Document   : loanApplication
    Created on : Aug 4, 2020, 9:30:03 AM
    Author     : Chris Lefebvre, Kristina Mantha
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
            input[type=text], select {
                width: 70%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type=submit] {
                width: 20%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type=submit]:hover {
                background-color: #45a049;
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
        <title>Callisto Finance Loan Application</title>
    </head>
    <body>
        <div class="topnav" id="myTopnav">
            <a href="http://localhost:8080/IST412Group3Callisto/index.html" class="active" style="background-color: black">Home</a>
            <a href="http://localhost:8080/IST412Group3Callisto/customerProfile.jsp">Customer Profile</a>
            <a href="http://localhost:8080/IST412Group3Callisto/loanBalance.jsp">Loan Balance</a>
            <a href="http://localhost:8080/IST412Group3Callisto/loanPayment.jsp">Loan Payment</a>
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
        <div align="center">
            <div class="container w3-blue-grey pt-4 bg-warning">
                <div class="form-row">  
                    <div class="form-group col-md-12">
                        <center> 
                            <br>
                            <center>
                                <h1 style="font-family: papyrus;">Loan Application</h1>
                            </center>
                            <br>
                        </center>
                    </div>
                </div>
                <div class="container w3-light-grey pt-4 bg-warning">
                    <div>
                        <form action="loanApplication" method="post">
                            <label for="customerFirstName">First Name</label>
                            <input type="text" id="customerFirstName" name="customerFirstName" placeholder="Your first name.." value="${customerFirstNameInput}"/>

                            <label for="customerLastName">Last Name</label>
                            <input type="text" id="customerLastName" name="customerLastName" placeholder="Your last name.." value="${customerLastNameInput}" />

                            <label for="customerAddress">Address</label>
                            <input type="text" id="customerAddress" name="customerAddress" placeholder="Your address.." value="${customerAddressInput}" /> 

                            <label for="customerPhoneNumber">Phone Number</label>
                            <input type="text" id="customerPhoneNumber" name="customerPhoneNumber" placeholder="Your phone number.." value="${customerPhoneNumberInput}" />  

                            <label for="customerEmail">Email</label>
                            <input type="text" id="customerEmail" name="customerEmail" placeholder="Your email.." value="${customerEmailInput}" /> 

                            <label for="loanAmt">Loan Amount</label>
                            <input type="text" id="loanAmt" name="loanAmt" placeholder="Loan amount.." value="${loanAmountInput}" />  

                            <label for="loanType">Type of Loan</label>
                            <select id="loanType" name="loanType" value="${loanTypeInput}" />
                            <option value="personal">Personal</option>
                            <option value="renovation">Renovation</option>
                            <option value="auto">Auto</option>
                            <option value="project">Project</option>
                            <option value="business">Business</option>
                            </select>

                            <label for="repay">Repayment Terms</label>
                            <select id="repay" name="repay" value="${repayInput}" />
                            <option value="6mos">6 Months</option>
                            <option value="12mos">12 Months</option>
                            <option value="24mos">24 Months</option>
                            <option value="36mos">36 Months</option>
                            <option value="48mos">48 Months</option>
                            </select>

                            <input type="submit" value="Submit">
                        </form>
                    </div>
                </div>
                <div class="container w3-blue-grey pt-3 bg-warning"></div>
            </div>
        </div>

    </body>
</html>