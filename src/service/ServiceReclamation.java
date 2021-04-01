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
import models.Reclamation;

/**
 *
 * @author karbo
 */
public class ServiceReclamation implements IService<Reclamation> {
    
    Connection cnx = Dbconnection.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation r) {
        
        try {
            String requete = "INSERT INTO reclamation (user_id,type_id,contenu,daterec,etat_id) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,r.getUser_id() );
            pst.setInt(2, r.getType_id());
            pst.setString(3, r.getContenu() );
            pst.setDate(4, r.getDaterec());
            pst.setInt(5, r.getEtat_id());
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
            }

    @Override
    public void supprimer(Reclamation r) {
        
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
            }

    @Override
    public void modifier(Reclamation r) {
        
        try {
            String requete = "UPDATE reclamation SET user_id=?,type_id=?,contenu=?,etat_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, r.getId());
            pst.setInt(1,r.getUser_id() );
            pst.setInt(2, r.getType_id());
            pst.setString(3, r.getContenu() );
            pst.setInt(4, r.getEtat_id());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }

    @Override
    public ObservableList<Reclamation> afficher() {
        
         ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("etat_id"),rs.getInt("type_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        }
    
    
    public ObservableList<Reclamation> trier() {
        
        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `reclamation` ORDER BY daterec  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("type_id"),rs.getInt("etat_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Reclamation> rechercher(String ch) {
        
        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `reclamation` WHERE (contenu LIKE ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("type_id"),rs.getInt("etat_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<String> listNomUsers () {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT username FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new String(rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list; 
    }
    
    public ObservableList<Reclamation> afficherparUserId(int id) {
        
         ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation where user_id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("type_id"),rs.getInt("etat_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        }
    
    public int getIduserByNom(String ch){
        
        int x = 0;
        try {
            String requete = "SELECT id FROM user where username=?";
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
    
    public String getnomuserByid(int id){
        
        String ch ="";
        try {
            String requete = "SELECT username FROM user where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ch=rs.getString("username");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ch ;
        
    }
    
    public ObservableList<Reclamation> trier2(int id) {
        
        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `reclamation` WHERE user_id=? ORDER BY daterec  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("type_id"),rs.getInt("etat_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Reclamation> rechercher2(String ch, int id) {
        
        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `reclamation` WHERE (contenu LIKE ?) AND user_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("type_id"),rs.getInt("etat_id"), rs.getString("contenu"), rs.getDate("daterec")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public int nbrrecbyetat(int idetat){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM reclamation Where etat_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idetat);
            ResultSet rs = pst.executeQuery();
            rs.next();
            x = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
    }
    
    public int nbrrecbytype(int idtype){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM reclamation Where type_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idtype);
            ResultSet rs = pst.executeQuery();
            rs.next();
            x = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;
    }
    
}
