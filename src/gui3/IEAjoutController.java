/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui3;

import dbconnection.Dbconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Emploi;
import org.controlsfx.control.Notifications;
import services.ServiceEmploi;


/**
 * FXML Controller class
 *
 * @author rania
 */
public class IEAjoutController implements Initializable {

    @FXML
    private ComboBox<String> tfIdSeance;
    @FXML
    private ComboBox<String> tfIdSalle;
    @FXML
    private DatePicker tfDate;
   
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    private String dateN="yyyy-MM-dd";
    @FXML
    private Button retourp;
    @FXML
    private Button gotouser;
    @FXML
    private Button gotoreclamation;
    @FXML
    private Button gotocommande;
    @FXML
    private Button gotoabonnement;
    @FXML
    private Button gotoemploi;
    @FXML
    private Button gotomateriel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillcombo();
        fillcombo1();
    }    

    @FXML
    private void AjoutB(ActionEvent event) {
        ServiceEmploi ef = new ServiceEmploi();
        int x=ef.getIdSeancebyIdEmploi(tfIdSeance.getValue());
        int y=ef.getIdzonebyIdzone(tfIdSalle.getValue());
        
        ef.ajouter(new Emploi(x,y,datN(tfDate.getValue())));
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Emploi du temps Ajoutée")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
    }
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateN);
    public String datN(LocalDate tfDate ) {
         if (tfDate != null) {
             return dateFormatter.format(tfDate);
         } else {
             return "";
         }
     }
    
    public void fillcombo(){
            Connection cnx = Dbconnection.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select seance.IdSeance  from  seance LEFT JOIN emploi_du_temps ON "
                   + "seance.IdSeance = emploi_du_temps.IdSeance WHERE emploi_du_temps.IdSeance IS NULL";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("IdSeance"));
              
                       }
              
           tfIdSeance.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
    public void fillcombo1(){
            Connection cnx = Dbconnection.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select zonedacces.idzone  from  zonedacces LEFT JOIN emploi_du_temps ON "
                   + "zonedacces.idzone = emploi_du_temps.idzone WHERE emploi_du_temps.idzone IS NULL";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("idzone"));
              
                       }
              
           tfIdSalle.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();   
        } catch (IOException e) {
        }}
    
     @FXML
    private void retourp(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/IEAfficher.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
}
    @FXML
    private void gotouser(MouseEvent event) {
         
    }

    @FXML
    private void gotoreclamation(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui1/Accueil.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void gotoabonnement(MouseEvent event) {
    }

    @FXML
    private void gotoemploi(MouseEvent event)   throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui3/Acc2.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
        
    }

    @FXML
    private void gotomateriel(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
    
    @FXML
    private void gotocommande(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui2/Boutique.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
}