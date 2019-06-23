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
    
    public ArrayList<Node> nodes = new ArrayList();
    public Node firstNode;
    
    public void Interpreter(String html)
    {   
        html = this.clearHTML(html);
        generateTree(html, null);         
    }    
    
    public String generateTree(String html, HTMLnode father)
    {       
        Atributtes atrib = null;
        
        if(father != null)
        {
            atrib = new Atributtes(father.tag,father); 
        }               
        
        String tagName = "", 
               closingTag = "", 
               contentInsideTag = "", 
               over = "";        
        
        //debug
        System.out.println("****************************************************");
        System.out.println("New iterration started");
        System.out.println("current HTML: " + html);
        
        //verify if has text before the tag
        Pattern pattern2 = Pattern.compile("(.*?)(<(\\w+)>)(.+)(<\\/\\3>)(.*)");
        Matcher matcher2 = pattern2.matcher(html);

        if(matcher2.matches())
        {
            if(!"".equals(matcher2.group(1)))
            {
                System.out.println("*******A text before tag was founded*********");
                Text text = new Text(matcher2.group(1).replaceAll("<\\w+>", ""), father);
                text.atributtes.add(atrib);
                nodes.add(text);
                father.children.add(text);
                html = html.replace(matcher2.group(1), "");
                System.out.println("Text created");
                System.out.println("TEXT CONTENT: " + text.text);
            }            
        }
        
        //TODO verificar se a tag fecha
        //TODO pegar atributos na regex e adicionar ao node
        
        //Matches the tag of HTML  
        Pattern pattern = Pattern.compile("(<(\\w+)>)(.+)(<\\/\\2>)(.*)");
        Matcher matcher = pattern.matcher(html);
        
        //if has tag
        if(matcher.matches()) {
            //just declaring variables to turn the code more readable
            tagName = matcher.group(2);
            closingTag = matcher.group(4);
            contentInsideTag = matcher.group(3);
            over = matcher.group(5);

            //eu tentando debugar os grupos - remover depois de pronto
            System.out.println("tagName = " + tagName);
            System.out.println("contentInsideTag = " + contentInsideTag);
            System.out.println("closingTag = " + closingTag);
            System.out.println("over = " + over);            
        } else {
            //this is a text node
            //create a text object
            System.out.println("*****This iteration just have a text*****");
            Text text = new Text(html, father);            
            text.atributtes.add(atrib);
            nodes.add(text);
            father.children.add(text);
            System.out.println("Text created");
            System.out.println("TEXT CONTENT: " + text.text);
            return null;
        }   
        
        //verify if the tag don't has a father
        if (father == null) {
            HTMLnode node = new HTMLnode(tagName, null);
            firstNode = node;
            nodes.add(node);
            return generateTree(contentInsideTag, node);
        } else {
            HTMLnode node = new HTMLnode(tagName, father);
            nodes.add(node); 
            node.content = contentInsideTag;
            father.children.add(node);
            //verify if tag has brothers
            if ("".equals(over.trim())){
                //verify if there is tags in content                
                if (matcher.matches()) {
                    //Has a tag in content
                    return generateTree(contentInsideTag, node);
                }
            } else {
                //remove the closing tag   
                //existe over
                over = over.replaceAll(closingTag, "");
                generateTree(over, father);
                return generateTree(contentInsideTag, father);               
            }
        }
        return null;
    }
    
    public String clearHTML(String html)
    {
        html = "<html><head><title>titulopagina</title></head><body><div><h1>TITULO</h1><p>TEXTO DENTRO<b>DO</b>PARAGRAFO</p></div></body></html>";
     
        //remove new lines from HTML
        String _html = html.replaceAll("\\n", "");
        System.out.println("Removed new lines: " + _html);
        
        //remove comments from HTML
        _html = _html.replaceAll("<!--.*?-->", "");
        System.out.println("Removed comentaries: " + _html);
            
        _html = _html.replaceAll(" ", "");
        return _html;
    }    
}
