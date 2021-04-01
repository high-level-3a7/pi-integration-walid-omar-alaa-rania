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
import models.ActiviteAffichage;

/**
 *
 * @author karbo
 */
public class ServiceActivite implements IService<Activite> {

    Connection cnx = Dbconnection.getInstance().getCnx();
    
    @Override
    public void ajouter(Activite a) {
        try {
            String requete = "INSERT INTO activite (type_id,nom,duree) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a.getType_id());
            pst.setString(2, a.getNom());
            pst.setString(3, a.getDuree());
            pst.executeUpdate();
            System.out.println("Activité ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }

    @Override
    public void supprimer(Activite a) {
        
        try {
            String requete = "DELETE FROM activite WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            System.out.println("Activité supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
            }
    public void supprimer2(int x){
        try {
            String requete = "DELETE FROM activite WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, x);
            pst.executeUpdate();
            System.out.println("Activité supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    @Override
    public void modifier(Activite a) {
        
        try {
            String requete = "UPDATE activite SET type_id=?,nom=?,duree=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, a.getId());
            pst.setInt(1, a.getType_id());
            pst.setString(2, a.getNom());
            pst.setString(3, a.getDuree());
            pst.executeUpdate();
            System.out.println("Activité modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
            }

    @Override
    public ObservableList<Activite> afficher() {
        
        ObservableList<Activite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM activite";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Activite(rs.getInt("id"), rs.getInt("type_id"), rs.getString("nom"), rs.getString("duree")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
//    public ObservableList<Activite> afficher2() {
//        
//        ObservableList<Activite> list2 = FXCollections.observableArrayList();
//        //String requete = "SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
//
//        try {
//            String requete = "SELECT a.id ,a.nom , a.duree,a.type_id, t.type FROM activite as a LEFT JOIN type_activite as t on a.type_id = t.id";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                list2.add(new Activite(rs.getInt("id"), rs.getString("nom"), rs.getString("duree"), rs.getString("type")));
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        return list2;
//    }
    

    public ObservableList<Activite> trier() {
        
        ObservableList<Activite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `activite` ORDER BY nom  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Activite(rs.getInt("id"), rs.getInt("type_id"), rs.getString("nom"), rs.getString("duree")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Activite> rechercher(String ch) {
        
        ObservableList<Activite> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `activite` WHERE (nom LIKE ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            String x = "%";
            String r = x+ch+x;
            pst.setString(1, r);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Activite(rs.getInt("id"), rs.getInt("type_id"), rs.getString("nom"), rs.getString("duree")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public int verifactexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM `activite` Where nom=?";
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
