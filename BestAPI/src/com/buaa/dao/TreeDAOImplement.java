package com.buaa.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.CategoryNode;
import com.buaa.model.DataInstance;
import com.buaa.model.DataItem;
import com.buaa.model.EvaluationTree;
import com.buaa.model.FactorNode;
import com.buaa.model.Node;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class TreeDAOImplement implements TreeDAOInterface {

	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	
	public TreeDAOImplement(Connection connect) {
		this.connect = connect;
	}
	
	private void recurse(Element elmt, CategoryNode father) {
		
		String name = elmt.elementText("name");
		double weight = Double.parseDouble(elmt.elementText("weight"));
		Element nodes = elmt.element("Nodes");
		
		if(nodes != null) {
			CategoryNode node = new CategoryNode();
			node.setName(name);
			node.setWeight(weight);
			father.getChild().add(node);
			for(@SuppressWarnings("rawtypes")
			Iterator iter = nodes.elementIterator(); iter.hasNext(); ) {
				recurse((Element)iter.next(), node);
			}
		} else {
			FactorNode node = new FactorNode();
			node.setName(name);
			node.setWeight(weight);
			father.getChild().add(node);
		}
	}
	
	private boolean readTree(EvaluationTree tree) {
		boolean flag = false;
		try {
			File target = new File(dir + "/" + tree.getName() + ".xml");
			if(target.exists()) {
				SAXReader sr = new SAXReader();
				Document doc = sr.read(target);
				Element root = doc.getRootElement();
				CategoryNode father = new CategoryNode();
				recurse(root, father);
				tree.setRoot(father.getChild().get(0));
				
				for(Node node : ((CategoryNode)tree.getRoot()).getChild()) {
					if(node.getName().contains("Static")) {
						List<FactorNode> staticFactors = new ArrayList<FactorNode>();
						tree.getFactors(node, staticFactors);
						tree.setStaticFactors(staticFactors);
					} else if(node.getName().contains("Dynamic")) {
						List<FactorNode> dynamicFactors = new ArrayList<FactorNode>();
						tree.getFactors(node, dynamicFactors);
						tree.setDynamicFactors(dynamicFactors);
					}
				}
				flag = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private void random(EvaluationTree tree) throws SQLException {
		try {
			
			int apinum = 7133, usernum = 100;
			
			for(int j = 0; j < usernum; ++j) {
				String name = "";
				char a = 'a';
				for(int i = 0; i < 6; ++i)
					name += String.valueOf((char)(a+(int)(Math.random()*26)));
				String email = name+"@gmail.com";
				String password = name;
				System.out.println(name);
				
				String sql = "SELECT * FROM user WHERE username='" + name + "'";
				this.stmt = this.connect.createStatement();
				ResultSet rs = this.stmt.executeQuery(sql);
				if(rs.next()) {
					rs.close();
					continue;
				} else {
					rs.close();
				}
				sql = "INSERT INTO user VALUE(?, ?, ?)";
				this.pstmt = this.connect.prepareStatement(sql);
				this.pstmt.setString(1, email);
				this.pstmt.setString(2, name);
				this.pstmt.setString(3, password);
				this.pstmt.executeUpdate();
			}
			
			for(int i = 1; i <= apinum; ++i) {
				String sql = "SELECT API_NAME FROM api LIMIT " + String.valueOf(i) + ",1";
				this.stmt = this.connect.createStatement();
				ResultSet rs = this.stmt.executeQuery(sql);
				if(rs.next()) {
					String apiname = rs.getString("API_NAME");
					rs.close();
					
					String qmark = "";
					for(int j = 0; j < tree.getStaticFactors().size(); ++j)
						qmark += "?,";
					qmark += "?";
					sql = "INSERT INTO Static_" + tree.getName() + " VALUE(" + qmark + ")";
					this.pstmt = this.connect.prepareStatement(sql);
					this.pstmt.setString(1, apiname);
					for(int j = 0; j < tree.getStaticFactors().size(); ++j)
						this.pstmt.setDouble(j+2, new Random().nextDouble());
					this.pstmt.executeUpdate();
					
					for(int j = 1; j <= usernum; ++j) {
						sql = "SELECT email FROM user LIMIT " + String.valueOf(j) + ",1";
						this.stmt = this.connect.createStatement();
						rs = this.stmt.executeQuery(sql);
						
						if(rs.next()) {
							String email = rs.getString("email");
							rs.close();
							
							qmark = "";
							for(int k = 0; k < tree.getDynamicFactors().size(); ++k)
								qmark += "?,";
							qmark += "?,?";
							sql = "INSERT INTO Dynamic_" + tree.getName() + " VALUE(" + qmark + ")";
							this.pstmt = this.connect.prepareStatement(sql);
							this.pstmt.setString(1, email);
							this.pstmt.setString(2, apiname);
							for(int k = 0; k < tree.getDynamicFactors().size(); ++k)
								this.pstmt.setDouble(k+3, new Random().nextDouble());
							this.pstmt.executeUpdate();
						} else {
							rs.close();
						}
					}
				} else {
					rs.close();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(this.stmt != null) {
				this.stmt.close();
			}
			if(this.pstmt != null) {
				this.pstmt.close();
			}
		}
	}
	
	public boolean createTree(EvaluationTree tree) throws Exception {
		boolean flag = false;
		try {
			if(readTree(tree)) {
				
				String sql = "DROP TABLE IF EXISTS Static_" + tree.getName();
				this.stmt = this.connect.createStatement();
				this.stmt.execute(sql);
				sql = "CREATE TABLE Static_" + tree.getName() + "(" + 
					  "API_NAME VARCHAR(255) PRIMARY KEY, ";
				for(FactorNode node : tree.getStaticFactors())
					sql += node.getName().toUpperCase().replaceAll(" ", "_") + " DOUBLE NOT NULL, ";
				sql += "FOREIGN KEY(API_NAME) REFERENCES api(API_NAME) ON DELETE CASCADE ON UPDATE CASCADE)";
				this.stmt.execute(sql);
				
				sql = "DROP TABLE IF EXISTS Dynamic_" + tree.getName();
				this.stmt.execute(sql);
				sql = "CREATE TABLE Dynamic_" + tree.getName() + "(" + 
					  "email VARCHAR(255) NOT NULL," +
					  "API_NAME VARCHAR(255) NOT NULL,";
				for(FactorNode node : tree.getDynamicFactors())
					sql += node.getName().toUpperCase().replaceAll(" ", "_") + " DOUBLE NOT NULL, ";
				sql += "FOREIGN KEY(email) REFERENCES user(email) ON DELETE CASCADE ON UPDATE CASCADE, ";
				sql += "FOREIGN KEY(API_NAME) REFERENCES api(API_NAME) ON DELETE CASCADE ON UPDATE CASCADE)";
				this.stmt.execute(sql);
				
				random(tree);// TODO 
				flag = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(this.stmt != null) {
				this.stmt.close();
			}
		}
		return flag;
	}
	
	public boolean getWeight(WebService api, EvaluationTree tree) throws Exception {
		boolean flag = false;
		try {
			File target = new File(dir + "/map.xml");
			if(target.exists()) {
				SAXReader sr = new SAXReader();
				Document doc = sr.read(target);
				Element root = doc.getRootElement();
				tree.setName(root.elementText(api.getAttributeContent(WebServiceAttribute.API_NAME)));
				readTree(tree);
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
	
	public boolean getValue(DataInstance instance) throws Exception {
		return getStaticValue(instance) && getDynamicValue(instance);
	}
	
	private boolean getStaticValue(DataInstance instance) throws Exception {
		boolean flag = false;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Static_" + instance.getTree().getName() + " WHERE API_NAME=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, instance.getApi().getAttributeContent(WebServiceAttribute.API_NAME));
			rs = this.pstmt.executeQuery();
			if(rs.next())
				for(DataItem item : instance.getStaticItems())
					item.setValue(rs.getDouble(item.getFactor().getName().toUpperCase().replaceAll(" ", "_")));
			flag = true;
		} catch(Exception e) {
			throw e;
		} finally {
			if(rs != null)
				rs.close();
			if(this.pstmt != null)
				this.pstmt.close();
		}
		return flag;
	}
	
	private boolean getDynamicValue(DataInstance instance) throws Exception {
		boolean flag = false;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Dynamic_" + instance.getTree().getName() + " WHERE email=? AND API_NAME=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, instance.getUser().getEmail());
			this.pstmt.setString(2, instance.getApi().getAttributeContent(WebServiceAttribute.API_NAME));
			rs = this.pstmt.executeQuery();
			if(rs.next())
				for(DataItem item : instance.getDynamicItems())
					item.setValue(rs.getDouble(item.getFactor().getName().toUpperCase().replaceAll(" ", "_")));
		} catch(Exception e) {
			throw e;
		} finally {
			if(this.pstmt != null)
				this.pstmt.close();
		}
		return flag;
	}
}
