package com.buaa.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MachineLearning {
	private final int x = 10;
	
	public double[] computeWeight(int n, HashMap<List<Double>, Boolean> dataSet) {
		double[] gain = computeGain(n, dataSet);
		double[] weight = new double[n];
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += gain[i];
		}
		for (int i = 0; i < n; i++) {
			weight[i] = gain[i] / sum;
		}
		return weight;
	}
	private double[] computeGain(int n, HashMap<List<Double>, Boolean> dataSet) {
		double entropy = computeEntropyOfDataSet(dataSet);
		System.out.println("用户选择记录数据集的熵Entropy(S)：" + entropy);
		double[] gain = new double[n];
		for (int i = 0; i < n; i++) {
			double fi = computeEntropyOfFactorI(dataSet, i);
			System.out.println("以第" + i + "个评价因子进行划分时的熵Entropy(f" + i + ")：" + fi);
			gain[i] = entropy - computeEntropyOfFactorI(dataSet, i);
			System.out.println("第" + i + "个评价因子的信息增益Gain(f" + i + ")：" + gain[i]);
//			System.out.println(entropy + "\t" + computeEntropyOfFactorI(dataSet, i) + "\t" + gain[i]);
		}
		return gain;
	}
	
	private double computeEntropyOfFactorI(HashMap<List<Double>, Boolean> dataSet, int i) {
		double entropy = 0.0;
		List<HashMap<List<Double>, Boolean>> dataSetList = splitDataSetByFactorI(dataSet, i);
		for (int j = 0; j < x; j++) {
//			System.out.println(dataSetList.get(j).size() + "\t" + dataSet.size() + "\t" + computeEntropyOfDataSet(dataSetList.get(j)));
			entropy += (dataSetList.get(j).size() * 1.0 / dataSet.size()) * computeEntropyOfDataSet(dataSetList.get(j));
		}
		return entropy;
	}
	
	private List<HashMap<List<Double>, Boolean>> splitDataSetByFactorI(HashMap<List<Double>, Boolean> dataSet, int i) {
		return splitDataSetByFactorI(dataSet, i, 0.0, 1.0);
	}
	
	private List<HashMap<List<Double>, Boolean>> splitDataSetByFactorI(HashMap<List<Double>, Boolean> dataSet, int i, double a, double b) {
		List<HashMap<List<Double>, Boolean>> dataSetList = new ArrayList<HashMap<List<Double>, Boolean>>();
		for (int j = 0; j < x; j++) {
			dataSetList.add(new HashMap<List<Double>, Boolean>());
		}
		for (List<Double> key : dataSet.keySet()) {
			int j = (int) (key.get(i) * x);
			dataSetList.get(j).put(key, dataSet.get(key));
		}
		return dataSetList;
	}
	
	private double computeEntropyOfDataSet(HashMap<List<Double>, Boolean> dataSet) {
		HashMap<List<Double>, Boolean> dataSet1 = new HashMap<List<Double>, Boolean>();
		HashMap<List<Double>, Boolean> dataSet2 = new HashMap<List<Double>, Boolean>();
		for (List<Double> key : dataSet.keySet()) {
			if (dataSet.get(key) == true)
				dataSet1.put(key, dataSet.get(key));
			else
				dataSet2.put(key, dataSet.get(key));
		}
		int n1 = dataSet1.size(), n2 = dataSet2.size();
		if (n1 == 0 && n2 == 0)
			return 0.0;
		else if (n1 == 0) {
			double p2 = n2 * 1.0 / (n1 + n2);
			return -p2 * Math.log(p2);
		} else if (n2 == 0) {
			double p1 = n1 * 1.0 / (n1 + n2);
			return -p1 * Math.log(p1);
		} else {
			double p1 = n1 * 1.0 / (n1 + n2), p2 = n2 * 1.0 / (n1 + n2);
			return -p1 * Math.log(p1) - p2 * Math.log(p2);
		}
	}
}
