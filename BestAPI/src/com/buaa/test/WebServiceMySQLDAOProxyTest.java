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
		WebService api = new WebService();
		api.setAttributeContent(WebServiceAttribute.API_NAME, "SAMPLE3");
		assertTrue("should be true", WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).submitWebService(api));
	}
	
	@Test
	public void testFuzzySearch() throws Exception {
		String keyword = "map";
		TreeSet<WebService> apis = new TreeSet<WebService>(new SimilarityComparator());
		assertTrue(WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).fuzzySearch(keyword, apis));
		assertTrue(!apis.isEmpty());
	}
	
	public static void main() {
		try {
			WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).saveWebServices();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
