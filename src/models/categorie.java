/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author walid
 */
public class categorie {
    private int id;
    private String nom;
    private String description;
    private String type_sport;

    public categorie(int id, String nom, String description, String type_sport) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type_sport = type_sport;
    }

    public categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public categorie() {
    }



  

    public categorie(String nom, String description, String type_sport) {
        this.nom = nom;
        this.description = description;
        this.type_sport = type_sport;
    }

    public categorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getType_sport() {
        return type_sport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType_sport(String type_sport) {
        this.type_sport = type_sport;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type_sport=" + type_sport + '}';
    }

   

   
}
