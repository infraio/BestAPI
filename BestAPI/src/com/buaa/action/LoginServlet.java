package com.buaa.action;

import com.buaa.dao.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = "login.jsp";
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<String> info = new ArrayList<String>();
		
		if(email == null || "".equals(email)) {
			info.add("email is empty.");
		}
		if(password == null || "".equals(password)) {
			info.add("password is empty.");
		}
		
		if(info.isEmpty()) {
			User user = new User(email, password);
			try {
				if(UserDAOFactory.getIUserDAOInstance().findUser(user))
					info.add("login success! welcom, " + user.getUsername());
				else
					info.add("login fail! wrong email or password!");
			} catch(Exception e) { e.printStackTrace(); }
		}
		req.setAttribute("info", info);
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
