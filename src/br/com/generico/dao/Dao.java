/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;
/**
 *
 * @author Marcos
 */
public class Dao {

    private static Dao instance;
    private static Logger logger = Logger.getLogger(Dao.class.getName());
    
    private Dao() {
    }

    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {        
        Class.forName("org.postgresql.Driver");
        String connectionURL = "jdbc:postgresql://localhost:5432/fisio";
        return DriverManager.getConnection(connectionURL, "fisio", "fisio");        
    }

    public static void main(String[] args) {
        try {
            Connection conn = new Dao().getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from test;");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
            conn.close();
            rs.close();
            stm.close();
        } catch (Exception e) {
            logger.error("Erro neste ponto:", e);
        }
    }
}
