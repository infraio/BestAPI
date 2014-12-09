package com.buaa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buaa.algorithm.WebServiceEvaluation;
import com.buaa.dao.UserDAOFactory;
import com.buaa.dao.WebServiceDAOFactory;
import com.buaa.model.DataItem;
import com.buaa.model.DataSource;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;
import com.buaa.model.WebServiceInstance;

class SimilarityComparator implements Comparator<WebService> {
	@Override
	public int compare(WebService o1, WebService o2) {
		return o2.getSimilarity() - o1.getSimilarity();
	}
}

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			user = new User("nouser@gmail.com", "nouser", "nouser");
		}
		ArrayList<String> thead = new ArrayList<String>();
		ArrayList<ArrayList<String>> tbody = new ArrayList<ArrayList<String>>();
		if(keyword != null && !"".equals(keyword)) {
			int[] needs = {0, 1, 5, 6};
			List<WebService> apis;
			List<WebServiceInstance> wsis;
			try {
				apis = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).fuzzySearch(keyword);
				Collections.sort(apis, new SimilarityComparator());
				wsis = WebServiceEvaluation.getInstance().getTop10Rank(user, apis);
				if(wsis != null) {
					WebServiceAttribute[] attributes = WebServiceAttribute.values();
					for (int i = 0; i < 4; i++)
						thead.add(attributes[needs[i]].getName());
					thead.add("Evaluation Result");
					for (WebServiceInstance wsi : wsis) {
						ArrayList<String> temp = new ArrayList<String>();
						for (int i = 0; i < 4; i++)
							temp.add(wsi.getWs().getAttributeContent(attributes[needs[i]]));
						temp.add(Double.toString(wsi.getEvaluationResult()));
						tbody.add(temp);
					}
				}
			} catch(Exception e) { 
				e.printStackTrace();
			}
			req.getSession().setAttribute("thead", thead);
			req.getSession().setAttribute("tbody", tbody);
			req.getRequestDispatcher("/result.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
