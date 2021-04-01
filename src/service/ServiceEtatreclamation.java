/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dbconnection.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Etatreclamation;
import models.Reclamation;
import models.Typereclamation;

/**
 *
 * @author karbo
 */
public class ServiceEtatreclamation implements IService<Etatreclamation> {
    Connection cnx = Dbconnection.getInstance().getCnx();

    @Override
    public void ajouter(Etatreclamation e) {
        try {
            String requete = "INSERT INTO etatreclamation (etat) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getEtat());
            pst.executeUpdate();
            System.out.println("Etat Reclamation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
            }

    @Override
    public void supprimer(Etatreclamation t) {
         try {
            String requete = "DELETE FROM etatreclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Etat Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }

    @Override
    public void modifier(Etatreclamation t) {
        
        try {
            String requete = "UPDATE etatreclamation SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());        
            pst.setString(1, t.getEtat());
            pst.executeUpdate();
            System.out.println("Etat Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }

    @Override
    public ObservableList<Etatreclamation> afficher() {
        
           ObservableList<Etatreclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM etatreclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Etatreclamation(rs.getInt("id"),rs.getString("etat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        }
    
    public int getidbyetat (String ch){
        int x = 0;
        try {
            String requete = "SELECT id FROM etatreclamation where etat=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                x=rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
    }
    
    public ObservableList<String> listetatreclamation () {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT etat FROM etatreclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new String(rs.getString("etat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list; 
    }
    
    public int getIdByetat(String ch){
        
        int x = 0;
        try {
            String requete = "SELECT id FROM etatreclamation where etat=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                x=rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
        
    }
    
    public ObservableList<String> listtypereclamation () {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT type FROM type_reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new String(rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list; 
    }
    
    public int getIdBytype(String ch){
        
        int x = 0;
        try {
            String requete = "SELECT id FROM type_reclamation where type=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                x=rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
        
    }
    
    public ObservableList<Etatreclamation> trier() {
        
        ObservableList<Etatreclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `etatreclamation` ORDER BY etat  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Etatreclamation( rs.getInt("id"),rs.getString("etat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Etatreclamation> rechercher(String ch) {
        
        ObservableList<Etatreclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `etatreclamation` WHERE (etat LIKE ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Etatreclamation(rs.getInt("id"),rs.getString("etat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        
    public int verifetatexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM `etatreclamation` Where etat=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            rs.next();
            x = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
    }
    
    public String getetatByid(int id){
        
        String ch ="";
        try {
            String requete = "SELECT etat FROM `etatreclamation` where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ch=rs.getString("etat");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ch ;
        
    }
        
    
}
