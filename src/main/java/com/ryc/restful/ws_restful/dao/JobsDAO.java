/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.dao;

import com.ryc.restful.ws_restful.modelo.Jobs;
import com.ryc.restful.ws_restful.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RyC
 */
public class JobsDAO {
    
    private final Conexion con = new Conexion();
    private Connection connection;
    
    public Jobs addJobs(Jobs job) throws SQLException, ClassNotFoundException {
        System.out.println("Method addEmploye()");
        
        String sql = "INSERT INTO JOBS(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) VALUES(?,?,?,?)";
        
        System.out.println("query " + sql);
        System.out.println(job.toString());
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, job.getJob_ib());
        ps.setString(2, job.getJob_title());
        ps.setDouble(3, job.getMin_salary());
        ps.setDouble(5, job.getMax_salary());
        
        int row = ps.executeUpdate();
        
        System.out.println("excecute " + row);
        
        con.desconectar();
        
        if(row > 0)
            return job;
        else return null;
    }
    
    public List<Jobs> JobsList() throws SQLException, ClassNotFoundException {
        System.out.println("Method EmployeesList()");
        
        List<Jobs> jobsList = new ArrayList<Jobs>();
        
        String sql = "SELECT * FROM JOBS";
        System.out.println("query " + sql);
        
        con.conectar();
        connection = con.getJdbcConnection();
        Statement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
            String id = rs.getString("JOB_ID");
            String title = rs.getString("JOB_TITLE");
            Double min_salary = rs.getDouble("MIN_SALARY");
            Double max_salary = rs.getDouble("MAX_SALARY");
            
            Jobs job = new Jobs(id, title, min_salary, max_salary);
            jobsList.add(job);
        }
        
        con.desconectar();
        
        return jobsList;
    }
    
    public Jobs getJobById(String id) throws SQLException, ClassNotFoundException {
        System.out.println("Method getJobById("+ id +")");
        
        Jobs job = null;
        
        String sql = "SELECT * FROM JOBS WHERE JOB_ID = ?";
        System.out.println("query " + sql);
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            job = new Jobs();
            
            job.setJob_ib(rs.getString("JOB_ID"));
            job.setJob_title(rs.getString("JOB_TITLE"));
            job.setMin_salary(rs.getDouble("MIN_SALARY"));
            job.setMax_salary(rs.getDouble("MAX_SALARY"));
        }
        
        con.desconectar();
        
        return job;
    }
    
    public Jobs updateJobs(Jobs job, String id) throws SQLException, ClassNotFoundException {
        System.out.println("Method updateJobs("+ id +")");
        
        String sql = "UPDATE JOBS SET JOB_TITLE = ?, MIN_SALARY = ?, MAX_SALARY = ? WHERE JOB_ID = ?";
        System.out.println("query " + sql);
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, job.getJob_title());
        ps.setDouble(2, job.getMin_salary());
        ps.setDouble(3, job.getMax_salary());
        ps.setString(4, id);
        
        int row = ps.executeUpdate();
        
        con.desconectar();
        
        System.out.println("excecute " + row);
        
        if(row > 0)
            return job;
        else return null;
    }
    
    public boolean deleteJob(String id) throws SQLException, ClassNotFoundException {
        System.out.println("Method deleteJob("+ id +")");
        
        boolean status = false;
        String sql = "DELETE FROM JOBS WHERE JOB_ID = ?";
        System.out.println("query " + sql);
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        
        int row = ps.executeUpdate();
        
        System.out.println("excecute " + row);
        
        con.desconectar();
        
        if(row > 0)
            status = true;
        
        return status;
    }
}