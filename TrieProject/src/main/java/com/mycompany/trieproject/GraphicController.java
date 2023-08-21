/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
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
        ArrayList<Integer> cantidadPorLetra = cantidadPorLetra();
        System.out.println(cantidadPorLetra);

    }

    public ArrayList<Integer> cantidadPorLetra() {
        String abecedario[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        System.out.println(abecedario.length);
        ArrayList<Integer> cantidades = new ArrayList<>();
        List<String> palabras = App.trieApp.getAllWordsFromTrie();

        for (int i = 0; i < 25; i++) {
            int contador = 0;
            for (String s : palabras) {
                String letra = s.substring(0,2);
                if (letra.equals(abecedario[i])) {
                    contador++;
                }
            }
            cantidades.add(contador);

        }

        return cantidades;
    }

    public ArrayList<Integer> longitudes() {
        ArrayList<Integer> longitudes = new ArrayList<>();

        return longitudes;
    }

    //int totalPalabras, ArrayList<Integer> cantidadPorLetra, ArrayList<Integer> longitudes
}
