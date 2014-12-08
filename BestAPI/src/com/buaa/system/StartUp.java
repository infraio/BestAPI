package com.buaa.system;

import javax.servlet.http.HttpServlet;

import com.buaa.algorithm.ValuePrediction;
import com.buaa.dao.FactorDataDAO;

public class StartUp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public StartUp() {
		
	}
	
	public void init() {
		DomainFactory.getInstance();
		QoSModelFactory.getInstance();
		EvaluationTreeFactory.getInstance();
//		ValuePrediction vp = new ValuePrediction(FactorDataDAO.getInstance().getMatrixForRelatedFactor("Response time"));
//		vp.predictAll();
//		FactorDataDAO.getInstance().savePredictionResult(vp.getResult(), "Response time");
		System.out.println("Start up");
	}
}
