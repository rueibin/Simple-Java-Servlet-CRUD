package com.rueibin.test;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.DepartmentDAOImpl;
import com.rueibin.dao.impl.EmployeeDAOImpl;

public class EmployeeTest {
	public static void main(String[] args) throws Exception {
		
		EmployeeDAO d=new EmployeeDAOImpl();
		System.out.println(d.selectEmployees());

	}

}
