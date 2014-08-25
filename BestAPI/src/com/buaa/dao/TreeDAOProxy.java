package com.buaa.dao;

import com.buaa.model.EvaluationConceptTree;
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
	
	public boolean buildTree(EvaluationConceptTree tree) {
		boolean flag = false;
		try { flag = this.dao.buildTree(tree);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean getTreeName(User user, WebService api, EvaluationConceptTree tree) {
		boolean flag = false;
		try { flag = this.dao.getTreeName(user, api, tree);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean addTree(EvaluationConceptTree tree) {
		boolean flag = false;
		try { flag = this.dao.addTree(tree);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { this.dbc.close(); }
		return flag;
	}
}
