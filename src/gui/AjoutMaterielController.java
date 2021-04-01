/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import gestion_materiel.DataSource;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import static jdk.nashorn.internal.runtime.Debug.id;
import models.Materiel;
import models.categorie;
import org.controlsfx.control.Notifications;
import service.ServiceEtat;
import service.ServiceCategorie;
import service.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class AjoutMaterielController implements Initializable {

    @FXML
    private TextField nom_materiel;
    @FXML
    private TextField marque_materiel;
    @FXML
    private TextField quantite_materiel;
    
    @FXML
    private ComboBox<String> comb_Etat;
   
    @FXML
    private ComboBox<String> nom;
    String urlfinal;
    String img;
    String s;
    @FXML
    private AnchorPane ajoutMat;
    @FXML
    private TextField source;
    @FXML
    private ImageView path;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fillcombo();
        fillcombo1();
      // getIdbyEtat(String ch);
    }    

   

    @FXML
    private void saveMateriel(ActionEvent event) throws SQLException 
    {
         
      //  System.out.println("clicked");
        ServiceMateriel SM = new ServiceMateriel();
        ServiceCategorie SE =new ServiceCategorie();
        ServiceEtat SE1 = new ServiceEtat();
//    javax.sql.rowset.serial.SerialBlob sb = new SerialBlob(s.getBytes());
            int x=SE.getIdbyNom(nom.getValue());
            int y= SE1.getIdbyEtat( comb_Etat.getValue());
            String nom=nom_materiel.getText();
 if (veriftextfield(nom_materiel)|veriftextfield(marque_materiel)|veriftextfield(quantite_materiel)){
                                 CreerAlert("Erreur","Les champs doivent etre remplis");

 }else {
                 int quantite = Integer.parseInt(quantite_materiel.getText());

     if(SM.verifactexist(nom)!=0){
                  CreerAlert("Erreur","Le nom existe déja");
     }else if(quantite <0 ){
                  CreerAlert("Erreur","Vérifer la quantite");
     }else{
          Materiel m= new Materiel(nom_materiel.getText(), marque_materiel.getText(), Integer.parseInt(quantite_materiel.getText()),source.getText(),x,y);
         SM.ajouter(m);
         Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Image  Ajouté")
                              .position(Pos.BOTTOM_RIGHT)
                              .hideAfter(javafx.util.Duration.seconds(7));
               n.darkStyle();
               n.show();  
     }
 }
    
 
          
        }
    
    
     public void fillcombo(){
            Connection cnx = DataSource.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select nom  from  categorie    ";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("nom"));
              
                       }
              
           nom.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
//     public int GetIdfromCombo(int id){
//        try {
//            Connection cnx = DataSource.getInstance().getCnx();
//            String requete = "select  id from categorie where nom=?   ";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setString(1,(String)nom.getSelectionModel().getSelectedItem());
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()){
//                return (rs.getInt("id"));
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AjoutMaterielController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
   //  }

//    private void recuperer_url(ActionEvent event) {
//         FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"),
//                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
//                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                new FileChooser.ExtensionFilter("All Files", "*.*"));
//        File file = fileChooser.showOpenDialog(null);
//        if (file != null) {
//             urlfinal=file.toPath().toUri().toString();
//            img=urlfinal.substring(8);
//        }
//            id_img.setText(urlfinal);
//            System.out.println(file.toPath().toUri().toString());
//           
//            System.out.println("urlFINAL "+img);
//
//    }
    public boolean veriftextfield (TextField t)
{
    if (t.getText().isEmpty() )
    return true ;
           return false; 
}
    public void  CreerAlert (String titre ,String text )
{
    Alert alert =new Alert (Alert.AlertType.ERROR) ;
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.show();
}
       public void fillcombo1(){
            Connection cnx = DataSource.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select type_etat  from  etat    ";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
     
           while(rs.next()){
             
comboString.add(rs.getString("type_etat"));
              
                       }
              
           comb_Etat.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

     }

    @FXML
    private void addimage(ActionEvent event) {
//         FileChooser fileChooser = new JFileChooser();
//    
//     fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
//		fileChooser.addChoosableFileFilter(filter);
//		int result = fileChooser.showSaveDialog(null);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = fileChooser.getSelectedFile();
//			String path = selectedFile.getAbsolutePath();
//			System.out.println(path);
//                       
//                        s = path;
//                      
//		} else if (result == JFileChooser.CANCEL_OPTION) {
//			System.out.println("No Data");
//		}
//        

        final  FileChooser fileChooser = new FileChooser();
       

        //upload.setOnAction((final ActionEvent e) -> {
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {

                img=file.toURI().toString();
                source.setText(img);
                Image image1 = new Image(img);
                path.setImage(image1);
                }
        //});
        

    }

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
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
}
    
  
       


    
