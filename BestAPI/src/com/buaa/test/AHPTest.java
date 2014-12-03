package com.buaa.test;
import com.buaa.algorithm.AnalyticHierarchyProcess;
public class AHPTest {

	public static void main(String[] args) {
		AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess();
		ahp.addPrinciple("静态属性");
		ahp.addPrinciple("动态属性");
		ahp.addPrinciple("领域属性");
		ahp.genJudgeMatrix();
		double[][] judgeMatrix = ahp.getJudgeMatrix();
		for (int i = 0; i < judgeMatrix.length; i++) {
			for (int j = 0; j < judgeMatrix[i].length; j++) {
				System.out.print(judgeMatrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		ahp.computeWeightVec();
		double[] rw = ahp.getRelativeWeight();
		for (int i = 0; i < rw.length; i++) {
			System.out.println(ahp.getPrinciples().get(i) + "的相对权重为" + rw[i]);
		}
	}

}
