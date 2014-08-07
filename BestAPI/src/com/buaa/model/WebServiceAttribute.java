package com.buaa.model;

public enum WebServiceAttribute {
	
	API_NAME("API_NAME"),
	API_PROVIDER("API_PROVIDER"), 
	API_ENDPOINT("API_ENDPOINT"), 
	API_HOMEPAGE("API_HOMEPAGE"),
	CONTACT_EMAIL("CONTACT_EMAIL"),
	PRIMARY_CATEGORY("PRIMARY_CATEGORY"), 
	SECONDARY_CATEGORIES("SECONDARY_CATEGORIES"), 
	PROTOCOL_FORMATS("PROTOCOL_FORMATS"), 
	APIHUB_URL("API_HUB_URL"),
	SSL_SUPPORT("SSL_SUPPORT"), 
	TWITER_URL("TWITER_URL"), 
	AUTHENTICATION_MODE("AUTHENTICATION_MODE");
	
	private String name;
	private String content;
	
	private WebServiceAttribute(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
}
