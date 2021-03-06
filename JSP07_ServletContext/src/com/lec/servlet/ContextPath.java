package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConPath")
public class ContextPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContextPath() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Context Path
		String conPath = request.getContextPath();
		
		// URL : Uniform Resource Locator
		StringBuffer url = request.getRequestURL();
		// URI : Uniform Resource Identifier 
		String uri = request.getRequestURI();
		
		// domain 추출하기
		String url_domain = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()
					;		
		
		// 물리적인 servletContextName
		ServletContext context = request.getServletContext();
		String servletContextName = context.getServletContextName();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<hr>");
		
		out.println("ContextPath: " + conPath + "<br>");
		out.println("URL: " + url + "<br>");
		out.println("URI: " + uri + "<br>");
		out.println("URL DOMAIN: " + url_domain + "<br>");
		out.println("Servlet ContextName: " + servletContextName + "<br>");
		
		out.println("<hr>");		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
















