package com.buaa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.dao.WebServiceDAOFactory;
import com.buaa.model.DataSource;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String user = req.getSession().getAttribute("user").toString();
		List<String> info = new ArrayList<String>();
		
		if(info.isEmpty()) {
			WebService api = new WebService();
			WebServiceAttribute[] attributes = WebServiceAttribute.values();
			
			for(int i = 0; i < attributes.length; ++i)
				api.setAttributeContent(attributes[i], req.getParameter(attributes[i].getName()));
			api.setAttributeContent(WebServiceAttribute.API_OWNER, user);
			try {
				if(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).submitWebService(api)) {
//					System.out.println("submit success");
					// TODO
				} else {
//					System.out.println("submit fail");
				}
			} catch (Exception e) { e.printStackTrace();}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
