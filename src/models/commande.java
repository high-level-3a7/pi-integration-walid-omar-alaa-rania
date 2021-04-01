/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author user
 */

    public class commande {
      private int idcommande; 
    private int idproduit;
    private String date;
    private String email ; 
    private int id ;

    public commande(int idcommande, int idproduit, String date, String email) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.date = date;
        this.email = email;
    }
  
 public commande(int idcommande, int idproduit) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
    } 
  

    public commande(int idcommande,int idproduit, String date) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.date = date;
        
        
    }
    
     public commande(int idproduit, String date,int idcommande) {
     
        this.idproduit = idproduit;
        this.date = date;
          this.idcommande = idcommande;
        
    }

    public commande(int idproduit) {
        this.idproduit = idproduit;
    }

    public commande(int idproduit, String date) {
        this.idproduit = idproduit;
        this.date = date;
    }

public int getId() {
        return id;
    }

public int getIdproduit() {
        return idproduit;
    }

    public String getDate() {
        return date;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdproduit(int Idproduit) {
        this.idproduit = idproduit;
    }
    public void setId(int Id) {
        this.id= id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "commande{" + "idproduit=" + idproduit + ", date=" + date + ", idcommande=" + idcommande + ", email=" + email + ", id=" + id+'}';
    }

    public commande(int idproduit, String date, String email,int idcommande) {
        this.idproduit = idproduit;
        this.date = date;
        this.email = email;
        this.idcommande = idcommande;
    }

    public commande(int idproduit, String date, String email) {
        this.idproduit = idproduit;
        this.date = date;
        this.email = email;
    }

    public commande(int idcommande, int idproduit, String date, String email, int id) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.date = date;
        this.email = email;
        this.id = id;
    }

    public commande(int idcommande, int idproduit, String email, int id) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.email = email;
        this.id = id;
    }
    
public commande(int idproduit, String date, String email, int id,int idcommande) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.date = date;
        this.email = email;
        this.id = id;
    }


    }

