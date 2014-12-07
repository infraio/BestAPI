package com.buaa.dao;

import com.buaa.model.User;

public interface UserDAOInterface {
	
	public User getUserByEmail(String email) throws Exception;
	public boolean addRandomUsers(int n) throws Exception;
	public boolean addRandomUser() throws Exception;
	public boolean addUser(User user) throws Exception;
	public boolean deleteUser(User user) throws Exception;
	public boolean deleteUserByEmail(String email) throws Exception;
	public boolean isExistUser(User user) throws Exception;
}
