/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trieproject.Trie;

import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Trie<E> {

    private TrieNode<E> root;

    public Trie() {
      
        root=new TrieNode<E>();
    }
    //permite insertar una palabra con su significado
    public boolean insert(String p, String m){
         if (p!=null ){
        //&& (!this.containsWord(p)
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

        if (containsWord(s)){
           
            TrieNode<E> nFinal=getEndWord(s);
            if (nFinal.hasChildren()){
                return true;
            }else{
                return false;
            }
        }
        }
        return false;
    }
    
    //Aqui me debe pasar un prefijo 
    private TrieNode<E> getCommonNode(String s){
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
    private TrieNode<E> getEndWord(String s){
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
        if (s!=null){
             s=s.toLowerCase();
            
         return getEndWord(s).getMeaning();
        }
        return null;
       
    }

    public void print() {
	    	List<String> res = new ArrayList<String>(); 
	    	helper(root, res, "");
	    	System.out.println(res);
	    }
    

     public List<String> getAllWordsFromTrie(){
        List<String> res = new ArrayList<String>(); 
	helper(root, res, "");

        return res;
    }
     public List<String> getWordsEndWith(String s){
       s=s.toLowerCase().strip();
       List<String> allWords=this.getAllWordsFromTrie();
       List<String> resultWords=new ArrayList<>();
       int lengthPosfijo=s.length();
       for (String sW: allWords){
           if (sW.length()>= lengthPosfijo){
              //si la palabra es mas grande o igual de grande vale la pena buscar
              String comparador=sW.substring(sW.length()-lengthPosfijo);
              if (comparador.equals(s)){
                  resultWords.add(sW);
              }
           }
       }
       return resultWords;
    }
     public List<String> getSimilarWords(String s){
       s=s.toLowerCase().strip();
       List<String> allWords=this.getAllWordsFromTrie();
       List<String> resultWords=new ArrayList<>();
       
       for (String sW: allWords){
           //tengo que calcular la distancia para cada palabra
           if (similarityDistance(s, sW)<=4){
               resultWords.add(sW);
           }
       }
       return resultWords;
    }
    public List<String> getPossibleWord(String s){
        List<String> res = new ArrayList<String>(); 
        if (s!=null && !s.isEmpty() && !s.isBlank()){
            if (prefixIncluded(s)){
                helper(getCommonNode(s),res,s.substring(0,s.length()-1));
            }
            else if(this.containsWord(s)){
                res.add(s);
            }
            
          
        }
        return res;
    }
    private void helper(TrieNode node, List<String> res, String prefix) {
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
        return this.getAllWordsFromTrie().size();
    }
    
    public boolean delete(String eliminar){
        //Agarrar el final char 
        eliminar=eliminar.toLowerCase();
        TrieNode nodoFinal = this.getEndWord(eliminar);
        TrieNode nodoPadre = this.root;
       
        if(this.containsWord(eliminar)){
            //se va a eliminar
            if (prefixIncluded(eliminar)){
                deleteNodePrefix(nodoPadre, nodoFinal, eliminar);

                return true;
            }else{
                deleteNode(eliminar);

                return true;
            }
            
            
           
        }
        return false;
        
    }
     private void deleteNode(String s){ 
  
         TrieNode nodoFinal=getEndWord(s);
         nodoFinal.setIsFinalChar(false);
    }
    
    private void deleteNodePrefix(TrieNode nodo, TrieNode nodoFinalPalabra, String eliminar){ 
        //Se va sacando el primer caracter
        eliminar=eliminar.toLowerCase();
        char caracter = eliminar.charAt(0);
        //la posicion 
        int index = caracter-'a';    
        TrieNode nodoHijo =nodo.getHijos()[index];
       
        if(nodoHijo == nodoFinalPalabra && nodoHijo.isIsFinalChar()==true){
    
            //tiene hijos?
            if(nodoHijo.hasChildren()){
                nodoHijo.setIsFinalChar(false);
            }
            
            else{
                //Si en caso no tiene hijos
                nodo.getHijos()[index] = null;

            }
        }
        else{
            String restoPalabra = eliminar.substring(1);
            deleteNodePrefix(nodoHijo,nodoFinalPalabra,restoPalabra);
            
            if(!nodoHijo.hasChildren()){
                nodo.getHijos()[index] = null;
            }
        }
    }
    private int similarityDistance(String word1 ,String word2){
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int substitutionCost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + substitutionCost,
                                       Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    
}

