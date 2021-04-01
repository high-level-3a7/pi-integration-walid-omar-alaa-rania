/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dbconnection.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Seance;

import java.sql.Statement;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author rania
 */
public class ServiceSeance implements IService<Seance> {
    Connection cnx = Dbconnection.getInstance().getCnx();
    private Statement ste;
    private ResultSet result;
    

    @Override
    public void ajouter(Seance t) {
        try {
            String requete = "INSERT INTO seance (Capacite,IdCoach,DateS,activiteid) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, t.getCapacite());
            pst.setInt(2, t.getIdCoach());
           
            pst.setString(3, t.getDateS());
            pst.setInt(4, t.getActiviteid());
            pst.executeUpdate();
            System.out.println("Seance ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(Seance t) {
        try {
            String requete = "DELETE FROM seance WHERE IdSeance=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdSeance());
            pst.executeUpdate();
            System.out.println("Seance supprimée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifier(Seance t) {
        try {
            String requete = "UPDATE seance SET Capacite=?, IdCoach=? , DateS=? , activiteid=? WHERE IdSeance=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1, t.getIdSeance());
            pst.setInt(1, t.getCapacite());
            pst.setInt(2, t.getIdCoach());
            pst.setString(3, t.getDateS());
           pst.setInt(4, t.getActiviteid());
            pst.setInt(5, t.getIdSeance());
            pst.executeUpdate();
            System.out.println("Seance modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public ObservableList<Seance> afficher() {
        ObservableList<Seance> list =FXCollections.observableArrayList();;

        try {
            String requete = "SELECT * FROM seance";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Seance (rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getString("DateS") , rs.getInt(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List<Seance> Search(String charac) {
           String requete="select * from seance where IdSeance LIKE '%"+charac+"%'or Capacite LIKE '%"+charac+"%' or IdCoach LIKE '%"+charac+"%' or DateS LIKE '%"+charac+"%'or activiteid LIKE '%"+charac+"%" ;
           
           List<Seance> Seance = new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(requete);           
     while(rst.next()) 
    {       
        //System.out.println("Event : "+rst.getString("description")+"\tMedia :"+rst.getString("source")+"\tNombre de J'aime :"+rst.getInt("nbrlikes") );
 
            Seance result = new Seance();
            result.setIdSeance(rst.getInt("IdSeance"));
            result.setCapacite(rst.getInt("Capacite"));
            result.setIdCoach(rst.getInt("IdCoach"));
           result.setDateS(rst.getString("DateS"));
           result.setActiviteid(rst.getInt("activiteid"));
            
            
         
            Seance.add(result);
          
    }
        } catch (SQLException ex) {
            System.out.println("No Seance Available !");
        } 
        return Seance;   
    }
    
    public List<Seance> readAllSeanceSortedById() {

        List<Seance> lu = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery("select IdSeance,Capacite,IdCoach,DateS,activiteid from Seance order by IdS  ");
            while (rs.next()) {
                int IdS = rs.getInt("IdSeance");
                int Capacite = rs.getInt("Capacite");
                int IdCoach = rs.getInt("IdCoach");
                String DateS = rs.getString("DateS");
                int activiteid = rs.getInt("activiteid");
                Seance t = new Seance(IdS,Capacite,IdCoach,DateS,activiteid);
                lu.add(t);
            }
        } catch (SQLException ex) {
        }
        return lu;
    } 
    
   public void sendmail(Seance se) {
		
		String to = "Admin@Gmail.com";
		String from = "0c6a23b0fb-fd007a@inbox.mailtrap.io";
		final String username = "da5efab3013dc9";
		final String password = "9f4802a2c82ecf";
		
		String host = "smtp.mailtrap.io";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");  
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");
		
		Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
    }
         });
		
		try {
    //create a MimeMessage object
    javax.mail.Message message = new MimeMessage(session);
 
    //set From email field 
    message.setFrom(new InternetAddress(from));
 
    //set To email field
    message.setRecipients(javax.mail.Message.RecipientType.TO,
               InternetAddress.parse(to));
 
    //set email subject field
    message.setSubject("Séance !");
 
    //set the content of the email message
    message.setText(se.toString());

    //send the email message
    Transport.send(message);

    System.out.println("Email Message Sent Successfully");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    } 
   public String getnombyactiviteid(int activiteid){
        
          
           String ch="";
            try {
                   String requete = "SELECT nom FROM activite where id= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setInt(1, activiteid);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               ch = rs.getString("nom");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return ch ;
    }

  
   
   
   public int getIdbynom1(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT id FROM activite where nom= ? ";
            
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
   
}  
   



    

