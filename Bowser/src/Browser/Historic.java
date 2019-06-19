/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Browser;

import java.util.ArrayList;

/**
 *
 * @author Felpi
 */
public class Historic {
    private ArrayList<String> historic = new ArrayList();
    
    public void add(String title, String date)
    {
        
    }
    
    public ArrayList<String> getHistoric()
    {
        historic.clear();
        historic.add("Historico 1");
        historic.add("Historico 2");
        historic.add("Historico 3");
        return historic;
    }
}

