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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Felpi
 */
public class Render {
    
    String tabName;
    
    public void startRender(Interpreter interpreter, JPanel sitePanel, JPanel tab)
    {
        render(interpreter.firstNode, sitePanel);    
        tab.setName(tabName);
    }
    
    private void verifyText(Node node, JPanel sitePanel)
    {    
        
        boolean write = true;
      
        for(Atributtes a : node.atributtes)
        {   
            System.out.println("Contem tag: " + a.atributte.toString());            
            write = true;        
            
//            Titulo
            if(a.atributte.contains(Tags.title.toString()))
            {         
                tabName = ((Text)node).text;
                write = false;
            }
//            Paragrafo
            else if(a.atributte.contains(Tags.p.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("\n" + ((Text)node).text + "\n");
                sitePanel.add(t);
                write = false;
            }
            else if(a.atributte.contains(Tags.br.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("\n" + ((Text)node).text + "\n");
                sitePanel.add(t);
                write = false;
            }
            else if(a.atributte.contains(Tags.b.toString()))
            {
                JTextArea t = new JTextArea();                
                t.setText("bold" + ((Text)node).text + "bold");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.i.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("italico" + ((Text)node).text + "italico");
                sitePanel.add(t);
                write = false;
            }
            else if(a.atributte.contains(Tags.h1.toString()))
            {
                JTextArea t = new JTextArea();                
                t.setText("titulo1" + ((Text)node).text + "titulo1");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.h2.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("titulo2" + ((Text)node).text + "titulo2");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.h3.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("titulo3" + ((Text)node).text + "titulo3");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.h4.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("titulo4" + ((Text)node).text + "titulo4");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.h5.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("titulo5" + ((Text)node).text + "titulo5");
                sitePanel.add(t);
                write = false;
            }            
            else if(a.atributte.contains(Tags.h6.toString()))
            {
                JTextArea t = new JTextArea();
                t.setText("titulo6" + ((Text)node).text + "titulo6");
                sitePanel.add(t);
                write = false;
                //do nothing
            }            
            else if(a.atributte.contains(Tags.div.toString()))
            {
                write = false;
                //do nothing for now
            }
            else if(a.atributte.contains(Tags.a.toString()))
            {
                write = false;
                //do nothing for now
            }
            else
            {
                write = false;
            }
        }

        if(write)
        {              
            JTextArea t = new JTextArea();
            t.setText("\n" + ((Text)node).text + "\n");
            sitePanel.add(t);
        }    
    }
    
    public void render(Node father, JPanel sitePanel)
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
