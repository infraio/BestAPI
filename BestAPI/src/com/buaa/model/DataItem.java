package com.buaa.model;

public class DataItem {

	private FactorNode factor;
	
	public DataItem(FactorNode factor) {
		this.setFactor(factor);
	}

	public FactorNode getFactor() {
		return factor;
	}

	public void setFactor(FactorNode factor) {
		this.factor = factor;
	}
}
