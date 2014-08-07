package com.buaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class WebServiceMySQLDAOImplement implements WebServiceDAOInterface {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	
	public WebServiceMySQLDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public boolean addWebService(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO api VALUE(?,?,?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			WebServiceAttribute[] attributes = WebServiceAttribute.values();
			
			for(int i = 0; i < attributes.length; ++i) 
				this.pstmt.setString(i+1, api.getAttributeContent(attributes[i]));
			if(this.pstmt.executeUpdate() == 1)
				flag = true;
		} catch(Exception e) { e.printStackTrace();
		} finally {
			if(this.pstmt != null) {
				try { this.pstmt.close();
				} catch(Exception e) { e.printStackTrace(); }
			}
		}
		return flag;
	}
	
	public boolean findWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM api WHERE APIName=" + api.getAttributeContent(WebServiceAttribute.API_NAME);
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			
			if(rs.next()) {
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 1; i < attributes.length; ++i) 
					api.setAttributeContent(attributes[i], rs.getString(i));
				flag = true;
			}
		} catch(Exception e) { e.printStackTrace();
		} finally {
			if(this.pstmt != null) {
				try { this.pstmt.close();
				} catch(Exception e) { e.printStackTrace(); }
			}
		}
		return flag;
	}
	
}
