package com.buaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.buaa.model.WebService;

public class WebServiceDAOImplement implements WebServiceDAOInterface {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	
	public WebServiceDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public boolean addWebService(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO api VALUE(?,?,?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, api.getAPIName());
			this.pstmt.setString(2, api.getAPIProvider());
			this.pstmt.setString(3, api.getAPIEndpoint());
			this.pstmt.setString(4, api.getAPIHomepage());
			this.pstmt.setString(5, api.getPrimaryCategory());
			this.pstmt.setString(6, api.getSecondaryCategories());
			this.pstmt.setString(7, api.getProtocolFormats());
			this.pstmt.setString(8, api.getAPIhubURL());
			this.pstmt.setString(9, api.getSSLSupport());
			this.pstmt.setString(10, api.getTwiterURL());
			this.pstmt.setString(11, api.getAuthenticationMode());
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
			String sql = "SELECT * FROM api WHERE APIName=" + api.getAPIName();
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			if(rs.next()) {
				api.setAPIProvider(rs.getString(1));
				api.setAPIEndpoint(rs.getString(2));
				api.setAPIHomepage(rs.getString(3));
				api.setPrimaryCategory(rs.getString(4));
				api.setSecondaryCategories(rs.getString(5));
				api.setProtocolFormats(rs.getString(6));
				api.setAPIhubURL(rs.getString(7));
				api.setSSLSupport(rs.getString(8));
				api.setTwiterURL(rs.getString(9));
				api.setAuthenticationMode(rs.getString(10));
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
