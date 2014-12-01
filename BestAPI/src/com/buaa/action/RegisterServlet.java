package com.buaa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.dao.UserDAOFactory;
import com.buaa.model.User;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");
		String info = "";
		
		if(email == null || "".equals(email)) {
			info = "邮箱为空！";
		} else if(username == null || "".equals(username)) {
			info = "用户名为空！";
		} else if(password == null || "".equals(password)) {
			info = "密码为空！";
		} else if(confirm == null || "".equals(confirm)) {
			info = "确认密码为空！";
		} else if(!password.equals(confirm)) {
			info = "两次输入的密码不一致！";
		} else {
			User user = new User(email, username, password);
			try {
				if(UserDAOFactory.getUserDAOInstance().addUser(user)) {
					req.getSession().setAttribute("user", user);
					req.getRequestDispatcher("/index.jsp").forward(req, resp);
				} else {
					info = "注册失败！请重试！";
				}
			} catch(Exception e) { e.printStackTrace(); }
		}
		req.setAttribute("info", info);
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
