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
import javafx.scene.control.ToggleGroup;

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
                this.meaningChart.clear();
                if (!this.bAproximada.isSelected() && !this.bPosfijo.isSelected()){
                    List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                    prepareComponents(meaningChart, wordsListContainer);
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound(wordsListContainer);
                   
                }else if (this.bPosfijo.isSelected()){
                    //como se comporta cuando hacer busqueda externa
                    //cual es mi lista de nuevas posibles palabra?
                    List<String> lpalabras=App.trieApp.getWordsEndWith(searchBar.getText());
                    prepareComponents(meaningChart, wordsListContainer);
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound(wordsListContainer);
                }
                else if (this.bAproximada.isSelected()){
                   List<String> lpalabras=App.trieApp.getSimilarWords(searchBar.getText());
                    prepareComponents(meaningChart, wordsListContainer);
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound(wordsListContainer);
                    
                }
                 
                 });
                
                this.bPosfijo.setOnAction(eh->{
                    if (bPosfijo.isSelected()){
                        this.bAproximada.setSelected(false);
                        prepareComponents(meaningChart, wordsListContainer);
                         List<String> lpalabras=App.trieApp.getWordsEndWith(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras); 
                         showNotfound(wordsListContainer);
                         
                    }
                    else if (!bAproximada.isSelected() && !bPosfijo.isSelected()){
                         this.bAproximada.setSelected(false);
                         prepareComponents(meaningChart, wordsListContainer);
                         List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras); 
                         showNotfound(wordsListContainer);
                        
                    }
                   
                });
                this.bAproximada.setOnAction(eh->{
                    if (bAproximada.isSelected()){
                        this.bPosfijo.setSelected(false);
                        prepareComponents(meaningChart, wordsListContainer);
                    List<String> lpalabras=App.trieApp.getSimilarWords(searchBar.getText());
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound(wordsListContainer);
                    
                    }  else if (!bAproximada.isSelected() && !bPosfijo.isSelected()){
                         this.bAproximada.setSelected(false);
                         prepareComponents(meaningChart, wordsListContainer);
                         List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras);
                         showNotfound(wordsListContainer);
                          
                    }
                    
                });
                 
        wordsListContainer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
              
             
              if (wordsListContainer.getSelectionModel().getSelectedItem()!=null){
                 palabraActual=wordsListContainer.getSelectionModel().getSelectedItem();
                 if (bAproximada.isSelected()){
                     palabraActual=palabraActual.substring(1);
                 }
                 else if (bPosfijo.isSelected()){
                     palabraActual=palabraActual.substring(1);
                 }
              meaning=App.trieApp.getMeaningFromWord(palabraActual);
              meaningChart.setText(meaning);
              }
             
             

                
              
              
            }
            
            
        });
    
    }    
    public static void showNotfound(ListView<String> s){
        if (s.getItems().isEmpty()){
            s.getItems().add("Word not found :(");
        }
        
    }
    public static void prepareComponents(TextArea tx,ListView<String> s ){
         tx.clear();
         s.getItems().clear();
    }
}
