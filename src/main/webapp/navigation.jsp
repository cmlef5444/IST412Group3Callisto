<%--
    Document   : navigation
    Created on : Jul 31, 2020, 3:59:34 PM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="stylesheet.css">
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
                text-align: left;
                font-size: 16px;
            }
            .grid-container{
                max-width: 800px;
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
        <title>Callisto Navigation</title>
    </head>
    <body>

        <div class="topnav" id="myTopnav">
            <a href="http://localhost:8080/IST412Group3Callisto/index.html" class="active" style="background-color: black">Home</a>
            <a href="#contact">Contact</a>
            <a href="#about">About</a>
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
    </div>
    <div align="center">
        <div class="container w3-blue-grey pt-4 bg-warning">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <center>
                        <br>
                        <center>
                            <h1 style="font-family: papyrus;">Make a Selection</h1>
                        </center>
                        <br>
                    </center>
                </div>
            </div>

            <div class="container w3-light-grey pt-4 bg-warning">

                <div class="grid-container">
                    <!--
         <center><a href="CustomerProfile">Click here for your Customer Profile page</a></center>
         <center><a href="LoanBalance">Click here for Loan Balance</a></center>
         <center><a href="LoanPayment">Click here for Loan Payment</a></center>
                    -->
                    <form action="Navigation" method="post">
                        <input type="submit" name="custProfile" value="Customer Profile" />
                        <input type="submit" name="loanBalance" value="Loan Balance" />
                        <input type="submit" name="loanPayment" value="Loan Payment" />
                        <input type="submit" name="loanApplication" value="Loan Application" />
                    </form>
                </div>

                <br>
            </div>
            <div class="container w3-blue-grey pt-3 bg-warning"></div>
        </div>
    </div>
</body>
</html>
