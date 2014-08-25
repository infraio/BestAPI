package com.buaa.model;

import java.util.List;

public class EvaluationConceptTree {
	
	private String name;
	private String path;
	private Node root;
	
	public EvaluationConceptTree() {}

	public void getFactors(Node node, List<DataItem> items) {
		if(node instanceof CategoryNode) {
			for(Node anode : ((CategoryNode)node).getChild()) 
				getFactors(anode, items);
		} else if(node instanceof FactorNode) { 
			items.add(new DataItem((FactorNode)node));
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
}
