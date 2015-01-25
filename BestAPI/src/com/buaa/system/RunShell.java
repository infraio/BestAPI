package com.buaa.system;

public class RunShell {
	
	private String shellPath = "";
	
	public RunShell(String shellPath) {
		System.out.println("启动评价因子预测算法脚本：" + shellPath);
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

