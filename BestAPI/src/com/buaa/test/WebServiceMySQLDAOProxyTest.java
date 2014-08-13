package com.buaa.test;

import static org.junit.Assert.*;
import com.buaa.model.*;
import com.buaa.dao.*;

import org.junit.Test;

public class WebServiceMySQLDAOProxyTest {

	@Test
	public void testWebServiceMySQLDAO() {
		WebService ws = new WebService();
		WebServiceMySQLDAOProxy wsDAO = new WebServiceMySQLDAOProxy();
		assertTrue("should be true", wsDAO.submitWebService(ws));
		assertTrue("should be true", wsDAO.findWebServiceByName(ws));
	}
}
