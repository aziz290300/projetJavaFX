/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class Myconnection {
    

    public String url="jdbc:mysql://localhost:3306/testdb";
    public String login="root";
    public String pwd="";
    Connection cnx;
    
    public Myconnection(){ 
        try {
           cnx= DriverManager.getConnection(url, login, pwd);
           System.out.println("connexion etablie!");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
        
}
    public Connection getcnx(){
        return cnx;
    }
}
