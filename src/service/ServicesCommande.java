/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import gui2.AjoutcommandeController;
import models.commande;

import gestion_materiel.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import java.sql.DriverManager;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**
 *
 * @author user
 */
public class ServicesCommande implements IServiceGestionMateriel<commande> {
    Connection cnx = DataSource.getInstance().getCnx();
     @Override
     public void ajouter(commande t) {
         try {
            String requete = "INSERT INTO commande (idproduit,date,email,id) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdproduit());
            pst.setString(2, t.getDate());
             pst.setString(3, t.getEmail());
             pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("Commande validée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

     @Override
    public void supprimer(commande t) {
          try {
            String requete = "DELETE FROM commande WHERE idcommande=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdcommande());
            pst.executeUpdate();
            System.out.println("commande supprimée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
 
    @Override
    public void modifier(commande t)  {
        try{
        Statement stm = cnx.createStatement();
        String query = "UPDATE commande SET  idproduit= '"+t.getIdproduit()+"', date= '"+t.getDate()+"', email= '"+t.getEmail()+"',id= '"+t.getId()+"' WHERE idcommande='"+t.getIdcommande()+"'";
        stm.executeUpdate(query);
        System.out.println("commande modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServicesCommande.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        @Override
    public List<commande> afficher() {
          List<commande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new commande(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
  
    
 
    
    public commande getIdcommande(int idcommande) throws SQLException {
        commande t = null;
        String req = "SELECT * FROM commande WHERE idcommande= '" + idcommande + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            t= new commande(rs.getInt("idproduit"),rs.getString("Date"),rs.getString("email"),rs.getInt("userid"));
        }
        return t;  
    }

 public commande getidproduit(int idproduit) throws SQLException {
        commande t = null;
        String req = "SELECT * FROM produit WHERE idproduit= '" + idproduit + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
 while (rs.next()) {
            t= new commande(rs.getInt("idproduit"));
        }
        return t; 
 }
   
   
    /**
     *
     * @return
     */
    public List<commande> readAllprecommandeSortedByDate() {

        List<commande> t = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select idcommande,idproduit,email,userid from commande order by Date ");
            while (rs.next()) {
                int idcommande = rs.getInt("idcommande");
                int idproduit = rs.getInt("idproduit");
                String email = rs.getString("email");
                  int userid = rs.getInt("iserid");
                
                commande prr = new commande(idcommande,idproduit,email,userid);
                t.add(prr);
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
              
               x = rs.getInt("id");
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

//     public static void sendMail(String recepient) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        Your gmail address
//        String myAccountEmail = "alaadhaouadi99@gmail.com";
//        Your gmail password
//        String password = "zapatista07";
//
//        Create a session with account credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//
//        Prepare email message
//        Message message = prepareMessage(session, myAccountEmail, recepient);
//
//        Send mail
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("My First Email from Java App");
//            String htmlCode = "<h1> WE LOVE JAVA </h1> <br/> <h2><b>Next Line </b></h2>";
//            message.setContent(htmlCode, "text/html");
//            return message;
//        } catch (Exception ex) {
//            Logger.getLogger(AjoutcommandeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

}
