package com.buaa.test;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.TreeSet;

import com.buaa.model.*;
import com.buaa.dao.*;

import org.junit.Test;

class SimilarityComparator implements Comparator<WebService> {
	@Override
	public int compare(WebService o1, WebService o2) {
		return o1.getSimilarity()-o2.getSimilarity();
	}
}

public class WebServiceMySQLDAOProxyTest {

	@Test
	public void testWebServiceMySQLDAO() throws Exception {
		String[] attributes = {"ICBC Payment Service", "ICBC", "http://www.icbc.com", "http://www.icbc.com", 
				"payment@icbc.com", "Payments", "WSDL", "http://www.icbc.com", "API key"};
		WebService api = new WebService(attributes);
		assertTrue("should be true", WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).addWebService(api));
	}
	
	@Test
	public void testDelete() throws Exception {
		String[] attributes = {"ICBC Payment Service", "ICBC", "http://www.icbc.com", "http://www.icbc.com", 
				"payment@icbc.com", "Payments", "WSDL", "http://www.icbc.com", "API key"};
		WebService api = new WebService(attributes);
		assertTrue("should be true", WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).deleteWebService(api));
	}
	
	@Test
	public void testFuzzySearch() throws Exception {
		String keyword = "Payment";
		TreeSet<WebService> apis = new TreeSet<WebService>(new SimilarityComparator());
		assertTrue(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).fuzzySearch(keyword, apis));
		assertTrue(!apis.isEmpty());
	}
}
