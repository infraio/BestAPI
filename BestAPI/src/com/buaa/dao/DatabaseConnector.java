package com.buaa.dao;
import java.sql.*;


public class DatabaseConnector {
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";				// 驱动程序所在的类
	private static final String DBURL = "jdbc:mysql://localhost:3306/bestapi";	// 数据库所在的路径
	private static final String DBUSER = "root";								// 访问数据库的用户
	private static final String DBPASSWORD = "adathespy";						// 用户对应的密码
	private Connection connect = null;
	
	public DatabaseConnector() throws Exception {
		try {
			Class.forName(DBDRIVER);
			this.connect = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch(Exception e) { e.printStackTrace(); return; }
	}
	
	public Connection getConnection() {
		return this.connect;
	}
	
	public void close() throws Exception {
		if(this.connect != null)
			try { this.connect.close();
			} catch(Exception e) { e.printStackTrace(); return;	}
	}
	
}
