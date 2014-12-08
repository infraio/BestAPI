package com.buaa.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.buaa.model.Domain;;

public class DomainFactory {
	private static String[] domainNameArray = {"Addresses", "Advertising", "Business", "Cloud", "Data","Education",
		"Entertainment", "Financial", "Health", "Media", "Music", "Other", "Payments", "Science", "Search",
		"Social", "Sports", "Storage", "Tools", "Travel", "Weather"};
	private static DomainFactory instance = null;
	private HashMap<String, Domain> map;
	
	private DomainFactory() {
		map = new HashMap<String, Domain>();
		initialize();
	}
	
	private void initialize() {
		for (int i = 0; i < domainNameArray.length; i++) {
			this.map.put(domainNameArray[i], new Domain(domainNameArray[i]));
		}
	}
	
	public static DomainFactory getInstance() {
		if (instance == null)
			instance = new DomainFactory();
		return instance;
	}
	
	public Domain getDomain(String name) {
		return this.map.get(name);
	}
	
	public List<Domain> getAllDomains() {
		List<Domain> domains = new ArrayList<Domain>();
		for (int i = 0; i < domainNameArray.length; i++) {
			domains.add(getDomain(domainNameArray[i]));
		}
		return domains;
	}
}
