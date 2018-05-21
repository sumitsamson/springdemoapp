package com.spring.service;

import java.util.List;

import com.spring.model.Employee;

public interface EmployeeService {

	public void saveOrUpdate(Employee employee);

	public void delete(int empId);

	public Employee get(int empId);

	public List<Employee> list();

}
