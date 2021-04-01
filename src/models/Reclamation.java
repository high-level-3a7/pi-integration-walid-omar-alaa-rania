/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.Objects;
import service.ServiceEtatreclamation;
import service.ServiceReclamation;
import service.ServiceTypereclamation;

/**
 *
 * @author karbo
 */
public class Reclamation {
    
    ServiceReclamation sr = new ServiceReclamation();
    ServiceEtatreclamation ser = new ServiceEtatreclamation();
    ServiceTypereclamation str = new ServiceTypereclamation();
    
   private int id ;
   private int user_id;
   private int etat_id;
   private int type_id;
   private String contenu;
   private Date daterec ;
   private String username;
   private String etat;
   private String type;

    public Reclamation() {
    }

    public Reclamation(int id, int user_id, int etat_id, int type_id, String contenu, Date daterec) {
        this.id = id;
        this.user_id = user_id;
        this.etat_id = etat_id;
        this.type_id = type_id;
        this.contenu = contenu;
        this.daterec = daterec;
        this.username = sr.getnomuserByid(user_id);
        this.etat = ser.getetatByid(etat_id);
        this.type = str.gettypeByid(type_id);
        
    }

    public Reclamation(int user_id, int etat_id, int type_id, String contenu, Date daterec) {
        this.user_id = user_id;
        this.etat_id = etat_id;
        this.type_id = type_id;
        this.contenu = contenu;
        this.daterec = daterec;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEtat_id() {
        return etat_id;
    }

    public void setEtat_id(int etat_id) {
        this.etat_id = etat_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.user_id;
        hash = 83 * hash + this.etat_id;
        hash = 83 * hash + this.type_id;
        hash = 83 * hash + Objects.hashCode(this.contenu);
        hash = 83 * hash + Objects.hashCode(this.daterec);
        hash = 83 * hash + Objects.hashCode(this.username);
        hash = 83 * hash + Objects.hashCode(this.etat);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.etat_id != other.etat_id) {
            return false;
        }
        if (this.type_id != other.type_id) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.daterec, other.daterec)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", etat_id=" + etat_id + ", type_id=" + type_id + ", contenu=" + contenu + ", daterec=" + daterec + ", username=" + username + ", etat=" + etat + ", type=" + type + '}';
    }

    public Reclamation(int id, int user_id, int etat_id, int type_id, String contenu, Date daterec, String username, String etat, String type) {
        this.id = id;
        this.user_id = user_id;
        this.etat_id = etat_id;
        this.type_id = type_id;
        this.contenu = contenu;
        this.daterec = daterec;
        this.username = sr.getnomuserByid(user_id);
        this.etat = ser.getetatByid(etat_id);
        this.type = str.gettypeByid(type_id);
    }

    
   
   
    
}
