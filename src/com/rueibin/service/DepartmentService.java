package com.rueibin.service;

import java.sql.SQLException;
import java.util.List;

import com.rueibin.entity.Department;

public interface DepartmentService {
	List<Department> getDepartments() throws SQLException;
}
