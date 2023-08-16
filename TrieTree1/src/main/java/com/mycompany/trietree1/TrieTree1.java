/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trietree1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;


/**
 *
 * @author USUARIO
 */
public class TrieTree1 {

    public static void main(String[] args) {
        Trie<Character>prueba=new Trie();
        prueba.insert("casa");
        prueba.insert("caseta");
        
        prueba.insert("casador","persona");
        prueba.insert("casadora");
        prueba.insert("casota");
        prueba.insert("casada");
       
        prueba.print();
        
        System.out.println( prueba.getNumberOfWords());
        System.out.println(prueba.delete("casota"));
        System.out.println(prueba.delete("casadora"));

        prueba.print();

    }
    public static Trie<Character> loadDictionary(String nomArchivo){
        
        Trie<Character> arbolDict=new Trie();
        if (nomArchivo!=null){
            String ruta="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ESTRUCTURAS DE DATOS\\TrieTreeProject\\TrieTree1\\src\\main\\java\\files\\"+nomArchivo+".txt";
            try(BufferedReader br=new BufferedReader(new FileReader(ruta))){
                String s=br.readLine();
                //me desago del headline
                while (s!=null){
                    String[] datos=s.split(";");
                    String palabra=datos[0];
                    String significado=datos[1];
                    arbolDict.insert(palabra, significado);
                    s=br.readLine();
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return arbolDict;
    }
}
