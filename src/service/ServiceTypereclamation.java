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
import models.Typeactivite;
import models.Typereclamation;

/**
 *
 * @author karbo
 */
public class ServiceTypereclamation implements IService<Typereclamation> {
    
    Connection cnx = Dbconnection.getInstance().getCnx();

    @Override
    public void ajouter(Typereclamation t) {
        try {
            String requete = "INSERT INTO type_reclamation (type) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("type reclamation ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Typereclamation t) {
        try {
            String requete = "DELETE FROM type_reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("type reclamation supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Typereclamation t) {
        try {
            String requete = "UPDATE type_reclamation SET type=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());        
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("type reclamation modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public ObservableList<Typereclamation> afficher() {
        ObservableList<Typereclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM type_reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typereclamation(rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    
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
    
    public ObservableList<Typereclamation> trier() {
        
        ObservableList<Typereclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `type_reclamation` ORDER BY type  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typereclamation( rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Typereclamation> rechercher(String ch) {
        
        ObservableList<Typereclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `type_reclamation` WHERE (type LIKE ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Typereclamation(rs.getInt("id"),rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public int veriftypeexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM `type_reclamation` Where type=?";
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
    
    public String gettypeByid(int id){
        
        String ch ="";
        try {
            String requete = "SELECT type FROM `type_reclamation` where id=?";
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
}
