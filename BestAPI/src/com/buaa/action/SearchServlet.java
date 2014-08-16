package com.buaa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.dao.WebServiceDAOFactory;
import com.buaa.model.DataSource;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

class SimilarityComparator implements Comparator<WebService> {
	@Override
	public int compare(WebService o1, WebService o2) {
		return o1.getSimilarity()-o2.getSimilarity();
	}
}

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		List<String> info = new ArrayList<String>();
		
		if(keyword != null && !"".equals(keyword)) {
// search by name
			/*keyword = keyword.toUpperCase().replaceAll(" ", "_");
			WebService api = new WebService();
			api.setAttributeContent(WebServiceAttribute.API_NAME, keyword);
			try {
				if(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.XMLFILES).findWebServiceByName(api)) {
					WebServiceAttribute[] attributes = WebServiceAttribute.values();
					for(int i = 0; i < attributes.length; ++i)
						info.add(attributes[i].getName() + ":&nbsp;&nbsp;&nbsp;&nbsp;" + api.getAttributeContent(attributes[i]));
				} else {
					info.add("no such web service found");
				}
			} catch(Exception e) { e.printStackTrace(); }*/
// fuzzy search
			TreeSet<WebService> apis = new TreeSet<WebService>(new SimilarityComparator());
			try {
				if(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).fuzzySearch(keyword, apis)) {
					WebServiceAttribute[] attributes = WebServiceAttribute.values();
					for(WebService api : apis) {
						for(int i = 0; i < attributes.length; ++i)
							info.add(attributes[i].getName() + ":&nbsp;&nbsp;&nbsp;&nbsp;" + api.getAttributeContent(attributes[i]));
						info.add("<br><br>");
					}
				} else {
					info.add("no such web service found");
				}
			} catch(Exception e) { e.printStackTrace(); }
			req.setAttribute("info", info);
			req.getRequestDispatcher("result.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
