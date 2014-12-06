package com.buaa.model;

import java.util.ArrayList;

public class FactorNode extends Node {

	private FactorType type;
	
	public FactorNode() {}
	
	public FactorNode(String name) {
		this.name = name;
	}
	
	public FactorNode(String name, FactorType type) {
		this.name = name;
		this.type = type;
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
	
	public FactorType getType() {
		if (this.type == null) {
			setType(decideTypeByName());
		}
		return this.type;
	}
	
	public void setType(FactorType type) {
		this.type = type;
	}
	
	private FactorType decideTypeByName() {
		if (this.name.equals("Pay success rate")) {
			return FactorType.related;
		} else if (this.name.equals("Response time")) {
			return FactorType.related;
		} else {
			return FactorType.independent;
		}
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
	
	@Override
	public String toXML() {
		String str = "";
		for (int i = 0; i < level - 1; i++)
			str += "\t";
		str += "<FactorNode>\n";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "<Name>" + name + "</Name>\n";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "<Weight>" + weight + "</Weight>\n";
		for (int i = 0; i < level - 1; i++)
			str += "\t";
		str += "</FactorNode>\n";
		return str;
	}
}
