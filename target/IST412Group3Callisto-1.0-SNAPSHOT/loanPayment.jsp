
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <h1>Hello World!</h1>
        
        <sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                                       url = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd;"
                                       />   
        <sql:query var="currentBalancesLoan" dataSource="${snapshot}">
                SELECT t.loanId from (SELECT entryId, loanId, ROW_NUMBER() OVER (PARTITION BY loanId ORDER BY entryId DESC) row_num FROM loan WHERE customerId =${customerIdentification})t WHERE t.row_num = 1
        </sql:query>
                

        
                    <!--, t.entryId -->    
                        <form action ="LoanPayment" method="post" onSubmit="JavaScript:ExampleJS()">>
                            <label for =" loanLabel">Select your Loan id:</label>
                            <select name ="loanOptions" id=""loans">
                                <c:forEach var="row" items="${currentBalancesLoan.rowsByIndex}">
                                    <option> <c:out value ="${row[0]}"/> </option>
                                </c:forEach>
                            </select>
                            <br/><br/>
                            <input type="submit" name="dropSubmit" value="Submit"/>

                        </form>    
                            <div style="color: #FF0000;">${errorMessage}</div>
                            <div style="color: #078417;">${confirmationMessage}</div>
                            <label for="entryIdInput">Entry ID</label>
                            <input type="text" name="entryIdInput" value="${newEntryId}" readonly="readonly"/>
                            <label for="loanIdInput">Loan ID</label>
                            <input type="text" name="loanIdInput" value="${newLoanId}" readonly="readonly"/>
                            <label for="currentTotalInput">Loan Remaining</label>
                            <input type="text" name="currentTotalInput" value="${currentTotalInput}" readonly="readonly"/>
                            <label for="principalAmountInput">Principal Amount</label>
                            <input type="text" name="principalAmountInput" value="${principalAmountInput}" readonly="readonly"/>
                            <label for="loanLengthInput">Length of Loan</label>
                            <input type="text" name="loanLengthInput" value="${loanLengthInput}" readonly="readonly"/>
                            <label for="currentDateInput">Date of Payment</label>
                            <input type="text" name="currentDateInput" value="${currentDateInput}" readonly="readonly"/>
                            <label for="initialDateInput">Initial Date</label>
                            <input type="text" name="initialDateInput" value="${initialDateInput}" readonly="readonly"/>
                    
                    
                    
                    
                    <form action="LoanPayment" method="post">
                            <div style="color: #FF0000;">${errorMessage}</div>
                            
                            <input type="text" placeholder="Enter payment amount" name="customerPaymentInput""/>
                            <input type="submit" value="Submit" name="paymentSubmitButton" />
                        </form>    
        
                    
    
 
        
        
    </body>
</html>
