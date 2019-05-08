/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Felpi
 */
public class Interpreter {    
    
    public ArrayList<HTMLnode> nodes = new ArrayList();
    
    
    public void Interpreter(String html)
    {   
        generateTree(html);         
    }
    
    public void generateTree(String html)
    {
        String _html = html;
        Pattern pattern = Pattern.compile("(\\<(\\w+)\\>).+(\\<\\/\\2\\>)");
        Matcher matcher = pattern.matcher(_html);
        
        while (matcher.find()) 
        {
            System.out.println("*********************************");
            System.out.println("Group 1: " + matcher.group(1));
            System.out.println("Group 3: " + matcher.group(3));
            System.out.println("Result: " + matcher.group());
            System.out.println("Tag: " + matcher.group(2));
            
            nodes.add(new HTMLnode(matcher.group(2)));
            
            _html = matcher.group();
            _html = _html.replaceFirst(matcher.group(1), "");
            _html = _html.replaceFirst(matcher.group(3), "");
            
            matcher = pattern.matcher(_html);            
        }        
        
        System.out.println("Teste tags");
        
        for(HTMLnode n : nodes)
        {
            System.out.println("Tag number: " + nodes.indexOf(n) + " = " + n.tag);
        }
    }
    
    public String clearHTML(String html)
    {
        return html;
    }
    
}
