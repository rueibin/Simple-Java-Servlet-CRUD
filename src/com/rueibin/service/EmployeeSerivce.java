package com.rueibin.service;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.entity.Employee;
import com.rueibin.util.PageModel;

public interface EmployeeSerivce {

	List<Employee> getEmployees() throws Exception;

	PageModel getEmployeesWithPage(int pn) throws Exception;

	Employee getEmployeeById(Integer id) throws SQLException;
	
	int getEmployeeName(String name) throws SQLException;

	int save(Employee emp) throws SQLException;

	int update(Employee emp) throws SQLException;

	int delete(Integer id) throws SQLException;
	
	int deleteBatch(String ids) throws SQLException; 

}
