package com.rueibin.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rueibin.base.BaseServlet;
import com.rueibin.entity.Department;
import com.rueibin.service.DepartmentService;
import com.rueibin.service.impl.DepartmentServiceImpl;

@WebServlet("/dept")
public class DepartmentServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private DepartmentService departmentService=new DepartmentServiceImpl();
	
	public void deptData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Department> list =  departmentService.getDepartments();
		Gson gson = new Gson();
		String deptJson = gson.toJson(list);
		response.getWriter().print(deptJson);
	}

}
