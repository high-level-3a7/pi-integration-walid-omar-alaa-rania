/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesCommande;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.commande;
import org.controlsfx.control.Notifications;
import gestion_materiel.DataSource;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeprecController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<commande> table;
   
    @FXML
    private TableColumn<?, ?> date;
 ObservableList<commande> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> idcommande;
    @FXML
    private TableColumn<?, ?> idproduit;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new commande(rs.getInt(1), rs.getInt(2),  rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServicesCommande spr = new service.ServicesCommande();
        idcommande.setCellValueFactory(new PropertyValueFactory<>("idcommande"));
        idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.setItems(list);
        
        
        
        
        
        
        
        
        
        tfrecherche.getText();
        
     idcommande.setCellValueFactory(new PropertyValueFactory<>("idcommande"));
        idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
      /* Abonnement Ab1 = new Abonnement("100dt", "GOLD", "Mussculation - Natation - Box - KaratÃ© - Taekowendo");
        Abonnement Ab2 = new Abonnement( "140dt", "PREMIUM", "Toutes les activitÃ©s");
        Abonnement Ab3 = new Abonnement("100dt", "PINK", "Aerobic - natation - Tae bo - Musculation - Zumba");
        Abonnement Ab4 = new Abonnement("80dt", "KID", "Natation - Gymnastique - KaratÃ© - Taekowendo");*/
       // list.addAll(Ab1,Ab2, Ab3, Ab4);
        table.setItems(list);
        
        FilteredList<commande> filteredData = new FilteredList<>(list, b -> true);
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(commande -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(commande.getIdcommande()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(commande.getIdproduit()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(commande.getDate()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                               else if (String.valueOf(commande.getEmail()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                               else if (String.valueOf(commande.getId()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<commande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        
        
        
    }
    @FXML
    private void actionBoutton(javafx.event.ActionEvent mouseEvent) throws IOException {
          commande selected=table.getSelectionModel().getSelectedItem();
        if (mouseEvent.getSource() == modifier) {
          loadStage("/gui2/Modifiercommande.fxml");
        }
        else if(mouseEvent.getSource() == ajouter){
            loadStage("/gui2/ajoutcommande.fxml");
        }
        else if(mouseEvent.getSource() == supprimer){
          ServicesCommande spr = new ServicesCommande();
          spr.supprimer(selected);
            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("Commande supprimée")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(5));
               n.darkStyle();
               n.show(); 
        }
      
    }
//    private void refrech(ActionEvent event) {
//        try {
//             list .clear();
//            Connection cnx = DataSource.getInstance().getCnx();
//             String requete = "SELECT * FROM commande ";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                 list .add(new commande(rs.getInt(1),rs.getInt(2),rs.getString(3)));
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
    
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();   
        } catch (IOException e) {
        }
    }

  
  @FXML
    private void modifier(MouseEvent event) {
         loadStage("/gui2/Modifiercommande.fxml");
    }

    @FXML
    private void ajouter(MouseEvent event) {
         loadStage("/gui2/ajoutcommande.fxml");
    }

    @FXML
    private void refrech1(ActionEvent event) {
        try {
             list .clear();
            Connection cnx = DataSource.getInstance().getCnx();
             String requete = "SELECT * FROM commande ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 list .add(new commande(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
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
    private void gotoemploi(MouseEvent event) {
    }

    @FXML
    private void gotomateriel(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

 
    
}
