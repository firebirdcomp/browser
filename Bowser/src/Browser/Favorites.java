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
public class Favorites {
    private ArrayList<String> favorites = new ArrayList();    
    
    public void add(String title, String date)
    {
        
    }
    
    public ArrayList<String> getFavorites()
    {
        favorites.clear();
        favorites.add("Favorito 1         www.teste.com.br           15-05-2019");
        favorites.add("Favorito 2         www.teste.com.br           15-05-2019");
        favorites.add("Favorito 3         www.teste.com.br           15-05-2019");
        return favorites;
    }
}
