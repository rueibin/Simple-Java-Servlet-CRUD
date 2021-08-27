package com.rueibin.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.EmployeeDAOImpl;
import com.rueibin.entity.Employee;
import com.rueibin.service.EmployeeSerivce;
import com.rueibin.util.PageModel;

public class EmployeeSerivceImpl implements EmployeeSerivce {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	@Override
	public List<Employee> getEmployees() throws Exception {
		return employeeDAO.selectEmployees();
	}

	@Override
	public PageModel getEmployeesWithPage(int pn) throws Exception {
		int totalRecords = employeeDAO.getCount();
		PageModel pm = new PageModel(pn, totalRecords, 5);
		List<Employee> list = employeeDAO.selectEmployeesWithPage((pn - 1) * 5, 5);
		pm.setList(list);

		pm.setUrl("");
		return pm;
	}

	@Override
	public Employee getEmployeeById(Integer id) throws SQLException {
		return employeeDAO.selectEmployeeById(id);
	}
	
	@Override
	public int getEmployeeName(String name) throws SQLException {
		return employeeDAO.selectEmployeeName(name);
	}

	@Override
	public int save(Employee emp) throws SQLException {
		employeeDAO.insert(emp);
		return 0;
	}

	@Override
	public int update(Employee emp) throws SQLException {
		employeeDAO.update(emp);
		return 0;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		employeeDAO.delete(id);
		return 0;
	}

	@Override
	public int deleteBatch(String ids) throws SQLException {
		return employeeDAO.deleteBatch(ids);
	}

	

}
