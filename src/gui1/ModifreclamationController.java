/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import models.Reclamation;
import service.ServiceEtatreclamation;
import service.ServiceReclamation;
import service.ServiceTypereclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class ModifreclamationController implements Initializable {

    @FXML
    private ComboBox combtyperec;
    @FXML
    private TextField contenuFid;
    @FXML
    private ComboBox combuserrec;
    @FXML
    private ComboBox combetatrec;
    
    Reclamation rec = new Reclamation();
    int recid ;
    ServiceReclamation sr = new ServiceReclamation();
    ServiceTypereclamation str = new ServiceTypereclamation();
    ServiceEtatreclamation ser = new ServiceEtatreclamation();
            

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

    void setTextField(int id, int user_id, int etat_id, int type_id, String contenu, Date daterec) {
        recid=id;
        int userid=user_id;
        int etatid=etat_id;
        int typeid=type_id;
        contenuFid.setText(contenu);
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
    private void modifrec(MouseEvent event) {
        String contenu = contenuFid.getText();

        if (contenu.isEmpty()|| combuserrec.getSelectionModel().isEmpty() || combtyperec.getSelectionModel().isEmpty()|| combetatrec.getSelectionModel().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs !");
            alert.showAndWait();
        }else{
        Reclamation reclamation = new Reclamation();
        reclamation.setId(recid);
        reclamation.setUser_id(sr.getIduserByNom((String) combuserrec.getValue()));
        reclamation.setEtat_id(ser.getIdByetat((String) combetatrec.getValue()));
        reclamation.setType_id(str.getIdBytype((String) combtyperec.getValue()));
        reclamation.setContenu(contenuFid.getText());   
        sr.modifier(reclamation);
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        contenuFid.setText(null);
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
