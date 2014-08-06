package com.buaa.dao;

public class WebServiceDAOFactory {
	
	public static WebServiceDAOInterface getWebServiceDAOInstance() {
		return new WebServiceDAOProxy();
	}
}
