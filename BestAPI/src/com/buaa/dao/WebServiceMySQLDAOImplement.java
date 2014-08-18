package com.buaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.TreeSet;

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

	public boolean removeWebServiceByName(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "DELETE FROM api WHERE API_NAME=" + api.getAttributeContent(WebServiceAttribute.API_NAME);
			this.stmt = connect.createStatement();
			if(this.stmt.executeUpdate(sql) >= 0)
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
	
	public boolean fuzzySearch(String key, TreeSet<WebService> apis) throws Exception {
		int[] weight = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		boolean flag = false;
		try {
			String sql = "SELECT * FROM api WHERE API_NAME LIKE '%" + key + 
						 "%' or API_OWNER LIKE '%" + key + "%' or API_PROVIDER LIKE '%" + key + 
						 "%' or API_ENDPOINT LIKE '%" + key + "%' or API_HOMEPAGE LIKE '%" + key + 
						 "%' or CONTACT_EMAIL LIKE '%" + key + "%' or PRIMARY_CATEGORY LIKE '%" + key + 
						 "%' or SECONDARY_CATEGORIES LIKE '%" + key + "%' or PROTOCOL_FORMATS LIKE '%" + key + 
						 "%' or API_HUB_URL LIKE '%" + key + "%' or SSL_SUPPORT LIKE '%" + key +
						 "%' or TWITER_URL LIKE '%" + key + "%' or AUTHENTICATION_MODE LIKE '%" + key + "%'";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			
			while(rs.next()) {
				WebService api = new WebService();
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				int similarity = 0;
				for(int i = 0; i < attributes.length; ++i)  {
					String attribute = rs.getString(i+1);
					if(attribute != null) {
						if(attribute.contains(key))
							similarity += weight[i];
						api.setAttributeContent(attributes[i], attribute);
					}
				}
				api.setSimilarity(similarity);
				apis.add(api);
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
