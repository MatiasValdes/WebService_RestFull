package com.ryc.restful.ws_restful.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyC
 */
@XmlRootElement
public class Employees implements Serializable{
    
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Date hre_date;
    private Double salary;
    private Double commission_pct;
    private String JOB_ID;
    private int DEPARTMENT_ID;
    private String password;
    private String token;
    
    private List<Navegation> navegation = new ArrayList<>();

    public Employees() {
    }
    
    public Employees(String first_name, String last_name, String email, String phone_number, Date hre_date, Double salary, Double commission_pct, String JOB_ID, int DEPARTMENT_ID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hre_date = hre_date;
        this.salary = salary;
        this.commission_pct = commission_pct;
        this.JOB_ID = JOB_ID;
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public Employees(int id, String first_name, String last_name, String email, String phone_number, Date hre_date, Double salary, Double commission_pct, String JOB_ID, int DEPARTMENT_ID) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hre_date = hre_date;
        this.salary = salary;
        this.commission_pct = commission_pct;
        this.JOB_ID = JOB_ID;
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public Employees(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getHre_date() {
        return hre_date;
    }

    public void setHre_date(Date hre_date) {
        this.hre_date = hre_date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommission_pct() {
        return commission_pct;
    }

    public void setCommission_pct(Double commission_pct) {
        this.commission_pct = commission_pct;
    }

    public String getJOB_ID() {
        return JOB_ID;
    }

    public void setJOB_ID(String JOB_ID) {
        this.JOB_ID = JOB_ID;
    }

    public int getDEPARTMENT_ID() {
        return DEPARTMENT_ID;
    }

    public void setDEPARTMENT_ID(int DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setDEPARTMENT_ID(Integer DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public List<Navegation> getNavegation() {
        return navegation;
    }

    public void setNavegation(List<Navegation> navegation) {
        this.navegation = navegation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return "Employees{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", phone_number=" + phone_number + ", hre_date=" + hre_date + ", salary=" + salary + ", commission_pct=" + commission_pct + ", JOB_ID=" + JOB_ID + ", DEPARTMENT_ID=" + DEPARTMENT_ID + '}';
    }

}
