/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter;

import java.util.ArrayList;

/**
 *
 * @author Felpi
 */
public class HTMLnode extends Node{
    public ArrayList<HTMLnode> children = new ArrayList();
    public ArrayList<Atributtes> atributtes = new ArrayList();
    public String content;
    public String tag;
    
    public HTMLnode(String tag, Node father)
    {
        this.tag = tag;
        this.father = father;
    }
}
