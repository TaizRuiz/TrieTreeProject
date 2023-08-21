/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    String palabraActual = null;
    String meaning = null;
    
    @FXML
    private ImageView btnModoJuego;
    @FXML
    private ImageView btnEstadisticas;
    @FXML
    private ImageView searchIcon;
    @FXML
    private TextField labelPalabra;
    @FXML
    private TextArea labelMeaning;
    @FXML
    private Label eliminarLabel;
    @FXML
    private Button tipAdd;
    @FXML
    private Button tipDelete;
    @FXML
    private Button tipExport;
    @FXML
    private Button tipImport;
    @FXML
    private Button tipStatistics;
    @FXML
    private Button tipGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startToolTips();
        this.eliminarLabel.setText(null);
        setTextStyle();
        initializeicons();
        searchBar.setOnKeyTyped(eh -> {
            this.labelMeaning.setText(null);
            this.labelPalabra.setText(null);
            this.eliminarLabel.setText(null);

            if (!this.bAproximada.isSelected() && !this.bPosfijo.isSelected()) {
                List<String> lpalabras = App.trieApp.getPossibleWord(searchBar.getText());
                prepareComponents();
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            } else if (this.bPosfijo.isSelected()) {
                //como se comporta cuando hacer busqueda externa
                //cual es mi lista de nuevas posibles palabra?
                List<String> lpalabras = App.trieApp.getWordsEndWith(searchBar.getText());
                prepareComponents();
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();
            } else if (this.bAproximada.isSelected()) {
                List<String> lpalabras = App.trieApp.getSimilarWords(searchBar.getText());
                prepareComponents();
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            }

        });

        this.bPosfijo.setOnAction(eh -> {
            if (bPosfijo.isSelected()) {
                this.bAproximada.setSelected(false);
                prepareComponents();
                List<String> lpalabras = App.trieApp.getWordsEndWith(searchBar.getText());
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            } else if (!bAproximada.isSelected() && !bPosfijo.isSelected()) {
                this.bAproximada.setSelected(false);
                prepareComponents();
                List<String> lpalabras = App.trieApp.getPossibleWord(searchBar.getText());
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            }

        });
        this.bAproximada.setOnAction(eh -> {
            if (bAproximada.isSelected()) {
                this.bPosfijo.setSelected(false);
                prepareComponents();
                List<String> lpalabras = App.trieApp.getSimilarWords(searchBar.getText());
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            } else if (!bAproximada.isSelected() && !bPosfijo.isSelected()) {
                this.bAproximada.setSelected(false);
                prepareComponents();
                List<String> lpalabras = App.trieApp.getPossibleWord(searchBar.getText());
                wordsListContainer.getItems().addAll(lpalabras);
                showNotfound();

            }

        });

        this.tipStatistics.setOnAction(eh -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/trieproject/graphic.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

                Stage mystage = (Stage) this.tipStatistics.getScene().getWindow();
                

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        wordsListContainer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                if (wordsListContainer.getSelectionModel().getSelectedItem() != null) {
                    palabraActual = wordsListContainer.getSelectionModel().getSelectedItem();
                    if (bAproximada.isSelected()) {
                        palabraActual = palabraActual.substring(1);
                    } else if (bPosfijo.isSelected()) {
                        palabraActual = palabraActual.substring(1);
                    }
                    meaning = App.trieApp.getMeaningFromWord(palabraActual);
                    showWordMeaning(palabraActual, meaning);

                }

            }

        });
        this.btnImport.setOnMouseClicked(eh -> {
            System.out.println("clickeado");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                String ruta = selectedFile.getPath().toString();
                App.rutaActual = ruta;
                System.out.println("ruta seleccionada:" + ruta);
                App.trieApp = App.loadDictionaryFromImport(ruta);

                App.trieApp.print();
                try {
                    App.setRoot("ventanaPrincipal");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                eh.consume();
            }

        });

    }

    public void setTextStyle() {
        this.labelMeaning.setStyle(
                "-fx-font-size: 20pt;"
        );
        this.labelPalabra.setStyle(
                "-fx-font-size: 10pt;"
                + "-fx-font-style: bold;"
                + "-fx-font-style:italic;"
        );

        this.eliminarLabel.setStyle("-fx-text-fill: red; -fx-padding: 5px 10px;");
    }

    public void showWordMeaning(String pal, String meaning) {
        this.labelPalabra.setText(pal);
        this.labelMeaning.setText(meaning);
        this.eliminarLabel.setText("Si desea eliminar la palabra de click al icono eliminar");

    }

    public void initializeicons() {
        this.searchIcon.setImage(new Image("file:iconos\\search.png"));
        this.btnAdd.setImage(new Image("file:iconos\\add.png"));
        iconEffect(btnAdd);
        this.btnDelete.setImage(new Image("file:iconos\\delete.png"));
        iconEffect(btnDelete);
        this.btnExport.setImage(new Image("file:iconos\\export.png"));
        iconEffect(btnExport);
        this.btnImport.setImage(new Image("file:iconos\\import.png"));
        iconEffect(btnImport);
        this.btnEstadisticas.setImage(new Image("file:iconos\\business-report.png"));
        iconEffect(btnEstadisticas);
        this.btnModoJuego.setImage(new Image("file:iconos\\computer-game.png"));
        iconEffect(btnModoJuego);
    }

    public void iconEffect(ImageView iv) {
        // Cambiar el cursor cuando el mouse entra en el ImageView
        iv.setOnMouseEntered(event -> {
            iv.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            iv.setCursor(Cursor.HAND);
        });

        // Restaurar el cursor cuando el mouse sale del ImageView
        iv.setOnMouseExited(event -> {
            iv.setStyle("-fx-effect: none;");
            iv.setCursor(Cursor.DEFAULT);
        });
    }

    public void showNotfound() {
        if (wordsListContainer.getItems().isEmpty()) {
            wordsListContainer.getItems().add("Word not found :(");

            meaning = "ingrese significado";
            this.labelMeaning.setStyle(
                    "-fx-font-size: 12pt;"
            );
            showWordMeaning("ingrese palabra", meaning);
            this.eliminarLabel.setText("de click en agregar para añadir a diccionario");
        }

    }

    public void prepareComponents() {

        this.labelMeaning.setText(null);
        this.labelPalabra.setText(null);
        this.eliminarLabel.setText(null);
        wordsListContainer.getItems().clear();
    }

    @FXML
    public void anadirPalabra() {

        if (this.labelPalabra.getText() == null) {
            showAlarm("Ingrese una palabra, el significado es opcional :)");
        } else if (this.labelPalabra.getText().equals("ingrese palabra")) {

            //si no se ha ingresado la palabra
            showAlarm("Ingrese una palabra, el significado es opcional :)");
        } else {
            String palabra = this.labelPalabra.getText();
            String meaning = this.labelMeaning.getText();
            if (meaning == null) {
                App.trieApp.insert(palabra);
                System.out.println("Se ha anadido: " + searchBar.getText());
                App.trieApp.print();
                this.prepareComponents();
                this.searchBar.clear();
            } else if (meaning.equals("ingrese significado")) {
                App.trieApp.insert(palabra);
                System.out.println("Se ha anadido: " + searchBar.getText());
                App.trieApp.print();
                this.prepareComponents();
                this.searchBar.clear();
            } else {
                //el usuario si ingreso significado
                App.trieApp.insert(palabra, meaning);
                System.out.println("Se ha anadido: " + searchBar.getText());
                App.trieApp.print();
                this.prepareComponents();
                this.searchBar.clear();
            }

        }

    }

    @FXML
    public void borrarPalabra() {
        if (this.searchBar.getText().isBlank() || this.searchBar.getText().isEmpty() || this.searchBar.getText() == null) {
            showAlarm("debe ingresar una palabra en el buscador");
        } else if (this.wordsListContainer.getSelectionModel().getSelectedItem() == null) {
            showAlarm("seleccione una palabra");
        } else {
            String palabraActual;

            palabraActual = wordsListContainer.getSelectionModel().getSelectedItem();
            if (bAproximada.isSelected()) {
                palabraActual = palabraActual.substring(1);
            } else if (bPosfijo.isSelected()) {
                palabraActual = palabraActual.substring(1);
            }

            App.trieApp.delete(palabraActual);
            System.out.println("Se ha eliminado: " + palabraActual);
            App.trieApp.print();
            this.searchBar.clear();
            this.prepareComponents();
            wordsListContainer.getItems().clear();

        }
    }

    private void createToolTip(String msg, Button bt) {
        Tooltip tp = new Tooltip(msg);
        bt.setTooltip(tp);

    }

    private void startToolTips() {
        createToolTip("añadir palabra", tipAdd);
        createToolTip("borrar palabra", tipDelete);
        createToolTip("exporta diccionario", tipExport);
        createToolTip("importa diccionario", tipImport);
        createToolTip("activa modo juego", tipGame);
        createToolTip("estadisticas de diccionario", tipStatistics);

    }

    public void showAlarm(String contexto) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(contexto);
        a.showAndWait();
    }

    public void exportDictionary() {
        //metodo que exportar el archivo txt a un directorio
    }

    public static void save() {

        App.saveDictionary(App.rutaApertura, App.trieApp);
        if (App.rutaActual != null) {
            App.saveDictionary(App.rutaActual, App.trieApp);
        }
    }

    public void abrirEstadisticas() {

    }

}
