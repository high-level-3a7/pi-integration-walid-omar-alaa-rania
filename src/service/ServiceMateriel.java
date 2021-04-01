/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import gestion_materiel.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.internal.util.xml.impl.Input;
import models.Materiel;
import models.categorie;

/**
 *
 * @author walid
 */
public class ServiceMateriel implements IServiceGestionMateriel<Materiel>{

   Connection cnx = DataSource.getInstance().getCnx();
     
    public void ajouter(Materiel m) {
        try {
            String requete = "INSERT INTO materiel (nom,marque,quantite,image,id_categorie,id_etat) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getMarque());
            pst.setInt(3, m.getQuantite());
            pst.setString(4, m.getImage());
             pst.setInt(5, m.getId_categorie());
            
             pst.setInt(6, m.getId_etat());
            pst.executeUpdate();
            System.out.println("matriel ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    
   @Override
    public void supprimer(Materiel m) {
        try {
            String requete = "DELETE FROM materiel WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, m.getId());
            pst.executeUpdate();
            System.out.println("materiel supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        
 //   }
//    public void modifier(categorie c) {
//        try {
//            String requete = "UPDATE categorie SET nom=?,marque=?,quantite=?,image=? WHERE id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//           pst.setInt(1, c.getId());
//            pst.setString(2, c.getNom());
//            pst.setString(3, c.getDescription());
//            pst.setString(4, c.getType_sport());
//            pst.executeUpdate();
//            System.out.println(" modifiée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }

        @Override
    public void modifier(Materiel m)  {
//        try{
//        Statement stm = cnx.createStatement();
//        String query = "UPDATE materiel SET nom= '"+m.getNom()+"', marque= '"+m.getMarque()+"', quantite= '"+m.getQuantite()+"',id_categorie= '"+m.getId_categorie()+"',id_etat= '"+m.getId_etat()+"' WHERE id='"+m.getId()+"'";
//        stm.executeUpdate(query);
//        System.out.println("materiel modifié !");
//    } catch (SQLException ex) {
//           System.err.println(ex.getMessage());
//}  
try {
            String requete = "UPDATE materiel SET nom=?, marque=? ,quantite=?,image=?,id_categorie=?,id_etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           // InputStream is =new FileInputStream(new File(s));
            pst.setString(1, m.getNom());
            pst.setString(2, m.getMarque());
            pst.setInt(3, m.getQuantite());
            pst.setString(4, m.getImage());
            pst.setInt(5, m.getId_categorie());
            pst.setInt(6, m.getId_etat());
            pst.setInt(7, m.getId());
            pst.executeUpdate();
            System.out.println("Materiel modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

      }
    
    
 
   @Override
    public List<Materiel> afficher(){
        List<Materiel> list = new ArrayList<>();

//        try {
//            String requete = "SELECT * FROM materiel ";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                list.add(new Materiel(
//                        rs.getInt(1), 
//                        rs.getString(2), 
//                        rs.getString(3),
//                        rs.getInt(4),
//                        rs.getInt(6),
//                        rs.getInt(7),
//                        rs.getString(5)
//                ));
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
 Connection cnx = DataSource.getInstance().getCnx();

        try {
           // String requete = "SELECT m. FROM materiel ";
            String requete = "SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             //   data1.add(new AffichageMateriel(rs.getString(2), rs.getString(3), rs.getInt(4),  rs.getString(5), rs.getString(6)));
             list.add(new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

         return  list;
    }
    
    
    
    public List<Materiel> Search(String charac) {
           String requete="select * from materiel where nom LIKE '%"+charac+"%' or marque LIKE '%"+charac+"%' or quantite LIKE '%"+charac+"%' or id_categorie LIKE '%"+charac+"%' or id_etat LIKE '%"+charac+"%'" ;
         //  System.out.println("req :"+requete);
           List<Materiel> materiel = new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(requete);           
     while(rst.next()) 
    {       
        //System.out.println("Event : "+rst.getString("description")+"\tMedia :"+rst.getString("source")+"\tNombre de J'aime :"+rst.getInt("nbrlikes") );
 
//            Materiel result = new Materiel();
        Materiel result =new Materiel();
            result.setId(rst.getInt("id"));
            result.setNom(rst.getString("nom"));
            result.setMarque(rst.getString("marque"));
            result.setQuantite(rst.getInt("quantite"));
            
            
          
           
            result.setId_categorie(rst.getInt("id_categorie"));
            
            result.setId_etat(rst.getInt("id_etat"));
            materiel.add(result);
          
    }
        } catch (SQLException ex) {
            System.out.println("No materiel Available !");
        } 
        System.out.println("liste :"+materiel.toString());
        return materiel;   
    }
   
    
    public int nbParEtat(String charac)  {
        int nb = 0;
        String reqete = "SELECT count(*) FROM materiel where id_etat LIKE '%"+charac+"%'";
        try {
        PreparedStatement pst = cnx.prepareStatement(reqete);


        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
    }
    
  
   public int nbrtot(String charac)  {
        int nb = 0;
        String reqete = "SELECT count(*) FROM materiel ";
        try {
        PreparedStatement pst = cnx.prepareStatement(reqete);


        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
   }
        
        
   
        public double statMaterilParEtat(String charac )  {
        float res = ((float) nbParEtat(charac) / nbrtot( charac ));
        System.out.println(Double.valueOf(new DecimalFormat("##.##").format(res * 100)) + "%");
        return Double.valueOf(new DecimalFormat("##.##").format(res * 100));
    }
//        public String statTerrain(String charac )  {
//        double res = ((double) nbParEtat(charac) / nbrtot(charac));  
//        double res2=res*100;
//        String stat =String.valueOf(res2)+"%";
//        return stat ;
//    }
// public ObservableList<Materiel> afficher2() {
//        
//        ObservableList<Materiel> list = FXCollections.observableArrayList();
//
//        try {
//            String requete = "SELECT M.nom , m.marque, m.quantite,m.id_categorie,m.id_etat FROM materiel m inner join categorie c on (m.id_categorie = c.id)";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                list.add(new Materiel(rs.getString("nom"), rs.getString("marque"),rs.getInt("quantite"), rs.getString("nom")));
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        return list;
//    }
        
      public int verifactexist(String ch){
        int x = 0 ;
        try {
            String requete = "SELECT count(*) FROM materiel Where nom=?";
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
   
   
   
    

    

