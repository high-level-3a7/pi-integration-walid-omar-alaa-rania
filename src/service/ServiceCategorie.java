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
import models.categorie;

/**
 *
 * @author walid
 */
public class ServiceCategorie implements IServiceGestionMateriel<categorie>{
    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void ajouter(categorie c) {
        try {
            String requete = "INSERT INTO categorie (nom,description,type_sport) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setString(3,c.getType_sport());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(categorie c) {
        try {
            String requete = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
      
//    @Override
//     public void modifier(int id,String nom,String description, String type_sport) {
//        try {
//            String requete = "UPDATE categorie SET  nom=?,description=?,type_sport=? WHERE id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1,id);
//            pst.setString(2,nom);
//            pst.setString(3,description);
//            pst.setString(4,type_sport);
//            pst.executeUpdate();
//            System.out.println("categeorie modifié !");
////            System.out.println("id : "+id+" nom : "+nom+" descrpt "+description+" type : "+type_sport);
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//    
    
   
//    public void updateCours(categorie c, int id) throws SQLException {
//        PreparedStatement timestamp = new PreparedStatement(System.currentTimeMillis());
//        timestamp stm = cnx.createStatement();
//        String query = "UPDATE cours SET nom= '"+c.getNom()+"', description= '"+c.getDescription()+"', type_sport= '"+timestamp"' WHERE id='"+c.getId()+"'";
//        stm.executeUpdate(query);     
//    }

    
    @Override
    
    public List<categorie> afficher(){
        List<categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorie(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

//    @Override
//    public void updateC(categorie p) {
//        try {
//            Statement stm = cnx.createStatement();
//            String query = "UPDATE cours SET nom= '"+p.getNom()+"', description= '"+p.getDescription()+"', type_sport= '"+p.getType_sport()"' WHERE id='"+p.getId()+"'";
//            stm.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public void modifier(categorie c)  {
        try{
        Statement stm = cnx.createStatement();
        String query = "UPDATE categorie SET nom= '"+c.getNom()+"', description= '"+c.getDescription()+"', type_sport= '"+c.getType_sport()+"' WHERE id='"+c.getId()+"'";
        stm.executeUpdate(query);
        System.out.println("categeorie modifié !");
    } catch (SQLException ex) {
           System.err.println(ex.getMessage());
}
//try {
//            String requete = "UPDATE categorie SET nom=?, description=? ,type_sport=? WHERE id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            
//           // InputStream is =new FileInputStream(new File(s));
//            pst.setString(1, c.getNom());
//            pst.setString(2, c.getDescription());
//           
//          pst.setString(3, c.getType_sport());
//            pst.setInt(4, c.getId());
//            pst.executeUpdate();
//            System.out.println("Activité modifiée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }
    
    public int getIdbyNom(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT id FROM categorie where nom= ? ";
            
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
            String requete = "SELECT count(*) FROM categorie Where nom=?";
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
    
    

    


  

   
    
    

