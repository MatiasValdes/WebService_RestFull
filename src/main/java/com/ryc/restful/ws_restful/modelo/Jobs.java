/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.modelo;

/**
 *
 * @author RyC
 */
public class Jobs {
    
    private String job_ib;
    private String job_title;
    private Double min_salary;
    private Double max_salary;
    
    Employees employee;

    public Jobs() {
    }

    public Jobs(String job_ib, String job_title, Double min_salary, Double max_salary) {
        this.job_ib = job_ib;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public String getJob_ib() {
        return job_ib;
    }

    public void setJob_ib(String job_ib) {
        this.job_ib = job_ib;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Double min_salary) {
        this.min_salary = min_salary;
    }

    public Double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(Double max_salary) {
        this.max_salary = max_salary;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
}
