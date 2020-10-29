/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stulab
 */
public class DbConnectionHelp {
    
    public static Connection conn;
    public DbConnectionHelp(){
        
    }
    
    public static Connection getConnection(){
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/easycms", "root", "fpt@user");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/easycms", "root", "");


        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnectionHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public static void closeConnection(Connection connection) throws Exception {
        if ((connection != null) && (!connection.isClosed())) {
            connection.close();
        }
    }
    
//    public static void main(String[] args) {
//        DbConnectionHelp d = new DbConnectionHelp();
//        System.out.println(getConnection());
//    }
    
}
