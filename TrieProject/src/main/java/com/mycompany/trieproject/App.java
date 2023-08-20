package com.mycompany.trieproject;

import com.mycompany.trieproject.Trie.Trie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String rutaApertura="src\\main\\java\\archivos\\diccionario.txt";
    public static String rutaActual=null;
    private static Scene scene;
    public static Trie trieApp;
    @Override
    public void start(Stage stage) throws IOException {
       
        trieApp=loadDictionary(rutaApertura); 
     
  
        scene = new Scene(loadFXML("ventanaPrincipal"), 780, 540);
        stage.setScene(scene);
        stage.show();
        scene.getWindow().setOnCloseRequest(eh->{
            System.out.println("saving");
            VentanaPrincipalController.save();
        });
        trieApp.print();
        
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Trie<Character> loadDictionary(String rutaArch){
        
        Trie<Character> arbolDict=new Trie();
        if (rutaArch!=null){
            
            String ruta=rutaArch;
            try(BufferedReader br=new BufferedReader(new FileReader(ruta))){
                String s=br.readLine();
                System.out.println("primera"+s);
                //me desago del headline
                while (s!=null){
                    System.out.println("entro al while");
                    System.out.println(s);
                    String[] datos=s.split(";");
                    String palabra=datos[0].substring(1);
                    String significado=datos[1];
                    System.out.println(palabra.length());
                    System.out.println(significado.length());
                    arbolDict.insert(palabra, significado);
                    s=br.readLine();
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return arbolDict;
    }
     public static Trie<Character> loadDictionaryFromImport(String rutaArch){
        
        Trie<Character> arbolDict=new Trie();
        if (rutaArch!=null){
            
            String ruta=rutaArch;
            try(BufferedReader br=new BufferedReader(new FileReader(ruta))){
                String s=br.readLine();
                System.out.println("primera"+s);
                //me desago del headline
                while (s!=null){
                    System.out.println("entro al while");
                    System.out.println(s);
                    String[] datos=s.split(";");
                    String palabra=datos[0];
                    String significado=datos[1];
                    System.out.println(palabra.length());
                    System.out.println(significado.length());
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
                FileWriter archivo = new FileWriter(nomArchivo);
                BufferedWriter escritor = new BufferedWriter(archivo);
                
                //Consigo la lista
                List<String> dictionaryWords = dictionary.getAllWordsFromTrie();
    
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
                System.out.println(ex);
            }
        }
        return false;
    }
}
