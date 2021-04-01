/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Emploi;
import dbconnection.Dbconnection;
import java.sql.Statement;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ServiceEmploi implements IService<Emploi> {
    Connection cnx = Dbconnection.getInstance().getCnx();
    private Statement ste;
    private ResultSet result;

    @Override
    public void ajouter(Emploi t) {
        try {
            String requete = "INSERT INTO emploi_du_temps (IdSeance,idzone,DateE) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1, t.getIdSeance());
            pst.setInt(2, t.getIdzone());
            pst.setString(3, t.getDateE());
            
            pst.executeUpdate();
            System.out.println("Emploi du temps ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(Emploi t) {
        try {
            String requete = "DELETE FROM emploi_du_temps WHERE IdEmploi=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdEmploi());
            pst.executeUpdate();
            System.out.println("Emploi du temps supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifier(Emploi t) {
        try {
            String requete = "UPDATE emploi_du_temps SET  IdSeance=?,idzone=?, DateE=?  WHERE IdEmploi=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1, t.getIdEmploi());
            pst.setInt(1, t.getIdSeance());
            pst.setInt(2, t.getIdzone());
            pst.setString(3, t.getDateE());
            
            pst.setInt(4,t.getIdEmploi());
            pst.executeUpdate();
            System.out.println("Emploi du temps modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public ObservableList<Emploi> afficher() {
        ObservableList<Emploi> list =FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM emploi_du_temps";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Emploi(rs.getInt("IdEmploi"), rs.getInt("IdSeance"), rs.getInt("idzone"), rs.getString("DateE")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

   
    public List<Emploi> readAllEmploiSortedById() {

        List<Emploi> lu = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery("select IdEmploi,IdSeance,idzone,DateE from emploi_du_temps order by IdEmploi  ");
            while (rs.next()) {
                int IdEmploi = rs.getInt("IdEmploi");
                int IdSeance = rs.getInt("IdSeance");
                int idzone = rs.getInt("idzone");
                String DateE = rs.getString("DateE");
                
                Emploi t = new Emploi(IdEmploi,IdSeance,idzone,DateE);
                lu.add(t);
            }
        } catch (SQLException ex) {
        }
        return lu;
    }

    public int getIdSeancebyIdEmploi(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT IdSeance FROM seance where IdSeance= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, ch);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               x = rs.getInt("IdSeance");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return x;
    }
    
    public int getIdEmploi(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT IdEmploi FROM emploi_du_temps where IdEmploi= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, ch);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               x = rs.getInt("IdEmploi");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return x;
    }
    public int getIdzonebyIdzone(String ch){
        
          
           int x=0;
            try {
                   String requete = "SELECT idzone FROM zonedacces where idzone= ? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setString(1, ch);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               x = rs.getInt("idzone");
           }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return x;
    }
    public void sendmail(Emploi em) {
		
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
    message.setSubject("Emploi du temps !");
 
    //set the content of the email message
    message.setText(em.toString());

    //send the email message
    Transport.send(message);

    System.out.println("Email Message Sent Successfully");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    }
    
}
