/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;


import service.ServicesProduit;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.produit;
import gestion_materiel.DataSource;
import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.Node;
import models.commande;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeprodController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<produit> table;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> description;

    ObservableList<produit> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> idproduit;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button telecharger;
    @FXML
    private TableColumn<?, ?> image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM produit";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new produit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServicesProduit sp = new service.ServicesProduit();
        idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.setItems(list);
        
        
        
        
        
        
        
          
        tfrecherche.getText();
        
        idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
      /* Abonnement Ab1 = new Abonnement("100dt", "GOLD", "Mussculation - Natation - Box - KaratÃ© - Taekowendo");
        Abonnement Ab2 = new Abonnement( "140dt", "PREMIUM", "Toutes les activitÃ©s");
        Abonnement Ab3 = new Abonnement("100dt", "PINK", "Aerobic - natation - Tae bo - Musculation - Zumba");
        Abonnement Ab4 = new Abonnement("80dt", "KID", "Natation - Gymnastique - KaratÃ© - Taekowendo");*/
       // list.addAll(Ab1,Ab2, Ab3, Ab4);
        table.setItems(list);
        
        FilteredList<produit> filteredData = new FilteredList<>(list, b -> true);
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(produit.getIdproduit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(produit.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(produit.getPrix()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(produit.getDescription()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(produit.getImage()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    
    }    
   @FXML
    private void actionBoutton(javafx.event.ActionEvent mouseEvent) throws IOException {
          produit selected=table.getSelectionModel().getSelectedItem();
        if (mouseEvent.getSource() == modifier) {
          loadStage("/gui2/Modifierproduit.fxml");
        }
        else if(mouseEvent.getSource() == ajouter){
            loadStage("/gui2/Ajoutproduit.fxml");
        }
        else if(mouseEvent.getSource() == supprimer){
          ServicesProduit sp = new ServicesProduit();
          sp.supprimer(selected);
            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("Produit supprimée")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(5));
               n.darkStyle();
               n.show(); 
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
        

    @FXML
    private void ajouter(MouseEvent event) {
        loadStage("/gui2/ajoutproduit.fxml");
    }

    @FXML
    private void modifier(MouseEvent event) {
        loadStage("/gui2/modifierproduit.fxml");
    }

   

   
 /*   private void rechercher(KeyEvent event) {
         if(tfrecherche.getText()==""){
            list.clear();
            Connection cnx = DataSource.getInstance().getCnx();
             try {
            String requete = "SELECT * FROM produit ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new produit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        ServicesProduit sp = new ServicesProduit();
         idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        nom.setCellValueFactory(new PropertyValueFactory <>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory <>("prix"));
        description.setCellValueFactory(new PropertyValueFactory <>("description"));
        

     
        table.setItems(list);
            
        }else{
            list.clear();
            ServicesProduit sp = new ServicesProduit();
            List<produit> arr=new ArrayList<>();
            arr=sp.Search(tfrecherche.getText());
            for (int i = 0; i < arr.size(); i++) {
                 list.add(arr.get(i));  
                 
            }
       idproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        nom.setCellValueFactory(new PropertyValueFactory <>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory <>("prix"));
        description.setCellValueFactory(new PropertyValueFactory <>("description"));
        

     
        table.setItems(list);
        }
    
    }*/

      @FXML
    private void telecharger(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException {
    
        try {
            String file_name="C:\\PDF\\produit.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from produit"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("Idproduit"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Nom"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Prix"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Description"));
            t.addCell(c4);
             
            t.setHeaderRows(1);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                 
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier :D");
    }
   @FXML
     private void refrech1(ActionEvent event) {
        try {
             list .clear();
            Connection cnx = DataSource.getInstance().getCnx();
             String requete = "SELECT * FROM produit ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new produit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

     @FXML
    private void gotouser(MouseEvent event) {
        
    }

    @FXML
    private void gotoreclamation(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui1/Accueil.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void gotoabonnement(MouseEvent event) {
    }

    @FXML
    private void gotoemploi(MouseEvent event) {
    }

    @FXML
    private void gotomateriel(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
   
    }




   
