package com.buaa.model;

public class WebService {
	
	private WebServiceAttribute name;
	private WebServiceAttribute provider;
	private WebServiceAttribute endpoint;
	private WebServiceAttribute homepage;
	private WebServiceAttribute email;
	private WebServiceAttribute primaryCategory;
	private WebServiceAttribute secondaryCategories;
	private WebServiceAttribute protocolFormats;
	private WebServiceAttribute hubURL;
	private WebServiceAttribute SSLSupport;
	private WebServiceAttribute twiterURL;
	private WebServiceAttribute authenticationMode;
	
	public WebService() {
		name = WebServiceAttribute.API_NAME;
		provider = WebServiceAttribute.API_PROVIDER;
		endpoint = WebServiceAttribute.API_ENDPOINT;
		homepage = WebServiceAttribute.API_HOMEPAGE;
		email = WebServiceAttribute.CONTACT_EMAIL;
		primaryCategory = WebServiceAttribute.PRIMARY_CATEGORY;
		secondaryCategories = WebServiceAttribute.SECONDARY_CATEGORIES;
		protocolFormats = WebServiceAttribute.PROTOCOL_FORMATS;
		hubURL = WebServiceAttribute.APIHUB_URL;
		SSLSupport = WebServiceAttribute.SSL_SUPPORT;
		twiterURL = WebServiceAttribute.TWITER_URL;
		authenticationMode = WebServiceAttribute.AUTHENTICATION_MODE;
	}
	
	public void setAttributeContent(WebServiceAttribute wsa, String content) {
		switch(wsa) {
			case API_NAME: 
				name.setContent(content); break;
			case API_PROVIDER: 
				provider.setContent(content); break;
			case API_ENDPOINT: 
				endpoint.setContent(content); break;
			case API_HOMEPAGE: 
				homepage.setContent(content); break;
			case CONTACT_EMAIL: 
				email.setContent(content); break;
			case PRIMARY_CATEGORY: 
				primaryCategory.setContent(content); break;
			case SECONDARY_CATEGORIES: 
				secondaryCategories.setContent(content); break;
			case PROTOCOL_FORMATS: 
				protocolFormats.setContent(content); break;
			case APIHUB_URL : 
				hubURL.setContent(content); break;
			case SSL_SUPPORT: 
				SSLSupport.setContent(content); break;
			case TWITER_URL: 
				twiterURL.setContent(content); break;
			case AUTHENTICATION_MODE : 
				authenticationMode.setContent(content); break;
		}
	}
	
	public String getAttributeContent(WebServiceAttribute wsa) {
		switch(wsa) {
			case API_NAME: 
				return name.getContent();
			case API_PROVIDER: 
				return provider.getContent();
			case API_ENDPOINT: 
				return endpoint.getContent();
			case API_HOMEPAGE: 
				return homepage.getContent();
			case CONTACT_EMAIL:
				return email.getContent();
			case PRIMARY_CATEGORY: 
				return primaryCategory.getContent();
			case SECONDARY_CATEGORIES: 
				return secondaryCategories.getContent();
			case PROTOCOL_FORMATS: 
				return protocolFormats.getContent();
			case APIHUB_URL : 
				return hubURL.getContent();
			case SSL_SUPPORT: 
				return SSLSupport.getContent();
			case TWITER_URL: 
				return twiterURL.getContent();
			case AUTHENTICATION_MODE : 
				return authenticationMode.getContent();
			default : return "";
		}
	}
	
	
}
