package com.buaa.model;

public class QoSModel {
	private String domain;
	private QoSAttribute root;
	
	public QoSModel(String domain) {
		this.domain = domain;
		root = null;
	}
	
	public String getDomain() {
		return this.domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public QoSAttribute getRoot() {
		return this.root;
	}
	
	public void setRoot(QoSAttribute root) {
		this.root = root;
	}
	
	@Override
	public String toString() {
		return this.domain + "\n" + this.root;
	}
}
