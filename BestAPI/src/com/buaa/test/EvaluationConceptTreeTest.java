package com.buaa.test;

import com.buaa.dao.TreeDAOFactory;
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.User;
import com.buaa.model.WebService;

public class EvaluationConceptTreeTest {

	public static void main(String[] args) throws Exception {
		
		EvaluationConceptTree tree = new EvaluationConceptTree();
		tree.setName("BaseTree");
		if(TreeDAOFactory.getTreeDAOInstance().createTree(tree)) {
//			WebService api = new WebService("Tapir", new User("nhjjjb@gmail.com", "nhjjjb", "nhjjjb"));
//			System.out.println(api.getInstance().getResult());
		}
	}
}
