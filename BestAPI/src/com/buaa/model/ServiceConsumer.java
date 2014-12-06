package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceConsumer extends User {
	
	private List<WebService> usedWebServices;
	
	public ServiceConsumer(String email, String password) {
		super(email, password);
		this.usedWebServices = new ArrayList<WebService>();
	}

	public ServiceConsumer(String email, String username, String password) {
		super(email, username, password);
		this.usedWebServices = new ArrayList<WebService>();
	}

	public List<WebService> getUsedWebServices() {
		return this.usedWebServices;
	}
	
	public void addWebServiceToUsed(WebService ws) {
		this.usedWebServices.add(ws);
	}
	
	
	
}
