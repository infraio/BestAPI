package com.buaa.test;
import com.buaa.dao.*;
import com.buaa.model.*;
import com.buaa.algorithm.*;

public class WeightDistributionTest {

	public static void main(String[] args) {
		QoSModelDAO qosDao = new QoSModelDAO();
		EvaluationTreeDAO etDao = new EvaluationTreeDAO();
		EvaluationTree eTree = etDao.genByQoSModel(qosDao.getByDomain("Payment"));
		WeightDistribution wd = new WeightDistribution();
		wd.weightSplitByAHP(eTree);
		System.out.println(eTree);
	}

}
