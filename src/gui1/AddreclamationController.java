/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Reclamation;
import service.ServiceEtatreclamation;
import service.ServiceReclamation;
import service.ServiceTypereclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class AddreclamationController implements Initializable {

    @FXML
    private ComboBox combtyperec;
    @FXML
    private TextField contenuFid;
    @FXML
    private ComboBox combuserrec;
    @FXML
    private ComboBox combetatrec;

    ServiceReclamation sr = new ServiceReclamation();
    ServiceTypereclamation str = new ServiceTypereclamation();
    ServiceEtatreclamation ser = new ServiceEtatreclamation();
    @FXML
    private TextField emailFid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listusers = FXCollections.observableArrayList();
        listusers = (ObservableList<String>) sr.listNomUsers();
        combuserrec.setItems(listusers);
        
        ObservableList<String> listtyperec = FXCollections.observableArrayList();
        listtyperec = (ObservableList<String>) str.listtypereclamation();
        combtyperec.setItems(listtyperec);
        
        ObservableList<String> listetatrec = FXCollections.observableArrayList();
        listetatrec = (ObservableList<String>) ser.listetatreclamation();
        combetatrec.setItems(listetatrec);
    }    

    @FXML
    private void adminorclient(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saverec(MouseEvent event) {
        String contenu = contenuFid.getText();
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
 
        Pattern pattern = Pattern.compile(regex);
        Matcher mt = pattern.matcher(emailFid.getText());

        if (contenu.isEmpty()|| combuserrec.getSelectionModel().isEmpty() || combtyperec.getSelectionModel().isEmpty()|| combetatrec.getSelectionModel().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs !");
            alert.showAndWait();
        }else{
        Reclamation rec = new Reclamation();
        rec.setUser_id(sr.getIduserByNom((String) combuserrec.getValue()));
        rec.setEtat_id(ser.getIdByetat((String) combetatrec.getValue()));
        rec.setType_id(str.getIdBytype((String) combtyperec.getValue()));
        rec.setContenu(contenuFid.getText());
        LocalDate localdate = LocalDate.now();
        Date date = Date.valueOf(localdate);
        rec.setDaterec(date);
        sr.ajouter(rec);
        }
        if(mt.matches()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Email invalid ! ");
            alert.showAndWait();
        }else{
            try {
                sendMail(emailFid.getText());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        contenuFid.setText(null);
    }
    
    public static void sendMail(String recepient) throws Exception{
        System.out.println("preparing to send email !");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String username = "omarnakech@gmail.com";
        String pass = "goldenomar1999";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, pass);
            }
});
        Message message = prepareMessage(session , username , recepient);
        Transport.send(message);
        System.out.println("message sent successfully !");
        
    }
    
     private static Message prepareMessage(Session session, String username, String recepient) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("reclamation ");
            message.setText("votre reclamation a ete bien enregistrer !");
            return message;
        } catch(Exception ex){
            System.out.println(ex);
        }
        return null ;
    }

    @FXML
    private void gogestmat(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/intgesreclamation.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
