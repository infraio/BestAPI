package com.buaa.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.buaa.model.User;

public class UserDAOImplement {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
	public UserDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public boolean isExistUser(User user) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM user WHERE email=? and password=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getEmail());
			this.pstmt.setString(2, user.getPassword());
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
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
	
	public User getUserByEmail(String email) throws Exception {
		User user = null;
		try {
			String sql = "SELECT * FROM user WHERE email=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, email);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return user;
	}
	
	public boolean addUser(User user) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO user VALUE(?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getEmail());
			this.pstmt.setString(2, user.getUsername());
			this.pstmt.setString(3, user.getPassword());
			if(this.pstmt.executeUpdate() == 1)
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
	
	public boolean deleteUserByEmail(String email) throws Exception {
		boolean flag = false;
		try {
			String sql = "DELETE FROM user WHERE email=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, email);
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
	
	public boolean deleteUser(User user) throws Exception {
		return deleteUserByEmail(user.getEmail());
	}
	
	public boolean addRandomUser() throws Exception {
		String name = "";
		char a = 'a';
		for(int i = 0; i < 8; ++i)
			name += String.valueOf((char)(a+(int)(Math.random()*26)));
		String email = name+"@gmail.com";
		String password = name;
		User user = new User(email, name, password);
		return addUser(user);
	}
	
	public boolean addRandomUsers(int n) throws Exception {
		boolean flag = false;
		for (int i = 0; i < n; i++)
			flag = addRandomUser();
		return flag;
	}
	
	public List<User> getAllUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user";
			this.pstmt = this.connect.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return users;
	}
	
}
