package com.buaa.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.buaa.dao.*;
import com.buaa.model.*;
import com.buaa.algorithm.*;

public class WeightDistributionTest {

	private final int Count = 100;
	
	public static void main(String[] args) {
		WeightDistributionTest wdt = new WeightDistributionTest();
		QoSModelDAO qosDao = new QoSModelDAO();
		EvaluationTreeDAO etDao = new EvaluationTreeDAO();
		EvaluationTree eTree = etDao.genByQoSModel(qosDao.getByDomain("Payment"));
		WeightDistribution wd = new WeightDistribution();
//		wd.weightSplitByAHP(eTree);
//		System.out.println(eTree);
		List<FactorNode> fList = new ArrayList<FactorNode>();
		eTree.getFactors(eTree.getRoot(), fList);
		HashMap<List<Double>, Boolean> dataSet = wdt.genDataSet(fList, 10);
//		System.out.println(dataSet.size());
		wd.weightSplitByML(fList, dataSet);
		System.out.println("评价因子\t权重");
		for (int i = 0; i < fList.size(); i++) {
			System.out.println(fList.get(i).getName() + "\t" + fList.get(i).getWeight());
		}
	}
	
	private HashMap<List<Double>, Boolean> genDataSet(List<FactorNode> fList, int m) {
		HashMap<List<Double>, Boolean> dataSet = new HashMap<List<Double>, Boolean>();
		int n = fList.size();
		for (int i = 0; i < 100; i++) {
			HashMap<List<Double>, Boolean> temp = new HashMap<List<Double>, Boolean>();
			for (int j = 0; j < m; j++) {
				List<Double> data = new ArrayList<Double>();
				for (int k = 0; k < n; k++) {
					data.add(Math.random());
				}
				temp.put(data, false);
			}
			chooseBest(fList, temp);
			dataSet.putAll(temp);
		}
		return dataSet;
	}
	
	private void chooseBest(List<FactorNode> fList, HashMap<List<Double>, Boolean> dataSet) {
		List<Double> bestKey = null;
		for (List<Double> key : dataSet.keySet()) {
			if (bestKey == null) {
				bestKey = key;
			} else if ((key.get(3) + key.get(7)) > (bestKey.get(3) + bestKey.get(7))) {
				bestKey = key;
			}
		}
		dataSet.put(bestKey, true);
	}
}
