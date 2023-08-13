/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trietree1;

/**
 *
 * @author melis
 */
public class Pruebas {
    public static void main(String[] args) {
        Trie<Character>prueba=new Trie();
        prueba.insert("Hola","saludo");
        prueba.insert("Papa");
        prueba.insert("Papaya","fruta");
        prueba.insert("Popeya");
        prueba.insert("Poramo");
        prueba.insert("Llave","entrar");
        prueba.print();
    System.out.println(prueba.getNumberOfWords());

        
//        System.out.println(prueba.containsWord("hola"));
//        System.out.println(prueba.containsWord("papa"));
//        System.out.println(prueba.containsWord("papaya"));
//        System.out.println(prueba.containsWord("popeya"));
        
        
        //CUANDO QUIERA HACER DELETE DEBEMOS PONER LAS LETRAS EN MINISCULA PORQUE
        //ASI ESTAN EN EL CONTENIDO DE CADA NODO TODAS MINISCULA

        
        System.out.println(prueba.delete("popeya"));
        System.out.println(prueba.delete("papa"));
        System.out.println(prueba.delete("llave"));
        
        prueba.print();
        System.out.println(prueba.getNumberOfWords());
        
        TrieTree1.saveDictionary("diccionarioPrueba", prueba);
    }
}
