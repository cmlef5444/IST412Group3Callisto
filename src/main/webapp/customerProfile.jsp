<%-- 
    Document   : customerProfile
    Created on : Jul 31, 2020, 4:26:56 PM
    Author     : cjani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="CustomerProfile" method="post">
            <div style="color: #FF0000;">${errorMessage}</div>
        <input type="text" name="customerFirstNameInput" value="${customerFirstNameInput}"/>
        <input type="text" name="customerLastNameInput" value="${customerLastNameInput}" />
        <input type="text" name="customerAddressInput" value="${customerAddressInput}" />
        <input type="text" name="customerPhoneNumberInput" value="${customerPhoneNumberInput}" />
        <input type="submit" value="Submit" name="personInfoSubmitButton" />
        </form>
        <form>
        <input type="text" name="customerEmail" value="${customerEmailInput}" />
        <input type="submit" value="Submit" name="emailSubmitButton" />
        </form>
        <form>
        <input type="text" name="customerPasswordInput1" value="" />
        <input type="text" name="customerPasswordInput2" value="" />
        <input type="submit" value="Submit" name="passwordSubmitButton" />
        </form>
    </body>
</html>
