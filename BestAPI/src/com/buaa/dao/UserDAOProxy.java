package com.buaa.dao;

import java.util.List;

import com.buaa.model.User;

public class UserDAOProxy implements UserDAOInterface {
	
	private DatabaseConnector dbc = null;
	private UserDAOImplement dao = null;
	
	public UserDAOProxy() {
		try { 
			this.dbc = new DatabaseConnector();
		} catch(Exception e) { 
			e.printStackTrace(); 
			return;
		}
		this.dao = new UserDAOImplement(this.dbc.getConnection());
	}
	
	public boolean isExistUser(User user) {
		boolean flag = false;
		try { 
			flag = this.dao.isExistUser(user);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		try { 
			user = this.dao.getUserByEmail(email);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return user;
	}
	
	public boolean addUser(User user) {
		boolean flag = false;
		try { 
			flag = this.dao.addUser(user);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean deleteUser(User user) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteUser(user);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean deleteUserByEmail(String email) {
		boolean flag = false;
		try { 
			flag = this.dao.deleteUserByEmail(email);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean addRandomUser() {
		boolean flag = false;
		try { 
			flag = this.dao.addRandomUser();
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public boolean addRandomUsers(int n) {
		boolean flag = false;
		try { 
			flag = this.dao.addRandomUsers(n);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return flag;
	}
	
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = this.dao.getAllUsers();
		} catch(Exception e) { 
			e.printStackTrace();
		} finally { 
			this.dbc.close(); 
		}
		return users;
 	}
}
