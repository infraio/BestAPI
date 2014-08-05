package com.buaa.dao;

import com.buaa.model.User;

public interface UserDAOInterface {
	
	public boolean login(User user) throws Exception;
	public boolean signUp(User user) throws Exception;
}
