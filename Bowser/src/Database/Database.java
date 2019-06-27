/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Felpi
 */
public class Database {
    
    public ResultSet GetHistoricTable()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/BancoDeDados;");
            Statement s = conn.createStatement();            
            s.execute("SELECT * FROM historico");
            ResultSet rs = s.getResultSet();
            return rs;            
        }
        catch(SQLException ex)
        {
            System.out.println("Error sql in select from historic: " + ex.getMessage()); 
            return null;
        }        
    }
    
    public ResultSet GetFavoritesTable()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/BancoDeDados;");
            Statement s = conn.createStatement();            
            s.execute("SELECT * FROM favoritos");
            ResultSet rs = s.getResultSet();
            return rs;            
        }
        catch(SQLException ex)
        {
            System.out.println("Error sql select from favoritos");   
            return null;
        }        
    }   
    
    public void AddFavorite(int user_id, String title, String date, String link)
    {
        String query = "INSERT INTO favoritos (USER_ID, TITLE, DATAHIST, LINKHIST)\n" +
        "VALUES (1, '" + title + "', '2019-01-01 00:00:00', '" + link + "')";
        
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/BancoDeDados;");
            Statement s = conn.createStatement(); 
            s.executeUpdate(query);
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error sql inserir favoritos: " + ex.getMessage());
        }  
    }
    
    public void AddHistoric(int user_id, String title, String date, String link)
    {
        String query = "INSERT INTO historico (USER_ID, TITLE, DATAHIST, LINKHIST)\n" +
        "VALUES (1, '" + title + "', '2019-01-01 00:00:00', '" + link + "')";
        
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/BancoDeDados;");
            Statement s = conn.createStatement(); 
            s.executeUpdate(query);
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error sql inserir historico:" + ex.getMessage());
        }        
    }
    
    public Database()
    {        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Derby driver not found.");
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/BancoDeDados;");
            Statement s = conn.createStatement();  
            //usuario padrao para cada sessão de teste
            s.executeUpdate("    CREATE TABLE usuario" +
                            "    (" +
                            "        nome      VARCHAR(30), " +
                            "        senha     VARCHAR(20), " +
                            "        id      INT not null primary key " +
                            "        GENERATED ALWAYS AS IDENTITY " +
                            "        (START WITH 1, INCREMENT BY 1) " +
                            "    )");
            
            s.executeUpdate("    CREATE TABLE historico " +
                            "    ( " +
                            "            id          INT not null primary key " +
                            "                        GENERATED ALWAYS AS IDENTITY " +
                            "                        (START WITH 1, INCREMENT BY 1), " +
                            "        FOREIGN KEY (user_id) REFERENCES usuario (id), " +
                            "            user_id   INT NOT NULL, " +
                            "        title     VARCHAR(60), " +
                            "        dataHist  DATE, " +
                            "        linkHist  VARCHAR(100) " +
                            "    ) ");
                            
            s.executeUpdate("    CREATE TABLE favoritos" +
                            "    (" +
                            "            id_fav          INT not null primary key " +
                            "                        GENERATED ALWAYS AS IDENTITY " +
                            "                        (START WITH 1, INCREMENT BY 1), " +
                            "        FOREIGN KEY (user_id) REFERENCES usuario (id), " +
                            "            user_id   INT NOT NULL, " +
                            "        title     VARCHAR(60), " +
                            "        dataHist  DATE, " +
                            "        linkHist  VARCHAR(100) " +
                            "    )");           
            s.executeUpdate("INSERT INTO USUARIO (NOME, SENHA) VALUES ('Padrao', '123')");
        } catch (SQLException ex) {
            System.out.println("Error sql criar tabelas iniciais: " + ex.getMessage());
        }
    }
}

//Criação de tabelas
    /*
    CREATE TABLE usuario
    (
        nome      VARCHAR(30),
        senha     VARCHAR(20),
        id      INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
    );

    CREATE TABLE historico
    (
            id          INT not null primary key
                        GENERATED ALWAYS AS IDENTITY
                        (START WITH 1, INCREMENT BY 1),
        FOREIGN KEY (user_id) REFERENCES usuario (id),
            user_id   INT NOT NULL, 
        title     VARCHAR(60),
        dataHist  DATE,
        linkHist  VARCHAR(100)
    );

    CREATE TABLE favoritos
    (
            id_fav          INT not null primary key
                        GENERATED ALWAYS AS IDENTITY
                        (START WITH 1, INCREMENT BY 1),
        FOREIGN KEY (user_id) REFERENCES usuario (id),
            user_id   INT NOT NULL, 
        title     VARCHAR(60),
        dataHist  DATE,
        linkHist  VARCHAR(100)
    );
    /*/
