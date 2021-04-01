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
import models.Activite;
import models.Etatreclamation;
import models.Typeactivite;

/**
 *
 * @author karbo
 */
public class ServiceTypeactivite implements IService<Typeactivite>{
    
    Connection cnx = Dbconnection.getInstance().getCnx();

    @Override
    public void ajouter(Typeactivite t) {
        try {
            String requete = "INSERT INTO type_activite (type) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("type activité ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }

    @Override
    public void supprimer(Typeactivite t) {
        try {
            String requete = "DELETE FROM type_activite WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("type Activité supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Typeactivite t) {
        try {
            String requete = "UPDATE type_activite SET type=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());        
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("type activité modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public ObservableList<Typeactivite> afficher() {
        ObservableList<Typeactivite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM type_activite";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typeactivite(rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    
    }
    
    public ObservableList<String> listtypeactivites () {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT type FROM type_activite";
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
            String requete = "SELECT id FROM type_activite where type=?";
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
    
    public String getTypeByid(int id){
        String ch ="";
        try {
            String requete = "SELECT type FROM type_activite where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ch=rs.getString("type");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ch ;
    }
    
    public ObservableList<Typeactivite> trier() {
        
        ObservableList<Typeactivite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `type_activite` ORDER BY type  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typeactivite( rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Typeactivite> rechercher(String ch) {
        
        ObservableList<Typeactivite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `type_activite` WHERE (type LIKE ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typeactivite(rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public int veriftypeexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM `type_activite` Where type=?";
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
    
}
