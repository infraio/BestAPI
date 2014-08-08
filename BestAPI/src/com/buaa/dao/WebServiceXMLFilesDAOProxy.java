package com.buaa.dao;

import java.util.HashSet;

import com.buaa.model.WebService;

public class WebServiceXMLFilesDAOProxy implements WebServiceDAOInterface {
	
	private XMLFilesLoader xfl = null;
	private WebServiceDAOInterface dao = null;

	public WebServiceXMLFilesDAOProxy() {
		this.xfl = new XMLFilesLoader();
		this.dao = new WebServiceXMLFilesDAOImplement(this.xfl.getDirectory());
	}
	
	public boolean findWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		flag = this.dao.findWebServiceByName(api);
		return flag;
	}
	
	public boolean submitWebService(WebService api) throws Exception {
		boolean flag = false;
		flag = this.dao.submitWebService(api);
		return flag;
	}
	
	public boolean findWebServicesByOwner(String owner, HashSet<WebService> apis) throws Exception {
		boolean flag = false;
		flag = this.dao.findWebServicesByOwner(null, apis);
		return flag;
	}
}
