package com.buaa.dao;

import com.buaa.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DataInstanceDAOImplement {
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
	public DataInstanceDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	public DataInstance getDataInstance(User user, WebService ws, EvaluationTree eTree) throws Exception {
		DataInstance di = new DataInstance();
		List<FactorNode> independentFactors = eTree.getIndependentFactors();
		for (int i = 0; i < independentFactors.size(); i++) {
			di.addIndependentItem(getIndependentItem(eTree.getDomain().getName(), ws.getName(), independentFactors.get(i)));
		}
		List<FactorNode> relatedFactors = eTree.getRelatedFactors();
		for (int i = 0; i < relatedFactors.size(); i++) {
			di.addRelatedItem(getRelatedItem(eTree.getDomain().getName(), user.getUsername(), ws.getName(), relatedFactors.get(i)));
		}
		return di;
	}
	
	private DataItem getIndependentItem(String domainName, String wsName, FactorNode factor) {
		DataItem item = new DataItem(factor);
		String tableName = domainName + "_independent_factors";
		double value = 0.0;
		try {
			String sql = "SELECT `" + factor.getName() + "` from " + tableName + " where apiname=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, wsName);
			//System.out.println(sql);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(this.pstmt != null) {
					this.pstmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		item.setValue(value);
		return item;
	}
	
	private DataItem getRelatedItem(String domainName, String username, String wsName, FactorNode factor) {
		DataItem item = new DataItem(factor);
		String tableName = domainName + "_related_factors";
		double value = 0.0;
		try {
			String sql = "SELECT `" + factor.getName() + "` from " + tableName + " where username=? and apiname=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setString(2, wsName);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(this.pstmt != null) {
					this.pstmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		item.setValue(value);
		return item;
	}
}
