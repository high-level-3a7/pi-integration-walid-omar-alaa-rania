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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Reclamation;
import service.ServiceEtatreclamation;
import service.ServiceReclamation;
import service.ServiceTypereclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class IntreclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tabrec;
    @FXML
    private TableColumn<Reclamation, String> userCol;
    @FXML
    private TableColumn<Reclamation, String> etatCol;
    @FXML
    private TableColumn<Reclamation, String> typeCol;
    @FXML
    private TableColumn<Reclamation, String> contenuCol;
    @FXML
    private TableColumn<Reclamation, String> dateCol1;
    @FXML
    private TextField searchFid;
    
    ObservableList<Reclamation>  reclamationList = FXCollections.observableArrayList();
    Reclamation reclamation = null ;
    ServiceReclamation sr = new ServiceReclamation();
    ServiceEtatreclamation ser = new ServiceEtatreclamation();
    ServiceTypereclamation str = new ServiceTypereclamation();
    @FXML
    private ComboBox combetatrec;
    @FXML
    private Label label;
    @FXML
    private ComboBox combtyperec;
    @FXML
    private Label label1;
    @FXML
    private TableColumn<Reclamation , String> editCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        ObservableList<String> listetatrec = FXCollections.observableArrayList();
        listetatrec = (ObservableList<String>) ser.listetatreclamation();
        combetatrec.setItems(listetatrec);
        
        ObservableList<String> listtyperec = FXCollections.observableArrayList();
        listtyperec = (ObservableList<String>) str.listtypereclamation();
        combtyperec.setItems(listtyperec);
        
        FilteredList<Reclamation> filteredData = new FilteredList<>(FXCollections.observableList(reclamationList));
        searchFid.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
        tabrec.setItems(filteredData);

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
        
        reclamationList.clear();
        reclamationList = (ObservableList<Reclamation>) sr.afficher();
        tabrec.setItems(reclamationList);
    }

    @FXML
    private void sort(MouseEvent event) {
        
        reclamationList.clear();
        reclamationList = (ObservableList<Reclamation>) sr.trier();
        tabrec.setItems(reclamationList);
    }

    private void chercher(MouseEvent event) {
        
        reclamationList.clear();
        reclamationList = (ObservableList<Reclamation>) sr.rechercher(searchFid.getText());
        tabrec.setItems(reclamationList);
    }
    
    private void loaddate() {
        
        refresh();
        
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contenuCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateCol1.setCellValueFactory(new PropertyValueFactory<>("daterec"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        //FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

//                        deleteIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#ff1744;"
//                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
//                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
//                            
//                            reclamation = tabrec.getSelectionModel().getSelectedItem();
//                            sr.supprimer(reclamation);
//                            refresh();
//                            
//                           
//
//                          
//
//                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              reclamation = tabrec.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui1/modifreclamation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifreclamationController modifreclamationController = loader.getController();
                            modifreclamationController.setTextField(reclamation.getId(), reclamation.getUser_id(),reclamation.getEtat_id(),reclamation.getType_id(),reclamation.getContenu(),reclamation.getDaterec());
                            Parent parent = loader.getRoot();
                            Scene sceneacc2 = new Scene(parent);
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(sceneacc2);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         tabrec.setItems(reclamationList);
        
    }

    @FXML
    private void select(ActionEvent event) {
        String s = (String) combetatrec.getValue();
        int x = ser.getIdByetat(s);
        int y = sr.nbrrecbyetat(x);
        label.setText(String.valueOf(y));
    }

    @FXML
    private void select2(ActionEvent event) {
        String s = (String) combtyperec.getValue();
        int x = str.getIdBytype(s);
        int y = sr.nbrrecbytype(x);
        label1.setText(String.valueOf(y));
        
    }
    
    private Predicate<Reclamation> createPredicate(String searchText){
    return reclamation -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(reclamation, searchText);
    };
}

    private boolean searchFindsOrder(Reclamation reclamation, String searchText){
        return (reclamation.getContenu().toLowerCase().contains(searchText))  ;
    }
    
    private ObservableList<Reclamation> filterList(List<Reclamation> list, String searchText){
    List<Reclamation> filteredList = new ArrayList<>();
    for (Reclamation reclamation : list){
        if(searchFindsOrder(reclamation, searchText)) filteredList.add(reclamation);
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
