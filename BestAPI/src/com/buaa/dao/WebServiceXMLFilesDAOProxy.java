package com.buaa.dao;

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
	
	public boolean addWebService(WebService api) throws Exception {
		boolean flag = false;
		flag = this.dao.addWebService(api);
		return flag;
	}
}
