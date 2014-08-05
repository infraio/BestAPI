package com.buaa.dao;

import com.buaa.model.User;

public class UserDAOProxy implements UserDAOInterface {
	
	private DatabaseConnector dbc = null;
	private UserDAOInterface dao = null;
	
	public UserDAOProxy() {
		try { this.dbc = new DatabaseConnector();
		} catch(Exception e) { e.printStackTrace(); return; }
		this.dao = new UserDAOImplement(this.dbc.getConnection());
	}
	
	public boolean login(User user) throws Exception {
		boolean flag = false;
		try { flag = this.dao.login(user);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
	
	public boolean signUp(User user) throws Exception {
		boolean flag = false;
		try { flag = this.dao.signUp(user);
		} catch(Exception e) { e.printStackTrace();
		} finally { this.dbc.close(); }
		return flag;
	}
}
