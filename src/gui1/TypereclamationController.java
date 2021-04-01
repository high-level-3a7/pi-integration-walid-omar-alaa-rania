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
import models.Typereclamation;
import service.ServiceTypereclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class TypereclamationController implements Initializable {

    @FXML
    private TextField searchFid;
    @FXML
    private TableView<Typereclamation> tabtyperec;
    @FXML
    private TableColumn<Typereclamation, String> typeCol;
    @FXML
    private TableColumn<Typereclamation, String> editCol;
    
    ObservableList<Typereclamation>  typerecList = FXCollections.observableArrayList();
    Typereclamation typerec = null ;
    ServiceTypereclamation str = new ServiceTypereclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        FilteredList<Typereclamation> filteredData = new FilteredList<>(FXCollections.observableList(typerecList));
        searchFid.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
        tabtyperec.setItems(filteredData);
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
        
        typerecList.clear();
        typerecList = (ObservableList<Typereclamation>) str.afficher();
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabtyperec.setItems(typerecList);
    }

    @FXML
    private void add(MouseEvent event) throws IOException {
        
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/addtypereclamation.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void sort(MouseEvent event) {
        typerecList.clear();
        typerecList = (ObservableList<Typereclamation>) str.trier();
        tabtyperec.setItems(typerecList);
    }

    private void chercher(MouseEvent event) {
        typerecList.clear();
        typerecList = (ObservableList<Typereclamation>) str.rechercher(searchFid.getText());
        tabtyperec.setItems(typerecList);
    }
    
    
    private void loaddate() {
        
        refresh();
        


        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        
        
        
        //add cell of button edit 
         Callback<TableColumn<Typereclamation, String>, TableCell<Typereclamation, String>> cellFoctory = (TableColumn<Typereclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Typereclamation, String> cell = new TableCell<Typereclamation, String>() {
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
                            
                            typerec = tabtyperec.getSelectionModel().getSelectedItem();
                            str.supprimer(typerec);
                            refresh();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              typerec = tabtyperec.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui1/modiftyperec.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModiftyperecController modiftyperecController = loader.getController();
                            modiftyperecController.setTextField(typerec.getId(),typerec.getType());
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
         tabtyperec.setItems(typerecList);
         
         
 }
    
        private Predicate<Typereclamation> createPredicate(String searchText){
    return typerec -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(typerec, searchText);
    };
}

    private boolean searchFindsOrder(Typereclamation typerec, String searchText){
        return (typerec.getType().toLowerCase().contains(searchText)) ;
    }
    
    private ObservableList<Typereclamation> filterList(List<Typereclamation> list, String searchText){
    List<Typereclamation> filteredList = new ArrayList<>();
    for (Typereclamation typerec : list){
        if(searchFindsOrder(typerec, searchText)) filteredList.add(typerec);
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
