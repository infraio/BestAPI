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


public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = "signUp.jsp";
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");
		List<String> info = new ArrayList<String>();
		
		if(email == null || "".equals(email)) {
			info.add("email is empty.");
		}
		if(username == null || "".equals(username)) {
			info.add("username is empty.");
		}
		if(password == null || "".equals(password)) {
			info.add("password is empty.");
		}
		if(confirm == null || "".equals(confirm)) {
			info.add("password confirm is empty.");
		}
		if(!password.equals(confirm)) {
			info.add("password can not be confirmed.");
		}
		
		if(info.isEmpty()) {
			User user = new User(email, username, password);
			try {
				if(UserDAOFactory.getIUserDAOInstance().signUp(user))
					info.add("sign up success!");
				else
					info.add("sign up fail!");
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
