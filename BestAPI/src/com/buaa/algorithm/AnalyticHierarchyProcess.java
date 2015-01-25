package com.buaa.algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AnalyticHierarchyProcess {
	
	private ArrayList<String> principles;
	private double[][] judgeMatrix;
	private double[] weightVec;
	
	public AnalyticHierarchyProcess() {
		this.principles = new ArrayList<String>();
	}
	
	public void addPrinciple(String principle) {
		this.principles.add(principle);
	}
	
	public void genJudgeMatrix() {
		int n = principles.size();
		judgeMatrix = new double[n][n];
		if (n == 1) {
			judgeMatrix[0][0] = 1;
			return;
		}
		for (int i = 0; i < n; i++) {
			judgeMatrix[i][i] = 1;
			for (int j = i + 1; j < n; j++) {
				judgeMatrix[i][j] = getContrast(principles.get(i), principles.get(j));
				judgeMatrix[j][i] = 1 / judgeMatrix[i][j];
			}
		}
		System.out.println("判断矩阵");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(judgeMatrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	public void computeWeightVec() {
		int n = principles.size();
		double[] colSum = new double[n];
		for (int j = 0; j < n; j++) {
			colSum[j] = 0.0;
			for (int i = 0; i < n; i++) {
				colSum[j] += judgeMatrix[i][j];
			}
		}
		weightVec = new double[n];
		for (int i = 0; i < n; i++) {
			weightVec[i] = 0.0;
			for (int j = 0; j < n; j++) {
				weightVec[i] += judgeMatrix[i][j] / colSum[j];
			}
			weightVec[i] /= n;
		}
	}
	
	public double[] getRelativeWeight() {
		int n = weightVec.length;
		double[] rw = new double[weightVec.length];
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += weightVec[i];
		}
		for (int i = 0; i < n; i++) {
			rw[i] = weightVec[i] / sum;
		}
		return rw;
	}
	
	private double getContrast(String a, String b) {
		System.out.println("请判断节点" + a + "和节点" + b + "的重要性对比结果:");
		System.out.println("1 : 两个因素同等重要");
		System.out.println("3 : 一个因素比另一个因素稍微重要");
		System.out.println("5 : 一个因素比另一个因素较强重要");
		System.out.println("7 : 一个因素比另一个因素强烈重要");
		System.out.println("9 : 一个因素比另一个因素绝对重要");
		System.out.println("2, 4, 6, 8 : 两相邻判断的中间值");
		System.out.println("倒数：对比顺序相反");
		double choice = 1.0;
		try {
			choice = str2Double(readUserInput("请输入您的判断："));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return choice;
	}
	
	private double str2Double(String s) {
		if (s.length() == 1) {
			return Double.valueOf(s);
		} else {
			return Double.valueOf(s.substring(0, 1)) / Double.valueOf(s.substring(2));
		}
	}
	
	private static String readUserInput(String prompt) throws IOException {
        System.out.print(prompt);
        InputStreamReader is_reader = new InputStreamReader(System.in);
        return new BufferedReader(is_reader).readLine();
    }
	
	public double[][] getJudgeMatrix() {
		return this.judgeMatrix;
	}
	
	public ArrayList<String> getPrinciples() {
		return this.principles;
	}
}
