package com.buaa.dao;

public class UserDAOFactory {

	public static UserDAOInterface getUserDAOInstance() {
		return new UserDAOProxy();
	}
}
