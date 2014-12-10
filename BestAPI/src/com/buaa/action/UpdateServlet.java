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

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		WebService api = new WebService();
		api.setName(req.getParameter("name"));
		api.setOwner(req.getParameter("owner"));
		api.setEndpoint(req.getParameter("endpoint"));
		api.setHomepage(req.getParameter("homepage"));
		api.setContactEmail(req.getParameter("email"));
		api.setCategory(req.getParameter("category"));
		api.setProtocolFormats(req.getParameter("protocol"));
		api.setHubUrl(req.getParameter("huburl"));
		api.setAuthenticationMode(req.getParameter("authentication"));
		try {
			if(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).updateWebService(api)) {
				req.getRequestDispatcher("/user.jsp").forward(req, resp);
//					System.out.println("submit success");
				// TODO
			} else {
//					System.out.println("submit fail");
			}
		} catch (Exception e) { e.printStackTrace();}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
