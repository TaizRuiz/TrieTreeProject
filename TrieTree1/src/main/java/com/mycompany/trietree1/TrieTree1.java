/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trietree1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author USUARIO
 */
public class TrieTree1 {

    public static void main(String[] args) {
        Trie<Character>prueba=new Trie();
        prueba.insert("Hola","saludo");
        prueba.insert("Papa");
        prueba.insert("Papaya","fruta");
        prueba.insert("Popeya");
        prueba.insert("Poramo");
        prueba.insert("Llave","entrar");
        System.out.println(prueba.getNumberOfWords());
        System.out.println(prueba.containsWord("Holi"));
        System.out.println(prueba.containsWord("Hola"));
        System.out.println(prueba.containsWord("Papa"));
       
        System.out.println(prueba.containsWord("paramo"));
        System.out.println(prueba.containsWord("Papaya"));
        System.out.println("Prueba prefijos");
        System.out.println(prueba.prefixIncluded("H"));
        System.out.println("Pa incluido prefijo");
        System.out.println(prueba.prefixIncluded("Pa"));
        System.out.println(prueba.prefixIncluded("Papa"));
        System.out.println(prueba.prefixIncluded("Ho"));
        System.out.println(prueba.prefixIncluded("Par"));
        System.out.println(prueba.prefixIncluded("Pas"));
        System.out.println("common node");
        System.out.println(prueba.getCommonNode("Pa"));
        System.out.println(prueba.getCommonNode("O"));
        System.out.println(prueba.getCommonNode("param"));
        System.out.println("Imprimir");
        prueba.print();
        System.out.println("posibles palabras");
        prueba.getPossibleWord("P");
        System.out.println("Obtener significado");
        System.out.println(prueba.getMeaningFromWord("llave"));
        System.out.println(prueba.getMeaningFromWord("Hola"));
        System.out.println(prueba.getMeaningFromWord("po"));
        Trie<Character>pruebaCargar=loadDictionary("diccionario");
        pruebaCargar.print();
        System.out.println(pruebaCargar.getMeaningFromWord("MANZANA"));
    }
    public static Trie<Character> loadDictionary(String nomArchivo){
        
        Trie<Character> arbolDict=new Trie();
        if (nomArchivo!=null){
            String ruta="src\\main\\java\\files\\"+nomArchivo+".txt";
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
    
    public static boolean saveDictionary(String nomArchivo, Trie dictionary){
        
        if(nomArchivo != null){
            try {
                FileWriter archivo = new FileWriter("src\\main\\java\\files\\"+nomArchivo+".txt");
                BufferedWriter escritor = new BufferedWriter(archivo);
                
                //Consigo la lista
                List<String> dictionaryWords = dictionary.dictionaryWords();
    
                //Iterando cada palabra con su significado
                for(String palabra: dictionaryWords){

                    //Comienza la palabra desde el indice 1 porque el 0 es espacio
                    String significado = dictionary.getMeaningFromWord(palabra.substring(1));
                    escritor.write(palabra + ";" + significado);
                    escritor.write("\n");
                    escritor.flush();
                }
                escritor.close();
                
                return true;
            } catch (IOException ex) {
                Logger.getLogger(TrieTree1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
