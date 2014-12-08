package com.buaa.model;

public class QoSModel {
	private Domain domain;
	private QoSAttribute root;
	
	public QoSModel(Domain domain) {
		this.domain = domain;
		root = null;
	}
	
	public Domain getDomain() {
		return this.domain;
	}
	
	public void setDomain(Domain domain) {
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
