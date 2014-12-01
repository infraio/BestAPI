package com.buaa.action;

import com.buaa.dao.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.model.DataSource;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = "index.jsp";
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		//String isUser = (String) req.getSession().getAttribute("user");
		String info = "";
		
		//if(isUser != null && !"".equals(isUser)) {
			
		//} else {
		
		if(email == null || "".equals(email)) {
			info = "用户名不能为空！";
		} else if(password == null || "".equals(password)) {
			info = "密码不能为空！";
		} else {
			User user = new User(email, password);
			try {
				if(UserDAOFactory.getUserDAOInstance().findUser(user)) {
					req.getSession().setAttribute("user", user);
					req.getRequestDispatcher("/index.jsp").forward(req, resp);
				} else {
					info = "用户名或密码错误！";
				}
			} catch(Exception e) { 
				e.printStackTrace();
			}
		}
		req.setAttribute("info", info);
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
