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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Etat;
import models.Materiel;
import service.ServiceEtat;
import service.ServiceMateriel;
/**
 * FXML Controller class
 *
 * @author walid
 */
public class ModifEtatController implements Initializable {

    @FXML
    private TextField id_type_etat;
    @FXML
    private TextField id_description;
    private TableView<Etat> tv;
    private TableColumn<Etat, String> id_id1;
    private TableColumn<Etat, String> type_sport1;
    int etatId;
 public ObservableList<Etat> data = FXCollections.observableArrayList();
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Connection cnx = DataSource.getInstance().getCnx();
    
    try { 
            String requete = "SELECT * FROM etat ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add (new Etat(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
                    }      
    
        ServiceEtat se = new ServiceEtat();
        ServiceMateriel sm = new ServiceMateriel();
         id_id1.setCellValueFactory(new PropertyValueFactory<Etat,String>("id"));
       type_sport1.setCellValueFactory(new PropertyValueFactory<Etat,String>("type_etat"));
       
         tv.setItems(data);
    }    

    @FXML
    private void ModifierEtat(ActionEvent event) {
        ServiceEtat SE =new ServiceEtat();
        Etat e = new Etat(etatId,id_type_etat.getText(), id_description.getText());
        SE.modifier(e);
        
    }
     void setTextField(int id, String type_etat, String description ) {
        etatId = id;
   //   int id1 = Integer.parseInt(ide.getText());
        id_type_etat.setText(type_etat);
        id_description.setText(description);
        
    }

    @FXML
    private void retour(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btnretour) {
           // loadStage("/Gui/GestionCategorie.fxml");
           Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/GestionEtat.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
}
