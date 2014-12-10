package com.buaa.dao;

import java.util.List;
import java.util.TreeSet;

import com.buaa.model.WebService;

public class WebServiceXMLFilesDAOProxy implements WebServiceDAOInterface {
	
	private XMLFilesLoader xfl = null;
	private WebServiceDAOInterface dao = null;

	public WebServiceXMLFilesDAOProxy() {
		this.xfl = new XMLFilesLoader();
		this.dao = new WebServiceXMLFilesDAOImplement(this.xfl.getDirectory());
	}
	
	public WebService getWebServiceByName(String name) {
		WebService ws = null;
		try { 
			ws = this.dao.getWebServiceByName(name);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return ws;
	}
	
	public boolean addWebService(WebService ws) {
		boolean flag = false;
		try { 
			flag = this.dao.addWebService(ws);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateWebService(WebService ws) {
		boolean flag = false;
		try { 
			flag = this.dao.updateWebService(ws);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<WebService> getWebServicesByCategory(String category) {
		List<WebService> wss = null;
		try { 
			wss = this.dao.getWebServicesByCategory(category);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return wss;
	}

	public boolean deleteWebServiceByName(String name) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteWebServiceByName(name);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteWebService(WebService ws) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteWebService(ws);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<WebService> fuzzySearch(String key) {
		return null;
	}
	
	public boolean saveWebServicesFromDbToFile(String filePath) {
		boolean flag = false;
		return flag;
	}
	
	public boolean saveWebServicesFromFileToDb(String filePath) {
		boolean flag = false;
		return flag;
	}
	
	public List<WebService> getAllWebServices() {
		return null;
	}
}
