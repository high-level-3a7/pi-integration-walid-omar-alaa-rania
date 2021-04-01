/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesProduit;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import static java.lang.Integer.parseInt;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import models.commande;
import models.produit;
import org.controlsfx.control.Notifications;
import gestion_materiel.DataSource;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierproduitController implements Initializable {

    @FXML
    private TextField prix;
   
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String> idproduit;
    @FXML
    private TextField description;
    @FXML
    private TextField nom;
    @FXML
    private TextField source;
    @FXML
    private ImageView path;
  String img;
     String urlfinal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillcombo();  // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
         ServicesProduit sp = new ServicesProduit();
          int x=sp.getIdproduit(idproduit.getValue());
     sp.modifier(new produit( nom.getText(), Float.parseFloat(prix.getText()),description.getText(),source.getText(),x)) ;
       Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Produit modifié")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(3));
               n.darkStyle();
               n.show();
    }

  private void fillcombo() {
         Connection cnx = DataSource.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
     // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select idproduit  from  produit ";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("idproduit"));
              
                       }
              
           idproduit.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
    
    
}

    @FXML
    private void addimage(ActionEvent event) {
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
    }
      
    }    