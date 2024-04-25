/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author shrey
 */
public class DBConnection {
    static Connection con=null;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","s34oPoQn@");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
  
}
