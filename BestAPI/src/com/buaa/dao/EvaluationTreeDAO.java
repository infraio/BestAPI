package com.buaa.dao;
import java.util.ArrayList;

import com.buaa.model.*;

public class EvaluationTreeDAO {

	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	
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
	
}
