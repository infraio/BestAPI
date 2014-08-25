package com.buaa.model;

public class WebService {
	
	private int similarity;
	private DataInstance datains;
	
	private String owner;
	private String name;
	private String provider;
	private String endpoint;
	private String homepage;
	private String email;
	private String primaryCategory;
	private String secondaryCategories;
	private String protocolFormats;
	private String hubURL;
	private String SSLSupport;
	private String twiterURL;
	private String authenticationMode;
	
	public WebService() {}
	
	public WebService(String name, User user) {
		this.name = name;
		datains = new DataInstance(user, this);
	}
	
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	
	public int getSimilarity() {
		return similarity;
	}
	
	public void setAttributeContent(WebServiceAttribute wsa, String content) {
		switch(wsa) {
			case API_OWNER:
				owner = content; break;
			case API_NAME: 
				name = content; break;
			case API_PROVIDER: 
				provider = content; break;
			case API_ENDPOINT: 
				endpoint = content; break;
			case API_HOMEPAGE: 
				homepage = content; break;
			case CONTACT_EMAIL: 
				email = content; break;
			case PRIMARY_CATEGORY: 
				primaryCategory = content; break;
			case SECONDARY_CATEGORIES: 
				secondaryCategories = content; break;
			case PROTOCOL_FORMATS: 
				protocolFormats = content; break;
			case APIHUB_URL : 
				hubURL = content; break;
			case SSL_SUPPORT: 
				SSLSupport = content; break;
			case TWITER_URL: 
				twiterURL = content; break;
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
			case API_PROVIDER: 
				return provider;
			case API_ENDPOINT: 
				return endpoint;
			case API_HOMEPAGE: 
				return homepage;
			case CONTACT_EMAIL:
				return email;
			case PRIMARY_CATEGORY: 
				return primaryCategory;
			case SECONDARY_CATEGORIES: 
				return secondaryCategories;
			case PROTOCOL_FORMATS: 
				return protocolFormats;
			case APIHUB_URL : 
				return hubURL;
			case SSL_SUPPORT: 
				return SSLSupport;
			case TWITER_URL: 
				return twiterURL;
			case AUTHENTICATION_MODE : 
				return authenticationMode;
			default : return "";
		}
	}

//	@Override
//	public boolean equals(Object obj) {
//		return obj instanceof WebService && 
//				((WebService)obj).getAttributeContent(WebServiceAttribute.API_NAME).equals(this.getAttributeContent(WebServiceAttribute.API_NAME));
//	}
//
//	@Override
//	public int hashCode() {
//		return 37;
//	}
	
}
