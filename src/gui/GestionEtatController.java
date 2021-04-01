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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Etat;
import models.Materiel;
import models.categorie;
import service.ServiceEtat;
import service.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class GestionEtatController implements Initializable {

    @FXML
    private TableView<Etat> table_etat;
   
   public ObservableList<Etat> data=FXCollections.observableArrayList() ;
    @FXML
    private TableColumn<Etat,String> id_type_etat;
    @FXML
    private TableColumn<Etat, String> id_description;
    @FXML
    private Label id_stat;
    private ComboBox<String> combo_etat;
    @FXML
    private Button btnadd;
    @FXML
    private FontAwesomeIconView btn;
    private Button btnedit;
    Etat etat= null;
    ServiceEtat se = new ServiceEtat();
    @FXML
    private TableColumn<Etat, String> sup_modif;
    @FXML
    private Button btnretour;
    @FXML
    private Button addetat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        fillcombo1();
        loaddate();
        
         Connection cnx = DataSource.getInstance().getCnx();
    
    try { 
            String requete = "SELECT * FROM etat ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Etat(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
                    }      
    
        ServiceEtat se = new ServiceEtat();
        ServiceMateriel sm = new ServiceMateriel();
         id_type_etat.setCellValueFactory(new PropertyValueFactory<Etat,String>("type_etat"));
       id_description.setCellValueFactory(new PropertyValueFactory<Etat,String>("description"));
       
         table_etat.setItems(data);
//          table_etat.setOnMouseClicked(Clicked(((MouseEvent e))->{
//                   int selectedIndex = table_etat.getSelectionModel().getSelectedIndex();
//                         if (selectedIndex!=-1) {
//                             System.out.println("chak");
//                            
//                              String nomEtat  = (String) table_etat.getSelectionModel().getSelectedItem().getType_etat();
//                              int idEtat = se.getIdbyEtat(nomEtat);
//                             double pourcentage = sm.statMaterilParEtat(String.valueOf(idEtat));
//                            id_stat.setText(pourcentage+" %");
//                         }                
               // });
          

//ServiceEtat st = new ServiceEtat();
//        
//       id_type_etat.setCellValueFactory(new PropertyValueFactory<Etat,String>("id_type_etat"));
//       id_description.setCellValueFactory(new PropertyValueFactory<Etat,String>(" id_description"));
//        
//       // ObservableList<Etat> list2 =  (ObservableList<Etat>) st.afficher();
//        public ObservableList<Etat> data=FXCollections.observableArrayList() ;
//       
//    table_etat.setItems(data);


    }



    @FXML
    private void refresh_Etat() {
        
        
    
    try { 
            data.clear();
            Connection cnx = DataSource.getInstance().getCnx();
            String requete = "SELECT * FROM etat ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Etat(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
                    }      
    }
    
    
    public void fillcombo1(){
//            Connection cnx = DataSource.getInstance().getCnx();
//          ObservableList<String> comboString= FXCollections.observableArrayList();
//      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
//           String requete = "select type_etat  from  etat    ";
//              try {
//                  
//            
//            PreparedStatement pst = cnx.prepareStatement(requete);
//                // pst.setInt(1, m.getId());
//                ResultSet rs = pst.executeQuery();
//     
//           while(rs.next()){
//             
//comboString.add(rs.getString("type_etat"));
//              
//                       }
//              
//           combo_etat.setItems(comboString);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }

     }

    @FXML
    private void add_stat(javafx.event.ActionEvent mouseEvent) {
      if (mouseEvent.getSource() == addetat) {
            loadStage("/Gui/StatEtat.fxml");
    }
    }

//    @FXML
//    private void handleButtonClicks(ActionEvent event) {
//         if (mouseEvent.getSource() == togestcat) {
//            loadStage("/Gui/GestionCategorie.fxml");
//        } else if (mouseEvent.getSource() == togestetat) {
//            loadStage("/Gui/GestionEtat.fxml");
//        } else if (mouseEvent.getSource() == btnadd) {
//            loadStage("/Gui/AjoutMateriel.fxml");
//        } else if (mouseEvent.getSource() == btnup) {
//            loadStage("/Gui/ModifMateriel.fxml");
//        } 
//    }
            private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) throws IOException
 {
        if (mouseEvent.getSource() == btnadd) {
           // loadStage("/Gui/AjoutEtat.fxml");
             Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/AjoutEtat.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
        }
         
        
    }


    private void DeletEtat(ActionEvent event) {
//        Etat selected= table_etat.getSelectionModel().getSelectedItem();
//        
//        if(selected==null){
//        
//           System.out.println("Aucun etat séléctionné");
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Aucun événement séléctionné");
//            alert.showAndWait();
//     
//        }else {
//           ServiceEtat st = new ServiceEtat();
//            String type_etat=selected.getType_etat();
//            
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Supprimer Evenement");
//                alert.setHeaderText(null);
//                alert.setContentText("Etes-vous sur de vouloir supprimer l'Etat " +" "+ selected.getType_etat());
//                Optional<ButtonType> action = alert.showAndWait();
//                if (action.get() == ButtonType.OK) {
//                    // System.out.println("sup1");
//                    st.modifier(selected);
//                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                    alert1.setTitle("Succés!");
//                    alert1.setHeaderText(null);
//                    alert1.setContentText("Materiel supprimé!");
//
//                    alert1.showAndWait();
//    }
//        }
    }
    
    
    
     private void loaddate() {
        
        refresh();
        

      id_type_etat.setCellValueFactory(new PropertyValueFactory<>("type_etat"));
        id_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
              
        //add cell of button edit 
         Callback<TableColumn<Etat, String>, TableCell<Etat, String>> cellFoctory = (TableColumn<Etat, String> param) -> {
            // make cell containing buttons
            final TableCell<Etat, String> cell = new TableCell<Etat, String>() {
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
                            
                            etat = table_etat.getSelectionModel().getSelectedItem();
                           se.supprimer(etat);
                            refresh();
                           refresh_Etat();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             etat = table_etat.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/modifEtat.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifEtatController modifEtatController = loader.getController();
                            modifEtatController.setTextField(etat.getId(), etat.getType_etat(), 
                                    etat.getDescription());
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
         sup_modif.setCellFactory(cellFoctory);
        table_etat.setItems(data);
       }

//    @FXML
//    private void DeletEtat(ActionEvent event) {
//    }

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
        
 
    
      
    

