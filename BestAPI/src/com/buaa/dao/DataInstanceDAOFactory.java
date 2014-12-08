package com.buaa.dao;

public class DataInstanceDAOFactory {
	public static DataInstanceDAOInterface getDataInstanceDAOInstance() {
		return new DataInstanceDAOProxy();
	}
}
