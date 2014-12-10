package com.buaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.buaa.model.User;
import com.buaa.model.DataSource;
import com.buaa.model.Relationship;
import com.buaa.model.WebService;

public class RelationshipDAO {
	
	private static RelationshipDAO instance = null;
	private DatabaseConnector dbc = null;
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
	private RelationshipDAO() {
		try {
			this.dbc = new DatabaseConnector();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connect = this.dbc.getConnection();
	}
	
	public static RelationshipDAO getInstance() {
		if (instance == null)
			instance = new RelationshipDAO();
		return instance;
	}
	
	private boolean insertRelationship(String username, String apiname, int relation) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO user_api VALUE(?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setString(2, apiname);
			this.pstmt.setInt(3, relation);
			if(this.pstmt.executeUpdate() == 1)
				flag = true;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				if(this.pstmt != null) {
					this.pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean insertProvideRelationship(User user, WebService ws) {
		return this.insertRelationship(user.getUsername(), ws.getName(), Relationship.provide.ordinal());
	}
	
	public boolean insertConsumeRelationship(User user, WebService ws) {
		return this.insertRelationship(user.getUsername(), ws.getName(), Relationship.consume.ordinal());
	}
	
	
	private List<WebService> getWebServices(String username, int relation) {
		List<WebService> apis = new ArrayList<WebService>();
		try {
			String sql = "SELECT apiname FROM user_api WHERE username=? and relation=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setInt(2, relation);
			System.out.println(sql);
			System.out.println(username);
			System.out.println(relation);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()) {
				String apiname = rs.getString(1);
				WebService api = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).getWebServiceByName(apiname);
				apis.add(api);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(this.pstmt != null) {
					this.pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return apis;
	}
	
	public List<WebService> getProvidedWebServicesByUser(User user) {
		if (user == null)
			return new ArrayList<WebService>();
		return getWebServices(user.getUsername(), Relationship.provide.ordinal());
	}
	
	public List<WebService> getConsumedWebServicesByUser(User user) {
		if (user == null)
			return new ArrayList<WebService>();
		return getWebServices(user.getUsername(), Relationship.consume.ordinal());
	}
	
	public boolean deleteRelationship(User user, WebService ws) throws Exception{
		boolean flag = false;
		try {
			String sql = "DELETE FROM user_api WHERE username=? and apiname=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getUsername());
			this.pstmt.setString(2, ws.getName());
			if(this.pstmt.executeUpdate() >= 0)
				flag = true;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return flag;
	}
}
