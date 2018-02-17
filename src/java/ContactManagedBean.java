
import entity.Contact;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.faces.context.ExternalContext;

import javax.inject.Named;
import repository.ContactRepository;
import repository.impl.ContactRepositoryImpl;
import util.MySqlConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author script-dark
 */
@Named(value = "contactBean")
@RequestScoped
public class ContactManagedBean implements Serializable {

    private ContactRepository repo;
    private Contact contact;
    private String message;
    private String messageClass;

    public ContactManagedBean() {
        this.repo = new ContactRepositoryImpl(new MySqlConnection());
        this.contact = new Contact();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageClass() {
        return messageClass;
    }

    public void save() {
        if (this.contact.getFirstName().isEmpty()
                || this.contact.getLastName().isEmpty()
                || this.contact.getPhone().isEmpty()) {
            this.message = "All Fields are required";
            this.messageClass = "error-message";

        } else if (this.contact.getId() != 0) {
            repo.update(this.contact);
            cleanContact();
            this.message = "Actualizado con exito";
            this.messageClass = "success-message";
            
        } else {
            Contact temp = new Contact(
                    this.contact.getId(),
                    this.contact.getFirstName(),
                    this.contact.getLastName(),
                    this.contact.getPhone());

            repo.save(temp);
            cleanContact();
            this.message = "Guardado con exito";
            this.messageClass = "success-message";
            
        }
    }

    public String update(int id) {
        this.contact = repo.findById(id);
        if (this.contact != null) {
            return "add_contact";
        } else {
            return "add_contact";
        }
    }

    public String delete(int id) {
        repo.delete(id);
        return "index";

    }

    public List<Contact> getContacts() {
        return repo.findAll();
    }
    
    private void cleanContact(){
        this.contact = new Contact();
    }
}