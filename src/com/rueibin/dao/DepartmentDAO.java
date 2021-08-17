package com.rueibin.dao;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.entity.Department;

public interface DepartmentDAO {
	List<Department> selectDepartments() throws SQLException;
}
