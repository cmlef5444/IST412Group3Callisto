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
       <!-- <jsp:include page="/customerProfile.jsp"/>
    <c:out value ="${customerFirstNameInput}"></c:out>-->
        <h1>Hello World!</h1>
        <form>
        <input type="text" name="customerFirstNameInput" value="${customerFirstNameInput}"/>
        <input type="text" name="customerLastNameInput" value="customerLastNameInput" />
        <input type="text" name="customerAddressInput" value="customerAddressInput" />
        <input type="text" name="customerPhoneNumberInput" value="customerPhoneNumberInput" />
        <input type="submit" value="Submit" name="personInfoSubmitButton" />
        </form>
        <form>
        <input type="text" name="customerEmail" value="customerEmailInput" />
        <input type="submit" value="Submit" name="emailSubmitButton" />
        </form>
        <form>
        <input type="text" name="customerPasswordInput1" value="customerPasswordInput1" />
        <input type="text" name="customerPasswordInput2" value="customerPasswordInput2" />
        <input type="submit" value="Submit" name="passwordSubmitButton" />
        </form>
    </body>
</html>
