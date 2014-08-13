package com.buaa.dao;

import java.sql.*;

import com.buaa.model.User;

public class UserDAOImplement implements UserDAOInterface {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
	public UserDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public boolean findUser(User user) {
		boolean flag = false;
		try {
			String sql = "SELECT username FROM user WHERE email=? AND password=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getEmail());
			this.pstmt.setString(2, user.getPassword());
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				user.setUsername(rs.getString(1));
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
	
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO user VALUE(?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getEmail());
			this.pstmt.setString(2, user.getUsername());
			this.pstmt.setString(3, user.getPassword());
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
	
	public boolean removeUser(User user) {
		boolean flag = false;
		return flag;
	}
}
