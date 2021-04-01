/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Etatreclamation;
import service.ServiceEtatreclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class ModifetatrecController implements Initializable {

    @FXML
    private TextField etatFid;
    
    Etatreclamation er = new Etatreclamation();
    int etatrecid ;
    ServiceEtatreclamation ser = new ServiceEtatreclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setTextField(int id, String etat) {
        etatrecid = id;
        etatFid.setText(etat);
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
    private void modifier(MouseEvent event) {
        String ty = etatFid.getText();
        int x = ser.verifetatexist(ty); 
        if (ty.isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs !");
            alert.showAndWait();
        }else{
        Etatreclamation er = new Etatreclamation();
        er.setId(etatrecid);
        er.setEtat(etatFid.getText());
        ser.modifier(er);
        }
    }
        

    @FXML
    private void clear(MouseEvent event) {
        etatFid.setText(null);
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
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/etatreclamation.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
