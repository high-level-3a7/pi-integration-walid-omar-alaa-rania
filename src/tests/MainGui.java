 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import gestion_materiel.DataSource;
import gui.AjoutMaterielController;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author walid
 */
public class MainGui extends Application {
    final ObservableList options = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws IOException {
        
     FXMLLoader Loader = new FXMLLoader(getClass().getResource("../gui3/ISAjout.fxml"));
        Parent root = Loader.load () ;
        Scene scene = new Scene(root);
       primaryStage.setTitle("Gestion Materiel");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("test1");
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
  
            
            
}

