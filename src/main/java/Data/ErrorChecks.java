/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Lefebvre
 */
public class ErrorChecks {
    
    private DBConnection connect;
    private Statement myStmt;
    private ResultSet myRs;
    
    private PreparedStatement ps;
    
    private boolean boolResult;
    private boolean emailBoolResult;
    
    private int currentId;
    
    public ErrorChecks(){
        
    }
    public boolean isValidName(String inputName){
        Pattern pattern = Pattern.compile("[^a-zA-Z|^'-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputName);
        boolean value = matcher.find();
        return value;
    }
    public boolean isValidAddress(String inputAddress){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])" 
                        + "(?=.*[a-z])(?=.*[A-Z])" 
                        +"(?=.*[#.-])"
                        + "(?=\\S+$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputAddress);        
        boolean value = matcher.find();
        return value;
    }//"[^a-zA-Z0-9|^'-]"
    public boolean isValidPhoneNumber(String inputPhoneNumber){  
        Pattern pattern = Pattern.compile("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
        Matcher matcher = pattern.matcher(inputPhoneNumber);        
        boolean value = matcher.matches();
        return value;
    }
    
    public boolean isValidEmail(String inputEmail){      
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(inputEmail);        
        boolean value = matcher.matches();
        return value;
    }
    public boolean isValidPassword(String inputPassword){
        //Move this to register and customerProfile
        Pattern pattern = Pattern.compile("^(?=.*[0-9])" 
                        + "(?=.*[a-z])(?=.*[A-Z])" 
                        +"(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$");
        Matcher matcher = pattern.matcher(inputPassword);
        boolean value = matcher.matches();
        return value;
    }
    public boolean nonDoubleEmail(String userEmail){   //FIX_ME has not been implemented or tested in servlet, will be in register Use Case
        //Move this to register and customerProfile
        setEmailBoolResult(false);
        try{
            setConnect(new DBConnection());
            getConnect().init();
            
            setPs(getConnect().getMyConnection().prepareStatement("select customerEmail "
                    + "from customer "
                    + "where customerEmail = '" + userEmail + "'"));
            System.out.println("Input: " + userEmail);
            setMyRs(getPs().executeQuery());
            setEmailBoolResult(getMyRs().next());
//            setEmailBoolResult(!isEmailBoolResult());
            while(getMyRs().next()){
                System.out.println("Email Input: " + userEmail + "Email SQL: " + getMyRs().getString("customerEmail"));              
            }
            if(isEmailBoolResult() == false){
                System.out.println("Email is not double");
     
            }
            else{
                System.out.println("Email is double");                
            }            
        }catch(Exception e){      
            e.printStackTrace();
        }finally{
            connect.killConnections();        
        }  
        return isEmailBoolResult();
    }   
    /**
     * @return the connect
     */
    public DBConnection getConnect() {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(DBConnection connect) {
        this.connect = connect;
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

    /**
     * @return the ps
     */
    public PreparedStatement getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * @return the boolResult
     */
    public boolean isBoolResult() {
        return boolResult;
    }

    /**
     * @param boolResult the boolResult to set
     */
    public void setBoolResult(boolean boolResult) {
        this.boolResult = boolResult;
    }

    /**
     * @return the emailBoolResult
     */
    public boolean isEmailBoolResult() {
        return emailBoolResult;
    }

    /**
     * @param emailBoolResult the emailBoolResult to set
     */
    public void setEmailBoolResult(boolean emailBoolResult) {
        this.emailBoolResult = emailBoolResult;
    }

    /**
     * @return the currentId
     */
    public int getCurrentId() {
        return currentId;
    }

    /**
     * @param currentId the currentId to set
     */
    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
}
