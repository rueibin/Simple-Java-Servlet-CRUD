package com.rueibin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Employee emp = employeeSerivce.getEmployeeById(Integer.parseInt(request.getParameter("id")));
		Gson gson = new Gson();
		String empJson = gson.toJson(emp);
		response.getWriter().print(empJson);
	}
	
	
	public void checkEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.getWriter().print(employeeSerivce.getEmployeeName(request.getParameter("name")));
	}
	

	public void saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee emp = Http2Object(request,1);
		
		if (empValidate(emp).size() != 0) {
			response.getWriter().print(new Gson().toJson(empValidate(emp)));
			return;
		}

		int dd = employeeSerivce.save(emp);
		response.getWriter().print(dd);
	}

	public void updateData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee emp = Http2Object(request,2);

		if (empValidate(emp).size() != 0) {
			response.getWriter().print(new Gson().toJson(empValidate(emp)));
			return;
		}

		int dd = employeeSerivce.update(emp);
		response.getWriter().print(dd);
	}

	public void deleteData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int dd = employeeSerivce.delete(Integer.parseInt(request.getParameter("id")));
		response.getWriter().print(dd);
	}

	public void deleteBatchData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("ids"));
		int dd = employeeSerivce.deleteBatch(request.getParameter("ids"));
		response.getWriter().print(dd);
	}

	private Map<String, String> empValidate(Employee emp) {
		Map<String, String> errorFields = new HashMap<String, String>();
		String nameRegex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)";
		String mailRegex = "^([a-zA-Z0-9_.+-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$";

		if (!emp.getName().matches(nameRegex)) {
			errorFields.put("name", "可以是2-5中文或是6-16英文與數字的組合");
		}

		if (emp.getName() == null || "".equals(emp.getName())) {
			errorFields.put("name", "必須輸入員工名稱");
		}

		if (!emp.getEmail().matches(mailRegex)) {
			errorFields.put("email", "email格式不正確");
		}

		if (emp.getEmail() == null || "".equals(emp.getEmail())) {
			errorFields.put("email", "email必填");
		}

		return errorFields;
	}
	
	private Employee Http2Object(HttpServletRequest request,int type) {
		Employee emp = new Employee();
		if(type==2) {
			emp.setId(Integer.parseInt(request.getParameter("id")));
		}
		emp.setName(request.getParameter("name"));
		emp.setEmail(request.getParameter("email"));
		emp.setGender(Integer.parseInt(request.getParameter("gender")));
		emp.setDeptId(Integer.parseInt(request.getParameter("deptId")));
		
		return emp;
	}
}
