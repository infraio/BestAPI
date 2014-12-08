package com.buaa.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.buaa.model.User;
import com.buaa.model.WebService;
import com.buaa.model.WebServiceInstance;

public class WebServiceEvaluation {
	private static WebServiceEvaluation instance = null;
	
	private WebServiceEvaluation() {
	}
	
	public static WebServiceEvaluation getInstance () {
		if (instance == null)
			instance = new WebServiceEvaluation();
		return instance;
	}
	
	public List<WebServiceInstance> getTop10Rank(User user, List<WebService> apis) {
		return getTopnRank(user, apis, 10);
	}
	
	public List<WebServiceInstance> getTopnRank(User user, List<WebService> apis, int n) {
		List<WebServiceInstance> wsis = new ArrayList<WebServiceInstance>();
		for (WebService ws : apis) {
			wsis.add(new WebServiceInstance(user, ws));
		}
		Collections.sort(wsis, new EvaluationResultComparator());
		if (n < wsis.size())
			return wsis.subList(0, n);
		else
			return wsis;
	}
}

class EvaluationResultComparator implements Comparator<WebServiceInstance> {
	@Override
	public int compare(WebServiceInstance o1, WebServiceInstance o2) {
		if (o1.getEvaluationResult() - o2.getEvaluationResult() > 0.0)
			return -1;
		else
			return 1;
	}
}
