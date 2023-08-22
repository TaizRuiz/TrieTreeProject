/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.trieproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author melis
 */
public class HistogramaController implements Initializable {

    @FXML
    private BarChart<?, ?> barchar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        XYChart.Series chart = new XYChart.Series<>();
        
        for(Map.Entry entrada: this.agruparPorLongitud().entrySet()){
            chart.getData().add(new XYChart.Data<>(entrada.getKey(),entrada.getValue()));
        }
        
        CategoryAxis axisX = new CategoryAxis();
        axisX.setLabel("Longitudes Posibles");
        
        NumberAxis axisY = new NumberAxis();
        axisY.setLabel("Cantidad de palabras con dicha longitud");
        
        BarChart<String,Number> barChart = new BarChart<>(axisX,axisY);
        barChart.getData().add(chart);
        
        barchar.getData().add(chart);
        
        Scene scene = new Scene(barChart, 1000,600);
        
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
