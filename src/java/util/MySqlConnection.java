package util;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public  class MySqlConnection implements ConnectionDB{
    
    private Connection connection;
    private void loadDriver(String driverName) throws ClassNotFoundException{
        Class.forName(driverName);
    }
    
    @Override
    public Connection getConnection() {
     Properties prop =  PropertyDB.getInstance();
        try {
            loadDriver(prop.getProperty("jdbc.driver"));
            
            connection = DriverManager.getConnection(prop.getProperty("jdbc.url"),
                prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));
        } 
        catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
}
