/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;

/**
 *
 * @author Chris Lefebvre
 */
public class DBConnection {
    
    private Connection myConnection;
    private Statement myStmt;
    private ResultSet myRs;
    //Azure database info
    //===============================================
        String host = "ist412group3server.database.windows.net:1433";
        String database = "Callisto";
        String user = "azureuser@ist412group3server";
        String password = "IST412Pa$$w0rd";
    //==================================================
    //Azure connection Strings
//   jdbc:sqlserver://ist412group3server.database.windows.net:1433;
//        database=Callisto;
//        user=azureuser@ist412group3server;
//        password=IST412Pa$$w0rd;
//        encrypt=true;
//        trustServerCertificate=false;
//        hostNameInCertificate=*.database.windows.net;
//        loginTimeout=30;
        //========================================================
    
    public DBConnection(){
        
    }
    
    public void init(){
        try{
            String connectionUrl = "jdbc:sqlserver://ist412group3server.database.windows.net:1433;databaseName=Callisto;user=azureuser@ist412group3server;password=IST412Pa$$w0rd";
            String selectSql = "select * from customer";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            setMyConnection(DriverManager.getConnection(connectionUrl));
        }catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
     public void killConnections(){
       try{
                if (getMyRs() != null){
                    getMyRs().close();
                }
                if( getMyStmt() != null){
                        getMyStmt().close();
                }
                if(getMyConnection()!=null){
                        getMyConnection().close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
//==============================================================================
//check() Methods
//==============================================================================
    public void checkCustomers(){
        try{
            String selectSql = "select * from customer";

            setMyStmt(getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                System.out.println(getMyRs().getString("customerId") + ", " + getMyRs().getString("customerLastName") + ", " + getMyRs().getString("customerFirstName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            killConnections();
        }               
		System.out.println("Execution finished.");
    }
    public void checkLoans(){
        try{
            String selectSql = "select * from loan";
           
            setMyStmt(getMyConnection().createStatement());
            setMyRs(getMyStmt().executeQuery(selectSql));
            
            while (getMyRs().next()){
                System.out.println(getMyRs().getString("loanId") + ", " + getMyRs().getString("customerId") + ", " + getMyRs().getString("principalAmount")+ ", " + getMyRs().getString("currentTotal")+ ", " + getMyRs().getString("singlePayment") + ", " + getMyRs().getString("currentDate"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to create connection to azure database. conneciton = null");
		}
        finally{
            killConnections();
        }               
		System.out.println("Execution finished.");
    }
//==============================================================================
//Getter & Setters
//==============================================================================
    public Connection getMyConnection(){
        return myConnection;
    }
    /**
     * @param myConnection the myConnection to set
     */
    public void setMyConnection(Connection myConnection) {
        this.myConnection = myConnection;
    }

    /**
     * @return the myStmt
     */
    public Statement getMyStmt() {
        return myStmt;
    }

    /**
     * @param myStmt the myStmt to set
     */
    public void setMyStmt(Statement myStmt) {
        this.myStmt = myStmt;
    }

    /**
     * @return the myRs
     */
    public ResultSet getMyRs() {
        return myRs;
    }

    /**
     * @param myRs the myRs to set
     */
    public void setMyRs(ResultSet myRs) {
        this.myRs = myRs;
    }
}
