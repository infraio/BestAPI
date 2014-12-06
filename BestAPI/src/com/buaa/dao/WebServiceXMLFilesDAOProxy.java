package com.buaa.dao;

import java.util.HashSet;
import java.util.TreeSet;

import com.buaa.model.WebService;

public class WebServiceXMLFilesDAOProxy implements WebServiceDAOInterface {
	
	private XMLFilesLoader xfl = null;
	private WebServiceDAOInterface dao = null;

	public WebServiceXMLFilesDAOProxy() {
		this.xfl = new XMLFilesLoader();
		this.dao = new WebServiceXMLFilesDAOImplement(this.xfl.getDirectory());
	}
	
	public boolean findWebServiceByName(WebService api) {
		boolean flag = false;
		try {
			flag = this.dao.findWebServiceByName(api);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean submitWebService(WebService api) {
		boolean flag = false;
		try {
			flag = this.dao.submitWebService(api);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean findWebServicesByOwner(String owner, HashSet<WebService> apis) {
		boolean flag = false;
		try {
			flag = this.dao.findWebServicesByOwner(owner, apis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean removeWebServiceByName(WebService api) {
		boolean flag = false;
		try {
			flag = this.dao.removeWebServiceByName(api);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean fuzzySearch(String key, TreeSet<WebService> apis) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.fuzzySearch(key, apis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public boolean saveWebServices() {
		return true;
	}
}
