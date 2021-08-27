package com.rueibin.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.rueibin.dao.DepartmentDAO;
import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.DepartmentDAOImpl;
import com.rueibin.dao.impl.EmployeeDAOImpl;

public class EmployeeTest {
	public static void main(String[] args) throws Exception {
		
		//EmployeeDAO d=new EmployeeDAOImpl();
		//System.out.println(d.selectEmployees());

		Map<String,String> aa =new HashMap<String,String>();
		
		//aa.put("aa", "a error");
		//aa.put("bb", "b error");
		
		Gson gson = new Gson();
		
		String cc=gson.toJson(aa);
		
		System.out.println(cc);
		
	}

}
