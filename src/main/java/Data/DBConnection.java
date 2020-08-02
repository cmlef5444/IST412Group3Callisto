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
    Statement myStmt;
    ResultSet myRs;
    
    public DBConnection(){
        
    }
    
    public void init(){
        try{
            System.out.println("Testing database Statement");
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection=DriverManager.getConnection("jdbc:mysql://cmlef-Surface:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false", "admin", "admin");
        
            myStmt = myConnection.createStatement();
            myRs = myStmt.executeQuery("select * from callistotest.customer");
            while (myRs.next()){
                System.out.println(myRs.getString("customerLastName") + ", " + myRs.getString("customerFirstName"));
            }
            
            
            
        }catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }finally{
            try{
                if (myRs!= null){
                    myRs.close();
            }
            if( myStmt != null){
                    myStmt.close();
            }
            if( getMyConnection() !=null){
                    getMyConnection().close();
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        try{
            System.out.println("Testing database Statement");
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection=DriverManager.getConnection("jdbc:mysql://cmlef-Surface:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false", "admin", "admin");
        
            myStmt = myConnection.createStatement();
            String query = "insert into callistotest.customer values (5, 'bob', 'marley', 'marley@example.com', 'MyPa$$w0rd', '123 main', '123-123-1234')";
        
            myStmt.executeUpdate(query);
            
            
            
        }catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }finally{
            try{
                if (myRs!= null){
                    myRs.close();
            }
            if( myStmt != null){
                    myStmt.close();
            }
            if( getMyConnection() !=null){
                    getMyConnection().close();
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
    public Connection getMyConnection(){
        return myConnection;
    }
    
   
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }
}
