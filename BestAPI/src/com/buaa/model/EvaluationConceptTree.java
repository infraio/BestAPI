package com.buaa.model;

import java.util.List;

public class EvaluationConceptTree {
	
	private String name;
	private Node root;
	private List<FactorNode> staticFactors;
	private List<FactorNode> dynamicFactors;
	
	public EvaluationConceptTree() {}
	
	public void getFactors(Node node, List<FactorNode> factors) {
		if(node instanceof CategoryNode) {
			for(Node anode : ((CategoryNode)node).getChild()) 
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
	
}
