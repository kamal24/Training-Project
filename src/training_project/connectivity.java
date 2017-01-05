package training_project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class connectivity {
    Connection connection;
    connectivity(){
       connection =null;
    }
    public void connect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        String host="localhost";
        String pwd="9417120358";
        String usrname="System";
        String dbname="XE";
        String port="1521";
        
        String url="jdbc:oracle:thin:@"+host+":"+port+":"+dbname;
        
        try{
            connection=DriverManager.getConnection(url, usrname, pwd);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }
           
    }
    
    public ResultSet execute_query(String query){
        ResultSet result=null;
        try{
          Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
          result=statement.executeQuery(query);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }
        return result;
    }
    
     public int execute_update(String query){
         int result=0;
        try{
          Statement statement=connection.createStatement();
          result=statement.executeUpdate(query);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return result;
    }
    
           
}
