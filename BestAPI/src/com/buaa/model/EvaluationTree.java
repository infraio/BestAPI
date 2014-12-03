package com.buaa.model;

import java.util.List;
import java.util.ArrayList;

public class EvaluationTree {
	
	private String name;
	private Node root;
	private List<FactorNode> staticFactors;
	private List<FactorNode> dynamicFactors;
	
	public EvaluationTree() {}
	
	public void getFactors(Node node, List<FactorNode> factors) {
		if(node instanceof CategoryNode) {
			for(Node anode : ((CategoryNode)node).getChilds()) 
				getFactors(anode, factors);
		} else if(node instanceof FactorNode) { 
			factors.add((FactorNode)node);
		}
	}
	
	public List<Node> getNodesByLevel(Node root, int level) {
		List<Node> nodes = new ArrayList<Node>();
		if (root.getLevel() == level)
			nodes.add(root);
		else if (root.getLevel() < level && root instanceof CategoryNode) {
			CategoryNode node = (CategoryNode) root;
			for (int i = 0; i < node.getChilds().size(); i++) {
				List<Node> temp = getNodesByLevel(node.getChilds().get(i), level);
				nodes.addAll(temp);
			}
		}
		return nodes;
	}
	
	public List<FactorNode> getStaticFactors() {
		return staticFactors;
	}

	public void setStaticFactors(List<FactorNode> staticFactors) {
		this.staticFactors = staticFactors;
	}

	public List<FactorNode> getDynamicFactors() {
		return dynamicFactors;
	}

	public void setDynamicFactors(List<FactorNode> dynamicFactors) {
		this.dynamicFactors = dynamicFactors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	@Override
	public String toString() {
		return this.name + this.root;
	}
	
	public boolean checkConstraintRules() {
		List<FactorNode> factors = new ArrayList<FactorNode>();
		getFactors(root, factors);
		double sumWeight = 0.0;
		for (int i = 0; i < factors.size(); i++)
			sumWeight += factors.get(i).getWeight();
		if (Math.abs(sumWeight - 1.0) < 0.0000001)
			return true;
		else
			return false;
	}
}
