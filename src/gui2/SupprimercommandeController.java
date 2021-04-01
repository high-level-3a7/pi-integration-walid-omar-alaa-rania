
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesCommande;
import static java.lang.Integer.parseInt;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.commande;
import models.produit;
import org.controlsfx.control.Notifications;
import gestion_materiel.DataSource;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SupprimercommandeController implements Initializable {

    @FXML
    private DatePicker date;

  
    @FXML
    private Button supprimer;
    @FXML
    private ComboBox<String> idproduit;
    @FXML
    private TextField idcommande;
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
    private void supprimer(ActionEvent event) {
         ServicesCommande spr = new ServicesCommande();
         int x=spr.getIdproduit(idproduit.getValue());
        spr.supprimer(new commande(Integer.parseInt(idcommande.getText()),x,datN(date.getValue()),email.getText(),(int) Float.parseFloat(id.getText())));
    Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text(" Commande supprimée")
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
    }
    