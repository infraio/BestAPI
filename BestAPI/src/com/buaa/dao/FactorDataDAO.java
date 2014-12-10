package com.buaa.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.buaa.model.DataItem;
import com.buaa.model.DataSource;
import com.buaa.model.Domain;
import com.buaa.model.EvaluationTree;
import com.buaa.model.FactorNode;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.system.DomainFactory;
import com.buaa.system.EvaluationTreeFactory;

public class FactorDataDAO {
	
	private static final String mapReduceInFile = "/home/xiaohao/github/BestAPI/BestAPI/data/mapreduce.in";
	private static final String mapReduceOutFile = "/home/xiaohao/github/BestAPI/BestAPI/data/mapreduce.in";
	private static FactorDataDAO instance = null;
	private DatabaseConnector dbc = null;
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private List<User> users;
	private List<WebService> wss;
	
	private FactorDataDAO() {
		try {
			this.dbc = new DatabaseConnector();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connect = this.dbc.getConnection();
		users = UserDAOFactory.getUserDAOInstance().getAllUsers();
		wss = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).getAllWebServices();
	}
	
	public static FactorDataDAO getInstance() {
		if (instance == null)
			instance = new FactorDataDAO();
		return instance;
	}
	
	public void saveRelatedFactorDataForMapReduce(String factorName) {
		double[][] matrix = this.getMatrixForRelatedFactor(factorName);
		int rowNum = users.size();
		int colNum = wss.size();
		System.out.println(rowNum + "\t" + colNum);
		try {
			FileWriter fw = new FileWriter(new File(mapReduceInFile));
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					if (matrix[i][j] != 0.0 && matrix[i][j] != -1.0) {
						fw.write(i + " " + j + " " + matrix[i][j] + "\n");
					}
				}
				fw.flush();
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double[][] getMatrixForRelatedFactor(String factorName) {
		int m = users.size();
		int n = wss.size();
		double[][] matrix = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = getValue(wss.get(j).getCategory(), users.get(i).getUsername(), wss.get(j).getName(), factorName);
			}
		}
		return matrix;
	}
	
	private double[][] readMatrixFromMapReduce() {
		int rowNum = users.size();
		int colNum = wss.size();
		double[][] matrix = new double[rowNum][colNum];
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapReduceOutFile));
			String s;
			int usernum = 0;
			while((s = br.readLine()) != null) {
				String[] ss = s.split("\t");
				for(int i = 0; i < colNum; ++i) {
					matrix[usernum][i] = Double.parseDouble(ss[i]);
				}
				usernum++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}
	
	public void savePredictionResultFromMapReduce(String factorName) {
		savePredictionResult(readMatrixFromMapReduce(), factorName);
	}
	
	public void savePredictionResult(double[][] result, String factorName) {
		int m = users.size();
		int n = wss.size();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				double value = getValue(wss.get(j).getCategory(), users.get(i).getUsername(), wss.get(j).getName(), factorName);
				if (value == 0.0 && result[i][j] > 0.0) {
					saveValue(result[i][j], wss.get(j).getCategory(), users.get(i).getUsername(), wss.get(j).getName(), factorName);
				}
			}
		}
	}
	
	private boolean saveValue(double value, String domainName, String username, String wsName, String factorName) {
		boolean flag = false;
		try {
			String tableName = domainName + "_related_factors";
			String sql = "INSERT INTO " + tableName + " VALUE(?,?,?,?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setString(2, wsName);
			this.pstmt.setDouble(3, value);
			// 1 means predict data
			this.pstmt.setInt(4, 1);
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
	
	private double getValue(String domainName, String username, String wsName, String factorName) {
		String tableName = domainName + "_related_factors";
		double value = 0.0;
		try {
			//String sql = "SELECT `" + factorName + "` from " + tableName + " where username=? and apiname=?";
			String sql = "SELECT * from " + tableName + " where username=? and apiname=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setString(2, wsName);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				// 0 means real data
				if (rs.getInt(4) == 0) {
					value = rs.getDouble(3);
				}
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
		return value;
	}
	
	public void prepareFactorData() {
		EvaluationTreeDAO etDao = new EvaluationTreeDAO(); 
		for (Domain domain : DomainFactory.getInstance().getAllDomains()) {
			EvaluationTree eTree = EvaluationTreeFactory.getInstance().getEvaluationTreeByDomain(domain);
			etDao.createDbForFactors(eTree);
		}
		
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
