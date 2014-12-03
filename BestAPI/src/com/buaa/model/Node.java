package com.buaa.model;

public abstract class Node {
	
	protected String name;
	protected double weight;
	protected double relativeWeight;
	protected int level;
	
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
	
	public double getRelativeWeight() {
		return this.relativeWeight;
	}
	
	public void setRelativeWeight(double relativeWeight) {
		this.relativeWeight = relativeWeight;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
