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
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private RadioButton bPosfijo;
    @FXML
    private RadioButton bAproximada;
    @FXML
    private ListView<String> wordsListContainer;
    @FXML
    private AnchorPane meaningChart;
    @FXML
    private ImageView btnAdd;
    @FXML
    private ImageView btnDelete;
    @FXML
    private ImageView btnExport;
    @FXML
    private ImageView btnImport;
    private List<String> palabras;
    String palabraActual=null;
    String meaning=null;
    @FXML
    private ImageView btnModoJuego;
    @FXML
    private ImageView btnEstadisticas;
    @FXML
    private ImageView searchIcon;
    @FXML
    private Label labelPalabra;
    @FXML
    private Label labelMeaning;
    @FXML
    private Label eliminarLabel;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                setTextStyle();
                initializeicons();
                searchBar.setOnKeyTyped(eh->{
                this.labelMeaning.setText(null);
                this.labelPalabra.setText(null);
                this.eliminarLabel.setText(null);
                if (!this.bAproximada.isSelected() && !this.bPosfijo.isSelected()){
                    List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                    prepareComponents();
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound();
                   
                }else if (this.bPosfijo.isSelected()){
                    //como se comporta cuando hacer busqueda externa
                    //cual es mi lista de nuevas posibles palabra?
                    List<String> lpalabras=App.trieApp.getWordsEndWith(searchBar.getText());
                    prepareComponents();
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound();
                }
                else if (this.bAproximada.isSelected()){
                   List<String> lpalabras=App.trieApp.getSimilarWords(searchBar.getText());
                    prepareComponents();
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound();
                    
                }
                 
                 });
                
                this.bPosfijo.setOnAction(eh->{
                    if (bPosfijo.isSelected()){
                        this.bAproximada.setSelected(false);
                        prepareComponents();
                         List<String> lpalabras=App.trieApp.getWordsEndWith(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras); 
                         showNotfound();
                         
                    }
                    else if (!bAproximada.isSelected() && !bPosfijo.isSelected()){
                         this.bAproximada.setSelected(false);
                         prepareComponents();
                         List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras); 
                         showNotfound();
                        
                    }
                   
                });
                this.bAproximada.setOnAction(eh->{
                    if (bAproximada.isSelected()){
                        this.bPosfijo.setSelected(false);
                        prepareComponents();
                    List<String> lpalabras=App.trieApp.getSimilarWords(searchBar.getText());
                    wordsListContainer.getItems().addAll(lpalabras);
                    showNotfound();
                    
                    }  else if (!bAproximada.isSelected() && !bPosfijo.isSelected()){
                         this.bAproximada.setSelected(false);
                         prepareComponents();
                         List<String> lpalabras=App.trieApp.getPossibleWord(searchBar.getText());
                         wordsListContainer.getItems().addAll(lpalabras);
                         showNotfound();
                          
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
                showWordMeaning(palabraActual, meaning);
                
              }
              
              
             
             

              
            }
            
            
        });

    }
    public void setTextStyle(){
        this.labelMeaning.setStyle(
        "-fx-font-size: 30pt;"
        );
         this.labelPalabra.setStyle(
        "-fx-font-size: 15pt;"
                + "-fx-font-style: bold;"+
                "-fx-font-style:italic;"
        );
         
        this.eliminarLabel.setStyle("-fx-text-fill: red; -fx-padding: 5px 10px;");
    }
    public void showWordMeaning(String pal,String meaning){
        this.labelPalabra.setText(pal);
        this.labelMeaning.setText(meaning);
        this.eliminarLabel.setText("Si desea eliminar la palabra de click al icono eliminar");
       
    }
    public void initializeicons(){
                this.searchIcon.setImage(new Image("file:iconos\\search.png"));
                this.btnAdd.setImage(new Image("file:iconos\\add.png"));
                // Cambiar el cursor cuando el mouse entra en el ImageView
                btnAdd.setOnMouseEntered(event -> {
                    btnAdd.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                    btnAdd.setCursor(Cursor.HAND);
                });

                // Restaurar el cursor cuando el mouse sale del ImageView
                btnAdd.setOnMouseExited(event -> {
                    btnAdd.setStyle("-fx-effect: none;");
                    btnAdd.setCursor(Cursor.DEFAULT);
                });
                this.btnDelete.setImage(new Image("file:iconos\\delete.png"));
                // Cambiar el cursor cuando el mouse entra en el ImageView
                btnDelete.setOnMouseEntered(event -> {
                    btnDelete.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                    btnDelete.setCursor(Cursor.HAND);
                });

                // Restaurar el cursor cuando el mouse sale del ImageView
                btnDelete.setOnMouseExited(event -> {
                    btnDelete.setStyle("-fx-effect: none;");
                    btnDelete.setCursor(Cursor.DEFAULT);
                });
                this.btnExport.setImage(new Image("file:iconos\\export.png"));
                this.btnImport.setImage(new Image("file:iconos\\import.png"));
                this.btnEstadisticas.setImage(new Image("file:iconos\\business-report.png"));
                this.btnModoJuego.setImage(new Image("file:iconos\\computer-game.png"));
    }
    public void showNotfound(){
        if (wordsListContainer.getItems().isEmpty()){
            wordsListContainer.getItems().add("Word not found :(");

            meaning= "Añada la palabra a su diccionario";
            showWordMeaning("No se ha encontrado", meaning);
        }
        
    }
    public  void prepareComponents(){
        
         this.labelMeaning.setText(null);
         this.labelPalabra.setText(null);
         this.eliminarLabel.setText(null);
         wordsListContainer.getItems().clear();
    }
    
    @FXML
    public void anadirPalabra(){
        App.trieApp.insert(searchBar.getText());
        System.out.println("Se ha anadido: " + searchBar.getText());
        App.trieApp.print();
      //Falta cuando quiera anadir significado
      this.prepareComponents();
    }    
    
    @FXML
    public void borrarPalabra(){
        App.trieApp.delete(searchBar.getText());
        System.out.println("Se ha eliminado: " + searchBar.getText());
        App.trieApp.print();
        this.prepareComponents();
    }
}
