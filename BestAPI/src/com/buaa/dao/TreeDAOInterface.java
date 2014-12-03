package com.buaa.dao;

import java.util.List;

import com.buaa.model.DataInstance;
import com.buaa.model.DataItem;
import com.buaa.model.EvaluationTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public interface TreeDAOInterface {
	
	public boolean createTree(EvaluationTree tree) throws Exception;
	public boolean getWeight(WebService api, EvaluationTree tree) throws Exception;
	public boolean getValue(DataInstance instance) throws Exception;
}
