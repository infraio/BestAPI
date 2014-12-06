package com.buaa.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.buaa.algorithm.*;

public class ValuePredictionTest {

	private final double density = 0.2;
	private static final String rtFile = "/home/xiaohao/github/BestAPI/BestAPI/data/rtmatrix.txt";
	private static final String resultFile = "/home/xiaohao/github/BestAPI/BestAPI/data/result.txt";
	private static final String mapReduceInFile = "/home/xiaohao/github/BestAPI/BestAPI/data/mapreduce.in";
	private static final int rowNum = 339;
	private static final int colNum = 5825;
	
	public static void main(String[] args) {
		ValuePredictionTest vpt = new ValuePredictionTest();
		double[][] matrix = vpt.getMatrix(rtFile);
		double[][] randomMatrix = vpt.getRandomMatrix(matrix);
		vpt.saveMatrixToFile(randomMatrix, mapReduceInFile);
		//for (int i = 0; i < colNum; i++)
		//	System.out.println(matrix[0][i] + "\t" + randomMatrix[0][i]);
		//ValuePrediction vp = new ValuePrediction(randomMatrix);
		//vp.predictAll();
		//double[][] result = vp.getResult();
		//double[][] result = vpt.getMatrix(resultFile);
		//vpt.computeMaeRmse(matrix, result);
	}
	
	private void saveMatrixToFile(double[][] matrix, String filePath) {
		try {
			FileWriter fw = new FileWriter(new File(filePath));
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					if (matrix[i][j] != 0.0 && matrix[i][j] != -1.0) {
						fw.write(i + " " + j + " " + matrix[i][j] + "\n");
					}
				}
				fw.flush();
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void computeMaeRmse(double[][] matrix, double[][] result) {
		int n = 0;
		double sumMae = 0, sumRmse = 0;
		for(int i = 0; i < rowNum; ++i) {
			for(int j = 0; j < colNum; ++j) {
				if (matrix[i][j] != -1.0) {
					sumMae += Math.abs(matrix[i][j] - result[i][j]);
					sumRmse += Math.pow(matrix[i][j] - result[i][j], 2);
				}
				n++;
			}
		}
		double mae = sumMae / n;
		double rmse = Math.sqrt(sumRmse / n);
		System.out.println(mae + "\t" + rmse);
	}

	private double[][] getMatrix(String filePath) {
		double[][] matrix = new double[rowNum][colNum];
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String s;
			int usernum = 0;
			while((s = br.readLine()) != null) {
				String[] ss = s.split("\t");
				for(int i = 0; i < colNum; ++i) {
					matrix[usernum][i] = Double.parseDouble(ss[i]);
				}
				usernum++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}
	
	private double[][] getRandomMatrix(double[][] matrix) {
		double[][] randomMatrix = new double[rowNum][colNum];
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < colNum; j++)
				randomMatrix[i][j] = matrix[i][j];
		int cnt = 0;
		while(cnt < rowNum * colNum * 0.8) {
			Random r = new Random();
			int i = r.nextInt(rowNum);
			int j = r.nextInt(colNum);
			if(matrix[i][j] != -1.0) {
				randomMatrix[i][j] = 0.0;
				++cnt;
			}
		}
		return randomMatrix;
	}
}
