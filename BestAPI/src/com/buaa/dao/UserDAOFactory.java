package com.buaa.dao;

public class UserDAOFactory {

	public static UserDAOInterface getIUserDAOInstance() {
		return new UserDAOProxy();
	}
}
