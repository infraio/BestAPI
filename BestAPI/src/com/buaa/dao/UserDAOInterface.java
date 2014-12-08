package com.buaa.dao;

import java.util.List;

import com.buaa.model.User;

public interface UserDAOInterface {
	
	public User getUserByEmail(String email);
	public boolean addRandomUsers(int n);
	public boolean addRandomUser();
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public boolean deleteUserByEmail(String email);
	public boolean isExistUser(User user);
	public List<User> getAllUsers();
}
