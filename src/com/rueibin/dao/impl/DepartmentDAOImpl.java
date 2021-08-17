package com.rueibin.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.entity.Department;
import com.rueibin.util.JDBCUtil;

public class DepartmentDAOImpl implements DepartmentDAO {

	@Override
	public List<Department> selectDepartments() throws SQLException {
		String sql = "select * from department";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		return qr.query(sql, new BeanListHandler<Department>(Department.class));
	}

}
