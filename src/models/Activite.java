/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;
import service.ServiceActivite;
import service.ServiceTypeactivite;

/**
 *
 * @author karbo
 */
public class Activite {
    
    ServiceTypeactivite sta = new ServiceTypeactivite();
    
    private int id ;
    private int type_id;
    private String nom ;
    private String duree;
    private String type ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Activite() {
    }

//    public Activite(int id, String nom, String duree, String type) {
//        this.id = id;
//        this.nom = nom;
//        this.duree = duree;
//        
//        this.type = type;
//    }

    public Activite(int id, int type_id, String nom, String duree) {
        this.id = id;
        this.type_id = type_id;
        this.nom = nom;
        this.duree = duree;
        this.type = sta.getTypeByid(type_id);
    }
    
    
   

    public Activite(String nom, String type, String duree) {
        this.nom = nom;
        this.type = type;
        this.duree = duree;
    }

    

    public Activite(int type_id, String nom, String duree) {
        this.type_id = type_id;
        this.nom = nom;
        this.duree = duree;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.type_id;
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.duree);
        hash = 83 * hash + Objects.hashCode(this.type);
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
        final Activite other = (Activite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.type_id != other.type_id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", type_id=" + type_id + ", nom=" + nom + ", duree=" + duree + ", type=" + type + '}';
    }

   
    
    
    
    
    
}
