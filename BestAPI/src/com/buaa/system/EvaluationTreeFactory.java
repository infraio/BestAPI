package com.buaa.system;
import java.util.HashMap;
import java.util.List;

import com.buaa.algorithm.WeightDistribution;
import com.buaa.dao.EvaluationTreeDAO;
import com.buaa.dao.RecordDAO;
import com.buaa.model.*;

public class EvaluationTreeFactory {
	
	private static EvaluationTreeFactory instance = null;
	private HashMap<Domain, EvaluationTree> map;
	private EvaluationTreeDAO dao;
	
	private EvaluationTreeFactory() {
		map = new HashMap<Domain, EvaluationTree>();
		dao = new EvaluationTreeDAO();
		initialize();
	}

	public static EvaluationTreeFactory getInstance() {
		if (instance == null)
			instance = new EvaluationTreeFactory();
		return instance;
	}
	
	private void initialize() {
		List<Domain> domains = DomainFactory.getInstance().getAllDomains();
		for (Domain domain : domains) {
			EvaluationTree eTree = dao.genByQoSModel(QoSModelFactory.getInstance().getQoSModelByDomain(domain));
			HashMap<List<Double>, Boolean> dataSet = RecordDAO.getInstance().readDataSet(domain, eTree.getFactors().size());
			WeightDistribution.getInstance().hybridMethod(eTree, dataSet, 1);
			map.put(domain, eTree);
		}
	}
	
	public EvaluationTree getEvaluationTreeByDomain(Domain domain) {
		return this.map.get(domain);
	}
	
}
