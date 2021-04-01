
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Blob;

/**
 *
 * @author walid
 */
public class Materiel {

  
    private int id;
    private String nom;
    private String marque;
    private int quantite;
    private String image;
    private int id_categorie;
    private int id_etat;
    private String nomCategorie;
    private String Type_etat;
   
    
    public Materiel(int id, String nom, String marque, int quantite, String image, int id_categorie, int id_etat) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.image = image;
        this.id_categorie = id_categorie;
        this.id_etat = id_etat;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getType_etat() {
        return Type_etat;
    }

    public void setType_etat(String Type_etat) {
        this.Type_etat = Type_etat;
    }

    public Materiel(int id, String nom, String marque, int quantite, String nomCategorie, String Type_etat) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.nomCategorie = nomCategorie;
        this.Type_etat = Type_etat;
    }

    public Materiel(int id, String nom, String marque, int quantite, String image, int id_categorie, int id_etat, String nomCategorie, String Type_etat) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.image = image;
        this.id_categorie = id_categorie;
        this.id_etat = id_etat;
        this.nomCategorie = nomCategorie;
        this.Type_etat = Type_etat;
    }

    

    public Materiel(String nom, String marque, int quantite, String image, int id_categorie, int id_etat) {
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.image = image;
        this.id_categorie = id_categorie;
        this.id_etat = id_etat;
    }

    public Materiel(int id, String nom, String marque, int quantite, int id_categorie, int id_etat, String photo) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.id_categorie = id_categorie;
        this.id_etat = id_etat;
        this.image = photo;
    }

    public Materiel(String nom, String marque, int quantite, int id_categorie, int id_etat) {
        this.nom = nom;
        this.marque = marque;
        this.quantite = quantite;
        this.id_categorie = id_categorie;
        this.id_etat = id_etat;
    }

    public Materiel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_etat() {
        return id_etat;
    }

    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
    }

//    @Override
//    public String toString() {
//        return "Materiel{" + "id=" + id + ", nom=" + nom + ", marque=" + marque + ", quantite=" + quantite + ", image=" + image + ", id_categorie=" + id_categorie + ", id_etat=" + id_etat + '}';
//    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", nom=" + nom + ", marque=" + marque + ", quantite=" + quantite + ", image=" + image + ", id_categorie=" + id_categorie + ", id_etat=" + id_etat + ", nomCategorie=" + nomCategorie + ", Type_etat=" + Type_etat + '}';
    }

    
    
}

   
    


   
    
   
   
    

   
    
    
    
    
