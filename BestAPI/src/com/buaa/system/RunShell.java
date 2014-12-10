package com.buaa.system;

public class RunShell {
	
	private String shellPath = "";
	
	public RunShell(String shellPath) {
		this.shellPath = shellPath;
	}
	
	public void run() {
		try {
		    Process ps = Runtime.getRuntime().exec(shellPath);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

