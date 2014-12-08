package com.buaa.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.buaa.model.Domain;

public class RecordDAO {
	
	private final static String dir = "/home/xiaohao/github/BestAPI/BestAPI/data/record";
	private static RecordDAO instance = null;
	
	private RecordDAO() {
		instance = null;
	}
	
	public static RecordDAO getInstance() {
		if (instance == null)
			instance = new RecordDAO();
		return instance;
	}
	
	public HashMap<List<Double>, Boolean> readDataSet(Domain domain, int n) {
		HashMap<List<Double>, Boolean> dataSet = new HashMap<List<Double>, Boolean>();
		File file = new File(dir + "/" + domain.getName() + ".txt");
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSet;
	}
}
