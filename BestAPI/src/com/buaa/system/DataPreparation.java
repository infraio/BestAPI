package com.buaa.system;
import com.buaa.model.*;
import com.buaa.dao.DatabaseConnector;
import com.buaa.dao.EvaluationTreeDAO;
import com.buaa.dao.UserDAOFactory;
import com.buaa.dao.WebServiceDAOFactory;
import com.buaa.model.DataSource;
import com.buaa.model.Domain;
import com.buaa.model.EvaluationTree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DataPreparation {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
	public DataPreparation() {
		try {
			this.connect = new DatabaseConnector().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataPreparation dp = new DataPreparation();
		//dp.genUsers();
		dp.prepareFactorData();
	}
	
	private void genUsers() {
		int n = 100;
		UserDAOFactory.getUserDAOInstance().addRandomUsers(n);
	}
	
	private void prepareFactorData() {
		EvaluationTreeDAO etDao = new EvaluationTreeDAO(); 
		for (Domain domain : DomainFactory.getInstance().getAllDomains()) {
			EvaluationTree eTree = EvaluationTreeFactory.getInstance().getEvaluationTreeByDomain(domain);
			etDao.createDbForFactors(eTree);
		}
		List<User> users = UserDAOFactory.getUserDAOInstance().getAllUsers();
		List<WebService> wss = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).getAllWebServices();
		
		for (WebService ws : wss) {
			Domain domain = DomainFactory.getInstance().getDomain(ws.getCategory());
			EvaluationTree eTree = EvaluationTreeFactory.getInstance().getEvaluationTreeByDomain(domain);
			int n = eTree.getIndependentFactors().size();
			List<Double> data = new ArrayList<Double>();
			for (int k = 0; k < n; k++) {
				data.add(Math.random());
			}
			insertToIndependentTable(ws, data);
		}
		
		for (User user : users) {
			for (WebService ws : wss) {
				if (Math.random() > 0.2)
					continue;
				Domain domain = DomainFactory.getInstance().getDomain(ws.getCategory());
				EvaluationTree eTree = EvaluationTreeFactory.getInstance().getEvaluationTreeByDomain(domain);
				int n = eTree.getRelatedFactors().size();
				List<Double> data = new ArrayList<Double>();
				for (int k = 0; k < n; k++) {
					data.add(Math.random());
				}
				insertToRelatedTable(user, ws, data);
			}
		}
	}
	
	private boolean insertToIndependentTable(WebService ws, List<Double> data) {
		boolean flag = false;
		try {
			String tableName = ws.getCategory() + "_independent_factors";
			String sql = "INSERT INTO " + tableName + " VALUE(?";
			for (int i = 0; i < data.size(); i++)
				sql += ",?";
			sql += ")";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, ws.getName());
			for (int i = 0; i < data.size(); i++)
				this.pstmt.setDouble(i + 2, data.get(i));
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
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	private boolean insertToRelatedTable(User user, WebService ws, List<Double> data) {
		boolean flag = false;
		try {
			String tableName = ws.getCategory() + "_related_factors";
			String sql = "INSERT INTO " + tableName + " VALUE(?,?";
			for (int i = 0; i < data.size(); i++)
				sql += ",?";
			sql += ",?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getUsername());
			this.pstmt.setString(2, ws.getName());
			for (int i = 0; i < data.size(); i++)
				this.pstmt.setDouble(i + 3, data.get(i));
			this.pstmt.setInt(data.size() + 3, 0);
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
				e.printStackTrace();
			}
		}
		return flag;
	}

}
