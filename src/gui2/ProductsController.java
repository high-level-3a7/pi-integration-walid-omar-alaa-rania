/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import service.ServicesProduit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.produit;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProductsController implements Initializable {

   @FXML
    private HBox hbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hbox.getChildren().clear();
        ServicesProduit sp = new ServicesProduit();
        List<produit> produits=new ArrayList<>();
        produits=sp.afficher();
//        for (int i = 0; i < materiels.size(); i++) {
//            System.out.println("materiel :"+materiels.get(i));
//        }
        try {
            displayMateriels(produits);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void displayMateriels(List<produit> produits) throws SQLException {
        hbox.getChildren().clear();
        for (int i = 0; i < produits.size(); i++) {
            produit current = produits.get(i);
            Pane mainpane = new Pane();
            mainpane.setPrefHeight(325);
            mainpane.setPrefWidth(216);
            
            Image thumb = new Image(current.getImage());
            final ImageView thumbnail = new ImageView();

            thumbnail.setLayoutX(0);
            thumbnail.setLayoutY(0);
            thumbnail.setFitHeight(325);
            thumbnail.setFitWidth(216);
            thumbnail.setPreserveRatio(false);
            thumbnail.setImage(thumb);
            mainpane.getChildren().add(thumbnail);
            
            Pane blackpane = new Pane();
            blackpane.setPrefHeight(325);
            blackpane.setPrefWidth(216);
            
            mainpane.getChildren().add(blackpane);
            
            
           /* Label nom = new Label();
            nom.setTextFill(Color.WHITE);
            nom.setText(current.getNom());
            nom.setPrefWidth(183);
            nom.setPrefHeight(34);
            nom.setLayoutX(19);
            nom.setLayoutY(20);
            
            mainpane.getChildren().add(nom);
            
            Pane marquepane = new Pane();
            marquepane.setPrefWidth(126);
            marquepane.setPrefHeight(34);
            marquepane.setLayoutX(12);
            marquepane.setLayoutY(269);
            marquepane.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(23), Insets.EMPTY)));
           
            
            Label marque = new Label();
            marque.setTextFill(Color.BLACK);
            marque.setText(current.getMarque());
            marque.setPrefHeight(29);
            marque.setPrefWidth(120);
            marque.setLayoutX(3);
            marque.setLayoutY(3);
            
            marquepane.getChildren().add(marque);
            
            mainpane.getChildren().add(marquepane);
            
  */          
            hbox.getChildren().add(mainpane);
    }
    
    
    }
}