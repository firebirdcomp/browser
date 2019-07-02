/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {     
    public ArrayList<Node> nodes = new ArrayList();
    public Node firstNode;
    public String lastTag;
    
    public void Interpreter(String html)
    {   
        html = this.clearHTML(html);
        generateTree(html, null);         
    }    
    
    public String generateTree(String html, HTMLnode father)
    {       
        Atributtes atrib = null;                    
        
        String tagName, closingTag, contentInsideTag, over;        
        
        //Debug
        System.out.println("****************************************************");
        System.out.println("New iterration started");
        System.out.println("current HTML: " + html);
        
        //Verifica se a texto depois da Tag
        Pattern pattern2 = Pattern.compile("(.*?)(<(\\w+)>)(.+)(<\\/\\3>)(.*)");
        Matcher matcher2 = pattern2.matcher(html);

        if(matcher2.matches())
        {
            if(!"".equals(matcher2.group(1)))
            {
                System.out.println("*******A text before tag was founded*********");
                Text text = new Text(matcher2.group(1).replaceAll("<\\w+>", ""), father);
                atrib = new Atributtes(lastTag,father);
                lastTag = "";
                text.atributtes.add(atrib);
                nodes.add(text);
                father.children.add(text);
                html = html.replace(matcher2.group(1), "");
                System.out.println("Text created");
                System.out.println("TEXT CONTENT: " + text.text);
                return generateTree(html, father);
            }            
        }
        
        //Verifica se tem tag
        Pattern pattern = Pattern.compile("(<(\\w+)>)(.+)(<\\/\\2>)(.*)");
        Matcher matcher = pattern.matcher(html);
        
        if(matcher.matches()) {
            tagName = matcher.group(2);
            closingTag = matcher.group(4);
            contentInsideTag = matcher.group(3);
            over = matcher.group(5);          
        } else {
            //Cria um objeto de texto
            System.out.println("This iteration just have a text");
            Text text = new Text(html, father);            
            atrib = new Atributtes(lastTag,father);
            lastTag = "";
            text.atributtes.add(atrib);
            nodes.add(text);
            father.children.add(text);
            System.out.println("Text created");
            System.out.println("TEXT CONTENT: " + text.text);
            return null;
        }   
        
        //Verifica se tem pai, se não tiver é a tag HTML
        if (father == null) {
            HTMLnode node = new HTMLnode(tagName, null);
            firstNode = node;
            nodes.add(node);
            return generateTree(contentInsideTag, node);
        } else {
            HTMLnode node = new HTMLnode(tagName, father);
            lastTag = tagName;
            nodes.add(node); 
            node.content = contentInsideTag;
            father.children.add(node);
            
            //Verifica se tem irmãos
            if ("".equals(over.trim())){
                //verifica se tem tag ainda no html dentro da tag              
                if (matcher.matches()) {
                    return generateTree(contentInsideTag, node);
                }
            } else {
                //existe html alem da tag selecionada
                over = over.replaceAll(closingTag, "");
                generateTree(contentInsideTag, father);     
                return generateTree(over, father);                         
            }
        }
        return null;
    }
    
    public String clearHTML(String html)
    {
        html = 
    "<html> " +
    "<head> " +
    "    <title>Titulo_Da_Pagina</title> " +
    "</head> " +
    "<body> " +
    "    <div> " +
    "        <h1>TITULOGRANDÃO</h1>" +
    "        <b>Texto_Negrito</b> " +
    "        <p>Paragrafo</p> " +
    "        <h6>TituloNãoTãoGrandão</h6>" +
    "        <i>Texto_Italico</i> " +
    "    </div> " +
    "</body> " +
    "</html> ";
                
        //remove new lines from HTML
        String _html = html.replaceAll("\\n", "");
        System.out.println("Removed new lines: " + _html);
        
        //remove comments from HTML
        _html = _html.replaceAll("<!--.*?-->", "");
        System.out.println("Removed comentaries: " + _html);
            
        //remove linhas em branco
        _html = _html.replaceAll(" ", "");
        return _html;
    }    
}
