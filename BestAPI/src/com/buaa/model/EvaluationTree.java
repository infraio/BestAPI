package com.buaa.model;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class EvaluationTree {
	
	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	private String name;
	private Node root;
	private List<FactorNode> independentFactors;
	private List<FactorNode> relatedFactors;
	private Domain domain;
	
	public EvaluationTree() {}
	
	public List<FactorNode> getFactors() {
		List<FactorNode> fList = new ArrayList<FactorNode>();
		this.getFactors(this.getRoot(), fList);
		return fList;
	}
	
	public void getFactors(Node node, List<FactorNode> factors) {
		if(node instanceof CategoryNode) {
			for(Node anode : ((CategoryNode)node).getChilds()) 
				getFactors(anode, factors);
		} else if(node instanceof FactorNode) { 
			factors.add((FactorNode)node);
		}
	}
	
	public List<Node> getNodesByLevel(Node root, int level) {
		List<Node> nodes = new ArrayList<Node>();
		if (root.getLevel() == level)
			nodes.add(root);
		else if (root.getLevel() < level && root instanceof CategoryNode) {
			CategoryNode node = (CategoryNode) root;
			for (int i = 0; i < node.getChilds().size(); i++) {
				List<Node> temp = getNodesByLevel(node.getChilds().get(i), level);
				nodes.addAll(temp);
			}
		}
		return nodes;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public List<FactorNode> getIndependentFactors() {
		if (independentFactors == null) {
			List<FactorNode> fList = getFactors();
			independentFactors = new ArrayList<FactorNode>();
			for (FactorNode factor : fList) {
				if (factor.getType() == FactorType.independent) {
					independentFactors.add(factor);
				}
			}
		}
		return independentFactors;
	}

	public void setIndependentFactors(List<FactorNode> independentFactors) {
		this.independentFactors = independentFactors;
	}

	public List<FactorNode> getRelatedFactors() {
		if (relatedFactors == null) {
			List<FactorNode> fList = getFactors();
			relatedFactors = new ArrayList<FactorNode>();
			for (FactorNode factor : fList) {
				if (factor.getType() == FactorType.related) {
					relatedFactors.add(factor);
				}
			}
		}
		return relatedFactors;
	}

	public void setRelatedFactors(List<FactorNode> relatedFactors) {
		this.relatedFactors = relatedFactors;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public void saveToXML() {
		try {
			FileWriter fw = new FileWriter(new File(dir + "/EvaluationTree_" + name + ".xml"));
			fw.write(root.toXML());
			fw.flush();
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString() {
		return this.name + this.root;
	}
	
	public boolean checkConstraintRules() {
		List<FactorNode> factors = new ArrayList<FactorNode>();
		getFactors(root, factors);
		double sumWeight = 0.0;
		for (int i = 0; i < factors.size(); i++)
			sumWeight += factors.get(i).getWeight();
		if (Math.abs(sumWeight - 1.0) < 0.0000001)
			return true;
		else
			return false;
	}
}
