/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.trieproject;

/**
 *
 * @author USUARIO
 */
import com.mycompany.trieproject.Trie.Trie;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
Trie t=new Trie();
t.insert("manzana");
t.delete("manzana");
t.print();
    }
    
}
