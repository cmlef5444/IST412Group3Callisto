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
       
        <sql:query var="currentLoanBalances" dataSource="${snapshot}">
            SELECT loanId FROM loan WHERE customerId=${customerIdentification}
        </sql:query>
        
        
       

        
        <sql:query var="pastLoanBalances" dataSource="${snapshot}">
            SELECT entryId, currentDate, loanId, customerId, currentTotal, singlePayment, loanLength, annualRate, PrincipalAmount FROM loan WHERE customerId=${customerIdentification}
        </sql:query>
    
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
