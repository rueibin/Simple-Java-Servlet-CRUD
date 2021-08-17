package com.rueibin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rueibin.base.BaseServlet;
import com.rueibin.entity.Employee;
import com.rueibin.service.EmployeeSerivce;
import com.rueibin.service.impl.EmployeeSerivceImpl;
import com.rueibin.util.PageModel;

@WebServlet("/emp")
public class EmployeeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeSerivce employeeSerivce = new EmployeeSerivceImpl();

	public void empData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pn = Integer.parseInt(request.getParameter("pn"));
		PageModel pm = employeeSerivce.getEmployeesWithPage(pn);
		Gson gson = new Gson();
		String empJson = gson.toJson(pm);
		response.getWriter().print(empJson);
	}
	
	public void empData2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee emp=employeeSerivce.getEmployeeById(Integer.parseInt(request.getParameter("id")));
		Gson gson = new Gson();
		String empJson = gson.toJson(emp);
		response.getWriter().print(empJson);
	}
	
	public void saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee emp=new Employee();
		emp.setName(request.getParameter("name"));
		emp.setEmail(request.getParameter("email"));
		emp.setGender(Integer.parseInt(request.getParameter("gender")));
		emp.setDeptId(Integer.parseInt(request.getParameter("deptId")));
		
		int dd=employeeSerivce.save(emp);
		response.getWriter().print(dd);
		
	}
	
	public void updateData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee emp=new Employee();
		emp.setId(Integer.parseInt(request.getParameter("id")));
		emp.setName(request.getParameter("name"));
		emp.setEmail(request.getParameter("email"));
		emp.setGender(Integer.parseInt(request.getParameter("gender")));
		emp.setDeptId(Integer.parseInt(request.getParameter("deptId")));
		
		int dd=employeeSerivce.update(emp);
		response.getWriter().print(dd);
	}
	
	public void deleteData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int dd=employeeSerivce.delete(Integer.parseInt(request.getParameter("id")));
		response.getWriter().print(dd);
	}
	
	public void deleteBatchData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
	}
}
