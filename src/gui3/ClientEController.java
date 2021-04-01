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
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Seance;
import services.ServiceSeance;

import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class ClientEController implements Initializable {

    @FXML
    private TextField tfrecherche;
    @FXML
  
    private TableView<Seance> table;
    @FXML
    private TableColumn<Seance, Integer> tvs;
    @FXML
    private TableColumn<Seance, Integer> tvC;
    @FXML
    private TableColumn<Seance, Integer> tvCo;
    
    @FXML
    private TableColumn<Seance, String> tvT;
    @FXML
    private TableColumn<Seance, String> tvDa;
    @FXML
    private Button butA;
ObservableList<Seance> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM seance";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Seance (rs.getInt(1), rs.getInt(2), rs.getInt(3) , rs.getString(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceSeance se = new services.ServiceSeance();
        tvs.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("IdSeance"));
        tvC.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("Capacite"));
        tvCo.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("IdCoach"));
        tvDa.setCellValueFactory(new PropertyValueFactory<Seance,String>("DateS"));
        tvT.setCellValueFactory(new PropertyValueFactory<Seance,String>("nom"));
        
        
       
        table.setItems(list);
        tfrecherche.getText();
        
        tvs.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("IdSeance"));
        tvC.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("Capacite"));
        tvCo.setCellValueFactory(new PropertyValueFactory<Seance,Integer>("IdCoach"));
        tvDa.setCellValueFactory(new PropertyValueFactory<Seance,String>("DateS"));
        tvT.setCellValueFactory(new PropertyValueFactory<Seance,String>("nom"));
        
        
       
        table.setItems(list);
        
        FilteredList<Seance> filteredData = new FilteredList<>(list, b -> true);
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Seance -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Seance.getIdSeance()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (String.valueOf(Seance.getCapacite()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(Seance.getIdCoach()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                               
                                
				else if (String.valueOf(Seance.getDateS()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(Seance.getActiviteid()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
				     return true;}
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Seance> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
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
    private void AffichS(ActionEvent event) {
        try {
             list .clear();
            Connection cnx = Dbconnection.getInstance().getCnx();
             String requete = "SELECT * FROM seance ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Seance (rs.getInt(1), rs.getInt(2), rs.getInt(3) , rs.getString(4) , rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
