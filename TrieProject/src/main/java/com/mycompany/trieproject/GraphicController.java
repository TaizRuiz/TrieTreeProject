/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    @FXML
    BarChart<?, ?> barchar;
    @FXML
    private CategoryAxis axisX;

    @FXML
    private NumberAxis axisY;

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
        
        XYChart.Series chart = new XYChart.Series<>();
        
        for(Map.Entry entrada: this.agruparPorLongitud().entrySet()){
            chart.getData().add(new XYChart.Data<>(entrada.getKey(),entrada.getValue()));
        }
        
        chart.setName("Longitudes");
        
        barchar.getData().add(chart);
        

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
    
    public Map<String,Integer> agruparPorLongitud(){
        Map<String,Integer> mapa = new HashMap<>();
        List<String> lista = App.trieApp.getAllWordsFromTrie();
        
        for(String st: lista){
            int longitud = st.length();
            if (longitud >= 1 && longitud <= 15) {
                mapa.put(String.valueOf(longitud), mapa.getOrDefault(String.valueOf(longitud), 0) + 1);
            }
        }
        
        // Ordenar las claves
        List<String> clavesOrdenadas = new ArrayList<>(mapa.keySet());
        Collections.sort(clavesOrdenadas, Comparator.comparingInt(Integer::parseInt));

        // Crear el nuevo mapa ordenado
        Map<String, Integer> mapaOrdenado = new LinkedHashMap<>();
        for (String clave : clavesOrdenadas) {
            mapaOrdenado.put(clave, mapa.get(clave));
        }

    return mapaOrdenado;
    }

}
