package com.buaa.dao;

import com.buaa.model.WebService;

public interface WebServiceDAOInterface {

	public boolean addWebService(WebService api) throws Exception;
	public boolean findWebServiceByName(WebService api) throws Exception;
}
