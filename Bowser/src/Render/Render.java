/*
 * Project by Firebird. All rigths reserved to Felpi and Luis.
 */
package Render;

import Interpreter.Atributtes;
import Interpreter.HTMLnode;
import Interpreter.Interpreter;
import Interpreter.Node;
import Interpreter.Tags;
import Interpreter.Text;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.text.Highlighter;

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
    
    private void verifyText(Node node, JEditorPane sitePanel)
    {
        String output = "";
        boolean write = true;
        
        if(node.atributtes.get(0).atributte.contains("title"))
        {         
            sitePanel.setText("PLACEHOLDERTITLE: " + ((Text)node).text + "\n\n" + sitePanel.getText());
            
        }
        else
        {
            for(Atributtes a : node.atributtes)
            {
                if(a.atributte.contains(Tags.p.toString()))
                {
                    sitePanel.setText(sitePanel.getText() + "\n");
                }
                
                if(a.atributte.contains(Tags.br.toString()))
                {
                    sitePanel.setText(sitePanel.getText() + "\n");
                }
                
                if(a.atributte.contains(Tags.b.toString()))
                {
                    sitePanel.setText(sitePanel.getText() + " (BOLD " + ((Text)node).text + " /BOLD) ");
                    write = false;
                }
            }
            
            if(write)
            {  
                sitePanel.setText(sitePanel.getText() + ((Text)node).text);
            }
            
        }      
    }
    
    public void render(Node father, JEditorPane sitePanel)
    {           
        for(Node node : ((HTMLnode)father).children)
        {
            System.out.println("**************************************************");
            System.out.println("Novo node descoberto, pai..." + ((HTMLnode)father).tag);            
            
            if(node instanceof Text)
            {
                verifyText(node, sitePanel);
            }
            
            if(node instanceof HTMLnode)
            {                
                System.out.println("É um node");
                System.out.print("Node output: ");                
                System.out.println(((HTMLnode)node).tag);
                render(node, sitePanel);
            }            
        }        
    }  
}
