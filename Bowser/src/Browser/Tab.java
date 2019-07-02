/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Browser;

public abstract class Tab {
    private String name;
    private String url;
    
    public void setName(String _name)
    {
        name = _name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setUrl(String _name)
    {
        name = _name;
    }
    
    public String getUrl()
    {
        return url;
    }
}
