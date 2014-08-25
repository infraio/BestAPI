package com.buaa.dao;

public class TreeDAOFactory {

	public static TreeDAOInterface getTreeDAOInstance() {
		return new TreeDAOProxy();
	}
}
