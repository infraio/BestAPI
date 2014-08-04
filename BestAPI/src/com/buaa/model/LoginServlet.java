package com.buaa.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String info = new String();
		
		if(username == null || "".equals(username)) {
			info += "username is empty.\r\n";
		}
		if(password == null || "".equals(password)) {
			info += "password is empty.\r\n";
		}
		if(info.isEmpty()) {
			User user = new User(username, password);
		}
		req.setAttribute("info", info);
//		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
