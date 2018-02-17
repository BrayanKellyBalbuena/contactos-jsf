/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;


import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.ContactRepository;
import util.ConnectionDB;



/**
 *
 * @author script-dark
 */
public class ContactRepositoryImpl implements ContactRepository{
    private Logger LOG = Logger.getLogger(ContactRepositoryImpl.class.getName());
    private List<Contact> contacList = new ArrayList<>();
    private Connection conn;
    private PreparedStatement ps;

    public ContactRepositoryImpl(ConnectionDB connection) {
        conn = connection.getConnection();
    }
    
    
    @Override
    public void save(Contact entity) {
        String QUERY="INSERT INTO contact(firstname, lastname, phone) values(?, ?, ?)";
        try {
            ps = conn.prepareStatement(QUERY);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            
            ps.executeUpdate();
         
            LOG.log(Level.INFO,"Guardado con exito");
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void update(Contact entity) {
        String QUERY = "UPDATE contact SET firstname = ?, lastname = ?, phone = ? WHERE id = ?";
        try{
            ps = conn.prepareStatement(QUERY);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            ps.setInt(4, entity.getId());
            
            ps.executeUpdate();
            LOG.log(Level.INFO,"Guardado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void delete(int id) {
       String query = "DELETE FROM contact WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
           if( ps.execute()){
               LOG.log(Level.INFO,"Eliminado con exito");
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       try{
           ps = conn.prepareStatement(query);
       } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
           try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }

    @Override
    public Contact findById(int id) {
        String query = "SELECT * FROM contact WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
           if(result.next()){
               LOG.log(Level.INFO,"Encontrado con exito");
               return new Contact(
                                result.getInt("id"),
                                result.getString("firstname"),
                                result.getString("lastname"),
                                result.getString("phone"));
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       try{
           ps = conn.prepareStatement(query);
       } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
           try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       return null;
    }

    @Override
    public List<Contact> findAll() {
         
        String QUERY="select * from contact";
        try {
          Statement stmt = conn.createStatement() ;
            ResultSet executeQuery = stmt.executeQuery(QUERY);
            
                fillContactList(executeQuery);
             
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return contacList;
    }
    
    
    private void fillContactList(ResultSet result) throws SQLException{
        while(result.next()){
            Contact tempContact = new Contact(
                                result.getInt("id"),
                                result.getString("firstname"),
                                result.getString("lastname"),
                                result.getString("phone"));
            
//            tempContact.setId(result.getInt("id"));
//            tempContact.setFirstName(result.getString("firstname"));
//           tempContact.setLastName(result.getString("lastname"));
//           tempContact.setPhone(result.getString("phone"));
           
            
            contacList.add(tempContact);
                    
        }
        
        
    }
    
}
