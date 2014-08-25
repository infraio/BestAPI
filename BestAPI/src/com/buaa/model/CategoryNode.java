package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode extends Node {

	private List<Node> child;
	
	public CategoryNode() {
		child = new ArrayList<Node>();
	}
	
	public CategoryNode(String name) {
		this.name = name;
		this.child = new ArrayList<Node>();
	}
	
	public CategoryNode(String name, double weight) {
		this.name = name;
		this.weight = weight;
		this.child = new ArrayList<Node>();
	}
	
	public List<Node> getChild() {
		return child;
	}

	public void setChild(List<Node> child) {
		this.child = child;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
