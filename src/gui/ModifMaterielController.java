/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gestion_materiel.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import  service.ServiceMateriel;
import models.Materiel;
import service.ServiceEtat;
import service.ServiceCategorie;
import service.ServiceMateriel;
/**
 * FXML Controller class
 *
 * @author walid
 */
public class ModifMaterielController implements Initializable {

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_marque;
    @FXML
    private TextField id_quatite;
    
    @FXML
    private ComboBox<String> id_cate_materiel;
    @FXML
    private ComboBox<String> id_etat_materiel;
public ObservableList<Materiel> data = FXCollections.observableArrayList();
 Materiel materiel = new Materiel();
    int materielId  ;
    String s;
     String img;
    @FXML
    private TextField source;
    @FXML
    private ImageView path;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcombo();
         fillcombo1();
           
  
            
        
    }    
//public void getModifierMateriel(Materiel m) {
//        id_id.setText(Integer.toString(m.getId()));
//        id_nom.setText(m.getNom());
//        id_image.setText(m.getImage());
//        id_quatite.setText(Integer.toString(m.getQuantite()));
//        id_marque.setText(m.getMarque());
//        id_cate_materiel.setText(Integer.toString(m.getId_categorie()));
//        id_etat_materiel.setText(Integer.toString(m.getId_etat()));
//}
    @FXML
    private void ModifMateriel(ActionEvent event) throws SQLException {
        
        ServiceMateriel SM = new ServiceMateriel();
           
        ServiceCategorie SE =new ServiceCategorie();
        ServiceEtat SE1 = new ServiceEtat();
        //javax.sql.rowset.serial.SerialBlob sb = new SerialBlob(s.getBytes());
        int x=SE.getIdbyNom(id_cate_materiel.getValue());
           int y= SE1.getIdbyEtat( id_etat_materiel.getValue());
             Materiel m= new Materiel(materielId,id_nom.getText(), id_marque.getText(), Integer.parseInt(id_quatite.getText()),source.getText(),x,y);
        
        SM.modifier(m);
//Materiel mat = new Materiel();
//
//        mat.setId(materielId);
//     //   System.out.println("id"+mat.setId(materielId)+");
//     
//        mat.setNom(id_nom.getText());
//        mat.setMarque(id_marque.getText());
//        mat.setQuantite(Integer.parseInt(id_quatite.getText()));
//        mat.setId_categorie(SE.getIdbyNom((String) id_cate_materiel.getValue()));
//        mat.setId_etat(SE.getIdbyNom((String) id_etat_materiel.getValue()));
//        SM.modifier(mat);
//        
        

        
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
              
           id_cate_materiel.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
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
              
           id_etat_materiel.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}  
    void setTextField(int id, String nom, String marque, int quantite,int id_categorie,int id_etat) {
        materielId = id;
   //   int id1 = Integer.parseInt(ide.getText());
        id_nom.setText(nom);
        id_marque.setText(marque);
        
    }

    @FXML
    private void modifimage(ActionEvent event) {
        
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
    

