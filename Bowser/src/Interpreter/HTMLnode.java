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
public class HTMLnode {
    public ArrayList<HTMLnode> children = null;
    public HTMLnode father = null;
    public ArrayList<Atributtes> atributtes = null;
    public String content;
    public String tag;
    public String content;
    public int level;
    
    public HTMLnode(String tag)
    {
        this.tag = tag;
    }
}
