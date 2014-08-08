package com.buaa.model;

public enum WebServiceAttribute {
	
	/*0*/API_NAME("API_NAME"),
	/*1*/API_OWNER("API_OWNER"),
	/*2*/API_PROVIDER("API_PROVIDER"), 
	/*3*/API_ENDPOINT("API_ENDPOINT"), 
	/*4*/API_HOMEPAGE("API_HOMEPAGE"),
	/*5*/CONTACT_EMAIL("CONTACT_EMAIL"),
	/*6*/PRIMARY_CATEGORY("PRIMARY_CATEGORY"), 
	/*7*/SECONDARY_CATEGORIES("SECONDARY_CATEGORIES"), 
	/*8*/PROTOCOL_FORMATS("PROTOCOL_FORMATS"), 
	/*9*/APIHUB_URL("API_HUB_URL"),
	/*10*/SSL_SUPPORT("SSL_SUPPORT"), 
	/*11*/TWITER_URL("TWITER_URL"), 
	/*12*/AUTHENTICATION_MODE("AUTHENTICATION_MODE");
	
	private String name;
	
	private WebServiceAttribute(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
