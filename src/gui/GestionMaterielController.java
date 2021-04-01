/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gestion_materiel.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;

import models.Materiel;
import service.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class GestionMaterielController implements Initializable {

    @FXML
    private TableView<Materiel> tableMateriel;
    @FXML
    private TableColumn<Materiel, String> nomMateriel;
    @FXML
    private TableColumn<Materiel, String> marqueMateriel;
   
   
    ObservableList<Materiel> data = FXCollections.observableArrayList();
    //public ObservableList<Materiel> data1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Materiel, String> quantite;
    @FXML
    private TableColumn<Materiel, String> categorie;
    @FXML
    private TableColumn<Materiel, String> etat;
    @FXML
    private Button togestcat;
    @FXML
    private Button togestetat;
    @FXML
    private Button btnadd;
    private Button btn_update;
    private Button btnup;
    ServiceMateriel sc = new ServiceMateriel();
     Materiel materiel = null;
    @FXML
    private TableColumn<Materiel, String> sup_modf;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddate();
        search();
        // TODO
        
        Connection cnx = DataSource.getInstance().getCnx();

        try {
           // String requete = "SELECT m. FROM materiel ";
            String requete = "SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             //   data1.add(new AffichageMateriel(rs.getString(2), rs.getString(3), rs.getInt(4),  rs.getString(5), rs.getString(6)));
             data.add(new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       // ServiceMateriel sc = new ServiceMateriel();
        nomMateriel.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nom"));
        marqueMateriel.setCellValueFactory(new PropertyValueFactory<Materiel, String>("marque"));
        quantite.setCellValueFactory(new PropertyValueFactory<Materiel, String>("Quantite"));
        

        categorie.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nomCategorie"));
        etat.setCellValueFactory(new PropertyValueFactory<Materiel, String>("type_etat"));
        tableMateriel.setItems(data);

        
        
       search.getText();
        
       nomMateriel.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nom"));
        marqueMateriel.setCellValueFactory(new PropertyValueFactory<Materiel, String>("marque"));
        quantite.setCellValueFactory(new PropertyValueFactory<Materiel, String>("Quantite"));
        

        categorie.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nomCategorie"));
        etat.setCellValueFactory(new PropertyValueFactory<Materiel, String>("type_etat"));
        
        
      /* Abonnement Ab1 = new Abonnement("100dt", "GOLD", "Mussculation - Natation - Box - KaratÃ© - Taekowendo");
        Abonnement Ab2 = new Abonnement( "140dt", "PREMIUM", "Toutes les activitÃ©s");
        Abonnement Ab3 = new Abonnement("100dt", "PINK", "Aerobic - natation - Tae bo - Musculation - Zumba");
        Abonnement Ab4 = new Abonnement("80dt", "KID", "Natation - Gymnastique - KaratÃ© - Taekowendo");*/
       // list.addAll(Ab1,Ab2, Ab3, Ab4);
        tableMateriel.setItems(data);
        
        FilteredList<Materiel> filteredData = new FilteredList<>(data, b -> true);
         search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Materiel -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Materiel.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Materiel.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Materiel.getQuantite()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(Materiel.getNomCategorie()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                 else if (String.valueOf(Materiel.getMarque()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                  else if (String.valueOf(Materiel.getType_etat()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                   
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Materiel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableMateriel.comparatorProperty());
        tableMateriel.setItems(sortedData);
        
    }
//   
   

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == togestcat) {
           // loadStage("/Gui/GestionCategorie.fxml");
           Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/GestionCategorie.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
        } else if (mouseEvent.getSource() == togestetat) {
            //loadStage("/Gui/GestionEtat.fxml");
             Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/GestionEtat.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
        } else if (mouseEvent.getSource() == btnadd) {
            //loadStage("/Gui/AjoutMateriel.fxml");
            Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/AjoutMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
       
        } else if (mouseEvent.getSource() == btnup) {
           // loadStage("/Gui/ModifMateriel.fxml");
            Parent parentacc2 = FXMLLoader.load(getClass().getResource("/GUI/ModifMateriel.fxml"));
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


 

//    @FXML
//    private void SuprimMateriel3(ActionEvent event) {
//        Materiel selected= tableMateriel.getSelectionModel().getSelectedItem();
//        
//        if(selected==null){
//        
//           System.out.println("Aucun materiel séléctionné");
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Aucun événement séléctionné");
//            alert.showAndWait();
//     
//        }else {
//           ServiceMateriel st = new ServiceMateriel();
//            String nom=selected.getNom();
//            
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Supprimer Evenement");
//                alert.setHeaderText(null);
//                alert.setContentText("Etes-vous sur de vouloir supprimer le Materiel " +" "+ selected.getNom());
//                Optional<ButtonType> action = alert.showAndWait();
//                if (action.get() == ButtonType.OK) {
//                    // System.out.println("sup1");
//                    st.supprimer(selected);
//                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                    alert1.setTitle("Succés!");
//                    alert1.setHeaderText(null);
//                    alert1.setContentText("Materiel supprimé!");
//
//                    alert1.showAndWait();
//    }}}
    public void stat(){
         ServiceMateriel sc = new ServiceMateriel();
        
    }

    @FXML
    private void refrech_action() {
        try {
            data.clear();
            Connection cnx = DataSource.getInstance().getCnx();
             String requete = "SELECT m.id, m.nom , m.marque, m.quantite,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               data.add(new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
 
     private void loaddate() {
        
        refresh();
        

       nomMateriel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        marqueMateriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        

        categorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        etat.setCellValueFactory(new PropertyValueFactory<>("type_etat"));
        
        
        
        //add cell of button edit 
         Callback<TableColumn<Materiel, String>, TableCell<Materiel, String>> cellFoctory = (TableColumn<Materiel, String> param) -> {
            // make cell containing buttons
            final TableCell<Materiel, String> cell = new TableCell<Materiel, String>() {
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
                            
                            materiel = tableMateriel.getSelectionModel().getSelectedItem();
                           sc.supprimer(materiel);
                          //  refresh();
                          refrech_action();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             materiel = tableMateriel.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/modifMateriel.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifMaterielController modifMaterielController = loader.getController();
                            modifMaterielController.setTextField(materiel.getId(), materiel.getNom(), 
                                    materiel.getMarque(), materiel.getQuantite(),materiel.getId_categorie(),materiel.getId_etat());
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
         sup_modf.setCellFactory(cellFoctory);
         tableMateriel.setItems(data);
        // refrech_action();
         
 }

  private void search() {
//       tfrecherche.getText();
//       
//        idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        
//        
//      /* Abonnement Ab1 = new Abonnement("100dt", "GOLD", "Mussculation - Natation - Box - KaratÃ© - Taekowendo");
//        Abonnement Ab2 = new Abonnement( "140dt", "PREMIUM", "Toutes les activitÃ©s");
//        Abonnement Ab3 = new Abonnement("100dt", "PINK", "Aerobic - natation - Tae bo - Musculation - Zumba");
//        Abonnement Ab4 = new Abonnement("80dt", "KID", "Natation - Gymnastique - KaratÃ© - Taekowendo");*/
//       // list.addAll(Ab1,Ab2, Ab3, Ab4);
//        table.setItems(list);
//        
//        FilteredList<produit> filteredData = new FilteredList<>(list, b -> true);
//        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(produit -> {
//                            if (newValue == null || newValue.isEmpty()) {
//					return true;
//                        }
//        String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (String.valueOf(produit.getIdproduit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.
//				} else if (String.valueOf(produit.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//				else if (String.valueOf(produit.getPrix()).indexOf(lowerCaseFilter)!=-1){
//				     return true;}
//                                else if (String.valueOf(produit.getDescription()).indexOf(lowerCaseFilter)!=-1){
//				     return true;}
//				     else  
//				    	 return false; 
//                                });
//                        
//		});
//        
//        SortedList<produit> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(table.comparatorProperty());
//        table.setItems(sortedData);
//    }

    
    }

    @FXML
    private void pdf_action(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException {
       
    
        try {
            String file_name="C:\\PDF\\materiel.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "SELECT  m.nom , m.marque, m.quantite,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(5);
            PdfPCell c1 = new PdfPCell(new Phrase("nom"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("marque"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("quantite"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("categorie"));
            t.addCell(c4);
            PdfPCell c5 = new PdfPCell(new Phrase("etat"));
            t.addCell(c5);
            
            t.setHeaderRows(1);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                t.addCell(rs.getString(5));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier 😃");
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

    
    

    

