package com.ryc.restful.ws_restful.dao;

import com.ryc.restful.ws_restful.Exceptions.NotFoundEntityException;
import com.ryc.restful.ws_restful.modelo.Employees; 
import com.ryc.restful.ws_restful.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author RyC
 */
public class EmployeesDAO {
    
    private final Conexion con = new Conexion();
    private Connection connection;
    
    public Employees addEmploye(Employees employee) throws SQLException, ClassNotFoundException {        
        String sql = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, SALARY, COMMISSION_PCT, JOB_ID, DEPARTMENT_ID, PASSWORD) VALUES(employees_seq.nextval, ?,?,?,?,?,?,?,?,?,?)";

        System.out.println("query " + sql);
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, employee.getFirst_name());
        ps.setString(2, employee.getLast_name());
        ps.setString(3, employee.getEmail());
        ps.setString(4, employee.getPhone_number());
        ps.setDate(5, new java.sql.Date(employee.getHre_date().getTime()));
        ps.setDouble(6, employee.getSalary());
        ps.setDouble(7, employee.getCommission_pct());
        ps.setString(8, employee.getJOB_ID());
        ps.setInt(9, employee.getDEPARTMENT_ID());
        ps.setString(10, employee.getPassword());
        
        int row = ps.executeUpdate();
        
        if(row > 0)
            employee = getEmployeeByEmail(employee.getEmail());
        
        con.desconectar();
        
        return employee;
    }
    
    public List<Employees> EmployeesList() throws SQLException, ClassNotFoundException {
        
        List<Employees> employeeList = new ArrayList<Employees>();
        String sql = "SELECT * FROM EMPLOYEES";
        
        con.conectar();
        connection = con.getJdbcConnection();
        Statement stm = connection.createStatement();
        ResultSet resulSet = stm.executeQuery(sql);

        while (resulSet.next()) {

            int id = resulSet.getInt("EMPLOYEE_ID");
            String first_name = resulSet.getString("FIRST_NAME");
            String last_name = resulSet.getString("LAST_NAME");
            String email = resulSet.getString("EMAIL");
            String phone_number = resulSet.getString("PHONE_NUMBER");
            Date hre_date = resulSet.getDate("HIRE_DATE");
            Double salary = resulSet.getDouble("SALARY");
            Double commission_pct = resulSet.getDouble("COMMISSION_PCT");
            String JOB_ID = resulSet.getString("JOB_ID");
            int DEPARTMENT_ID = resulSet.getInt("DEPARTMENT_ID");

            Employees employee = new Employees(id, first_name, last_name, email, phone_number, hre_date, salary, commission_pct, JOB_ID, DEPARTMENT_ID);
            employeeList.add(employee);
        }
        con.desconectar();
        
        return employeeList;
    }
    
    public Employees getEmployeeById(int id) throws ClassNotFoundException{

        Employees employee = null;
        String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
            
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                System.out.println("WORKING");
                
                employee = new Employees();
                
                employee.setId(rs.getInt("EMPLOYEE_ID"));
                employee.setFirst_name(rs.getString("FIRST_NAME"));
                employee.setLast_name(rs.getString("LAST_NAME"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setPhone_number(rs.getString("PHONE_NUMBER"));
                employee.setHre_date(rs.getDate("HIRE_DATE"));
                employee.setSalary(rs.getDouble("SALARY"));
                employee.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
                employee.setJOB_ID(rs.getString("JOB_ID"));
                employee.setDEPARTMENT_ID(rs.getInt("DEPARTMENT_ID"));
            }
            con.desconectar();
            
        } catch (SQLException ex) {
            //Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        if(employee == null)
        {
            throw new NotFoundEntityException("No hay datos");   
        }
        
        return employee;
    }
    
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        System.out.println("Method deleteEmployee( "+ id +" )");
        
        String sql = "delete from EMPLOYEES where EMPLOYEE_ID = ?";
        boolean status = false;
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        
        int row = ps.executeUpdate();
        
        System.out.println("excecute " + row);
        
        con.desconectar();
        
        if(row > 0)
            status= true;
        
        return status;
    }
    
    public boolean updateEmployee(Employees employee, int id) throws SQLException, ClassNotFoundException {
        
        String sql = "UPDATE  EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, HIRE_DATE = ?, JOB_ID = ?, SALARY = ?, COMMISSION_PCT = ?, DEPARTMENT_ID = ?  WHERE EMPLOYEE_ID = ?";
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, employee.getFirst_name());
        ps.setString(2, employee.getLast_name());
        ps.setString(3, employee.getEmail());
        ps.setString(4, employee.getPhone_number());
        ps.setDate(5, new java.sql.Date(employee.getHre_date().getTime()));
        ps.setString(6, employee.getJOB_ID());
        ps.setDouble(7, employee.getSalary());
        ps.setDouble(8, employee.getCommission_pct());
        ps.setInt(9, employee.getDEPARTMENT_ID());
        ps.setInt(10, id);
        
        int row = ps.executeUpdate();
        
        con.desconectar();
        
        if(row > 0)
            return true;
        else
            return false;
    }

    public List<Employees> getEmployeesByDepartmentID(int department_id) throws SQLException, ClassNotFoundException {
        
        List<Employees> employeeList = new ArrayList<Employees>();
        String sql = "SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID = ?";
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, department_id);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {

            int id = rs.getInt("EMPLOYEE_ID");
            String first_name = rs.getString("FIRST_NAME");
            String last_name = rs.getString("LAST_NAME");
            String email = rs.getString("EMAIL");
            String phone_number = rs.getString("PHONE_NUMBER");
            Date hre_date = rs.getDate("HIRE_DATE");
            Double salary = rs.getDouble("SALARY");
            Double commission_pct = rs.getDouble("COMMISSION_PCT");
            String JOB_ID = rs.getString("JOB_ID");
            int DEPARTMENT_ID = rs.getInt("DEPARTMENT_ID");

            Employees employee = new Employees(id, first_name, last_name, email, phone_number, hre_date, salary, commission_pct, JOB_ID, DEPARTMENT_ID);
            employeeList.add(employee);
        }
        
        con.desconectar();
        
        return employeeList;
    }

    public Employees getEmployeeByEmail(String email) throws ClassNotFoundException{

        Employees employee = null;
        String sql = "SELECT * FROM EMPLOYEES WHERE EMAIL = ?";
            
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                employee = new Employees();
                
                employee.setId(rs.getInt("EMPLOYEE_ID"));
            }
            con.desconectar();
            
        } catch (SQLException ex) {
            //Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if(employee == null)
        {
            System.out.println("No hay DATOS");
            throw new NotFoundEntityException("No hay datos");   
        }
        
        return employee;
    }

    public boolean updateToken(int id, String token) throws SQLException, ClassNotFoundException
    {
        String sql = "UPDATE EMPLOYEES SET TOKEN = ? WHERE EMPLOYEE_ID = ?";
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, token);
        ps.setInt(2, id);
        
        int row = ps.executeUpdate();
        
        con.desconectar();
        
        if(row > 0)
            return true;
        
        return false;
    }
    
    public String getTokenEmployeeById(int id) throws ClassNotFoundException {
        String token = "";
        String sql = "SELECT TOKEN FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
            
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                token = rs.getString("TOKEN");
            }
            con.desconectar();
            
        } catch (SQLException ex) {
            //Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("*******token******** " + token);
        
        return token;
    }
}
