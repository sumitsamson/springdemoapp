package com.spring.model;

public class Employee {

	private String empName;
	private int empId;
	private String designation;
	
	public Employee(){}
	
	public Employee(int empId,String empName,String designation){
		this.empId = empId;
		this.empName = empName;
		this.designation = designation;
	}

	public String getEmpName() {
		return empName;
	}

	public int getEmpId() {
		return empId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
