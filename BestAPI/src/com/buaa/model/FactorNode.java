package com.buaa.model;

import java.util.ArrayList;

public class FactorNode extends Node {

	public FactorNode() {}
	
	public FactorNode(String name) {
		this.name = name;
	}
	
	public FactorNode(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public FactorNode(String name, int level) {
		this.name = name;
		this.level = level;
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
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < level; i++)
			str += "\t";
//		str += this.name + "," + this.weight + "," + this.relativeWeight + "\n";
		str += this.name + "," + this.weight + "\n";
		return str;
	}
}
