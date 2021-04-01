/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gestion_materiel.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Materiel;
import models.categorie;
import service.ServiceCategorie;
import service.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class ModifCategorieController implements Initializable {

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_description;
   // private TextField id_type_sport;
    private TextField id_id;
    private TableView<categorie> table_modif_etat;
     
    private TableColumn<categorie, String> id_id1;
    private TableColumn<categorie, String> id_nom1;
  int categorieId ;
   private boolean update;
   String query = null;
  categorie c = new categorie();
    /**
     * Initializes the controller class.
     */
    public ObservableList <categorie> ob = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> combo_sport;
    @FXML
    private Button btnretour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       combo_sport.getItems().addAll("sport individuel","sport collectif");
    
    } 
    
//public void getModifierMateriel(categorie c) {
//        id_id.setText(Integer.toString(c.getId()));
//        id_nom.setText(c.getNom());
//        id_description.setText(c.getDescription());
//       
//        id_type_sport.setText(c.getType_sport());
//        
//}
    @FXML
    private void ModifierCategorie(ActionEvent event) {
          ServiceCategorie SE =new ServiceCategorie();
//        int id=Integer.parseInt(id_id.getText());
//        String nom=id_nom.getText() ;
//        String descString=id_description.getText(); 
//       String type_sport=id_type_sport.getText(); 
      // categorie c = new categorie();
       
        c.setId(categorieId);
        c.setNom(id_nom.getText());
        c.setDescription(id_description.getText());
        c.setType_sport(combo_sport.getValue());
        System.out.println("categorie id "+categorieId);
       SE.modifier(c);
   // categorie c = new categorie(categorieId, , query, query);
       // categorie c= new categorie(categorieId,id_nom.getText(), id_description.getText(),id_type_sport.getText());
     //   categorie c= new categorie(categorieId,id_nom.getText(), id_description.getText(),id_type_sport.getText()); 
//        System.out.println(categorieId);
      // SE.modifier(c);
       
     
       
      // println(tf_nom_livreur.getText() +tf_prenom_livreur.getText()+tf_numero_livreur.getText()+tf_age_livreur.getText() )
//       categorie c =new categorie(categorieId, nom, descString,type_sport) ;
//        System.out.println(c);
         
//         Alert alert =new Alert (Alert.AlertType.INFORMATION) ;
//        alert.setHeaderText(null);
//        alert.setContentText("modification reussite");
//        alert.show();
//       //SE.modifier(c);
    }
    void setTextField(int id, String nom, String description, String type_sport) {
        categorieId = id;
   //   int id1 = Integer.parseInt(ide.getText());
        id_nom.setText(nom);
        id_description.setText(description);
        
        
    }

    @FXML
    private void retour(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btnretour) {
           // loadStage("/Gui/GestionCategorie.fxml");
           Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/GestionCategorie.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
}
}
    

    

