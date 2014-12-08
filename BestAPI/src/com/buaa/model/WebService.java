package com.buaa.model;

public class WebService {
	
	private int id;
	private int similarity;
	
	private String name;
	private String owner;
	private String endpoint;
	private String homepage;
	private String contactEmail;
	private String category;
	private String protocolFormats;
	private String hubUrl;
	private String authenticationMode;
	
	public WebService() {}
	
	public WebService(String[] attributes) {
		this.name = attributes[0];
		this.owner = attributes[1];
		this.endpoint = attributes[2];
		this.homepage = attributes[3];
		this.contactEmail = attributes[4];
		this.category = attributes[5];
		this.protocolFormats = attributes[6];
		this.hubUrl = attributes[7];
		this.authenticationMode = attributes[8];
	}
	
	public WebService(String name, User user) throws Exception {
		this.name = name;
	}

	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	
	public int getSimilarity() {
		return similarity;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProtocolFormats() {
		return protocolFormats;
	}

	public void setProtocolFormats(String protocolFormats) {
		this.protocolFormats = protocolFormats;
	}

	public String getHubUrl() {
		return hubUrl;
	}

	public void setHubUrl(String hubUrl) {
		this.hubUrl = hubUrl;
	}

	public String getAuthenticationMode() {
		return authenticationMode;
	}

	public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}

	public void setAttributeContent(WebServiceAttribute wsa, String content) {
		switch(wsa) {
			case API_OWNER:
				owner = content; break;
			case API_NAME: 
				name = content; break;
			case API_ENDPOINT: 
				endpoint = content; break;
			case API_HOMEPAGE: 
				homepage = content; break;
			case CONTACT_EMAIL: 
				contactEmail = content; break;
			case CATEGORY: 
				category = content; break;
			case PROTOCOL_FORMATS: 
				protocolFormats = content; break;
			case APIHUB_URL : 
				hubUrl = content; break;
			case AUTHENTICATION_MODE : 
				authenticationMode = content; break;
		}
	}
	
	public String getAttributeContent(WebServiceAttribute wsa) {
		switch(wsa) {
			case API_OWNER:
				return owner;
			case API_NAME: 
				return name;
			case API_ENDPOINT: 
				return endpoint;
			case API_HOMEPAGE: 
				return homepage;
			case CONTACT_EMAIL:
				return contactEmail;
			case CATEGORY: 
				return category;
			case PROTOCOL_FORMATS: 
				return protocolFormats;
			case APIHUB_URL : 
				return hubUrl;
			case AUTHENTICATION_MODE : 
				return authenticationMode;
			default : return "";
		}
	}
}
