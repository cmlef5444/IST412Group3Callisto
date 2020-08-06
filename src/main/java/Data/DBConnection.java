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
    public void closeMyConnection(){
        try{
            getMyConnection().close();
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    public Connection getMyConnection(){
        return myConnection;
    }
    
   
    
//    public void close(ResultSet rs){
//        
//        if(rs !=null){
//            try{
//               rs.close();
//            }
//            catch(Exception e){}
//        
//        }
//    }
//    
//     public void close(java.sql.Statement stmt){
//        
//        if(stmt !=null){
//            try{
//               stmt.close();
//            }
//            catch(Exception e){}
//        
//        }
//    }
//     
//  public void destroy(){
//  
//    if( getMyConnection() !=null){
//    
//         try{
//                getMyConnection().close();
//            }
//            catch(Exception e){}
//        
//        
//    }
//  }

    /**
     * @param myConnection the myConnection to set
     */
    public void setMyConnection(Connection myConnection) {
        this.myConnection = myConnection;
    }
}
