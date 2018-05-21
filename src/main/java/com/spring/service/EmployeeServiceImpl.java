package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.EmployeeDao;
import com.spring.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);

	}

	@Override
	public void delete(int empId) {
		employeeDao.delete(empId);

	}

	@Override
	public Employee get(int empId) {
		
		return employeeDao.get(empId);
	}

	@Override
	public List<Employee> list() {
		// TODO Auto-generated method stub
		return employeeDao.list();
	}

}
