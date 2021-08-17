package com.rueibin.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.dao.impl.DepartmentDAOImpl;
import com.rueibin.entity.Department;
import com.rueibin.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

	@Override
	public List<Department> getDepartments() throws SQLException {
		return departmentDAO.selectDepartments();
	}

}
