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
public class Etat {
    private int id;
    private String type_etat;
    private String description;

    public Etat(String type_etat, String description) {
        this.type_etat = type_etat;
        this.description = description;
    }

    public Etat(int id, String type_etat, String description) {
        this.id = id;
        this.type_etat = type_etat;
        this.description = description;
    }

    public Etat(int id, String type_etat) {
        this.id = id;
        this.type_etat = type_etat;
    }

    public Etat() {
    }
    

    public Etat(int id) {
        this.id = id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setType_etat(String type_etat) {
        this.type_etat = type_etat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType_etat() {
        return type_etat;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Etat{" + "id=" + id + ", type_etat=" + type_etat + ", description=" + description + '}';
    }
    
}
