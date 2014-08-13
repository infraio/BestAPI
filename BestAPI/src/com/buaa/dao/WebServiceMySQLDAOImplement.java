package com.buaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class WebServiceMySQLDAOImplement implements WebServiceDAOInterface {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	
	public WebServiceMySQLDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public boolean submitWebService(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO api VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			WebServiceAttribute[] attributes = WebServiceAttribute.values();
			
			for(int i = 0; i < attributes.length; ++i) 
				this.pstmt.setString(i+1, api.getAttributeContent(attributes[i]));
			if(this.pstmt.executeUpdate() == 1)
				flag = true;
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return flag;
	}
	
	public boolean findWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM api WHERE API_NAME=" + api.getAttributeContent(WebServiceAttribute.API_NAME);
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			
			if(rs.next()) {
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 1; i < attributes.length; ++i) 
					api.setAttributeContent(attributes[i], rs.getString(i+1));
				flag = true;
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
					this.pstmt.close();
			}
		}
		return flag;
	}
	
	public boolean findWebServicesByOwner(String owner, HashSet<WebService> apis) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM api WHERE API_OWNER=\"" + owner + "\"";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			
			while(rs.next()) {
				WebService api = new WebService();
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 0; i < attributes.length; ++i) 
					api.setAttributeContent(attributes[i], rs.getString(i+1));
				apis.add(api);
//				for(WebService a : apis) System.out.println(a.getAttributeContent(WebServiceAttribute.API_NAME));
				flag = true;
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return flag;
	}
	
}
