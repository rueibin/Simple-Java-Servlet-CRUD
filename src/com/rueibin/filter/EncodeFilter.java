package com.rueibin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class EncodeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getContextPath();
		String url = req.getRequestURI();

		System.out.println(url);
		
		if (url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".jpg")) {
			
			//System.out.println("jpg");
			
			chain.doFilter(request, response);			
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
