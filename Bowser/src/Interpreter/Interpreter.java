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
                if (contentInsideTag.matches("\\<(\\w+)\\>")) {
                    return generateTree(contentInsideTag, node);
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
        html = "<html>\n" +
"<head>\n" +
"    <title>Exemplo de página</title>\n" +
"</head>\n" +
"<body>\n" +
"    <h1><u>Modelo de documento HTML</u></h1>\n" +
"    \n" +
"    <!-- Cabeçalhos -->\n" +
"    <h1>Hello <s>World</s> Gurizada! </h1>\n" +
"    <h2>Hello <s>World</s> Abiguinhos! </h2>\n" +
"    <h3>Hello <s>World</s> Cambada! </h3>\n" +
"    <!-- Linha de separação -->\n" +
"    \n" +
"    <!-- \n" +
"        A quebra de linha feita no HTML não irá influenciar o texto.\n" +
"        Apenas irá quebrar a linha caso seja utilizado.\n" +
"    -->\n" +
"    <p>\n" +
"        Lorem, ipsum <b>dolor sit amet</b> consectetur adipisicing elit.\n" +
"        Deserunt<i> tempore ea, architecto suscipit voluptas sed</i> beatae  \n" +
"        dicta incidunt minima sint unde hic, iure porro animi ab nihil \n" +
"        error. Enim, repellat.\n" +
"        </p>\n" +
"    <!-- Tabelas -->\n" +
"    <h2>Tabela</h2>\n" +
"    <table>\n" +
"        <!-- Cabeçalho da tabela -->\n" +
"        <thead>\n" +
"            <!-- Cria uma linha -->\n" +
"            <tr>\n" +
"                <!-- <th> destaca o texto e marca como um elemento do cabeçalho da tabela -->\n" +
"                    <th>Nome</th>\n" +
"                    <th>Idade</th>\n" +
"                    <th>País</th>\n" +
"                </tr>\n" +
"        </thead>\n" +
"        <!-- Corpo da tabela -->\n" +
"        <tbody>\n" +
"            <tr>\n" +
"                <td>Pedro</td>\n" +
"                <td>12</td>\n" +
"                <td>Brasil</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>Arduino</td>\n" +
"                <td>9</td>\n" +
"                <td>Brasil</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>Mateus</td>\n" +
"                <td>18</td>\n" +
"                <td>Brasil</td>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table>\n" +
"    <!-- Listas -->\n" +
"    <h2>Listas</h2>\n" +
"    <h3>Lista comum</h3>\n" +
"    <ul>\n" +
"        <li>Item 1</li>\n" +
"        <li>Item 2</li>\n" +
"        <li>Item 3</li>\n" +
"        <li>Item 4</li>\n" +
"    </ul>\n" +
"    <h3>Lista com ordenação</h3>\n" +
"    <ol>\n" +
"        <li>Item 1</li>\n" +
"        <li>Item 2</li>\n" +
"        <li>Item 3</li>\n" +
"        <li>Item 4</li>\n" +
"    </ol>\n" +
"</body>\n" +
"</html> ";
     
//        remove new lines from HTML
        String _html = html.replaceAll("\\n", "");
            System.out.println("Removed new lines: " + _html);
//        remove comments from HTML
         _html = _html.replaceAll("<!--.*?-->", "");
            System.out.println("Removed comentaries: " + _html);
        return _html;
    }    
}
