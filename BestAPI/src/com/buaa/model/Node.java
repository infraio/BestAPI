package com.buaa.model;

public abstract class Node {
	
	protected String name;
	protected double weight;
	
	public Node() {}
	
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
