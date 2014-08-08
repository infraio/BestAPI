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
		String path = "login.jsp";
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String isUser = (String) req.getSession().getAttribute("user");
		List<String> info = new ArrayList<String>();
		
		if(isUser != null && !"".equals(isUser)) {
			
		} else {
			if(email == null || "".equals(email)) {
				info.add("email is empty.");
			}
			if(password == null || "".equals(password)) {
				info.add("password is empty.");
			}
			
			if(info.isEmpty()) {
				User user = new User(email, password);
				try {
					if(UserDAOFactory.getUserDAOInstance().findUser(user)) {
						req.getSession().setAttribute("username", user.getUsername());
						req.getSession().setAttribute("user", user.getEmail());			// set is_user in session
						HashSet<WebService> apis = new HashSet<WebService>();
						if(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).findWebServicesByOwner(user.getEmail(), apis)) {
							req.getSession().setAttribute("apis", apis);
//							for(WebService api : apis) System.out.println(api.getAttributeContent(WebServiceAttribute.API_NAME));
						}
					} else {
						info.add("login fail! wrong email or password!");
						req.getSession().setAttribute("user", "");
					}
				} catch(Exception e) { e.printStackTrace(); }
			}
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
