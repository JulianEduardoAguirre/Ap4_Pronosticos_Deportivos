/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aguir
 */
public abstract class DAO {
    protected Connection conexion;
    protected ResultSet resultado;
    protected Statement sentencia;
    
    private String USER = "root";
    private String PASSWORD = "root";
    private String DATABASE = "personas_prode";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void conectarBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    
    protected void desconectarBase() throws Exception {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }          
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    protected void consultarBase (String sql) throws Exception{
        try {
            conectarBase();
            
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }  

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setDATABASE(String DATABASE) {
        this.DATABASE = DATABASE;
    }
    
    
    
}
