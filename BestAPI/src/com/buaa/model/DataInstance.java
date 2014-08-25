package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

import com.buaa.dao.TreeDAOFactory;

public class DataInstance {
	
	private EvaluationConceptTree tree;
	private List<DataItem> items;
	private User user;
	private WebService api;
	
	public DataInstance(User user, WebService api) {
		this.user = user;
		this.api = api;
		this.items = new ArrayList<DataItem>();
		this.tree = new EvaluationConceptTree();
		try {
			if(TreeDAOFactory.getTreeDAOInstance().getTreeName(user, api, tree)) {
				if(TreeDAOFactory.getTreeDAOInstance().buildTree(tree)) {
					tree.getFactors(tree.getRoot(), items);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
