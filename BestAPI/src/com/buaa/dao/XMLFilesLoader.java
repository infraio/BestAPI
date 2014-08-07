package com.buaa.dao;

public class XMLFilesLoader {
	
	private final String DIRECTORY = System.getProperty("user.home") + "/Documents/data/api/";

	public XMLFilesLoader() {
		
	}
	
	public String getDirectory() {
		return this.DIRECTORY;
	}
	
}
