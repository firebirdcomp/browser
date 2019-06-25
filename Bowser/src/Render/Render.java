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
import javax.swing.JPanel;
import javax.swing.text.Highlighter;

/**
 *
 * @author Felpi
 */
public class Render {
    
    String tabName;
    
    public void startRender(Interpreter interpreter, JEditorPane sitePanel, JPanel tab)
    {
        render(interpreter.firstNode, sitePanel);    
        tab.setName(tabName);
    }
    
    private void verifyText(Node node, JEditorPane sitePanel)
    {
        String output = "";
        boolean write = true;
      
        for(Atributtes a : node.atributtes)
        {   
            System.out.println("Contem tag: " + a.atributte.toString());            
            write = true;        
            
            if(a.atributte.contains(Tags.title.toString()))
            {         
                //sitePanel.setText("<tituloPagina> " + ((Text)node).text + " <tituloPagina>\n\n" + sitePanel.getText());
                tabName = ((Text)node).text;
                write = false;
            }
            else if(a.atributte.contains(Tags.p.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "\n" + ((Text)node).text + "\n");
                write = false;
            }
            else if(a.atributte.contains(Tags.br.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "\n" + ((Text)node).text + "\n");
                write = false;
            }
            else if(a.atributte.contains(Tags.b.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "<bold>" + ((Text)node).text + "</bold>");
                write = false;
            }            
            else if(a.atributte.contains(Tags.i.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "<italico>" + ((Text)node).text + "</italico>");
                write = false;
            }
            else if(a.atributte.contains(Tags.h1.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "<titulo1>" + ((Text)node).text + "</titulo1>");
                write = false;
            }            
            else if(a.atributte.contains(Tags.h2.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo2>" + ((Text)node).text + "</titulo2> ");
                write = false;
            }            
            else if(a.atributte.contains(Tags.h3.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo3>" + ((Text)node).text + "</titulo3> ");
                write = false;
            }            
            else if(a.atributte.contains(Tags.h4.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo4>" + ((Text)node).text + "</titulo4> ");
                write = false;
            }            
            else if(a.atributte.contains(Tags.h5.toString()))
            {
                sitePanel.setText(sitePanel.getText() + " <titulo5>" + ((Text)node).text + "</titulo5> ");
                write = false;
            }            
            else if(a.atributte.contains(Tags.h6.toString()))
            {
                sitePanel.setText(sitePanel.getText() + "<titulo6>" + ((Text)node).text + "</titulo6>");
                write = false;
            }            
            else if(a.atributte.contains(Tags.div.toString()))
            {
                write = false;
                //do nothing
            }
            else
            {
                sitePanel.setText(sitePanel.getText() + "\n" + ((Text)node).text + "\n");
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
            if(node instanceof Text)
            {
                verifyText(node, sitePanel);
            }
            
            if(node instanceof HTMLnode)
            {                           
                render(node, sitePanel);
            }            
        }        
    }  
}
