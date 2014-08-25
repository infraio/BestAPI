package com.buaa.dao;

import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public interface TreeDAOInterface {

	public boolean buildTree(EvaluationConceptTree tree) throws Exception;
	public boolean getTreeName(User user, WebService api, EvaluationConceptTree tree) throws Exception;
	public boolean addTree(EvaluationConceptTree tree) throws Exception;
}
