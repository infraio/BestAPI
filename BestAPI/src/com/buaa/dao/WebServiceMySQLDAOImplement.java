package com.buaa.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
	
	public boolean saveWebServicesFromDbToFile(String filePath) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM api";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			File file = new File(filePath);
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			while(rs.next()) {
				for (int i = 1; i < 9; i++)
					bw.write(rs.getString(i) + "\t");
				bw.write(rs.getString(9) + "\n");
				bw.flush();
				flag = true;
			}
			bw.close();
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
					this.pstmt.close();
			}
		}
		return flag;
	}
	
	public boolean saveWebServicesFromFileToDb(String filePath) throws Exception {
		boolean flag = false;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				while (line != null) {
					String[] s = line.split("\t");
					if (s.length == 9) {
						WebService ws = new WebService(s);
						flag = addWebService(ws);
					}
					line = br.readLine();
				}
				br.close();
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
	
	public boolean addWebService(WebService api) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO api VALUE(?,?,?,?,?,?,?,?,?)";
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
	
	public WebService getWebServiceByName(String name) throws Exception {
		WebService ws = null;
		try {
			String sql = "SELECT * FROM api WHERE name=\"" + name + "\"";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			if(rs.next()) {
				ws = new WebService();
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 1; i < attributes.length; ++i) 
					ws.setAttributeContent(attributes[i], rs.getString(i+1));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
					this.pstmt.close();
			}
		}
		return ws;
	}
	
	public List<WebService> getWebServicesByCategory(String category) throws Exception {
		List<WebService> wss = new ArrayList<WebService>();
		try {
			String sql = "SELECT * FROM api WHERE category=\"" + category + "\"";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			while(rs.next()) {
				WebService ws = new WebService();
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 0; i < attributes.length; ++i) 
					ws.setAttributeContent(attributes[i], rs.getString(i+1));
				wss.add(ws);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return wss;
	}

	public boolean deleteWebServiceByName(String name) throws Exception {
		boolean flag = false;
		try {
			String sql = "DELETE FROM api WHERE name=\"" + name + "\"";
			System.out.println(sql);
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
	
	public boolean deleteWebService(WebService ws) throws Exception {
		return deleteWebServiceByName(ws.getName());
	}
	
	public List<WebService> fuzzySearch(String key) throws Exception {
		List<WebService> apis = new ArrayList<WebService>();
		try {
			String sql = "SELECT * FROM api WHERE name LIKE '%" + key + "%'"
					+ " or category LIKE '%" + key + "%'"
					+ " or protocol_formats LIKE '%" + key + "%'";
			this.stmt = connect.createStatement();
			ResultSet rs = this.stmt.executeQuery(sql);
			while(rs.next()) {
				WebService api = new WebService();
				WebServiceAttribute[] attributes = WebServiceAttribute.values();
				for(int i = 0; i < attributes.length; ++i)  {
					String attribute = rs.getString(i+1);
					api.setAttributeContent(attributes[i], attribute);
				}
				int similarity = 0;
				if (api.getName().contains(key)) {
					similarity += 2;
				}
				if (api.getCategory().contains(key)) {
					similarity += 1;
				}
				if (api.getProtocolFormats().contains(key)) {
					similarity += 1;
				}
				api.setSimilarity(similarity);
				apis.add(api);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return apis;
	}
}
