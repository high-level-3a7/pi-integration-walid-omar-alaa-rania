/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gestion_materiel.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Etat;
import models.Materiel;
import models.categorie;
import service.ServiceCategorie;
import service.ServiceEtat;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class GestionCategorieController implements Initializable {

    @FXML
    private TableColumn<categorie, String> id_nom;
    @FXML
    private TableColumn<categorie, String> id_description;
    @FXML
    private TableColumn<categorie, String> id_type_sport;
public ObservableList<categorie> data =FXCollections.observableArrayList() ;
    @FXML
    private TableView<categorie> table_categorie;
    @FXML
    private Button addCate;
    private Button tndModif;
    @FXML
    private TableColumn<categorie, String> modif_sup;
    ServiceCategorie sc =new ServiceCategorie();
    categorie categories = null;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loaddate();
        // TODO
         // TODO
         Connection cnx = DataSource.getInstance().getCnx();
    
    try { 
            String requete = "SELECT * FROM categorie ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new categorie( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
                    }      
    
        ServiceCategorie sc = new ServiceCategorie();
            id_nom.setCellValueFactory(new PropertyValueFactory<categorie,String>("nom"));
        id_description.setCellValueFactory(new PropertyValueFactory<categorie,String>("description"));
       id_type_sport.setCellValueFactory(new PropertyValueFactory<categorie,String>("type_sport"));
        
          table_categorie.setItems(data);
    }    

    private void SuprimCategorie(ActionEvent event) {
          categorie selected= table_categorie.getSelectionModel().getSelectedItem();
        
        if(selected==null){
        
           System.out.println("Aucun categorie séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");
            alert.showAndWait();
     
        }else {
           ServiceCategorie st = new ServiceCategorie();
            String nom=selected.getNom();
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer le categorie " +" "+ selected.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    st.supprimer(selected);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Categorie supprimé!");

                    alert1.showAndWait();
    }}}

    @FXML
    private void refresh_Categorie() {
        
        try { 
            data.clear();
            Connection cnx = DataSource.getInstance().getCnx();
            String requete = "SELECT * FROM categorie ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new categorie( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
                    }      
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) throws IOException {
           if (mouseEvent.getSource() == tndModif) {
            loadStage("/Gui/ModifCategorie.fxml");
        }
         else if (mouseEvent.getSource() == addCate) {
           // loadStage("/Gui/AjoutCategorie.fxml");
             
           // loadStage("/Gui/GestionCategorie.fxml");
           Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/AjoutCategorie.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    }

           private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

           
       private void loaddate() {
        
        refresh();
        

       id_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        id_type_sport.setCellValueFactory(new PropertyValueFactory<>("type_sport"));
              
        //add cell of button edit 
         Callback<TableColumn<categorie, String>, TableCell<categorie, String>> cellFoctory = (TableColumn<categorie, String> param) -> {
            // make cell containing buttons
            final TableCell<categorie, String> cell = new TableCell<categorie, String>() {
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
                            
                            categories = table_categorie.getSelectionModel().getSelectedItem();
                           sc.supprimer(categories);
                            refresh();
                            refresh_Categorie();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             categories = table_categorie.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/ModifCategorie.fxml"));
                
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifCategorieController modifCategorieController = loader.getController();
                            System.out.println("cat id "+categories.getId());
                            modifCategorieController.setTextField(categories.getId(), categories.getNom(), 
                                    categories.getDescription(),categories.getType_sport());
                            Parent parent = loader.getRoot();
                           Scene sceneacc2 = new Scene(parent);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();;
                            

                           

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
         modif_sup.setCellFactory(cellFoctory);
        table_categorie.setItems(data);
       }

    @FXML
    private void retour(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btnretour) {
           // loadStage("/Gui/GestionCategorie.fxml");
           Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
}

    @FXML
    private void gotoRec(MouseEvent event) throws IOException {
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui1/acceuil2.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
}

         
         
      
           
  
    

