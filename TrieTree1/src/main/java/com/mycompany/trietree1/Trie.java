/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trietree1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
   

    public boolean prefixIncluded(String s){
        if (s!=null){
           s=s.toLowerCase();
           char[] caracteres=s.toCharArray();
            TrieNode actual=root;
            boolean exist=true;
            for (int i=0;i<caracteres.length;i++){
                char caracter=caracteres[i];
                 int posChar=getCharPos(caracteres[i]);
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
    
    //Aqui me debe pasar un prefijo 
    public TrieNode<E> getCommonNode(String s){
        TrieNode<E> node=null;
        if (s!=null){
            if (prefixIncluded(s)){
                 s=s.toLowerCase();
                 char[] caracteres=s.toCharArray();
                 TrieNode actual=root;
                 for (int i=0;i<caracteres.length;i++){
                     
                     int posChar=getCharPos(caracteres[i]);
                      if (actual.getHijos()[posChar]!=null){
                          actual=actual.getHijos()[posChar];
                     }
                 }
                 node=actual;
            }
        
         } 
        return node;
         } 
    
    public int getCharPos(char c){
        return c-'a';
    }
    public TrieNode<E> getEndWord(String s){
        TrieNode<E> nodo=null;
        if (this.containsWord(s)){
            s=s.toLowerCase();
             char[] chars=s.toCharArray();
            TrieNode nodoCurrent=root;
            for (int i=0;i<s.length();i++){
               int indice=getCharPos(chars[i]);
               nodoCurrent=nodoCurrent.getHijos()[indice];
            }
            nodo=nodoCurrent;
        }
        return nodo;
    }
    public String getMeaningFromWord(String s ){
        s=s.toLowerCase();
        return getEndWord(s).getMeaning();
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
    
    public boolean delete(String eliminar){
        //Agarrar el final char 
        TrieNode nodoFinal = this.getEndWord(eliminar);
        TrieNode nodoPadre = this.root;
       
        if(this.containsWord(eliminar)){
            //se va a eliminar
            deleteNode(nodoPadre, nodoFinal, eliminar);
            numWords = numWords -1;
            return true;
        }
        return false;
        
    }
    
    private void deleteNode(TrieNode nodo, TrieNode nodoFinalPalabra, String eliminar){ 
        //Se va sacando el primer caracter
        char caracter = eliminar.charAt(0);
        //la posicion 
        int index = caracter-'a';    
        TrieNode nodoHijo =nodo.getHijos()[index];
        
        if(nodoHijo == nodoFinalPalabra){
            //tiene hijos?
            if(nodoHijo.hasChildren()){
                nodoHijo.setIsFinalChar(false);
            }
            else{
                //deberia poner en nulo desde el padre pero no puedo alcanzarlo
                nodo.getHijos()[index] = null;
            }
        }
        else{
            String restoPalabra = eliminar.substring(1);
            deleteNode(nodoHijo,nodoFinalPalabra,restoPalabra);
            
            if(!nodoHijo.hasChildren()){
                nodo.getHijos()[index] = null;
            }
        }
    }
}
