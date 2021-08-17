package com.rueibin.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.entity.Department;
import com.rueibin.entity.Employee;
import com.rueibin.util.JDBCUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> selectEmployees() throws Exception {
		String sql = "select e.id, e.name, e.gender, e.email, e.dept_id, d.name as dept_name from employee e,department d where e.dept_id=d.id";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);

		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(processor));
		List<Employee> emps = new ArrayList<Employee>();
		for (Map<String, Object> map : query) {
			Employee emp = new Employee();
			Department dept = new Department();
			emp.setId((Integer) map.get("id"));
			emp.setName((String) map.get("name"));
			emp.setGender((Integer) map.get("gender"));
			emp.setEmail((String) map.get("email"));
			emp.setDeptId((Integer) map.get("dept_id"));
			dept.setId(emp.getDeptId());
			dept.setName((String) map.get("dept_name"));
			emp.setDept(dept);
//			BeanUtils.populate(emp,map);
			emps.add(emp);
		}

		return emps;
	}

	@Override
	public List<Employee> selectEmployeesWithPage(int i, int j) throws Exception {
		String sql = "select e.id, e.name, e.gender, e.email, e.dept_id, d.name as dept_name from employee e,department d where e.dept_id=d.id limit ?, ?";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(processor), i, j);
		List<Employee> emps = new ArrayList<Employee>();
		for (Map<String, Object> map : query) {
			Employee emp = new Employee();
			Department dept = new Department();
			emp.setId((Integer) map.get("id"));
			emp.setName((String) map.get("name"));
			emp.setGender((Integer) map.get("gender"));
			emp.setEmail((String) map.get("email"));
			emp.setDeptId((Integer) map.get("dept_id"));
			dept.setId(emp.getDeptId());
			dept.setName((String) map.get("dept_name"));
			emp.setDept(dept);
			emps.add(emp);
		}

		return emps;
	}

	@Override
	public Employee selectEmployeeById(Integer id) throws SQLException {
		String sql = "select * from employee where id=?";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		return qr.query(sql, new BeanHandler<Employee>(Employee.class, processor), id);
	}

	@Override
	public int insert(Employee emp) throws SQLException {
		String sql = "insert into employee(name, gender, email, dept_id) values(?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		Object[] params = { emp.getName(), emp.getGender(), emp.getEmail(), emp.getDeptId() };

		return qr.update(sql, params);
	}

	@Override
	public int update(Employee emp) throws SQLException {
		String sql = "update employee set name=?, gender=?, email=?, dept_id=? where id=?";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		Object[] params = { emp.getName(), emp.getGender(), emp.getEmail(), emp.getDeptId(), emp.getId() };

		return qr.update(sql, params);
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from employee where id=?";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

		return qr.update(sql, id);
	}

	@Override
	public int getCount() throws SQLException {
		String sql = "select count(id) from employee";
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

}
