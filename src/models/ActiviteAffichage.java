/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author karbo
 */
public class ActiviteAffichage {
    
    private int id ;
    private String nom ;
    private String type;
    private String duree;

    public ActiviteAffichage(int id, String type, String nom, String duree) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.duree = duree;
    }

    public ActiviteAffichage() {
    }

    public ActiviteAffichage(String nom, String type, String duree) {
        this.nom = nom;
        this.type = type;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + Objects.hashCode(this.nom);
        hash = 43 * hash + Objects.hashCode(this.duree);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActiviteAffichage other = (ActiviteAffichage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActiviteAffichage{" + "id=" + id + ", type=" + type + ", nom=" + nom + ", duree=" + duree + '}';
    }
    
    
    
}
