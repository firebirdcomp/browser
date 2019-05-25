/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter;

/**
 *
 * @author Felpi
 */
public class Text extends Node{
    public String text;
    
    
    public Text(String content, Node father)
    {
        this.father = father;
        text = content;
    }
}
