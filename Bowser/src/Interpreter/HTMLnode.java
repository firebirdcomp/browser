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
    public ArrayList<HTMLnode> children;
    public HTMLnode father;
    public ArrayList<Atributtes> atributtes;
    public String tag;
    
    public HTMLnode(String tag)
    {
        this.tag = tag;
    }
}
