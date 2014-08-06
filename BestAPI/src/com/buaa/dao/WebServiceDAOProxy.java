package com.buaa.dao;

import com.buaa.model.WebService;

public class WebServiceDAOProxy implements WebServiceDAOInterface {

	private DatabaseConnector dbc = null;
	private WebServiceDAOInterface dao = null;
	
	public WebServiceDAOProxy() {
		try { this.dbc = new DatabaseConnector();
		} catch(Exception e) { e.printStackTrace(); return; }
		this.dao = new WebServiceDAOImplement(this.dbc.getConnection());
	}
	
	public boolean findWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		try { flag = this.dao.findWebServiceByName(api);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean addWebService(WebService api) throws Exception {
		boolean flag = false;
		try { flag = this.dao.addWebService(api);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
}
