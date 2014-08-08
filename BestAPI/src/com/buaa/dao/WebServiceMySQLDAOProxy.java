package com.buaa.dao;

import java.util.HashSet;

import com.buaa.model.WebService;

public class WebServiceMySQLDAOProxy implements WebServiceDAOInterface {

	private DatabaseConnector dbc = null;
	private WebServiceDAOInterface dao = null;
	
	public WebServiceMySQLDAOProxy() {
		try { this.dbc = new DatabaseConnector();
		} catch(Exception e) { e.printStackTrace(); return; }
		this.dao = new WebServiceMySQLDAOImplement(this.dbc.getConnection());
	}
	
	public boolean findWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		try { flag = this.dao.findWebServiceByName(api);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean submitWebService(WebService api) throws Exception {
		boolean flag = false;
		try { flag = this.dao.submitWebService(api);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean findWebServicesByOwner(String owner, HashSet<WebService> apis) throws Exception {
		boolean flag = false;
		try { flag = this.dao.findWebServicesByOwner(owner, apis);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
}
