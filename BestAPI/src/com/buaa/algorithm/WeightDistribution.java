package com.buaa.algorithm;
import com.buaa.model.*;

public class WeightDistribution {

	private final int K = 4;
	
	public void weightSplitByAHP(EvaluationTree eTree) {
		Node root = eTree.getRoot();
		root.setWeight(1.0);
		root.setRelativeWeight(1.0);
		computeRelativeWeightOfChilds(root);
	}
	
	private void computeRelativeWeightOfChilds(Node root) {
		if (root instanceof CategoryNode) {
			CategoryNode node = (CategoryNode) root;
			AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess();
			for (int i = 0; i < node.getChilds().size(); i++) {
				ahp.addPrinciple(node.getChilds().get(i).getName());
			}
			ahp.genJudgeMatrix();
			ahp.computeWeightVec();
			double[] rw = ahp.getRelativeWeight();
			for (int i = 0; i < node.getChilds().size(); i++) {
				node.getChilds().get(i).setRelativeWeight(rw[i]);
				node.getChilds().get(i).setWeight(node.getWeight() * rw[i]);
				computeRelativeWeightOfChilds(node.getChilds().get(i));
			}
		}
	}

}
