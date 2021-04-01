/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestion_materiel.DataSource ;

import java.sql.DriverManager;
import service.IServiceGestionMateriel;


/**
 *
 * @author aissa
 */
public class ServicesProduit implements IServiceGestionMateriel<produit> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(produit t) {
         try {
            String requete = "INSERT INTO produit (nom,prix,description,image) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setFloat(2, t.getPrix());
             pst.setString(3, t.getDescription());
              pst.setString(4, t.getImage());
            pst.executeUpdate();
            System.out.println("Produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   @Override
    public void supprimer(produit t) {
          try {
            String requete = "DELETE FROM produit WHERE idproduit=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdproduit());
            pst.executeUpdate();
            System.out.println("Produit supprimé!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

 
    @Override
    public void modifier(produit t)  {
        try{
        Statement stm = cnx.createStatement();
        String query = "UPDATE produit SET  nom= '"+t.getNom()+"', prix= '"+t.getPrix()+"', description= '"+t.getDescription()+"', image= '"+t.getImage()+"' WHERE idproduit='"+t.getIdproduit()+"'";
        stm.executeUpdate(query);
        System.out.println("produit modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServicesCommande.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public List<produit> afficher() {
          List<produit> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM produit";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new produit(rs.getInt(1), rs.getString(2), rs.getFloat(3),rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    /*  public produit getIdpr(int idpr) throws SQLException {
        produit p = null;
        String req = "SELECT * FROM produit WHERE idpr= '" + idpr + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            p= new produit(rs.getString("nom"), rs.getFloat("prix"), rs.getString("description"));
        }
        return p;  
    }*/
  public List<produit> Search(String charac) {
          String requete="select * from produit where idproduit LIKE '%"+charac+"%'or nom LIKE '%"+charac+"%' or prix LIKE '%"+charac+"%' or description LIKE '%"+charac+"%"  ;

           
           List<produit> produit = new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(requete);           
     while(rst.next()) 
    {       
        //System.out.println("Event : "+rst.getString("description")+"\tMedia :"+rst.getString("source")+"\tNombre de J'aime :"+rst.getInt("nbrlikes") );
 
            produit result = new produit();
         result.setIdproduit(rst.getInt("idproduit"));
            result.setNom(rst.getString("nom"));
            result.setPrix(rst.getFloat("prix"));
            result.setDescription(rst.getString("description"));
              result.setImage(rst.getString("image"));
            produit.add(result);
          
    }
        } catch (SQLException ex) {
            System.out.println("No produit Available !");
        } 
        return produit;   
    }
   
    
    
   
   
    /**
     *
     * @return
     */
    public List<produit> readAllproduitSortedByPrix() {

        List<produit> t = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select idproduit, nom ,description,image from produit order by Prix ");
            while (rs.next()) {
                int idproduit = rs.getInt("idproduit");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                 String image = rs.getString("image");
                
                
                produit pp = new produit(idproduit,nom,description,image);
                t.add(pp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return t;
    }
       public int getIdbyNom(String t){
        
          
           int x=0;
            try {
                   String requete = "SELECT idproduit FROM produit where nom= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, t);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               x = rs.getInt("idproduit");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return x;
    }

    public int getIdproduit(String ch){
       
         
           int x=0;
            try {
                   String requete = "SELECT Idproduit FROM produit where Idproduit= ? ";
           
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, ch);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
         
           while(rs.next()){
             
               x = rs.getInt("Idproduit");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
       
       return x;
    }

  
    }



