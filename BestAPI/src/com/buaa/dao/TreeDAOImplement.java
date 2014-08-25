package com.buaa.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.CategoryNode;
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.FactorNode;
import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class TreeDAOImplement implements TreeDAOInterface {

	private Connection connect = null;
	private PreparedStatement pstmt = null;
	
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
	
	public boolean buildTree(EvaluationConceptTree tree) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT path FROM tree WHERE name=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, tree.getName());
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				tree.setPath(rs.getString(1));
				flag = true;
			}
			if(flag) {
				File target = new File(tree.getPath());
				if(target.exists()) {
					SAXReader sr = new SAXReader();
					Document doc = sr.read(target);
					Element root = doc.getRootElement();
					CategoryNode father = new CategoryNode();
					recurse(root, father);
					tree.setRoot(father.getChild().get(0));
				}
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
	
	public boolean getTreeName(User user, WebService api, EvaluationConceptTree tree) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT treename FROM master WHERE email=? and apiname=?";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, user.getEmail());
			this.pstmt.setString(2, api.getAttributeContent(WebServiceAttribute.API_NAME));
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				tree.setName(rs.getString(1));
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
	
	public boolean addTree(EvaluationConceptTree tree) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO tree VALUE(?, ?)";
			this.pstmt = this.connect.prepareStatement(sql);
			this.pstmt.setString(1, tree.getName());
			this.pstmt.setString(2, tree.getPath());
			if(this.pstmt.executeUpdate() >= 0)
				flag = true;
			if(flag) {
				sql = "CREATE TABLE " + tree.getName() + " (" +
					  "";
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
}
