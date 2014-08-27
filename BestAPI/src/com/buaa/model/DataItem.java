package com.buaa.model;

public class DataItem {

	private FactorNode factor;
	private double value;
	
	public DataItem(FactorNode factor) {
		this.setFactor(factor);
	}

	public FactorNode getFactor() {
		return factor;
	}

	public void setFactor(FactorNode factor) {
		this.factor = factor;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
