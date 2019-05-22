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
    public int actualLevel;    
    
    public void Interpreter(String html)
    {   
        html = this.clearHTML(html);
        generateTree(html, null);         
    }
    
    public String generateTree(String html, HTMLnode father)
    {        
        Pattern pattern = Pattern.compile("(<(\\w+)>)(.+)(<\\/\\2>)(.*)");
        Matcher matcher = pattern.matcher(html);
        
//        debug
        System.out.println("****************************************************");
        System.out.println("New iterration started");
        System.out.println("current HTML: " + html);
        
        String tagName = "", 
               closingTag = "", 
               contentInsideTag = "", 
               over = "";
        
        if(matcher.matches())
        {
//            just declaring variables to turn the code more readable
            tagName = matcher.group(2);
            closingTag = matcher.group(4);
            contentInsideTag = matcher.group(3);
            over = matcher.group(5);

//            eu tentando debugar os grupos - remover depois de pronto
            System.out.println("tagName = " + tagName);
            System.out.println("contentInsideTag = " + contentInsideTag);
            System.out.println("closingTag = " + closingTag);
            System.out.println("over = " + over);
        }
        else 
        {
            System.out.println("Nothing was matched");
        }
        
//        verify if the tag don't has a father
        if (father == null) {
            HTMLnode node = new HTMLnode(tagName);
            return generateTree(contentInsideTag, node);
        } else {
            HTMLnode node = new HTMLnode(tagName);
            node.content = contentInsideTag;
            node.father = father;
            father.children.add(node);  //always return error ????
//            verify if tag has brothers
            if ("".equals(over)) {
//                verify if there is tags in content
                Pattern pattern2= Pattern.compile("\\<(\\w+)\\>");
                Matcher matcher2 = pattern.matcher(contentInsideTag);
                if (matcher2.matches()) {
                    return generateTree(contentInsideTag, node);
                }
                else
                {
                    //TODO
                }
            } else {
//                remove the closing tag
                over = over.replaceAll(closingTag, "");
                generateTree(over, father);
                return generateTree(contentInsideTag, father);
            }
        }
        return "Something went wrong";
    }
    
    public String clearHTML(String html)
    {
        html = "<html><head><title></title></head><body><div><h1>TITULO</h1><p>TEXTO DENTRO<b>DO</b>PARAGRAFO</p></div></body></html>";
     
//        remove new lines from HTML
        String _html = html.replaceAll("\\n", "");
            System.out.println("Removed new lines: " + _html);
//        remove comments from HTML
         _html = _html.replaceAll("<!--.*?-->", "");
            System.out.println("Removed comentaries: " + _html);
            
         _html = _html.replaceAll(" ", "");
        return _html;
    }    
}
