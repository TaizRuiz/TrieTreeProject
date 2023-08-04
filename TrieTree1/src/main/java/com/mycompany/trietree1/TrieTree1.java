/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trietree1;

/**
 *
 * @author USUARIO
 */
public class TrieTree1 {

    public static void main(String[] args) {
        Trie<Character>prueba=new Trie();
        prueba.insert("Hola");
        prueba.insert("Papa");
        prueba.insert("Papaya");
        prueba.insert("Popeya");
        prueba.insert("Poramo");
        prueba.insert("Llave");
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
        
     
    }
}
