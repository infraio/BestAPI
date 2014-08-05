package com.buaa.dao;

import com.buaa.model.User;

public interface UserDAOInterface {
	
	public boolean findUser(User user) throws Exception;
	public boolean addUser(User user) throws Exception;
}
