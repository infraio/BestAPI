package com.buaa.model;

public enum WebServiceAttribute {
	
	/*0*/API_NAME("API_NAME"),
	/*1*/API_OWNER("API_OWNER"),
	/*2*/API_ENDPOINT("API_ENDPOINT"), 
	/*3*/API_HOMEPAGE("API_HOMEPAGE"),
	/*4*/CONTACT_EMAIL("CONTACT_EMAIL"),
	/*5*/CATEGORY("CATEGORY"), 
	/*6*/PROTOCOL_FORMATS("PROTOCOL_FORMATS"), 
	/*7*/APIHUB_URL("API_HUB_URL"),
	/*8*/AUTHENTICATION_MODE("AUTHENTICATION_MODE");
	
	private String name;
	
	private WebServiceAttribute(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
