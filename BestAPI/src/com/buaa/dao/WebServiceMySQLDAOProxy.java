package com.buaa.dao;

import java.util.List;
import java.util.TreeSet;

import com.buaa.model.WebService;

public class WebServiceMySQLDAOProxy implements WebServiceDAOInterface {

	private DatabaseConnector dbc = null;
	private WebServiceDAOInterface dao = null;
	
	public WebServiceMySQLDAOProxy() {
		try {
			this.dbc = new DatabaseConnector();
		} catch (Exception e) {
			e.printStackTrace(); return;
		}
		this.dao = new WebServiceMySQLDAOImplement(this.dbc.getConnection());
	}
	
	public WebService getWebServiceByName(String name) {
		WebService ws = null;
		try { 
			ws = this.dao.getWebServiceByName(name);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return ws;
	}
	
	public boolean addWebService(WebService ws) {
		boolean flag = false;
		try { 
			flag = this.dao.addWebService(ws);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public List<WebService> getWebServicesByCategory(String category) {
		List<WebService> wss = null;
		try { 
			wss = this.dao.getWebServicesByCategory(category);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return wss;
	}

	public boolean deleteWebServiceByName(String name) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteWebServiceByName(name);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean deleteWebService(WebService ws) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteWebService(ws);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean fuzzySearch(String key, TreeSet<WebService> apis) {
		boolean flag = false;
		try { 
			flag = this.dao.fuzzySearch(key, apis);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean saveWebServicesFromDbToFile(String filePath) {
		boolean flag = false;
		try { 
			flag = this.dao.saveWebServicesFromDbToFile(filePath);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close();
		}
		return flag;
	}
	
	public boolean saveWebServicesFromFileToDb(String filePath) {
		boolean flag = false;
		try { 
			flag = this.dao.saveWebServicesFromFileToDb(filePath);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close();
		}
		return flag;
	}
}
