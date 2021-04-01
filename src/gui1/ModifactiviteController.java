/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import models.Activite;
import service.ServiceActivite;
import service.ServiceTypeactivite;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class ModifactiviteController implements Initializable {

    @FXML
    private ComboBox combtypeact;
    @FXML
    private TextField nomFid;
    @FXML
    private TextField dureeFid;
    
    Activite activite = new Activite();
    int activiteId ;
    ServiceActivite sa = new ServiceActivite();
    ServiceTypeactivite sta = new ServiceTypeactivite();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list = (ObservableList<String>) sta.listtypeactivites();
        combtypeact.setItems(list);
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
    private void modifact(MouseEvent event) {
        String nom = nomFid.getText();
        String duree = dureeFid.getText();
        int x = sa.verifactexist(nom); 
        if (nom.isEmpty() || duree.isEmpty() || combtypeact.getSelectionModel().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs !");
            alert.showAndWait();
        }else{
        Activite act = new Activite();
        act.setId(activiteId);
        act.setNom(nomFid.getText());
        act.setDuree(dureeFid.getText());
        act.setType_id(sta.getIdBytype((String) combtypeact.getValue()));
        sa.modifier(act);
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        nomFid.setText(null);
        dureeFid.setText(null);
    }
    
    void setTextField(int id, String nom, int type_id, String duree) {
        activiteId = id;
        nomFid.setText(nom);
        dureeFid.setText(duree);
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
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/intactivite.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
