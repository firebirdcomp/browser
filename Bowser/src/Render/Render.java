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
      
        for(Atributtes a : node.atributtes)
        {            
            if(a.atributte.contains(Tags.title.toString()))
            {         
                sitePanel.setText("<tituloPagina> " + ((Text)node).text + " <tituloPagina>\n\n" + sitePanel.getText());
            }

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
                sitePanel.setText(sitePanel.getText() + " <bold> " + ((Text)node).text + " </bold> ");
                write = false;
            }

            if(a.atributte.contains(Tags.h1.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo> " + ((Text)node).text + " </titulo> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.h2.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo2> " + ((Text)node).text + " </titulo2> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.h3.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo3> " + ((Text)node).text + " </titulo3> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.h4.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo4> " + ((Text)node).text + " </titulo4> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.h5.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo5> " + ((Text)node).text + " </titulo5> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.h6.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo6> " + ((Text)node).text + " </titulo6> ");
                write = false;
            }
            
            if(a.atributte.contains(Tags.div.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <div> " + ((Text)node).text);
                write = false;
            }
        }

        if(write)
        {  
            sitePanel.setText(sitePanel.getText() + ((Text)node).text);
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
