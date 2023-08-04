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
        prueba.insert("Papaya");
        prueba.insert("Paramo");
        System.out.println(prueba.getNumberOfWords());
        System.out.println(prueba.containsWord("Holi"));
        System.out.println(prueba.containsWord("Hola"));
        System.out.println(prueba.containsWord("Papa"));
        System.out.println(prueba.containsWord("paramo"));
        System.out.println(prueba.containsWord("Papaya"));
    }
}
