package com.buaa.dao;

import java.util.HashSet;
import java.util.TreeSet;

import com.buaa.model.WebService;

public interface WebServiceDAOInterface {

	public boolean submitWebService(WebService api) throws Exception;
	public boolean findWebServiceByName(WebService api) throws Exception;
	public boolean findWebServicesByOwner(String owner, HashSet<WebService> apis) throws Exception;
	public boolean removeWebServiceByName(WebService api) throws Exception;
	public boolean fuzzySearch(String key, TreeSet<WebService> apis) throws Exception;
	
}
