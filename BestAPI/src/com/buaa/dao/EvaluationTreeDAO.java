package com.buaa.dao;
import java.util.ArrayList;
import java.util.List;

import com.buaa.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EvaluationTreeDAO {

	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	private DatabaseConnector dbc = null;
	private Connection connect = null;
	
	public EvaluationTreeDAO() {
		try { 
			this.dbc = new DatabaseConnector();
		} catch(Exception e) { 
			e.printStackTrace(); return;
		}
		this.connect = dbc.getConnection();
	}
	
	public EvaluationTree genByQoSModel(QoSModel qosModel) {
		EvaluationTree eTree = new EvaluationTree();
		eTree.setName(qosModel.getDomain());
		eTree.setRoot(convertAttributeToNode(qosModel.getRoot()));
		return eTree;
	}
	
	private Node convertAttributeToNode(QoSAttribute a) {
		if (a.getQoSAttributes().size() == 0) {
			return new FactorNode(a.getName(), a.getLevel());
		} else {
			CategoryNode node = new CategoryNode(a.getName(), a.getLevel());
			ArrayList<QoSAttribute> attributes = a.getQoSAttributes();
			for (int i = 0; i < attributes.size(); i++) {
				node.addchild(convertAttributeToNode(attributes.get(i)));
			}
			return node;
		}		
	}
	
	public void createDbForFactors(EvaluationTree eTree) {
		List<FactorNode> factors = eTree.getFactors();
		List<FactorNode> independentFactors = new ArrayList<FactorNode>();
		List<FactorNode> relatedFactors = new ArrayList<FactorNode>();
		for (int i = 0; i < factors.size(); i++) {
			if (factors.get(i).getType() == FactorType.independent) {
				independentFactors.add(factors.get(i));
			} else {
				relatedFactors.add(factors.get(i));
			}
		}
		createDbForRelatedFactors(relatedFactors, eTree.getName() + "_" + FactorType.related + "_" + "factors");
		createDbForRelatedFactors(independentFactors, eTree.getName() + "_" + FactorType.independent + "_" + "factors");
	}
	
	private void createDbForRelatedFactors(List<FactorNode> factors, String tableName) {
		String sql = "create table " + tableName + " ( " 
				+ "apiname varchar(255) not null, "
				+ "username varchar(255) not null, ";
		for (int i = 0; i < factors.size(); i++) {
			sql += "`" + factors.get(i).getName() + "` double not null, ";
		}
		sql += "CONSTRAINT combination_key PRIMARY KEY (apiname, username))";
		try {
			Statement s = this.connect.createStatement();
			s.execute("drop table if exists " + tableName);
			s.execute(sql);
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createDbForIndenpendentFactors(List<FactorNode> factors, String tableName) {
		String sql = "create table " + tableName + " ( " 
				+ "apiname varchar(255) primary key, ";
		for (int i = 0; i < factors.size(); i++) {
			if (i == factors.size() - 1) {
				sql += "`" + factors.get(i).getName() + "` double not null)";
			} else {
				sql += "`" + factors.get(i).getName() + "` double not null, ";
			}
		}
		try {
			Statement s = this.connect.createStatement();
			s.execute("drop table if exists " + tableName);
			s.execute(sql);
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
