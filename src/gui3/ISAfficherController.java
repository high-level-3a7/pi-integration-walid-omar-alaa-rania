/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui3;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dbconnection.Dbconnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Seance;
import org.controlsfx.control.Notifications;
import services.ServiceSeance;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class ISAfficherController implements Initializable {

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
    @FXML
    private Button btnm;
    @FXML
    private Button btnSE;
    @FXML
    private Button btnSE1;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TextField tfSS;
    @FXML
    private Button telecharger;
    @FXML
    private Button mail;
    @FXML
    private Button retourp;
    @FXML
    private Button gotouser;
    @FXML
    private Button gotoreclamation;
    @FXML
    private Button gotocommande;
    @FXML
    private Button gotoabonnement;
    @FXML
    private Button gotoemploi;
    @FXML
    private Button gotomateriel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     services.ServiceSeance ss=new ServiceSeance();
          
        list = (ObservableList<Seance>) ss.afficher();
        tvs.setCellValueFactory(new PropertyValueFactory<>("IdSeance"));
        tvC.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
        tvCo.setCellValueFactory(new PropertyValueFactory<>("IdCoach"));
        tvT.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tvDa.setCellValueFactory(new PropertyValueFactory<>("DateS"));
        table.setItems(list);
        
        
        
        
        
        
        
        
        
        
        
        
        
         tfrecherche.getText();
        
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
    private void AffichS(MouseEvent event) {
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
    

    @FXML
    private void SuppE(ActionEvent event) {
       ServiceSeance ss = new ServiceSeance();
        ss.supprimer(new Seance((int) Float.parseFloat(tfSS.getText())));
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Séance supprimée")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
    }

    

    @FXML
    private void modifB(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/ISModifier.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void AjoutB(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/ISAjout.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void telecharger(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException {
    
        try {
            String file_name="D:\\PDF\\Seance.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = Dbconnection.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from seance"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(5);
            PdfPCell c1 = new PdfPCell(new Phrase("IdSeance"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Capacite"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("IdCoach"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("DateS"));
            t.addCell(c4);
            PdfPCell c5 = new PdfPCell(new Phrase("activteid"));
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
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  PDF téléchargé vérifier votre dossier")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
        //JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier :D");
    }

@FXML
    private void mailB(MouseEvent event) throws SQLException {
        Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            ServiceSeance se = new services.ServiceSeance();
            String requete = "SELECT * FROM Seance";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                se.sendmail(new Seance (rs.getInt(1), rs.getInt(2), rs.getInt(3) , rs.getString(4) , rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Mail envoyé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show(); 
    }

    @FXML
    private void retourp(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/Acc2.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
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
    private void gotoemploi(MouseEvent event)   throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui3/Acc2.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
        
    }

    @FXML
    private void gotomateriel(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
    
    @FXML
    private void gotocommande(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui2/Boutique.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
    
    
    
    
    
    
    
    
    
    }

    


    

    
