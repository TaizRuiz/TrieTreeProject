/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trietree1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    //permite insertar una palabra con su significado
    public boolean insert(String p, String m){
         if (p!=null && (!this.containsWord(p))){
           p=p.toLowerCase();
           char[] chars=p.toCharArray();
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
           // de tener significado le agrega el significado
           actual.setIsFinalChar(true);
           if (m!=null){
               actual.setMeaning(m);
               System.out.println(actual.getMeaning());
           }
          
           numWords++;
       }
       return false;
    }
    public boolean insert(String s){
        return insert(s, null);
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
    public void getMeaning(String s){
      //metodo que me retorna el significado asociado a una palabra de existir
    }
    //

    public boolean prefixIncluded(String s){
        if (s!=null){
           s=s.toLowerCase();
           char[] caracteres=s.toCharArray();
            TrieNode actual=root;
            boolean exist=true;
            for (int i=0;i<caracteres.length;i++){
                char caracter=caracteres[i];
                int posChar=caracter-'a';
               //posicion en el arreglo  
                 if (actual.getHijos()[posChar]!=null){
                     if (actual.getHijos()[posChar].getContent()==caracter){
                         actual=actual.getHijos()[posChar];
                     }else{
                         exist=false;
                         break;
                     }
                 }else{
                     exist=false;
                     break;
                 }
            }
           return exist; 
        }
        return false;
    }
    public TrieNode<E> getCommonNode(String s){
        TrieNode<E> node=null;
        if (s!=null){
            if (prefixIncluded(s)){
                 s=s.toLowerCase();
                 char[] caracteres=s.toCharArray();
                 TrieNode actual=root;
                 for (int i=0;i<caracteres.length;i++){
                     char caracter=caracteres[i];
                     int posChar=caracter-'a';
                      if (actual.getHijos()[posChar]!=null){
                          actual=actual.getHijos()[posChar];
                     }
                 }
                 node=actual;
            }
        
         } 
        return node;
         } 
    
    

    public void print() {
	    	List<String> res = new ArrayList<String>(); 
	    	helper(root, res, "");
	    	System.out.println(res);
	    }
			
    public List<String> getPossibleWord(String s){
        List<String> res = new ArrayList<String>(); 
        if (s!=null){
            if (prefixIncluded(s)){
                helper(getCommonNode(s),res,s.substring(0,s.length()-1));
            }
            
            for (String sa: res){
                System.out.println(sa);
            }
        }
        return res;
    }
    public void helper(TrieNode node, List<String> res, String prefix) {
                        prefix=prefix.toLowerCase();
			if (node == null ) //base condition			
				return;		
			if (node.isIsFinalChar()) {
                                    String word = prefix.substring(0)+ node.getContent();
                                    res.add(word); //skip the first space from root
			}
			for (TrieNode child: node.getHijos())	{
                            
                             helper(child, res, prefix+ node.getContent());	   
                        }
                               
		}
    

   
    public int getNumberOfWords(){
        return this.numWords;
    }
}
