package com.buaa.dao;

import com.buaa.model.*;
import java.util.List;

public class UserDao {
	public boolean checkUserName (String userName) {
		return false;
	}
	
	public void save (User user) {
		
	}
	
	public boolean exists (User user) {
		return true;
	}
	
	public User getById (int id) {
		return new User("test", "test");
	}
	
	public User getByName (String userName) {
		return new User("test", "test");
	}
	
	public void delete (User user) {
		
	}
	
	public void update (User user) {
		
	}
}
