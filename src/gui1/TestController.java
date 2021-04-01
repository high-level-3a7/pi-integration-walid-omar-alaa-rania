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
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Typeactivite;
import service.ServiceTypeactivite;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class TestController implements Initializable {

    @FXML
    private TextField searchFid;
    @FXML 
    private TableView<Typeactivite> tabtypeact;
    @FXML
    private TableColumn<Typeactivite, String> typeCol;
    @FXML
    private TableColumn<Typeactivite, String> editCol;
    
    ObservableList<Typeactivite>  typeactiviteList = FXCollections.observableArrayList();
    Typeactivite typeactivite = null ;
    ServiceTypeactivite sta = new ServiceTypeactivite();
    
    ObservableList<Typeactivite>  listfiltered = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        FilteredList<Typeactivite> filteredData = new FilteredList<>(FXCollections.observableList(typeactiviteList));
        searchFid.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
        tabtypeact.setItems(filteredData);

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
        typeactiviteList.clear();
        typeactiviteList = (ObservableList<Typeactivite>) sta.afficher();
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        FilteredList<Typeactivite> filteredData = new FilteredList<>(FXCollections.observableList(typeactiviteList));
        tabtypeact.setItems(filteredData);
        
    }

    @FXML
    private void getAddactint(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui1/addtypeactivite.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void sort(MouseEvent event) {
        typeactiviteList.clear();
        typeactiviteList = (ObservableList<Typeactivite>) sta.trier();
        tabtypeact.setItems(typeactiviteList);
    }

    @FXML
    private void chercher(MouseEvent event) {
        typeactiviteList.clear();
        typeactiviteList = (ObservableList<Typeactivite>) sta.rechercher(searchFid.getText());
        tabtypeact.setItems(typeactiviteList);
    }
    
    private void loaddate() {
        
        refresh();
        
        
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        
        //add cell of button edit 
         Callback<TableColumn<Typeactivite, String>, TableCell<Typeactivite, String>> cellFoctory = (TableColumn<Typeactivite, String> param) -> {
            // make cell containing buttons
            final TableCell<Typeactivite, String> cell = new TableCell<Typeactivite, String>() {
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
                            
                            typeactivite = tabtypeact.getSelectionModel().getSelectedItem();
                            sta.supprimer(typeactivite);
                            refresh();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              typeactivite = tabtypeact.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/modiftypeactivite.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModiftypeactiviteController modiftypeactiviteController = loader.getController();
                            modiftypeactiviteController.setTextField(typeactivite.getId(),typeactivite.getType());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
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
         tabtypeact.setItems(typeactiviteList);
         
         
 }
    private Predicate<Typeactivite> createPredicate(String searchText){
    return typeactivite -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(typeactivite, searchText);
    };
}

    private boolean searchFindsOrder(Typeactivite typeactivite, String searchText){
        return (typeactivite.getType().toLowerCase().contains(searchText)) ;
    }
    
    private ObservableList<Typeactivite> filterList(List<Typeactivite> list, String searchText){
    List<Typeactivite> filteredList = new ArrayList<>();
    for (Typeactivite typeactivite : list){
        if(searchFindsOrder(typeactivite, searchText)) filteredList.add(typeactivite);
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
    private void retour(MouseEvent event) {
    }
}
