package com.rueibin.dao;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.entity.Employee;

public interface EmployeeDAO {
	List<Employee> selectEmployees() throws Exception;

	List<Employee> selectEmployeesWithPage(int i, int j) throws Exception;

	Employee selectEmployeeById(Integer id) throws SQLException;
	
	int selectEmployeeName(String name) throws SQLException;

	int insert(Employee emp) throws SQLException;

	int update(Employee emp) throws SQLException;

	int delete(Integer id) throws SQLException;

	int deleteBatch(String ids) throws SQLException;

	int getCount() throws SQLException;
}
