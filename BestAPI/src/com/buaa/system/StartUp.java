package com.buaa.system;

import java.io.IOException;
import java.util.concurrent.Callable;  
import java.util.concurrent.FutureTask; 

import javax.servlet.http.HttpServlet;

import com.buaa.algorithm.ValuePrediction;
import com.buaa.dao.FactorDataDAO;

public class StartUp extends HttpServlet {
	
	private final String startShellPath = "/home/xiaohao/github/BestAPI/BestAPI/shell/start.sh";
	private final String logFilePath = "/home/xiaohao/github/BestAPI/BestAPI/shell/mapreduce.log";
	private static final long serialVersionUID = 1L;

	public StartUp() {
		
	}
	
	public void init() {
		
		FutureTask<String> task = new FutureTask<String>(new Callable<String>(){  	  
			@Override  
			public String call() throws Exception {  
				//startUp(); // 使用另一个线程来执行该方法，会避免占用Tomcat的启动时间  
				return "Collection Completed";  
	        }  
		});  
	          
	        new Thread(task).start();
	}
	
	private void startUp() {
		DomainFactory.getInstance();
		QoSModelFactory.getInstance();
		EvaluationTreeFactory.getInstance();
//		ValuePrediction vp = new ValuePrediction(FactorDataDAO.getInstance().getMatrixForRelatedFactor("Response time"));
//		vp.predictAll();
//		FactorDataDAO.getInstance().savePredictionResult(vp.getResult(), "Response time");
		System.out.println("Start up");
		new RunShell(startShellPath).run();
		try {
			Thread.sleep(3000);
			new LogView(logFilePath).realtimeShowLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
