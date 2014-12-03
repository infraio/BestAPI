package com.buaa.dao;

import java.util.List;

import com.buaa.model.DataInstance;
import com.buaa.model.DataItem;
import com.buaa.model.EvaluationTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public class TreeDAOProxy implements TreeDAOInterface {

	private DatabaseConnector dbc = null;
	private TreeDAOInterface dao = null;
	
	public TreeDAOProxy() {
		try { 
			this.dbc = new DatabaseConnector();
		} catch(Exception e) { 
			e.printStackTrace(); return;
		}
		this.dao = new TreeDAOImplement(this.dbc.getConnection());
	}
	
//	public boolean readTree(EvaluationConceptTree tree) {
//		boolean flag = false;
//		try { flag = this.dao.readTree(tree);
//		} catch (Exception e) { e.printStackTrace(); 
//		} finally { this.dbc.close(); }
//		return flag;
//	}
	
	public boolean createTree(EvaluationTree tree) {
		boolean flag = false;
		try { flag = this.dao.createTree(tree);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean getWeight(WebService api, EvaluationTree tree) {
		boolean flag = false;
		try { flag = this.dao.getWeight(api, tree);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean getValue(DataInstance instance) {
		boolean flag = false;
		try { flag = this.dao.getValue(instance);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
	
}