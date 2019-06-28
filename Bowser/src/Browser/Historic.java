/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Browser;

import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author Felpi
 */
public class Historic {
    private ArrayList<String> historic = new ArrayList();
    
    public void add(String title, String date, String link, int user_Id)
    {
        Database db = new Database();
        db.AddHistoric(user_Id, title, date, link);
    }
    
    public ArrayList<String> getHistoric()
    {
        historic.clear();
//        historic.add("Historico 1         www.teste.com.br           15-05-2019");
//        historic.add("Historico 2         www.teste.com.br           15-05-2019");
//        historic.add("Historico 3         www.teste.com.br           15-05-2019");
        return historic;
    }
}

