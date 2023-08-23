/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.print.attribute.standard.Media;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */


public class ModoJuegoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int numPalabras=0;
    private int aciertos=0;
    private int tiempo=0;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView faceProgress;
    @FXML
    private ImageView faceProgress1;
    @FXML
    private VBox containerPalabra;
    
    @FXML
    private HBox casillasPalabras;
    @FXML
    private Label wordMeaning;
    @FXML
    private Button siguiente;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.containerPalabra.setAlignment(javafx.geometry.Pos.CENTER);
       this.casillasPalabras.setAlignment(javafx.geometry.Pos.CENTER);
       Timeline tl=new Timeline(new KeyFrame(Duration.seconds(1), e->{
           tiempo++;
           setTimeIcons(tiempo);
       
       }));
       
       tl.setCycleCount(30);
       tl.play();
       mostarCasillas();
       
        tl.setOnFinished(eh->{
            //pantalla que muestra el resultado
            System.out.println(numPalabras+":"+aciertos);
          
            this.casillasPalabras.getChildren().clear();
            this.wordMeaning.setText(this.aciertos+" / "+this.numPalabras);
            for (int i=1; i<=this.aciertos;i++){
                this.generarEstrella();
            }
            for (int i=1; i<=this.numPalabras-this.aciertos;i++){
                this.generarEstrellaFallo();
            }
            this.siguiente.setOnAction(e->{
                e.consume();
            });
            
        });
           
      
      
        this.siguiente.setOnAction(eh->{
           numPalabras++;
           voidVerificar();
           mostarCasillas();
           
            
        });
     
    }
    private void generarEstrellaFallo(){
        String ruta="file:iconos\\fallo.png";
        ImageView iv=new ImageView(new Image(ruta));
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        this.casillasPalabras.getChildren().add(iv);
    }
    private void generarEstrella(){
        String ruta="file:iconos\\star.png";
        ImageView iv=new ImageView(new Image(ruta));
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        this.casillasPalabras.getChildren().add(iv);
    }
    private void voidVerificar(){
        if (casillasPalabras!=null){
                String palabra="";
                ObservableList<Node> ol=this.casillasPalabras.getChildren();
                for (Node n: ol){
                    TextArea ta=(TextArea) n;
                    palabra+=ta.getText();
                }
                if (App.trieApp.containsWord(palabra)){
                    aciertos++;
                }
            }
    }
    private void mostarCasillas(){
      this.casillasPalabras.getChildren().clear();
      String s=generateRandomWord(App.trieApp.getAllWordsFromTrie());
      List<Integer> indices=randomIndexes(s);
      generarEspacios(s);
      for (Integer inte: indices){
          bloquearEspacios(inte);
      }
        LlenarTexto(s);
        
      this.wordMeaning.setText(App.trieApp.getMeaningFromWord(s));
          
          
    }
    private void LlenarTexto(String s){
        char[] strings=s.toCharArray();
         ObservableList<Node> ol=this.casillasPalabras.getChildren();
        for (int i=0;i<ol.size();i++){
            TextArea ta=(TextArea)ol.get(i);
            if (!ta.isEditable()){
                ta.setText(String.valueOf(strings[i]));
            }
        }
    }
    private void bloquearEspacios(int indice){
        ObservableList<Node> ol=this.casillasPalabras.getChildren();
        for (int i=0;i<ol.size();i++){
            TextArea ta=(TextArea)ol.get(i);
            ta.setStyle("  -fx-font-family: \"Arial\";" +
"    -fx-font-size: 12px;\n" +
"    -fx-background-color: #FFFFFF; " +
"    -fx-text-fill: #ac3f3f; " +
"     \n" +
"    -fx-border-color: #D2B48C; " +
"    -fx-border-width: 2px;   "+
"    -fx-border-radius: 5px;  " +
"    -fx-background-radius: 5px;  "+
"    -fx-background-insets: 0; ");
            if (i==indice){
                ta.setEditable(false);
                
            }
        }
        
    }
    private void generarEspacios(String s){
        char[] strings=s.toCharArray();
              for (char c:strings){
                  TextArea ta=new TextArea();
                  ta.setStyle("style.css");
                  ta.setOnKeyTyped(eh->{
                      if (ta.getText().length()==1){
                          ta.setEditable(false);
                      }
                  });
                  ta.setMaxSize(60, 60);
                  this.casillasPalabras.getChildren().add(ta);
              }
    }
    public List<Integer> randomIndexes (String s){
       
       int i=s.length()/2;
       List<Integer> indices=new ArrayList<>();
          while(indices.size()!=i){
              Random random = new Random(); 
              int randomIndex = random.nextInt(s.length());
              if (!indices.contains(randomIndex)){
                  indices.add(randomIndex);
              }
          } 
          return indices;
    }
    
   public String generateRandomWord(List<String> wordsList) {
       if (wordsList!=null){
           
          Random random = new Random(); 
          int randomIndex = random.nextInt(wordsList.size());
          return wordsList.get(randomIndex).substring(1);
       }
       return null;
    }
   
    public void timeOver(int i){
        if (i==20){
            //juego acaba
        }
    }
    
     public void setTimeIcons(int i){
        if (i>=0 && i<=6){
               this.timeLabel.setText(String.valueOf(i));
               this.faceProgress.setImage(new Image("file:iconos\\good.jpg"));
               this.faceProgress1.setImage(new Image("file:iconos\\good.jpg"));
           }else  if (i>=7 && i<=15){
               this.timeLabel.setText(String.valueOf(i));
               this.faceProgress.setImage(new Image("file:iconos\\gettinWorse.jpg"));
                 this.faceProgress1.setImage(new Image("file:iconos\\gettinWorse.jpg"));
               
           }else if (i>=16 && i<=25){
               this.timeLabel.setText(String.valueOf(i));
               this.faceProgress.setImage(new Image("file:iconos\\gettingbad.jpg"));
               this.faceProgress1.setImage(new Image("file:iconos\\gettingbad.jpg"));
           } 
           else if (i>=26 && i<=30){
               this.timeLabel.setText(String.valueOf(i));
               this.faceProgress.setImage(new Image("file:iconos\\badbad.jpg"));
               this.faceProgress1.setImage(new Image("file:iconos\\badbad.jpg"));
           } 
    }
   

}
