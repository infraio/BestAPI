package com.buaa.model;

import java.util.List;

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
}
