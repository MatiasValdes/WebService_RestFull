/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.dao;

import com.ryc.restful.ws_restful.Exceptions.NotFoundEntityException;
import com.ryc.restful.ws_restful.modelo.Employees;
import com.ryc.restful.ws_restful.util.Conexion;
import com.ryc.restful.ws_restful.util.Constantes;
import com.ryc.restful.ws_restful.util.CustomLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RyC
 */
public class UsersDAO {
    
    private final Conexion con = new Conexion();
    private Connection connection;
    private CustomLogger logger = new CustomLogger();
    
    public boolean validateUser(Employees user) throws ClassNotFoundException
    {    
        Employees employee = null;
        try {
            
            String sql = "SELECT * FROM EMPLOYEES WHERE EMAIL = ? AND PASSWORD = ?";
            
            con.conectar();
            connection = con.getJdbcConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                employee = new Employees();
                
                employee.setId(rs.getInt("EMPLOYEE_ID"));
                employee.setFirst_name(rs.getString("FIRST_NAME"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setPassword(rs.getString("PASSWORD"));
            }
            con.desconectar();
            
        } catch (SQLException ex) {
            logger.Customlogg("UsersDAO", "validateUser", ex.getMessage(), Constantes.TYPE_LOG.FATAL);
        }
        
        if(employee == null)
        {
            logger.simpleLog("usuario " + user.getEmail() + " - " +user.getPassword()+ " no se encuentra", Constantes.TYPE_LOG.INFO);
            //throw new NotFoundEntityException("USUARIO NO VALIDO");
        }else if(employee.getEmail().equals(user.getEmail()) && employee.getPassword().equals(user.getPassword()))
            return true;

        return false;
    }
    
}
