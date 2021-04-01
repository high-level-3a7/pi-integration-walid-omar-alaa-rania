/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import gestion_materiel.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Etat;
import models.Materiel;


/**
 *
 * @author walid
 */
public class ServiceEtat implements IServiceGestionMateriel<Etat> {

    Connection cnx = DataSource.getInstance().getCnx();
     
    public void ajouter(Etat e) {
        try {
            String requete = "INSERT INTO etat (type_etat,description) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getType_etat());
            pst.setString(2, e.getDescription());
            pst.executeUpdate();
            System.out.println("etat ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(Etat e)  {
        try{
        Statement stm = cnx.createStatement();
        String query = "UPDATE etat SET type_etat= '"+e.getType_etat()+"', description= '"+e.getDescription()+"' WHERE id='"+e.getId()+"'";
        stm.executeUpdate(query);
        System.out.println("etat modifié !");
    } catch (SQLException ex) {
           System.err.println(ex.getMessage());
}
      }
    @Override
    public List<Etat> afficher(){
        List<Etat> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM etat ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Etat(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

         return  list;
    }
     @Override
    public void supprimer(Etat e) {
        try {
            String requete = "DELETE FROM etat WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println("etat supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    public int getIdbyEtat(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT id FROM etat where type_etat= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, ch);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               x = rs.getInt("id");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return x;
    }
     public int verifactexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM etat Where type_etat=?";
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
