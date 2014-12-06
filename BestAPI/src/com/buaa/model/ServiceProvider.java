package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends User{

	private List<WebService> providedWebServices;
	
	public ServiceProvider(String email, String password) {
		super(email, password);
		providedWebServices = new ArrayList<WebService>();
	}
	
	public ServiceProvider(String email, String username, String password) {
		super(email, username, password);
		providedWebServices = new ArrayList<WebService>();
	}
	
	public List<WebService> getprovidedWebServices() {
		return this.providedWebServices;
	}
	
	public void addWebServiceToProvided(WebService ws) {
		this.providedWebServices.add(ws);
	}
	
}
