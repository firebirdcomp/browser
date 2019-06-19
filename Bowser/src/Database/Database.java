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
            ex.printStackTrace();   
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
            ex.printStackTrace();   
            return null;
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
            s.execute("SELECT * FROM usuario");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                System.out.println("Nome: "+rs.getString("NOME"));
                System.out.println("Senha: "+rs.getString("SENHA"));
                System.out.println("ID: "+rs.getString("ID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        dataHist  DATE 
    );

    CREATE TABLE favoritos
    (
            id_fav          INT not null primary key
                        GENERATED ALWAYS AS IDENTITY
                        (START WITH 1, INCREMENT BY 1),
        FOREIGN KEY (user_id) REFERENCES usuario (id),
            user_id   INT NOT NULL, 
        title     VARCHAR(60),
        dataHist  DATE 
    );
    /*/
