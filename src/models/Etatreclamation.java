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
public class Etatreclamation {
    
    private int id ;
    private String etat ;

    public Etatreclamation(String etat) {
        this.etat = etat;
    }

    public Etatreclamation() {
    }

    public Etatreclamation(int id, String etat) {
        this.id = id;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.etat);
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
        final Etatreclamation other = (Etatreclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etatreclamation{" + "id=" + id + ", etat=" + etat + '}';
    }
    
    
    
}
