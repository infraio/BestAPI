package com.buaa.model;

import com.buaa.dao.DataInstanceDAOFactory;
import com.buaa.system.DomainFactory;
import com.buaa.system.EvaluationTreeFactory;

public class WebServiceInstance {
	
	private User user;
	private WebService ws;
	private DataInstance di;
	private double evaluationResult;
	
	public WebServiceInstance(User user, WebService ws) {
		this.user = user;
		this.ws = ws;
		evaluation();
	}
	
	public void evaluation() {
		Domain domain = DomainFactory.getInstance().getDomain(ws.getCategory());
		EvaluationTree eTree = EvaluationTreeFactory.getInstance().getEvaluationTreeByDomain(domain);
		di = DataInstanceDAOFactory.getDataInstanceDAOInstance().getDataInstance(user, ws, eTree);
		this.setEvaluationResult(computeEvaluationResult());
	}
	
	public double computeEvaluationResult() {
		double result = 0;
		for(DataItem item : this.di.getIndependentItems())
			result += item.getValue() * item.getFactor().getWeight();
		for(DataItem item : this.di.getRelatedItems())
			result += item.getValue() * item.getFactor().getWeight();
		return result;
	}
	
	public double getEvaluationResult() {
		return this.evaluationResult;
	}

	public void setEvaluationResult(double evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WebService getWs() {
		return ws;
	}

	public void setWs(WebService ws) {
		this.ws = ws;
	}
}
