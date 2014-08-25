package com.buaa.test;

import com.buaa.dao.TreeDAOFactory;
import com.buaa.model.EvaluationConceptTree;

public class EvaluationConceptTreeTest {

	public static void main(String[] args) throws Exception {
		
		EvaluationConceptTree tree = new EvaluationConceptTree();
		tree.setName("BaseTree");
		tree.setPath("/home/wuyinan/Repository/BestAPI/BestAPI/data/BaseTree.xml");
		if(TreeDAOFactory.getTreeDAOInstance().addTree(tree)) {
			
		}
	}
}
