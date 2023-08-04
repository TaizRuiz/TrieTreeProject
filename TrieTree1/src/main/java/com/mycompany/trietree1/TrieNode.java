/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trietree1;

/**
 *
 * @author USUARIO
 */
public class TrieNode<E> {
   private boolean isFinalChar;
   private TrieNode[] hijos;
   private char content;
   private String meaning;
    public TrieNode() {
        this.hijos=new TrieNode[26];
        this.isFinalChar=false;
    }
     public TrieNode(char s) {
       this();
       this.content=s;
    }
    public TrieNode(char s, String meaning){
        this(s);
        this.meaning=meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    
    public boolean isIsFinalChar() {
        return isFinalChar;
    }

    public void setIsFinalChar(boolean isFinalChar) {
        this.isFinalChar = isFinalChar;
    }

    public TrieNode[] getHijos() {
        return hijos;
    }

    public void setHijos(TrieNode[] hijos) {
        this.hijos = hijos;
    }

    public char getContent() {
        return content;
    }
   
}
