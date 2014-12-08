package com.buaa.dao;

import com.buaa.model.DataInstance;
import com.buaa.model.EvaluationTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public class DataInstanceDAOProxy implements DataInstanceDAOInterface {

	private DatabaseConnector dbc = null;
	private DataInstanceDAOImplement dao = null;
	
	public DataInstanceDAOProxy() {
		try { 
			this.dbc = new DatabaseConnector();
		} catch(Exception e) { 
			e.printStackTrace(); 
			return;
		}
		this.dao = new DataInstanceDAOImplement(this.dbc.getConnection());
	}
	
	public DataInstance getDataInstance(User user, WebService ws, EvaluationTree eTree) {
		DataInstance di = null;
		try {
			di = this.dao.getDataInstance(user, ws, eTree);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return di;
	}
	
}
