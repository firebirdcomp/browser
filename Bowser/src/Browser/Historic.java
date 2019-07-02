/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Browser;

import Database.Database;

public class Historic {

    public void add(String title, String date, String link, int user_Id)
    {
        Database db = new Database();
        db.AddHistoric(user_Id, title, date, link);
    }
}

