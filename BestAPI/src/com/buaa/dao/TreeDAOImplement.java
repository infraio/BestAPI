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
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.FactorNode;
import com.buaa.model.Node;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class TreeDAOImplement implements TreeDAOInterface {

	private final String dir = "/home/wuyinan/Repository/BestAPI/BestAPI/data";
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
	
	private boolean readTree(EvaluationConceptTree tree) {
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
	
	private void random(EvaluationConceptTree tree) throws SQLException {
		try {
			
			String sql = "INSERT INTO user VALUE('wuyinan0126@gmail.com', 'wuyinan', 'wuyinan')";
			this.stmt = this.connect.createStatement();
			this.stmt.executeUpdate(sql);
			
			sql = "INSERT INTO api VALUE('APIFON', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a')";
			this.stmt = this.connect.createStatement();
			this.stmt.executeUpdate(sql);
			
			String qmark = "";
			for(int i = 0; i < tree.getStaticFactors().size(); ++i)
				qmark += "?,";
			qmark += "?";
			sql = "INSERT INTO Static_" + tree.getName() + " VALUE(" + qmark + ")";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, "APIFON");
			for(int i = 0; i < tree.getStaticFactors().size(); ++i)
				this.pstmt.setDouble(i+2, new Random().nextDouble());
			this.pstmt.executeUpdate();
			
			qmark = "";
			for(int i = 0; i < tree.getDynamicFactors().size(); ++i)
				qmark += "?,";
			qmark += "?,?";
			sql = "INSERT INTO Dynamic_" + tree.getName() + " VALUE(" + qmark + ")";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, "wuyinan0126@gmail.com");
			this.pstmt.setString(2, "APIFON");
			for(int i = 0; i < tree.getDynamicFactors().size(); ++i)
				this.pstmt.setDouble(i+3, new Random().nextDouble());
			this.pstmt.executeUpdate();
			
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
	
	public boolean createTree(EvaluationConceptTree tree) throws Exception {
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
	
	public boolean getWeight(WebService api, EvaluationConceptTree tree) throws Exception {
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
