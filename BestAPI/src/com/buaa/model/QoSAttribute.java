package com.buaa.model;
import java.util.ArrayList;

public class QoSAttribute {
	private String name;
	private int level;
	private ArrayList<QoSAttribute> attributes;
	
	public QoSAttribute(String name, int level) {
		this.name = name;
		this.level = level;
		attributes = new ArrayList<QoSAttribute>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public ArrayList<QoSAttribute> getQoSAttributes() {
		return this.attributes;
	}
	
	public void addAttribute(QoSAttribute a) {
		attributes.add(a);
	}
	
	public void delAttribute(QoSAttribute a) {
		int i;
		for (i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).equals(a)) {
				attributes.remove(i);
				break;
			}
		}
	}
	
	@Override
	public boolean equals(Object a) {
		if (!(a instanceof QoSAttribute))
			return false;
		QoSAttribute temp = (QoSAttribute) a;
		if (!temp.name.equals(this.name))
			return false;
		if (this.attributes.size() != temp.attributes.size())
			return false;
		for (int i = 0; i < this.attributes.size(); i++) {
			if (!this.attributes.get(i).equals(temp.attributes.get(i)))
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < level; i++)
			str += "\t";
		str += this.name + "\n";
		for (int i = 0; i < attributes.size(); i++) {
			str += attributes.get(i);
		}
		return str;
	}
}
