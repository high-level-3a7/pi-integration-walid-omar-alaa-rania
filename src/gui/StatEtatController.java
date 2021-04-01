
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gestion_materiel.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class StatEtatController implements Initializable {

    @FXML
    private PieChart stat_etat;
    ObservableList<PieChart.Data> stat_etatData;
    ArrayList<Integer> cell = new ArrayList<Integer>();
    ArrayList<String> name = new ArrayList<String>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        stat_etat.setData(stat_etatData);
    }    
    public void loadData (){
        String reqete = "SELECT * FROM etat ";
        stat_etatData=FXCollections.observableArrayList();
        try {
        Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement pst = cnx.prepareStatement(reqete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            stat_etatData.add(new PieChart.Data(rs.getString("type_etat"),rs.getInt("id")));
            name.add(rs.getString("type_etat"));
            cell.add(rs.getInt("id"));
        }
        
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

