package com.buaa.dao;

import com.buaa.model.DataSource;

public class WebServiceDAOFactory {
	
	public static WebServiceDAOInterface getWebServiceDAOInstance(DataSource src) {
		
		switch(src) {
			case MYSQL : return new WebServiceMySQLDAOProxy();
			case XMLFILES : return new WebServiceXMLFilesDAOProxy();
			default : return null;
		}
	}
}
