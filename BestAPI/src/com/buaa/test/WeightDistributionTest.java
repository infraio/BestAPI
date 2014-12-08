package com.buaa.test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.buaa.dao.*;
import com.buaa.model.*;
import com.buaa.system.DomainFactory;
import com.buaa.algorithm.*;

public class WeightDistributionTest {

	private final int Count = 1000;
	private final static String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	
	public static void main(String[] args) {
		WeightDistributionTest wdt = new WeightDistributionTest();
//		QoSModelDAO qosDao = new QoSModelDAO();
//		EvaluationTreeDAO etDao = new EvaluationTreeDAO();
//		EvaluationTree eTree = etDao.genByQoSModel(qosDao.getByDomain("Business"));
//		etDao.createDbForFactors(eTree);
//		List<FactorNode> factors = eTree.getFactors();
//		System.out.println(factors.size());
		for (Domain domain : DomainFactory.getInstance().getAllDomains()) {
			if (domain.getName().equals("Payments"))
				continue;
			try {
				wdt.saveDataSet(wdt.genDataSet(16), domain.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		try {
//			WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).saveWebServicesFromDbToFile(dir + "/apis_test.txt");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		WeightDistribution wd = new WeightDistribution();
//		wd.weightSplitByAHP(eTree);
//		System.out.println(eTree);
//		List<FactorNode> fList = new ArrayList<FactorNode>();
//		eTree.getFactors(eTree.getRoot(), fList);
//		HashMap<List<Double>, Boolean> dataSet = wdt.readDataSet(fList.size());
//		wd.hybridMethod(eTree, dataSet);
//		System.out.println(eTree);
//		System.out.println(eTree.checkConstraintRules());
//		eTree.saveToXML();
//		System.out.println(dataSet.size());
/*		wd.weightSplitByML(fList, dataSet);
		System.out.println("评价因子\t权重");
		for (int i = 0; i < fList.size(); i++) {
			System.out.println(fList.get(i).getName() + "\t" + fList.get(i).getWeight());
		}*/
	}
	
	
	private HashMap<List<Double>, Boolean> readDataSet(int n) {
		HashMap<List<Double>, Boolean> dataSet = new HashMap<List<Double>, Boolean>();
		File file = new File(dir + "/dataSet.txt");
		try {
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				while (line != null) {
					String[] s = line.split("\t");
					List<Double> key = new ArrayList<Double>();
					for (int i = 0; i < n; i++) {
						key.add(Double.valueOf(s[i]));
					}
					if (Integer.valueOf(s[n]) == 1) {
						dataSet.put(key, true);
					} else {
						dataSet.put(key, false);
					}
					line = br.readLine();
				}
			} else {
				dataSet = genDataSet(n);
				//saveDataSet(dataSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSet;
	}
	
	private void saveDataSet(HashMap<List<Double>, Boolean> dataSet, String name) throws IOException{
		File file = new File(dir + "/record/" + name + ".txt");
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (List<Double> key : dataSet.keySet()) {
			for (int i = 0; i < key.size(); i++) {
				bw.write(key.get(i) + "\t");
			}
			if (dataSet.get(key) == true) {
				bw.write("1\n");
			} else {
				bw.write("0\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	private HashMap<List<Double>, Boolean> genDataSet(int n) {
		return genDataSet(n, 10);
	}
	
	private HashMap<List<Double>, Boolean> genDataSet(int n, int m) {
		HashMap<List<Double>, Boolean> dataSet = new HashMap<List<Double>, Boolean>();
		for (int i = 0; i < Count; i++) {
			HashMap<List<Double>, Boolean> temp = new HashMap<List<Double>, Boolean>();
			for (int j = 0; j < m; j++) {
				List<Double> data = new ArrayList<Double>();
				for (int k = 0; k < n; k++) {
					data.add(Math.random());
				}
				temp.put(data, false);
			}
			chooseBest(temp);
			dataSet.putAll(temp);
		}
		return dataSet;
	}
	
	private void chooseBest(HashMap<List<Double>, Boolean> dataSet) {
		List<Double> bestKey = null;
		for (List<Double> key : dataSet.keySet()) {
			if (bestKey == null) {
				bestKey = key;
			} else if (compareTwoKey(key, bestKey)) {
				bestKey = key;
			}
		}
		dataSet.put(bestKey, true);
	}
	
	private boolean compareTwoKey(List<Double> a, List<Double> b) {
		double[] weight = {0.05, 0.05, 0.05, 0.1, 0.05, 0.05, 0.05, 0.1, 0.05, 0.05, 0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
		double ra = 0.0, rb = 0.0;
		for (int i = 0; i < a.size(); i++) {
			ra += weight[i] * a.get(i);
			rb += weight[i] * b.get(i);
		}
		if (ra > rb)
			return true;
		else
			return false;
	}
}
