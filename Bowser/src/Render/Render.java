/*
 * Project by Firebird. All rigths reserved to Felpi and Luis.
 */
package Render;

import Interpreter.HTMLnode;
import Interpreter.Interpreter;
import Interpreter.Node;
import Interpreter.Text;
import java.awt.Font;
import javax.swing.JEditorPane;

/**
 *
 * @author Felpi
 */
public class Render {
    
    public void startRender(Interpreter interpreter, JEditorPane sitePanel)
    {
        System.out.println("**************************************************");
        System.out.println("Inicio do render");
        render(interpreter.firstNode, sitePanel);    
    }
    
    public void render(Node father, JEditorPane sitePanel)
    {           
        for(Node node : ((HTMLnode)father).children)
        {
            System.out.println("**************************************************");
            System.out.println("Novo node descoberto, pai..." + ((HTMLnode)father).tag);
            
            
            
            String output = "";
            //output += "\nNovo node descoberto, pai..." + ((HTMLnode)father).tag;  
            
            if(node instanceof Text)
            {       
                if(node.atributtes.get(0).atributte.contains("p"))
                {
                    sitePanel.setText(sitePanel.getText() + "\n");
                }                
                
                if(!node.atributtes.get(0).atributte.contains("title"))
                {                   
                    //output += "\n\nÉ um texto: ";
                    output += ((Text)node).text;
                    //output += "\nAtibutes: " + atributes;                
                    System.out.println("É um texto"); 
                    System.out.println("Content: " + ((Text)node).text);
                    System.out.println("Atribute: " + node.atributtes.get(0).atributte);
                    sitePanel.setText(sitePanel.getText() + output);
                }
                else
                {
                    sitePanel.setText("PLACEHOLDERTITLE: " + ((Text)node).text + "\n" + sitePanel.getText());
                }                
            }
            
            
            
            if(node instanceof HTMLnode)
            {                
                //output += "\nTAG: " + ((HTMLnode)node).tag;
                //output += "\nCONTENT: " + ((HTMLnode)node).content; 
                //output += "\nÉ um node";
                System.out.println("É um node");
                System.out.print("Node output: ");                
                System.out.println(((HTMLnode)node).tag);
                render(node, sitePanel);
            }            
        }        
    }
    
}
