package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

import com.buaa.dao.TreeDAOFactory;

public class DataInstance {
	
	private EvaluationConceptTree tree;
	private List<DataItem> staticItems;
	private List<DataItem> dynamicItems;
	private User user;
	private WebService api;
	
	public DataInstance(User user, WebService api) throws Exception {
		this.user = user;
		this.api = api;
		this.staticItems = new ArrayList<DataItem>();
		this.dynamicItems = new ArrayList<DataItem>();
		this.tree = new EvaluationConceptTree();
		TreeDAOFactory.getTreeDAOInstance().getWeight(api, tree);
		for(FactorNode node : tree.getStaticFactors())
			staticItems.add(new DataItem(node));
		for(FactorNode node : tree.getDynamicFactors())
			dynamicItems.add(new DataItem(node));
		TreeDAOFactory.getTreeDAOInstance().getValue(this);
	}
	
	public double getResult() {
		double result = 0;
		for(DataItem item : this.staticItems)
			result += item.getValue() * item.getFactor().getWeight();
		for(DataItem item : this.dynamicItems)
			result += item.getValue() * item.getFactor().getWeight();
		return result;
	}

	public EvaluationConceptTree getTree() {
		return tree;
	}

	public void setTree(EvaluationConceptTree tree) {
		this.tree = tree;
	}

	public List<DataItem> getStaticItems() {
		return staticItems;
	}

	public void setStaticItems(List<DataItem> staticItems) {
		this.staticItems = staticItems;
	}

	public List<DataItem> getDynamicItems() {
		return dynamicItems;
	}

	public void setDynamicItems(List<DataItem> dynamicItems) {
		this.dynamicItems = dynamicItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WebService getApi() {
		return api;
	}

	public void setApi(WebService api) {
		this.api = api;
	}
	
}
