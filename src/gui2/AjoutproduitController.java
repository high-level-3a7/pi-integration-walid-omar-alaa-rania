/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesProduit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.produit;
import gestion_materiel.DataSource ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javax.xml.datatype.Duration;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutproduitController implements Initializable {


    @FXML
    private Button ajouter;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    
    private ImageView path;
    @FXML
    private TextField source;
    String img;
     String urlfinal;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException  {
        ServicesProduit sp = new ServicesProduit();
        sp.ajouter(new produit(nom.getText(),Float.parseFloat(prix.getText()), description.getText(),source.getText()));
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Produit Ajouté")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(5));
               n.darkStyle();
               n.show();
       
        
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
