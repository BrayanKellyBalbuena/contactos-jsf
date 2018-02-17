/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entity.Contact;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import repository.ContactRepository;
import repository.impl.ContactRepositoryImpl;
import util.MySqlConnection;


/**
 *
 * @author brayan kelly
 */
@ManagedBean(name ="contactView")
@ViewScoped
public class Home implements Serializable{
    
    private List<Contact> contacts;
    private List<Contact> filteredContacts;
    private ContactRepository repo;
    
    public Home() {
       this.repo = new ContactRepositoryImpl(new MySqlConnection());
    }
    
    @PostConstruct
    public void init() {
        contacts = repo.findAll();
    }
    
    public List<Contact> getContacts() {
        return contacts;
    }
    
    public List<Contact> getFilteredContacts(){
        return filteredContacts;
    }
    
    public void setFilteredContacts(List<Contact> filteredContacts){
       this.filteredContacts = filteredContacts;
    }
    
 
    
    
   
    
}
