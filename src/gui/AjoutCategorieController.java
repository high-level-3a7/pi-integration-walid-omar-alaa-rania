/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Materiel;
import models.categorie;
import service.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private TextField id_description;
    @FXML
    private TextField id_nom;
    @FXML
    private ComboBox<String> comb_type;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comb_type.getItems().addAll("sport individuel","sport collectif");
    }    

    @FXML
    private void saveCategorie(ActionEvent event) {
        
        ServiceCategorie SE =new ServiceCategorie();
        String nom=id_nom.getText();
        
        if (veriftextfield(id_nom)|veriftextfield(id_description)){
                                 CreerAlert("Erreur","Les champs doivent etre remplis");

 }else {
            
       
            
            if(SE.verifactexist(nom)!=0){
                  CreerAlert("Erreur","Le nom existe déja");
     }else{
        
        categorie c= new categorie(id_nom.getText(), id_description.getText(),comb_type.getValue());
         SE.ajouter(c);
    } }}
        public boolean veriftextfield (TextField t)
{
    if (t.getText().isEmpty() )
    return true ;
           return false; 
}
           
        
        public void  CreerAlert (String titre ,String text )
{
    Alert alert =new Alert (Alert.AlertType.ERROR) ;
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.show();
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
