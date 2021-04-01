/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Activite;
import service.ServiceActivite;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class IntactiviteController implements Initializable {

    @FXML
    private TableView<Activite> tabact;
    @FXML
    private TableColumn<Activite, String> nomCol;
    @FXML
    private TableColumn<Activite, String>  typeCol;
    @FXML
    private TableColumn<Activite, String>  dureeCol;
    @FXML
    private TableColumn<Activite, String>  editCol;

    ObservableList<Activite>  activiteList = FXCollections.observableArrayList();
    Activite activite = null ;
    ServiceActivite sa = new ServiceActivite();
    @FXML
    private TextField searchFid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        FilteredList<Activite> filteredData = new FilteredList<>(FXCollections.observableList(activiteList));
        searchFid.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
        tabact.setItems(filteredData);
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
    private void refresh() {
        
        activiteList.clear();
        activiteList = (ObservableList<Activite>) sa.afficher();
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabact.setItems(activiteList);
    }
    
    private void loaddate() {
        
        refresh();
        

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
        
        
        //add cell of button edit 
         Callback<TableColumn<Activite, String>, TableCell<Activite, String>> cellFoctory = (TableColumn<Activite, String> param) -> {
            // make cell containing buttons
            final TableCell<Activite, String> cell = new TableCell<Activite, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            activite = tabact.getSelectionModel().getSelectedItem();
                            sa.supprimer(activite);
                            refresh();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              activite = tabact.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui1/modifactivite.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifactiviteController modifactiviteController = loader.getController();
                            modifactiviteController.setTextField(activite.getId(), activite.getNom(), 
                                    activite.getType_id(), activite.getDuree());
                            Parent parent = loader.getRoot();
                            Scene sceneacc2 = new Scene(parent);
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(sceneacc2);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         tabact.setItems(activiteList);
         
         
 }

    @FXML
    private void getAddactint(MouseEvent event) throws IOException {
        
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/addactivite.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void sort(MouseEvent event) {
        
        activiteList.clear();
        activiteList = (ObservableList<Activite>) sa.trier();
        tabact.setItems(activiteList);
    }

    private void chercher(MouseEvent event) {
        activiteList.clear();
        activiteList = (ObservableList<Activite>) sa.rechercher(searchFid.getText());
        tabact.setItems(activiteList);
    }
    
    private Predicate<Activite> createPredicate(String searchText){
    return activite -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(activite, searchText);
    };
}

    private boolean searchFindsOrder(Activite activite, String searchText){
        return (activite.getNom().toLowerCase().contains(searchText))||
            (activite.getDuree().toLowerCase().contains(searchText.toLowerCase()))  ;
    }
    
    private ObservableList<Activite> filterList(List<Activite> list, String searchText){
    List<Activite> filteredList = new ArrayList<>();
    for (Activite activite : list){
        if(searchFindsOrder(activite, searchText)) filteredList.add(activite);
    }
    return FXCollections.observableList(filteredList);
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
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/intadmin.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
