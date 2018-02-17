/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author script-dark
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpelegrino
 */
public class TestJdbcProperties {
    
    public static void run() {
        
//        Properties prop=new Properties();        
//        ClassLoader loader=Thread.currentThread().getContextClassLoader();
//        InputStream input=loader.getResourceAsStream("datasource.properties");
//        try {
//            prop.load(input);
//            
//            System.out.println(prop.getProperty("jdbc.driver"));
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(TestJdbcProperties.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
          Properties prop=PropertyDB.getInstance();
        
        System.out.println(prop.getProperty("jdbc.url"));
        Connection con=null;
        con=new MySqlConnection().getConnection();
        System.out.println("con:: "+ con);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TestJdbcProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}