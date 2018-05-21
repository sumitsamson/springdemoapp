package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate){
		
		this.jdbcTemplate =jdbcTemplate;
		try{
			
			StringBuffer createTable = new StringBuffer();
			createTable.append("CREATE TABLE IF NOT EXISTS employee (");
			createTable.append("empId INT NOT NULL AUTO_INCREMENT,");
			createTable.append("empName VARCHAR(100) NOT NULL,");
			createTable.append("designation VARCHAR(40) NOT NULL,");
			createTable.append("PRIMARY KEY(empId))");
			
			
			StringBuffer records = new StringBuffer();
			records.append("INSERT INTO employee (empName, designation) VALUES ");
			records.append("('Jones','Developer'), ('Dean','Tester'),('Sam','Lead')");
			
			
			jdbcTemplate.execute(createTable.toString());
			jdbcTemplate.execute(records.toString());
			
			logger.info("************************************");
			logger.info("*********** DUMMY DATA ADDED  ******");
			logger.info("************************************");
			
		}catch(Exception e){
			logger.error("Error occured while creating table employee and dummy data",e);
			logger.info("**********************************************************");
			logger.info("*********** ERROR OCCURED WHILE CREATING DUMMY DATA ******");
			logger.info("**********************************************************");
			
		}
	}

	@Override
	public void saveOrUpdate(Employee employee) {
		if (employee.getEmpId() > 0) {
			// update
			String sql = "UPDATE employee SET empName=?, designation=? WHERE empId=?";
			jdbcTemplate.update(sql, employee.getEmpName(), employee.getDesignation(), employee.getEmpId());

		} else {
			// insert
			String sql = "INSERT INTO employee (empName, designation)" + " VALUES (?, ?)";
			jdbcTemplate.update(sql, employee.getEmpName(), employee.getDesignation());
		}

	}

	@Override
	public void delete(int empId) {
		logger.info("Deleting employee :"+empId);
		String sql = "DELETE FROM employee WHERE empId=?";
		jdbcTemplate.update(sql, empId);

	}

	@Override
	public Employee get(int employeeId) {
		String sql = "SELECT * FROM employee WHERE empId=" + employeeId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Employee>() {

			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Employee employee = new Employee();
					employee.setEmpId(rs.getInt("empId"));
					employee.setEmpName(rs.getString("empName"));
					employee.setDesignation(rs.getString("designation"));
					return employee;
				}

				return null;
			}

		});
	}

	@Override
	public List<Employee> list() {
		String sql = "SELECT * FROM employee";
		List<Employee> employeeList = jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setEmpId(rs.getInt("empId"));
				employee.setEmpName(rs.getString("empName"));
				employee.setDesignation(rs.getString("designation"));
				return employee;
			}

		});

		return employeeList;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
