package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode extends Node {

	private List<Node> child;
	
	public CategoryNode() {
		child = new ArrayList<Node>();
	}

	public List<Node> getChild() {
		return child;
	}

	public void setChild(List<Node> child) {
		this.child = child;
	}
}
