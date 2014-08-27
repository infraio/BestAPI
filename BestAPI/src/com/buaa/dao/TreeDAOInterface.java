package com.buaa.dao;

import java.util.List;

import com.buaa.model.DataInstance;
import com.buaa.model.DataItem;
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public interface TreeDAOInterface {
	
	public boolean createTree(EvaluationConceptTree tree) throws Exception;
	public boolean getWeight(WebService api, EvaluationConceptTree tree) throws Exception;
	public boolean getValue(DataInstance instance) throws Exception;
}
