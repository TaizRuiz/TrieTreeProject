/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private Button btnBuscar;
    @FXML
    private RadioButton bPosfijo;
    @FXML
    private RadioButton bAproximada;
    @FXML
    private ListView<String> wordsListContainer;
    @FXML
    private TextArea meaningChart;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExport;
    @FXML
    private Button btnImport;
    private List<String> palabras;
    String palabraActual=null;
    String meaning=null;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
     
        searchBar.setOnKeyTyped(eh->{
            List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
            wordsListContainer.getItems().clear();
            wordsListContainer.getItems().addAll(lpalabras);
        });
        
        wordsListContainer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
              palabraActual=wordsListContainer.getSelectionModel().getSelectedItem();

              meaning=App.trieApp.getMeaningFromWord(palabraActual);
              meaningChart.setText(meaning);
                
              
              
            }
            
            
        });
    
    }    
    
}
