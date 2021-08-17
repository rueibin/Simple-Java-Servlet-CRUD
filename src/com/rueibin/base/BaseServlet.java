package com.rueibin.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String md = request.getParameter("method");
		String path = null;
		if (null != md) {
			Class clazz = this.getClass();
			try {
				Method method = clazz.getMethod(md, HttpServletRequest.class, HttpServletResponse.class);
				if (null != method) {
					path = (String) method.invoke(this, request, response);
				}

				if (null != path) {
					request.getRequestDispatcher(path).forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return null;
	}
}
