package com.buaa.test;

import java.io.File;
import java.io.IOException;

import org.dom4j.DocumentException;

import com.buaa.dao.EvaluationConceptTreeBuilder;
import com.buaa.model.CategoryNode;
import com.buaa.model.EvaluationConceptTree;
import com.buaa.model.FactorNode;
import com.buaa.model.Node;

public class EvaluationConceptTreeTest {

	private void print(Node node, int level) {
		
		if(node instanceof CategoryNode) {
			CategoryNode cnode = (CategoryNode)node;
			for(int i = 0; i < level; ++i)
				System.out.print("\t");
			System.out.println(cnode.getName() + " " + cnode.getWeight());
			for(Node anode : cnode.getChild()) 
				print(anode, level+1);
		} else if(node instanceof FactorNode) { 
			FactorNode fnode = (FactorNode)node;
			for(int i = 0; i < level; ++i)
				System.out.print("\t");
			System.out.println(fnode.getName() + " " + fnode.getWeight());
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/home/wuyinan/Code/y");
//		file.mkdirs();
		file.createNewFile();
		System.out.println(file.isDirectory());
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		EvaluationConceptTree tree = new EvaluationConceptTree();
//		EvaluationConceptTreeTest test = new EvaluationConceptTreeTest();
//		EvaluationConceptTreeBuilder builder = new EvaluationConceptTreeBuilder("/home/wuyinan/Repository/BestAPI/BestAPI/data/BaseTree.xml", tree);
//		try {
//			builder.build();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		test.print(tree.getRoot(), 0);
	}
}
