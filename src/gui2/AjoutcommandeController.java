/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesCommande;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.commande;
import gestion_materiel.DataSource;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutcommandeController implements Initializable {

    @FXML
    private Button ajouter;

    @FXML
    private DatePicker date;
    @FXML
    private Label nom1;
    @FXML
    private ComboBox<String> idproduit;
    private String dateN="yyyy-MM-dd";
    @FXML
    private TextField email;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fillcombo(); 
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, Exception {
        ServicesCommande spr = new ServicesCommande();
      if (event.getSource()==ajouter)
      {
      int x=spr.getIdproduit(idproduit.getValue());
     spr.ajouter(new commande(x, datN(date.getValue()),email.getText(),(int) Float.parseFloat(id.getText()))) ;
    
//               spr.sendMail(email.getText());
               
      }        
                
               
               
               
               
            
              try {
        
        String host = "smtp.gmail.com";
        String user = "mohamedalaa.dhaouadi@esprit.tn";
        String pass = "181JMT0832";
        String to =email.getText();
        String Subject ="commande cree";
        String message ="Votre commande a ete cree Code : ";
        boolean sessionDebug =false ;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","host");
        pros.put("mail.smtp.port","587");
        pros.put("mail.smtp.starttls.auth","true");
        pros.put("mail.smtp.starttls.required","true");
        
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailsession =Session.getDefaultInstance(pros,null);
        mailsession.setDebug(sessionDebug);
        Message msg = new MimeMessage(mailsession);
        msg.setFrom(new InternetAddress(user));
        InternetAddress [] address ={new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(Subject);
        msg.setText(message);
        Transport transport =mailsession.getTransport("smtp");
        transport.connect(host,user,pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        
        JOptionPane.showMessageDialog(null,"code has been send to the mail");
        }catch (Exception ex)
                {
                   
                }
        
               
               Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Commande Ajoutée")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(3));
               n.darkStyle();
               n.show();
      
               
               
               
               
               
               
               
               
               
               
               
    }
  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateN);
    public String datN(LocalDate date ) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }
    private void fillcombo() {
         Connection cnx = DataSource.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
     // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select idproduit  from  produit ";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("idproduit"));
              
                       }
              
           idproduit.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
              
}
    
// public static void sendMail(String recepient) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        //Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        //Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        //Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        //Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        //Your gmail address
//        String myAccountEmail = "mohamedalaa.dhaouadi@esprit.tn";
//        //Your gmail password
//        String password = "181JMT0832";
//
//        //Create a session with account credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//
//        //Prepare email message
//        Message message = prepareMessage(session, myAccountEmail, recepient);
//
//        //Send mail
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