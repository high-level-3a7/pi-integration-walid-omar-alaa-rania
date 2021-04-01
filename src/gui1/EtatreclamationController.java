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
import models.Etatreclamation;
import service.ServiceEtatreclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class EtatreclamationController implements Initializable {

    @FXML
    private TextField searchFid;
    @FXML
    private TableView<Etatreclamation> tabtyperec;
    @FXML
    private TableColumn<Etatreclamation, String> etatCol;
    @FXML
    private TableColumn<Etatreclamation, String> editCol;
    
    ObservableList<Etatreclamation>  etatrecList = FXCollections.observableArrayList();
    Etatreclamation etatrec = null ;
    ServiceEtatreclamation ser = new ServiceEtatreclamation();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        FilteredList<Etatreclamation> filteredData = new FilteredList<>(FXCollections.observableList(etatrecList));
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
        etatrecList.clear();
        etatrecList = (ObservableList<Etatreclamation>) ser.afficher();
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tabtyperec.setItems(etatrecList);
    }

    @FXML
    private void add(MouseEvent event) throws IOException {
      
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/addetatreclamation.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void sort(MouseEvent event) {
        etatrecList.clear();
        etatrecList = (ObservableList<Etatreclamation>) ser.trier();
        tabtyperec.setItems(etatrecList);
    }

    private void chercher(MouseEvent event) {
        etatrecList.clear();
        etatrecList = (ObservableList<Etatreclamation>) ser.rechercher(searchFid.getText());
        tabtyperec.setItems(etatrecList);
    }
    
    private void loaddate() {
        
        refresh();
        


        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));

        
        
        
        //add cell of button edit 
         Callback<TableColumn<Etatreclamation, String>, TableCell<Etatreclamation, String>> cellFoctory = (TableColumn<Etatreclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Etatreclamation, String> cell = new TableCell<Etatreclamation, String>() {
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
                            
                            etatrec = tabtyperec.getSelectionModel().getSelectedItem();
                            ser.supprimer(etatrec);
                            refresh();
                            
                           

                       
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              etatrec = tabtyperec.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui1/modifetatrec.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifetatrecController modifetatrecController = loader.getController();
                            modifetatrecController.setTextField(etatrec.getId(),etatrec.getEtat());
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
         tabtyperec.setItems(etatrecList);
         
         
 }
    
            private Predicate<Etatreclamation> createPredicate(String searchText){
    return etatrecList -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(etatrecList, searchText);
    };
}

    private boolean searchFindsOrder(Etatreclamation typerecList, String searchText){
        return (typerecList.getEtat().toLowerCase().contains(searchText)) ;
    }
    
    private ObservableList<Etatreclamation> filterList(List<Etatreclamation> list, String searchText){
    List<Etatreclamation> filteredList = new ArrayList<>();
    for (Etatreclamation etatrecList : list){
        if(searchFindsOrder(etatrecList, searchText)) filteredList.add(etatrecList);
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
