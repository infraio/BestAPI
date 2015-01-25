package com.buaa.algorithm;
import com.buaa.model.*;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class WeightDistribution {

	private int K = 2;
	private static WeightDistribution instance = null;
	
	private WeightDistribution() {
	}
	
	public static WeightDistribution getInstance() {
		if (instance == null)
			instance = new WeightDistribution();
		return instance;
	}
	
	public void hybridMethod(EvaluationTree eTree, HashMap<List<Double>, Boolean> dataSet) {
		Node root = eTree.getRoot();
		root.setWeight(1.0);
		root.setRelativeWeight(1.0);
		computeRelativeWeightOfChilds(root);
		List<Node> nodes = eTree.getNodesByLevel(root, K);
		int start = 0;
		for (int i = 0; i < nodes.size(); i++) {
			List<FactorNode> fList = new ArrayList<FactorNode>();
			eTree.getFactors(nodes.get(i), fList);
			System.out.println("机器学习：以节点" + nodes.get(i).getName() + "为根的子树");
			weightSplitByML(fList, getSubDataSet(dataSet, start, fList.size()));
			computeWeight(nodes.get(i), fList);
			start += fList.size();
		}
	}
	
	public void hybridMethod(EvaluationTree eTree, HashMap<List<Double>, Boolean> dataSet, int k) {
		this.K = k;
		System.out.println("为领域" + eTree.getDomain().getName() + "的评价树进行权重分配");
		hybridMethod(eTree, dataSet);
	}
	
	private void computeWeight(Node root, List<FactorNode> fList) {
		for (int i = 0; i < fList.size(); i++) {
			System.out.println("评价因子" + fList.get(i).getName() + "在以" + root.getName() + "为根的子树中的权重：" + fList.get(i).getWeight());
			fList.get(i).setWeight(fList.get(i).getWeight() * root.getWeight());
			System.out.println("评价因子" + fList.get(i).getName() + "在以评价树中的权重：" + fList.get(i).getWeight());
		}
		if (root instanceof CategoryNode) {
			CategoryNode node = (CategoryNode) root;
			for (int i = 0; i < node.getChilds().size(); i++) {
				if (node.getChilds().get(i) instanceof CategoryNode) {
					CategoryNode temp = (CategoryNode) node.getChilds().get(i);
					temp.setWeightByChilds();
				}
			}
		}
	}
	
	private HashMap<List<Double>, Boolean> getSubDataSet(HashMap<List<Double>, Boolean> dataSet, int start, int n) {
		HashMap<List<Double>, Boolean> subDataSet = new HashMap<List<Double>, Boolean>();
		for (List<Double> key : dataSet.keySet()) {
			List<Double> subKey = new ArrayList<Double>();
			for (int i = start; i < start + n; i++) {
				subKey.add(key.get(i));
			}
			subDataSet.put(subKey, dataSet.get(key));
		}
		return subDataSet;
	}
	
	public void weightSplitByML(List<FactorNode> fList, HashMap<List<Double>, Boolean> dataSet) {
		int n = fList.size();
		MachineLearning ml = new MachineLearning();
		double[] weight = ml.computeWeight(n, dataSet);
		for (int i = 0; i < n; i++) {
			fList.get(i).setWeight(weight[i]);
		}
	}
	
	public void weightSplitByAHP(EvaluationTree eTree) {
		Node root = eTree.getRoot();
		root.setWeight(1.0);
		root.setRelativeWeight(1.0);
		computeRelativeWeightOfChilds(root);
	}
	
	private void computeRelativeWeightOfChilds(Node root) {
		if (root instanceof CategoryNode && root.getLevel() < K) {
			CategoryNode node = (CategoryNode) root;
			AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess();
			System.out.println("层次分析法求解节点" + node.getName() + "的各个子节点的最近相对权重");
			for (int i = 0; i < node.getChilds().size(); i++) {
				System.out.println("\t" + node.getChilds().get(i).getName());
				ahp.addPrinciple(node.getChilds().get(i).getName());
			}
			ahp.genJudgeMatrix();
			ahp.computeWeightVec();
			double[] rw = ahp.getRelativeWeight();
			for (int i = 0; i < node.getChilds().size(); i++) {
				System.out.println("子节点" + node.getChilds().get(i).getName() + "的最近相对权重：" + rw[i]);
				node.getChilds().get(i).setRelativeWeight(rw[i]);
				node.getChilds().get(i).setWeight(node.getWeight() * rw[i]);
				System.out.println("子节点" + node.getChilds().get(i).getName() + "的权重：" + node.getWeight() * rw[i]);
				computeRelativeWeightOfChilds(node.getChilds().get(i));
			}
		}
	}

}
