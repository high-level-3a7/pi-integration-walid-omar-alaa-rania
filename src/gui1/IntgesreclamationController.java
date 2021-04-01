/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Reclamation;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author karbo
 */
public class IntgesreclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tabrec;
    @FXML
    private TableColumn<Reclamation, String> userCol;
    @FXML
    private TableColumn<Reclamation, String> etatCol;
    @FXML
    private TableColumn<Reclamation, String> typeCol;
    @FXML
    private TableColumn<Reclamation, String> contenuCol;
    @FXML
    private TableColumn<Reclamation, String> dateCol1;
    @FXML
    private TextField searchFid;
    @FXML
    private ComboBox combNom;
    
    ObservableList<Reclamation>  reclamationList = FXCollections.observableArrayList();
    Reclamation reclamation = null ;
    ServiceReclamation sr = new ServiceReclamation();
    @FXML
    private TableColumn<Reclamation , String> editCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list = (ObservableList<String>) sr.listNomUsers();
        combNom.setItems(list);
        loaddate();
        
        FilteredList<Reclamation> filteredData = new FilteredList<>(FXCollections.observableList(reclamationList));
        searchFid.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
        tabrec.setItems(filteredData);
    }    

    @FXML
    private void adminorclient(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void refresh() {
        reclamationList.clear();
        int idu = sr.getIduserByNom((String) combNom.getValue());
        reclamationList = (ObservableList<Reclamation>) sr.afficherparUserId(idu);
        tabrec.setItems(reclamationList);
    }

    @FXML
    private void sort(MouseEvent event) {
        reclamationList.clear();
        int x = sr.getIduserByNom((String) combNom.getValue());
        reclamationList = (ObservableList<Reclamation>) sr.trier2(x);
        tabrec.setItems(reclamationList);
    }

    private void chercher(MouseEvent event) {
        reclamationList.clear();
        int x = sr.getIduserByNom((String) combNom.getValue());
        reclamationList = (ObservableList<Reclamation>) sr.rechercher2(searchFid.getText(),x);
        tabrec.setItems(reclamationList);
    }

    @FXML
    private void getAddrecint(MouseEvent event) throws IOException {
        
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/addreclamation.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
    private void loaddate() {
        
        refresh();
        
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contenuCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateCol1.setCellValueFactory(new PropertyValueFactory<>("daterec"));
        
        //add cell of button edit 
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
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
                            
                            reclamation = tabrec.getSelectionModel().getSelectedItem();
                            sr.supprimer(reclamation);
                            refresh();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              reclamation = tabrec.getSelectionModel().getSelectedItem();
                              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui1/modifreclamation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            ModifreclamationController modifreclamationController = loader.getController();
                            modifreclamationController.setTextField(reclamation.getId(), reclamation.getUser_id(),reclamation.getEtat_id(),reclamation.getType_id(),reclamation.getContenu(),reclamation.getDaterec());
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
         editCol.setCellFactory(cellFoctory);
         tabrec.setItems(reclamationList);
         
         
 }
    
    private Predicate<Reclamation> createPredicate(String searchText){
    return reclamation -> {
        if (searchText.isEmpty() || searchText == null) return true;
        return searchFindsOrder(reclamation, searchText);
    };
}

    private boolean searchFindsOrder(Reclamation reclamation, String searchText){
        return (reclamation.getContenu().toLowerCase().contains(searchText)||reclamation.getEtat().toLowerCase().contains(searchText))  ;
    }
    
    private ObservableList<Reclamation> filterList(List<Reclamation> list, String searchText){
    List<Reclamation> filteredList = new ArrayList<>();
    for (Reclamation reclamation : list){
        if(searchFindsOrder(reclamation, searchText)) filteredList.add(reclamation);
    }
    return FXCollections.observableList(filteredList);
    }

    @FXML
    private void print_pdf(MouseEvent event) throws FileNotFoundException, Exception {
        
        //creation de pdf
        File out = new File ("reclamations.pdf");
        FileOutputStream fos = new FileOutputStream(out);
        PDF pdf = new PDF(fos);
        Page page = new Page(pdf, A4.PORTRAIT);
        
        
        //fonts
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);
        Font f2 = new Font(pdf, CoreFont.HELVETICA);
        
        Table table = new Table();
        List<List<Cell>> tableData = new ArrayList<List<Cell>>();
        
        //table row
        List<Cell> tableRow = new ArrayList<>();
        Cell cell = new Cell(f1, userCol.getText());
        tableRow.add(cell);
        
        cell = new Cell(f1, etatCol.getText());
        tableRow.add(cell);
        
        cell = new Cell(f1, typeCol.getText());
        tableRow.add(cell);
        
        cell = new Cell(f1, contenuCol.getText());
        tableRow.add(cell);
        
        cell = new Cell(f1, dateCol1.getText());
        tableRow.add(cell);
        
        tableData.add(tableRow);
        List<Reclamation> items = tabrec.getItems();
        
        for (Reclamation rec : items) {
            Cell userid = new Cell(f2, String.valueOf(rec.getUser_id()));
            Cell etatid = new Cell(f2, String.valueOf(rec.getEtat_id()));
            Cell typeid = new Cell(f2, String.valueOf(rec.getType_id()));
            Cell contenu = new Cell(f2, rec.getContenu());
            Cell date = new Cell(f2, rec.getDaterec().toString());
            
            tableRow = new ArrayList<Cell>();
            tableRow.add(userid);
            tableRow.add(etatid);
            tableRow.add(typeid);
            tableRow.add(contenu);
            tableRow.add(date);
            
            tableData.add(tableRow);
        }
        
        table.setData(tableData);
        table.setPosition(70f,60f);
        table.setColumnWidth(0, 50f);
        table.setColumnWidth(1, 50f);
        table.setColumnWidth(2, 50f);
        table.setColumnWidth(3, 300f);
        table.setColumnWidth(4, 100f);
        
        //creating a new page if it's full
        while(true){
            table.drawOn(page);
            if(!table.hasMoreData()){
                table.resetRenderedPagesCount();
                break;
            }
            page = new Page(pdf, A4.PORTRAIT);
        }
        pdf.close();
        fos.close();
        System.out.println("saved tp : "+ out.getAbsolutePath());
        
    }

      @FXML
    private void gogestmat(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("/gui1/intclient.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
}
