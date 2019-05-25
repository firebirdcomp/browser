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
public class Atributtes extends Node{
    public String atributte;
    
    public Atributtes(String atributte, Node father)
    {
        this.atributte = atributte;
        this.father = father;
    }
}
