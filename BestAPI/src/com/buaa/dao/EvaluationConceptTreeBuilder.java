package com.buaa.dao;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.CategoryNode;
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.FactorNode;

public class EvaluationConceptTreeBuilder {

	private EvaluationConceptTree tree;
	private String path;
	
	public EvaluationConceptTreeBuilder(String path, EvaluationConceptTree tree) {
		this.path = path;
		this.tree = tree;
	}
	
	private void recurse(Element elmt, CategoryNode father) {
		
		String name = elmt.elementText("name");
		double weight = Double.parseDouble(elmt.elementText("weight"));
		Element nodes = elmt.element("Nodes");
		
		if(nodes != null) {
			CategoryNode node = new CategoryNode();
			node.setName(name);
			node.setWeight(weight);
			father.getChild().add(node);
			for(@SuppressWarnings("rawtypes")
			Iterator iter = nodes.elementIterator(); iter.hasNext(); ) {
				recurse((Element)iter.next(), node);
			}
		} else {
			FactorNode node = new FactorNode();
			node.setName(name);
			node.setWeight(weight);
			father.getChild().add(node);
		}
	}
	
	public boolean build() throws DocumentException {
		boolean flag = false;
		File target = new File(path);
		if(target.exists()) {
			SAXReader sr = new SAXReader();
			Document doc = sr.read(target);
			Element root = doc.getRootElement();
			CategoryNode father = new CategoryNode();
			recurse(root, father);
			tree.setRoot(father.getChild().get(0));
		}
		return flag;
	}
	
	
}
