/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Browser;

import Database.Database;

/**
 *
 * @author Felpi
 */
public class Favorites {  
    
    public void add(String title, String date, String link, int id)
    {
        Database db = new Database();
        db.AddFavorite(id, title, date, link);
    }
}
