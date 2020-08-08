
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : payment
    Created on : Aug 8, 2020, 3:00:20 PM
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
        <sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                                       url = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd;"
                                       />                    
                        
        <sql:query var="currentSelectedBalance" dataSource="${snapshot}">
                SELECT loanId, entryId, currentDate, customerId, currentTotal, singlePayment, loanLength, annualRate, PrincipalAmount FROM loan WHERE entryId=(Select MAX(entryId) from loan where loanId = ${newLoanId})
        </sql:query>  
                
                <table id="balance" border="1">
                        <!-- column headers -->
                        <tr>
                            <!--<c:forEach var="columnName" items="${currentSelectedBalance.columnNames}">
                                <th><c:out value="${columnName}"/></th>
                            </c:forEach>-->
                            <th>Loan ID</th>
                            <th>Entry ID</th>
                            <th>Date</th>                            
                            <th>Customer ID</th>
                            <th>Current Total</th>
                            <th>Single Payment</th>
                            <th>Loan Length Remaining</th>
                            <th>Annual Rate</th>
                            <th>Principal Amount</th>
                        </tr>
                        <!-- column data -->
                        <c:forEach var="row" items="${currentSelectedBalance.rowsByIndex}">
                            <tr>
                                <c:forEach var="column" items="${row}">
                                    <td><c:out value="${column}"/></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                    
                    
                    <form action="LoanPayment" method="post">
                            <div style="color: #FF0000;">${errorMessage}</div>
                            
                            <input type="text" placeholder="Enter payment amount" name="customerPaymentInput""/>
                            <input type="submit" value="Submit" name="paymentSubmitButton" />
                        </form>    
    </body>
</html>
