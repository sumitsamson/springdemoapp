package com.spring.dao;

import java.util.List;

import com.spring.model.Employee;

public interface EmployeeDao {
	
	
	public void saveOrUpdate(Employee employee);
    
    public void delete(int employeeId);
     
    public Employee get(int employeeId);
     
    public List<Employee> list();

}
