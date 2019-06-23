/*
 * Project by Firebird. All rigths reserved to Felpi and Luis.
 */
package Render;

import Interpreter.HTMLnode;
import Interpreter.Interpreter;
import Interpreter.Node;
import Interpreter.Text;
import javax.swing.JEditorPane;

/**
 *
 * @author Felpi
 */
public class Render {
    
    public void startRender(Interpreter interpreter, JEditorPane sitePanel)
    {
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("Inicio do render");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        render(interpreter.firstNode, sitePanel);    
    }
    
    public void render(Node father, JEditorPane sitePanel)
    {           
        for(Node node : ((HTMLnode)father).children)
        {
            System.out.println("");
            System.out.println("**************************************************");
            System.out.println("Novo node descoberto, pai..." + ((HTMLnode)father).tag);
            String output = "\n";
            output += "\nNovo node descoberto, pai..." + ((HTMLnode)father).tag;
            
            if(node instanceof Text)
            {
                output += "\nÉ um texto";
                output += "\nisTEXT: ";
                output += ((Text)node).text;                
                System.out.println("É um texto");
                System.out.println("Node output: " + output);
            }
            
            if(node instanceof HTMLnode)
            {                
                output += "\nTAG: " + ((HTMLnode)node).tag;
                output += "\nCONTENT: " + ((HTMLnode)node).content; 
                output += "\nÉ um node";
                System.out.println("É um node");
                System.out.println("Node output: " + output);
                System.out.println("\n");
                render(node, sitePanel);
            }

            sitePanel.setText(sitePanel.getText() + output);            
        }  
        
        System.out.println("NENHUM NODE DESCOBERTO em pai.." + ((HTMLnode)father).tag);
    }
    
    
    
}
