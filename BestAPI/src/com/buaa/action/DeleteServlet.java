package com.buaa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.dao.RelationshipDAO;
import com.buaa.dao.WebServiceDAOFactory;
import com.buaa.model.DataSource;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String apiname = req.getParameter("apiname");
		try {
			WebService ws = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).getWebServiceByName(apiname);
			boolean flag = RelationshipDAO.getInstance().deleteRelationship(user, ws);
			flag = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).deleteWebService(ws);
			if (flag == true) {
				req.getRequestDispatcher("/user.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
