/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyC
 * 
 */
public class Conexion {
    
    private Connection jdbcConnection = null;
    private String jdbcURL = "jdbc:oracle:thin:@192.168.20.140:1521:orcl";
    private String jdbcUsername = "CLASEPLSQL";
    private String jdbcPassword = "Ryc2018";
    
    public Conexion() {        
        this.jdbcURL = "jdbc:oracle:thin:@192.168.20.140:1521:orcl";
        this.jdbcUsername = "CLASEPLSQL";
        this.jdbcPassword = "Ryc2018";
    }
 
    public void conectar() throws SQLException, ClassNotFoundException{
            if (jdbcConnection == null || jdbcConnection.isClosed()) {
                Class.forName("oracle.jdbc.OracleDriver");
                jdbcConnection = DriverManager.getConnection(
                            jdbcURL, jdbcUsername, jdbcPassword);
            }
    }
     
    public void desconectar() throws SQLException {        
        if (jdbcConnection != null && !jdbcConnection.isClosed())
            jdbcConnection.close();
    }
 
	public Connection getJdbcConnection() {
		return jdbcConnection;
	}
}
