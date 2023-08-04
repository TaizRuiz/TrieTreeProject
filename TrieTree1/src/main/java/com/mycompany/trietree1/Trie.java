/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trietree1;

/**
 *
 * @author USUARIO
 */
public class Trie<E> {
    private int numWords;
    private TrieNode<E> root;

    public Trie() {
        this.numWords=0;
        root=new TrieNode<E>();
    }
    public boolean insert(String s){
       if (s!=null && (!this.containsWord(s))){
           s=s.toLowerCase();
           char[] chars=s.toCharArray();
           TrieNode<E> actual=this.root;
           for (int i=0;i<chars.length;i++){
               char caracter=chars[i];
               //contenido
               int posChar=caracter-'a';
               //posicion en el arreglo 
               if (actual.getHijos()[posChar]==null){
                   //si ese caracter no esta en la posicion del arreglo 
                   //crea una referencia a un nuevo nodo en ese indice 
                   TrieNode<E> nuevoNodo=new TrieNode(caracter);
                   actual.getHijos()[posChar]=nuevoNodo;
               }
               //pasa al siguiente 
               actual=actual.getHijos()[posChar];
           }
           //en el ultimo lo setea a true 
           actual.setIsFinalChar(true);
           numWords++;
       }
       return false;
    }
    
    public boolean containsWord(String s){
        boolean contains=true;
        if (s!=null){
            
           s=s.toLowerCase();
           char[] chars=s.toCharArray();
           TrieNode<E> actual=this.root;
           for (int i=0;i<chars.length;i++){
               char caracter=chars[i];
               //contenido
               int posChar=caracter-'a';
               //posicion en el arreglo  
            if (contains==true){
                if (actual.getHijos()[posChar]!=null){
                    if (actual.getHijos()[posChar].getContent()!=caracter){
                         contains=false;
                    }
                    actual=actual.getHijos()[posChar];
                }else{
                   contains=false;
                }
                    
           }else{
                   break;
               }
            
        }
       
    }
         return contains;
}   
    
    public int getNumberOfWords(){
        return this.numWords;
    }
}
