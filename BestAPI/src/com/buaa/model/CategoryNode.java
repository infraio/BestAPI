package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode extends Node {

	private List<Node> childs;
	
	public CategoryNode() {
		childs = new ArrayList<Node>();
	}
	
	public CategoryNode(String name) {
		this.name = name;
		this.childs = new ArrayList<Node>();
	}
	
	public CategoryNode(String name, double weight) {
		this.name = name;
		this.weight = weight;
		this.childs = new ArrayList<Node>();
	}
	
	public CategoryNode(String name, int level) {
		this.name = name;
		this.level = level;
		this.childs = new ArrayList<Node>();
	}
	
	public List<Node> getChilds() {
		return childs;
	}

	public void setChilds(List<Node> childs) {
		this.childs = childs;
	}
	
	public void addchild(Node child) {
		this.childs.add(child);
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
	
	public void setWeightByChilds() {
		double tempWeight = 0.0;
		for (int i = 0; i < childs.size(); i++) {
			if (childs.get(i) instanceof CategoryNode) {
				CategoryNode temp = (CategoryNode) childs.get(i);
				temp.setWeightByChilds();
			}
			tempWeight += childs.get(i).getWeight();
		}
		setWeight(tempWeight);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < level; i++)
			str += "\t";
//		str += this.name + "," + this.weight + "," + this.relativeWeight + "\n";
		str += this.name + "," + this.weight + "\n";
		for (int i = 0; i < childs.size(); i++) {
			str += childs.get(i);
		}
		return str;
	}
	
	@Override
	public String toXML() {
		String str = "";
		for (int i = 0; i < level - 1; i++)
			str += "\t";
		str += "<CategoryNode>\n";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "<Name>" + name + "</Name>\n";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "<Weight>" + weight + "</Weight>\n";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "<Childs>\n";
		for (int i = 0; i < childs.size(); i++)
			str += childs.get(i).toXML();
		for (int i = 0; i < level; i++)
			str += "\t";
		str += "</Childs>\n";
		for (int i = 0; i < level - 1; i++)
			str += "\t";
		str += "</CategoryNode>\n";
		return str;
	}
}
