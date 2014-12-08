package com.buaa.dao;

import java.util.List;
import java.util.TreeSet;

import com.buaa.model.WebService;

public interface WebServiceDAOInterface {

	public boolean addWebService(WebService api) throws Exception;
	public WebService getWebServiceByName(String name) throws Exception;
	public List<WebService> getWebServicesByCategory(String category) throws Exception;
	public boolean deleteWebService(WebService ws) throws Exception;
	public boolean deleteWebServiceByName(String name) throws Exception;
	public List<WebService> fuzzySearch(String key) throws Exception;
	public boolean saveWebServicesFromDbToFile(String filePath) throws Exception;
	public boolean saveWebServicesFromFileToDb(String filePath) throws Exception;
	public List<WebService> getAllWebServices();
}
