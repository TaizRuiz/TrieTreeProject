/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author noeli
 */
public class GraphicController implements Initializable {

    @FXML
    BarChart<?, ?> grafica;
    @FXML
    Label txtTotal;
    @FXML
    private CategoryAxis ejeX;
    @FXML
    private NumberAxis ejeY;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setValues();
    }

    @FXML
    public void setValues() {
        int numberOfWords = App.trieApp.getNumberOfWords();

        txtTotal.setText(String.valueOf(numberOfWords));

        TreeMap<String, Integer> cantidadPorLetra = cantidadPorLetra();

        TreeMap<String, Integer> repeticiones = repeticionLetras();
        System.out.println(repeticiones);

        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName("Cantidad de palabras");
        for (String letra : cantidadPorLetra.keySet()) {
            set1.getData().add(new XYChart.Data(letra, cantidadPorLetra.get(letra)));
        }

        XYChart.Series set2 = new XYChart.Series<>();
        set2.setName("Repeticion de letra");
        for (String letra : repeticiones.keySet()) {
            set2.getData().add(new XYChart.Data(letra, repeticiones.get(letra)));
        }

        grafica.getData().addAll(set1, set2);

    }

    public TreeMap<String, Integer> repeticionLetras() {
        TreeMap<String, Integer> repeticiones = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            String letra = String.valueOf(ch);
            repeticiones.put(letra, 0);
        }

        List<String> palabras = App.trieApp.getAllWordsFromTrie();

        for (String letra : repeticiones.keySet()) {
            int contador = 0;
            for (String s : palabras) {
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    String l = String.valueOf(c);
                    if (l.equals(letra)) {
                        contador++;
                        repeticiones.put(l, contador);
                    }
                }
            }

        }
        return repeticiones;
    }

    public TreeMap<String, Integer> cantidadPorLetra() {
        TreeMap<String, Integer> cantidades = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            String letra = String.valueOf(ch);
            cantidades.put(letra, 0);
        }
        List<String> palabras = App.trieApp.getAllWordsFromTrie();

        for (String letra : cantidades.keySet()) {
            int contador = 0;
            for (String s : palabras) {
                String primeraLetra = s.substring(1, 2);
                if (primeraLetra.equals(letra)) {
                    contador++;
                    cantidades.put(letra, contador);
                }
            }
        }

        return cantidades;
    }

}
