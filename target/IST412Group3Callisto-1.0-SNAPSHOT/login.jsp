<%-- 
    Document   : login
    Created on : Jul 31, 2020, 12:58:39 PM
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

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            @import "bourbon";

            body {
                background: #eee !important;	
            }

            .wrapper {	
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .form-signin {
                max-width: 380px;
                padding: 15px 35px 45px;
                margin: 0 auto;
                background-color: #fff;
                border: 1px solid rgba(0,0,0,0.1);  

                .form-signin-heading,
                .checkbox {
                    margin-bottom: 30px;
                }

                .checkbox {
                    font-weight: normal;
                }

                .form-control {
                    position: relative;
                    font-size: 16px;
                    height: auto;
                    padding: 10px;
                    @include box-sizing(border-box);

                    &:focus {
                        z-index: 2;
                    }
                }

                input[type="text"] {
                    margin-bottom: -1px;
                    border-bottom-left-radius: 0;
                    border-bottom-right-radius: 0;
                }

                input[type="password"] {
                    margin-bottom: 20px;
                    border-top-left-radius: 0;
                    border-top-right-radius: 0;
                }
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
    </head>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div align="center">
            <div class="container w3-blue-grey pt-4 bg-warning">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <center> 
                            <br>
                            <center>
                                <center><h2 style="font-family: papyrus;">Welcome to Callisto Finance</h2></center>
                            </center>
                            <br>
                        </center>
                    </div>
                </div>
                <div class="container w3-light-grey pt-4 bg-warning">

                    <div class="grid-container">    
                        <div class="wrapper">
                            <form class="form-signin" action="Login" method="post" onSubmit = "return > <div class="container">
                                <h2 class="form-signin-heading">Please login</h2>
                                <div style="color: #FF0000;">${errorMessage}</div>
                                <input type="text" class="form-control" name="customerEmail" placeholder="Email Address" required="" autofocus="" />
                                <input type="password" class="form-control" name="customerPassword" placeholder="Password" required=""/>      
                                <label class="checkbox">
                                    <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
                                </label>
                                <button class="btn btn-lg btn-primary btn-block" name = "loginButton" type="submit">Login</button>   
                                <button class="btn btn-lg btn-primary btn-block" name = "registerButton" type="submit" formnovalidate >Register</button>                                    
                            </form>
                            <br>
                            <br>
                        </div>                            
                    </div>            
                </div>
                <div class="container w3-blue-grey pt-3 bg-warning"></div>
            </div>
        </div>
    </div>
</body>
</html>

