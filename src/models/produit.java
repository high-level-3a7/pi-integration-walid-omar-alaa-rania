/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author user
 */
public class produit {
     private int idproduit;
    private String nom;
    private float prix;
    private String description;
    private String image;

    public produit(int idproduit, String nom, float prix, String description, String image) {
        this.idproduit = idproduit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public produit() {
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "produit{" + "idproduit=" + idproduit + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", image=" + image + '}';
    }

    public produit(String nom, float prix, String description, String image) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public produit(int idproduit, String nom, float prix, String description) {
        this.idproduit = idproduit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public produit(int idproduit, String nom, String description, String image) {
        this.idproduit = idproduit;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
     public produit( String nom,Float prix, String description, String image,int idproduit) {
        this.idproduit = idproduit;
        this.nom = nom;
        this.description = description;
        this.image = image;
         this.prix = prix;
    }

    public produit(int idproduit) {
        this.idproduit = idproduit;
    }

    public produit(String image) {
        this.image = image;
    }
     


}

    

   
   

   


